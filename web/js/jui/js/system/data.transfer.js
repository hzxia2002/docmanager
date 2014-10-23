$.juiceDefaults.Grid.url = serviceUrl = "../../EiService";

var eiContant = {
    resultBlock:"result",
    queryBlock:"inqu_status"
};

$.juiceMethos.Grid.dataTransform = function(data){
    this.eiInfo = Json2EiInfo.prototype.parseJsonObject(data);
    var block = getBlock(data);
    var rows = getRows(block);
    var count = rows.length;
    if(block.attr){
        count = block.attr.count||count;
    }
    return {Rows:getRows(block),Total:count};
};

$.juiceMethos.Grid.initParams = function(p){
    var params = p.parms;
    var urlParams = p.urlParams||{};
    var hasEiInfo = false;
    var eiInfo;
    for(var key in urlParams){
        if(key=="eiinfo"){
            hasEiInfo = true;
        }else{
            params.push({name:key,value:urlParams[key]});
        }
    }
    if(hasEiInfo){
        eiInfo = urlParams[key];
    }else{
        eiInfo = new EiInfo();
    }
    var eiBlock = new EiBlock(eiContant.resultBlock);
    eiBlock.set("showCount",true);
    eiBlock.set("limit",p.pageSize);
    eiBlock.set("offset",(p.newPage-1)*p.pageSize);
    eiInfo.addBlock(eiBlock);
    for(var i=0;i<params.length;i++){
        if(params[i].name=="eiinfo"){
            params[i].value = EiInfo2Json.prototype.EiInfo2JsonString(eiInfo);
            return;
        }
    }
    params.push({name:"eiinfo",value:EiInfo2Json.prototype.EiInfo2JsonString(eiInfo)});

};
/**
 * form 表单初始化数据转换
 * @param params
 * @param datas
 */
$.juiceMethos.Form.initParams = function(params,datas){
    var eiInfo = new EiInfo();
    if(params.eiinfo){
        if(typeof params.eiinfo=="string"){
            eiInfo =  Json2EiInfo.prototype.parseJsonObject(params.eiinfo);
        }else if(typeof params.eiinfo=="object"){
            eiInfo =  params.eiinfo;
        }
    }
    for(var i=0;i<datas.length;i++) {
        eiInfo.setByNameValue((eiContant.resultBlock + "-0-" + datas[i].name),datas[i].value);
    }
    params["eiinfo"] = EiInfo2Json.prototype.EiInfo2JsonString(eiInfo);
};

$.juiceMethos.ComboBox = $.juiceMethos.ComboBox || {};

$.juiceMethos.ComboBox.dataTransform =   function(data){
    return getRows(getBlock(data));
};

$.juiceDefaults.Tree.url = serviceUrl;
$.juiceMethos.Tree.dataTransform =   function(data){
    return getRows(getBlock(data));
};

$.juiceDefaults.Form.url = serviceUrl;
$.juiceMethos.Form.dataTransform =   function(data){
    var rows = getRows(getBlock(data));
    if(rows.length>0){
        return rows[0];
    }
    return {};
};

$.juiceMethos.Tree.initParams = function(p,node){
    var params = p.param;
    var eiInfo;

    if(params["eiinfo"]){
        eiInfo = Json2EiInfo.prototype.parseJsonObject(params["eiinfo"]);
    }else{
        eiInfo = new EiInfo();
    }
    var eiBlock = new EiBlock(eiContant.resultBlock);
    eiInfo.addBlock(eiBlock);
    if(node){
        for(var key in node){
            eiInfo.setByNameValue((eiContant.resultBlock + "-0-" + key),node[key]);
        }
    }
    //去掉分页限制
    eiBlock.set("limit",-1);
    params["eiinfo"] = EiInfo2Json.prototype.EiInfo2JsonString(eiInfo);
};

function getBlock(data){
    var key;
    if(!data||!data.blocks){
        return null;
    }else{
        for(key in data.blocks){
            break;
        }
    }
    return (data.blocks&&data.blocks.result||data.blocks[key])||{};
};

/**
 * 取block 中的数据集并转成json数组
 * @param block
 */
