var isIE6 = navigator.userAgent.indexOf("MSIE 6.0")>0;
var isIE7 = navigator.userAgent.indexOf("MSIE 7.0")>0;
var isIE8 = navigator.userAgent.indexOf("MSIE 8.0")>0;

$.jui.controls.Dialog.prototype.close = function(data){
    var g = this, p = this.options;
    $.jui.win.removeTask(this);
    g.unmask();
    g._removeDialog();
    g.trigger("close",data);
    $('body').unbind('keydown.dialog');
    if(g.jiframe&&g.jiframe[0].contentWindow&&g.jiframe[0].contentWindow.document){
        g.jiframe[0].contentWindow.document.write('');
        g.jiframe[0].contentWindow.document.clear();
    }
};

//数据提交
function submitFormData(formId,url,win,gridId,callbackFunc,params){
//    $(document).mask('数据处理中...');
    var sendData = [];
    var grid = $.jui.get(gridId);
    if(win){
        var doc = win.jiframe[0].contentDocument||win.jiframe[0].contentWindow.document;
        if (formId != null && doc) {
            sendData = $("#" + formId,doc).serializeArray();
            url = url|| $("#" + formId,doc)[0].action;
        }
    }
    if(params){
        sendData.push(params);
    }
    $.ajax({
        type: 'POST',
        url: url,
        data: sendData,
        dataType: 'json',
        success: function(data) {
            if (data.success) {
                if(win){
                    win.close();
                }
                if(grid){
                    grid.loadData(true);
                }

                if (callbackFunc != null) {
                    if ($.isFunction(callbackFunc)) {
                        callbackFunc(data);
                    } else {
                        eval(callbackFunc);
                    }
                } else {
                    window.top.$.juiceDialog.success(data.msg);
                }
            } else {
                window.top.$.juiceDialog.error(data.msg);
            }
        },
        error: function(xmlR, status, e) {
//            $(document).unmask();
            window.top.$.juiceDialog.error("[" + e + "]" + xmlR.responseText);
        }
    });
}

//查询
function search(gridId,queryRegion){
//    debugger;

    var inputs = $("#"+queryRegion+" input");

    var params = {};
    var tmpParams = {};
    var length = 0;

    inputs.each(function(index){
        if($(this).attr("type") === "text" || $(this).attr("type") === "hidden"
            || ($(this).attr("type") === "radio" && $(this).attr("checked"))){
            var param = {};
            if($(this).val() && $(this).attr("name") != undefined){
                param["propertyName"] = $(this).attr("name");
                param["operator"] =getOperator($(this).attr("op")||"like");
                param["firstValue"] = $(this).val();
                param["type"] = $(this).attr("dType")||"String";
                param["entity"] = $(this).attr("entity")||"";
                param["isCapital"] = $(this).attr("isCapital")||"";
                params[length.toString()] = param;
                length++;
            }
        } else if($(this).attr("type") === "checkbox" && $(this).attr("checked")) {
            if(tmpParams[$(this).attr("name")]) {
                tmpParams[$(this).attr("name")]["firstValue"] += "," + $(this).val();
            } else {
                var param = {};
                param["firstValue"] = $(this).val();
                param["propertyName"] = $(this).attr("name");
                param["operator"] =getOperator($(this).attr("op")||"in");
                param["type"] = $(this).attr("dType")||"String";
                param["entity"] = $(this).attr("entity")||"";
                param["isCapital"] = $(this).attr("isCapital")||"";

                tmpParams[$(this).attr("name")] = param;
            }
        }
    });

    inputs = $("#"+queryRegion+" select");

    inputs.each(function(index){
        var param = {};
        if($(this).val()){
            param["propertyName"] = $(this).attr("name");
            param["operator"] =getOperator($(this).attr("op")||"in");

            try{
                param["firstValue"] = $(this).val().join(",");
            } catch (error) {
                param["firstValue"] = $(this).val();
            }

            param["type"] = $(this).attr("dType")||"String";
            param["entity"] = $(this).attr("entity")||"";
            param["isCapital"] = $(this).attr("isCapital")||"";

            params[(length).toString()] = param;
            length++;
        }
    });

    for(var key in tmpParams) {
        params[(length++).toString()] = tmpParams[key];
    }
    $.jui.get(gridId).options.parms = [];
    $.jui.get(gridId).options.parms.push({name:"condition",value:$.jui.toJSON(params)});
    $.jui.get(gridId).options.page=$.jui.get(gridId).options.newPage = 1;
    $.jui.get(gridId).loadData(true);

    // 将查询换成easyui datagrid
//    jQuery("#" + gridId).datagrid({queryParams:{condition:toJsonString(params)}, page:1}).trigger('reload');
}

function getOperator(key){
    var ops = {leftLike:"like '|%'",rightLike:"like '%|'",
        like:"like '%|%'",less:"<",great:">",lessAndEq:"<=",greatAndEq:">=",eq:"=",'in':"in"};
    return ops[key]||"";
}

function commonDelete(gridId,deleteUrl,callback) {
    var rows = getGridRows(gridId);
    var id ;
    var deleteCallback = function(){
        if(callback){
            callback();
        }
        var grid = $.jui.get(gridId);
        if(grid){
            grid.loadData(true);
        }
        window.top.$.juiceDialog.success('删除数据成功');
    };

    if(rows.length>0){
        $.juiceDialog.confirm("您确定要删除选中的记录?",function(yes){
            if(yes){
                for(var i=0;i<rows.length;i++){
                    id = rows[i].id;
                    submitFormData(null,deleteUrl+"?id=" + id,null,"",((rows.length-1)==i?deleteCallback:function(){}));
                }
            }
        }) ;
    }else{
        window.top.$.juiceDialog.warn('请选择要删除的记录');
    }
}
function commonAccept(gridId,url,callback){
	var rows = getGridRows(gridId);
    var id ;
    var acceptCallback = function(){
        if(callback){
            callback();
        }
        var grid = $.jui.get(gridId);
        if(grid){
            grid.loadData(true);
        }
        window.top.$.juiceDialog.success('受理成功');
    };

    if(rows.length>0){
        $.juiceDialog.confirm("您确定要受理选中的记录?",function(yes){
            if(yes){
                for(var i=0;i<rows.length;i++){
                    id = rows[i].id;
                    submitFormData(null,url+"?id=" + id,null,"",((rows.length-1)==i?acceptCallback:function(){}));
                }
            }
        }) ;
    }else{
        window.top.$.juiceDialog.warn('请选择要受理的记录');
    }
}
function getGridRows(gridId){
    var rows =  $.jui.get(gridId).getSelecteds();
    return rows;
}