function getRows(block){
    var rows = [];
    if(block){
        var datas = block.rows;
        var columns = block.meta.columns;

        for(var i=0;i<datas.length;i++){
            var rowData = {};
            for(var j=0;j<columns.length;j++){
                if(datas[i][j]){
                    rowData[columns[j].name] = (datas[i][j]);
                }else{
                    rowData[columns[j].name] = "";
                }
            }
            rows.push(rowData);
        }
    }
    return rows;
}
/**
 * grid数据增删改操作
 * @param params
 */
function opGridData(params){
    if(params.beforeOP){
        params.beforeOP.call(this);
    }
    var type = params.type;
    var gridId = params.gridId;
    var grid = $.jui.get(gridId);
    var datas ;
    if(type=="add"){
        datas = grid.getAdded();
    }else if(type=="update"){
        datas = grid.getUpdated()
    }else if(type=="delete"){
        datas = grid.getDeleted();
    }
    if(datas.length>0){
        var eiInfo = new EiInfo();
        var gridBlock = grid.eiInfo.getBlock(eiContant.resultBlock);
        var block = new EiBlock(gridBlock.getBlockMeta());
        //分页代码参数
        block.set("showCount",gridBlock.get("showCount")||true);
        block.set("limit",gridBlock.get("limit")||10);
        block.set("offset",0);
        eiInfo.addBlock(block);
        for(var i=0;i<datas.length;i++){

            for(var key in datas[i]){
                if(grid.getColumnByName(key)!=null){
                    block.setCell(i,key,datas[i][key]);
                }
            }
        }
        for(var key in params.params){
            eiInfo.setByNameValue((eiContant.queryBlock + "-0-" + key),params.params[key]);
        }
        params["eiinfo"] = EiInfo2Json.prototype.EiInfo2JsonString(eiInfo);
    }else{
        return;
    }
    submitGridData(params);
}
/**
 * grid 数据查询，
 * @param params 包含查询表单的id，grid的id
 */
function query(params){
    var settings = {
        formId:"",
        beforeOp:null,
        afterOP:null,
        gridId:"",
        method:"",
        service:"",
        packageName:"",
        params:{},
        showCountName:"showCount"
    };
    $.extend(settings,params);
    var grid = $.jui.get(params.gridId);

    var eiInfo = new EiInfo() ;
    var gridBlock = grid.eiInfo?grid.eiInfo.getBlock(eiContant.resultBlock):new EiBlock(eiContant.resultBlock);
    var block = new EiBlock(eiContant.resultBlock);
    //分页代码参数
    block.set("showCount",gridBlock.get("showCount")||true);
    block.set("limit",gridBlock.get("limit")||10);
    block.set("offset",0);
    eiInfo.addBlock(block);
    if(settings.formId){
        var datas = $("#"+settings.formId).serializeArray();
        for(var i=0;i<datas.length;i++) {
            eiInfo.setByNameValue((eiContant.queryBlock + "-0-" + datas[i].name),datas[i].value);
        }
    }
    for(var key in settings.params){
        eiInfo.setByNameValue((eiContant.queryBlock + "-0-" + key),settings.params[key]);
    }
    settings["eiinfo"] = EiInfo2Json.prototype.EiInfo2JsonString(eiInfo);

    //grid 参数设置
    grid.options.urlParams = grid.options.urlParams||{};
    grid.options.newPage = 1;
    var gridUrlParams = grid.options.urlParams;
    gridUrlParams["eiinfo"] = eiInfo;

    submitGridData(settings);
}
/**
 * 可编辑 grid 数据提交
 * @param params
 */
function submitGridData(params){
    var grid = $.jui.get(params.gridId);
    grid.loadColumnData();
    var ajaxOptions = {
        type: "post",
        url: serviceUrl,
        data: params,
        dataType: 'json',
        success: function (data)
        {
            grid.eiInfo = Json2EiInfo.prototype.parseJsonObject(data);
            grid.load($.juiceMethos.Grid.dataTransform(data));
            if(params.afterOP){
                params.afterOP.call(this);
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown)
        {
            alert("操作失败");
        }
    };
    $.ajax(ajaxOptions);
}

function initLoad(options){
    var settings = {
        method:"initLoad",
        service:"",
        eiinfo:"{}",
        packageName:""
    };
    $.extend(settings,options);
    var ajaxOptions = {
        type: "post",
        url: serviceUrl,
        data: settings,
        dataType: 'json',
        success: function (data)
        {
            alert($.jui.toJSON(data));
        }
    };
    $.ajax(ajaxOptions);
}