function commonAddOrUpdate(loadUrl,gridId,saveUrl,formId,options, buttonName){
    buttonName = (buttonName != null) ? buttonName : "确定";
    var juiId =  window.top.$.jui.getId();
    if(loadUrl.indexOf("?")>=0){
       loadUrl += "&dialogJuiId=" + juiId;
    } else{
       loadUrl += "?dialogJuiId=" + juiId;
    }
    var settings = {
        height: 600,
        url: loadUrl,
        id:juiId,
        width: 700,
        buttons:[
            { text: buttonName, onclick: function (item, dialog) {
                var g = this;
                var frameWin = dialog.jiframe[0].contentWindow|| dialog.jiframe[0].contentWindow.document;

                if(frameWin&&frameWin.v.form()){
                    var callback = options?options.callback :null;
                    submitFormData(formId,saveUrl,dialog,gridId,callback);
                }
            } },
            { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
        ]
    };
    $.extend(settings,options);
    window.top.$.juiceDialog.open(settings);
}

/**
 * 记录查看方法，查看的URL中包含完全的地址，不进行URL拼接
 *
 * @param loadUrl
 * @param gridId
 * @param options
 */
function commonViewWithId(loadUrl,gridId,options){
    var rows = getGridRows(gridId);

    if(rows.length == 1){
        var settings = {
            title: "查看",
            height: 600,
            url: loadUrl,
            width: 700,
            buttons:[
                { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
            ]
        };
        $.extend(settings,options);
        window.top.$.juiceDialog.open(settings);
    } else {
        window.top.$.juiceDialog.warn('请选择一条要查看的记录!');
    }
}

/**
 * 记录查看方法，查看的URL中不包含完全的地址，后台进行ID的拼接
 *
 * @param loadUrl
 * @param gridId
 * @param options
 */
function commonView(loadUrl,gridId,options){
    var rows = getGridRows(gridId);

    if(rows.length == 1){
        loadUrl = initUrl(loadUrl, gridId);

        if(loadUrl) {
            commonViewWithId(loadUrl, gridId, options);
        }
    } else {
        window.top.$.juiceDialog.warn('请选择一条要查看的记录!');
    }
}

function openView(loadUrl, options) {
    var settings = {
        title: "查看",
        height: 600,
        url: loadUrl,
        width: 700,
        buttons:[
            { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
        ]
    };
    $.extend(settings,options);
    window.top.$.juiceDialog.open(settings);
}

function createTree(treeId,options){
    var tree;
    if(!options.onContextmenu){
        options.initMenu = function(node){
            if(tree.getSelected()&&tree.getSelected().data.id!= node.data.id){
                tree.selectNode(node.data.id) ;
            }
            if(tree.menu){
                //销毁先前menu
                tree.menu.hide();
                tree.menu = null;
                delete tree.menu;
            }
        };
        if(options.createMenu){
            options.onContextmenu = function(node,e){
                options.initMenu(node);
                var menu = options.createMenu(node);
                if(menu){
                    tree.menu = menu;
                    menu.show({ top: e.pageY, left: e.pageX });
                }
                return false;
            }
        }
    }
    tree = $("#"+treeId).juiceTree(options);
}

//创建url
function initUrl(url,gridId,msg){
    var rows = getGridRows(gridId);
    if(rows.length>1){
        window.top.$.juiceDialog.warn('只能选择一条记录!');
        return false;
    }else if(rows.length==1){
        url +="?id=" + rows[rows.length-1].id
    }else{
//        alert(msg||"请选择要操作的记录");
        window.top.$.juiceDialog.warn('请选择要操作的记录!');
        return false;
    }
    return url;
}
//tree 操作
var treeActions = {
    //刷新节点
    refreshNode:function (treeNode, zTree) {
        treeNode = treeNode || zTree.getSelectedNodes()[0];
        zTree.reAsyncChildNodes(treeNode, "refresh");
    },
    //刷新父节点
    refreshParentNode:function (treeNode, zTree) {
        treeNode = treeNode || zTree.getSelectedNodes()[0];
        zTree.reAsyncChildNodes(treeNode.getParentNode(), "refresh");
    },
    moveUp:function (treeNode, zTree, className) {
        if (!isVaildTreeId(treeNode)) return;
        className = className || clazz;
        $.get(CONTEXT_NAME + "/commonPage/moveUp.do?id=" + treeNode.uid + "&clazz=" + className, function () {
            treeActions.refreshParentNode(treeNode, zTree);
        });
    },
    moveDown:function (treeNode, zTree, className) {
        if (!isVaildTreeId(treeNode)) return;
        className = className || clazz;
        $.get(CONTEXT_NAME + "/commonPage/moveDown.do?id=" + treeNode.uid + "&clazz=" + className, function () {
            treeActions.refreshParentNode(treeNode, zTree);
        });
    }
};

function isValid(item,index,value){
    return value=="1"||value=="true"?"是":"否";
}

function booleanFormatter(item,index,value){
    return value=="1"||value=="true"?"是":"否";
}

function quantitativeRender(item,index,value) {
    return value=="0"||value=="false"?"定性":"定量";
}

function cardTypeRender(item,index,value) {
    return value=="0"||value=="false"?"默认卡":"专属卡";
}

function depositRender(item,index,value) {
    return value=="0"||value=="false"?"消费":"存入";
}

function intervalUnitFormatter(item,index,value) {
    var ret = "";
    if(value == 'Y') {
        ret = "年";
    } else if(value == 'M') {
        ret = "月";
    } else if(value == 'D') {
        ret = "天";
    }

    return ret;
}

function genderFormatter(item,index,value){
    return value=="1"||value=="true"?"男":"女";
}

function viewButtonRender(item,index,value){
    var ret = "<a href='javascript:void(0)'onclick='doView("+item.id+")'>查看</a>";

    return ret;
}

function refreshTreeNode(nodeData, tree) {
//    var nodeData = node.data;
    if(nodeData.children){
        $(nodeData.children).each(function (index, data) {
            tree.remove(data);
        });
    }
    tree.loadData(nodeData, tree.options.url, nodeData);
//    }
}

function refreshCurrentNode(tree){
    var node = tree.getSelected();
    if(node){
        refreshTreeNode(node.data, tree);
    }
}

function refreshParentNode(tree){
    var node = tree.getSelected();
    if(node){
        var data = node.data;
        var nodeDom = tree.getNodeDom(data);
        var parentNodeData = tree.getParent(data,parseInt($(nodeDom).attr("outlinelevel"))-1);
        refreshTreeNode(parentNodeData, tree);
    }
}

function refreshRoot(tree){
    var rootNode = tree.getRoot();
    refreshTreeNode(rootNode.data, tree);
}

/**
 * 上移节点
 *
 * @param tree
 */
function moveUp(tree, className) {
    var node = tree.getSelected();

    if(node) {
        $.get(CONTEXT_NAME + "/commonPage/moveUp.do?id=" + node.data.uid + "&clazz=" + className, function () {
            refreshParentNode(tree);
        });
    }
}

/**
 * 下移节点
 *
 * @param tree
 * @param className
 */
function moveDown(tree, className) {
    var node = tree.getSelected();

    if(node) {
        $.get(CONTEXT_NAME + "/commonPage/moveDown.do?id=" + node.data.uid + "&clazz=" + className, function () {
            refreshParentNode(tree);
        });
    }
}

/**
 * 表单存储
 * @param url
 * @param formId
 * @param callbackFunc
 */
function saveAjaxData(url, formId, window, gridId, callbackFunc) {
    var sendData = "";

    if (formId != null && $('#' + formId).length > 0) {
        sendData = $("#" + formId).serializeArray();
    }

    $.ajax({
        type: 'POST',
        url: url,
        data: sendData,
        dataType: 'json',
        success: function(data) {
            if (callbackFunc != null) {
                if ($.isFunction(callbackFunc)) {
                    callbackFunc(data);
                } else {
                    eval(callbackFunc);
                }
            } else {
                if (data.success) {
                    if(window != null) {
                        window.close();
                    }

                    refreshGrid(gridId);
                    showInfoMsg(data.msg,"info");
                } else {
                    showInfoMsg(data.msg, "error");
                }
            }
        },
        error: function(xmlR, status, e) {
            showInfoMsg("[" + e + "]" + xmlR.responseText, "error");
        }
    });
}

//刷新grid
function refreshGrid(gridId) {
    if (gridId == null) {
        return;
    }

    var grid = $.jui.get(gridId);

    if(grid != null) {
        grid.loadData(true);
    }
}

function showInfoMsg(msgString, msgType) {
    var title = "系统提示";
    if(msgType == null || "" == msgType) msgType = "info";

    if(msgType == "info" || msgType == "warning"){
        title = "系统提示";
    }else if(msgType == "error"){
        title = "出错了";
    }

    var settings = {
        title: title,
        type: msgType,
        content: msgString,
        buttons:[
            { text: '关闭', onclick: function (item, dialog) { dialog.close(); } }
        ]
    };

    window.top.$.juiceDialog.open(settings);
}

/**
 * 加载XML
 *
 * @param xmlString
 * @returns {null}
 */
function loadXML(xmlString){
    var xmlDoc=null;
    if(!window.DOMParser && window.ActiveXObject){
        var xmlDomVersions = ['MSXML.2.DOMDocument.6.0','MSXML.2.DOMDocument.3.0','Microsoft.XMLDOM'];
        for(var i=0;i<xmlDomVersions.length;i++){
            try{
                xmlDoc = new ActiveXObject(xmlDomVersions[i]);
                xmlDoc.async = false;
                xmlDoc.loadXML(xmlString);
                break;
            }catch(e){
            }
        }
    }

    else if(window.DOMParser && document.implementation && document.implementation.createDocument){
        try{

            domParser = new   DOMParser();
            xmlDoc = domParser.parseFromString(xmlString, 'text/xml');
        }catch(e){
        }
    }
    else{
        return null;
    }

    return xmlDoc;
}

/**
 * 解析XML
 *
 * @param xmlString
 * @returns {null}
 */
function parseXML(xmlString) {
    var xmlDoc = loadXML(xmlString);
    if(xmlDoc==null){
        alert('您的浏览器不支持xml文件读取,于是本页面禁止您的操作,推荐使用IE5.0以上可以解决此问题!');

    }
    return xmlDoc;
}

/**
 * 取得节点
 *
 * @param nodeData
 * @returns {string}
 */
function getNode(nodeData) {
    var node = nodeData.childNodes;
    var str = '<ul >';
    for (var i = 0; i < node.length; i++) {
        if (node[i].hasChildNodes()) {
            str += "<li><a>" + node[i].nodeName + "</a>";
            str += getNode(node[i]);
            str += "</li>";
        } else {
            str += "<li><a>" + $.trim(node[i].nodeValue) + "</a></li>";
        }
    }
    str += "</ul>";
    return str;
}

function onOffFormatter(item,index,value){
    if(value=="1"){
        return "开";
    }else if(value=="0"){
        return "关";
    }
}

/**
 * 改变是否限量发售时触发的函数
 * @param obj
 */
function changeIsLimited(obj) {
    var val = obj.value + '';

    if(val == 'true') {
        $("#sharesMin").removeAttr('disabled');
        $("#sharesMin").removeAttr('readonly');
        $("#shares").removeAttr('disabled');
        $("#shares").removeAttr('readonly');
    } else {
        $("#sharesMin").attr('disabled', 'true');
        $("#sharesMin").attr('value', '');
        $("#shares").attr('disabled', 'true');
        $("#shares").attr('value', '');
    }
}

function dateDiff(startDateStr,endDateStr){
    return parseInt(new Date(endDateStr).getTime()-new Date(startDateStr).getTime())/(3600*24*1000);
}
