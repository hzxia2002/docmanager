/**
 * User: tcg
 * Date: 14-7-20
 * Time: 下午5:13
 */

(function ($)
{
    $.jui.controls.Dialog.prototype.close = function(data){
        var g = this, p = this.options;
        $.jui.win.removeTask(this);
        g.unmask();
        g._removeDialog();
        g.trigger("close",data);
        $('body').unbind('keydown.dialog');
    };

    FileUploadManager = {
        container:{},
        setFileUpload:function(id,obj){
            this.container[id] = obj;
        },
        getFileUpload:function(id){
            return  this.container[id];
        }
    };
    $.fn.FileUpload = function (options){
        var contextPath = CONTEXT_NAME||"";
        this.options = {
            contextPath:contextPath,
            uploadImg:"",
            loadDataUrl:contextPath+"/fileUpload/loadData.do",
            downloadUrl:contextPath+"/fileUpload/downloadAttachment.do",
            uploadInitUrl:contextPath+"/fileUpload/fileUploadInit.do"
        };
        $.extend(this.options,options);

        return new FileUploadConstruct().init(this);
    };

    var FileUploadConstruct = function(){};
    FileUploadConstruct.prototype = {
        init:function($obj){
            this.obj = $obj;
            var p = this.options = $obj.options;
            var g = this;
            g.id = g.getId();

            g.createHtml();
            g.bindEvent();
            FileUploadManager.setFileUpload(g.id,this);
            this.obj.attr("id",g.getId());
            g.setDocId(this.obj.attr("documentId"));
            g.loadData(g.getDocId());
            return this;
        },
        getId:function(){
            var elementId = this.obj.attr("id");
            return "FileUpload_"+ elementId||new Date().getTime();
        },
        createHtml:function(){
            var p = this.options;
            var g = this;
            var htmlArr = [];
            htmlArr.push("<div >");
            if(g.obj.attr("type")!="view"){
                var notNeedBiz = (g.getAttr("notNeedBiz")=="true");
                htmlArr.push('<img src="'+ p.contextPath+'/skin/icons/add.gif" style="cursor: pointer" class="uploadInit"  title="添加附件" onClick="upload(\''+g.id+'\','+notNeedBiz+')"/>');
            }
            htmlArr.push('<input type="hidden"  class="docIdCls" value="" />');
            htmlArr.push('<ul class="files">');
            htmlArr.push('</ul>');
            htmlArr.push("</div>");
            g.obj.html(htmlArr.join(""));
            htmlArr = null;
            delete htmlArr;
        },
        reload:function(){
            var g = this;
            g.loadData(g.getDocId());
        },
        loadData:function(docId){
            var p = this.options;
            var g = this;
            $.ajax({
                url: p.loadDataUrl+"?docId="+(docId||""),
                type:"post",
                success:function(data){
                    var htmlArr = [];
                    for(var i=0;i<data.length;i++){
                        htmlArr.push('<li style="float: left;padding-right: 20px;">');
                        htmlArr.push('    <a href="'+(p.downloadUrl+"?attachmentId="+data[i].attachmentId)+'" target="_blank">'+data[i].original+'</a>');
                        if(g.obj.attr("type")!="view"){
                            htmlArr.push('    <img src="'+ p.contextPath+'/skin/icons/tc.png" style="cursor: pointer" onClick="deleteAttachMent('+data[i].attachmentId+',\''+g.id+'\')">');
                        }
                        htmlArr.push('</li>');
                    }
                    g.obj.find(".files").html(htmlArr.join(""));
                    htmlArr = null;
                    delete htmlArr;
                }
            });
        },
        setDocId:function(docId){
            var g = this;
            var bindId = g.getAttr("bindId");
            if(bindId){
                $("#"+bindId).val(docId);
            }
            g.obj.find(".docIdCls").val(docId||"");
        },
        getDocId:function(){
            var g = this;
            return g.obj.find(".docIdCls").val()||"";
        },
        getBizId:function(){
            var g = this;
            return g.obj.attr("bizId")||"";
        },
        setBizId:function(bizId){
            var g = this;
            return g.obj.attr("bizId",bizId||"");
        },
        getDomain:function(){
            var g = this;
            return g.obj.attr("domain")||"";
        },
        setDomain:function(domain){
            var g = this;
            g.obj.attr("domain",domain||"");
        },
        getAttr:function(attr){
            var g = this;
            return g.obj.attr(attr)||"";
        },
        setAttr:function(attr,value){
            var g = this;
            g.obj.attr(attr,value||"");
        },
        destroy:function(){
            delete this;
        },
        bindEvent:function(){
            var p = this.options;

            var g = this;
            $(window).unload(function(){
                g.destroy();
            });

        }
    };



})(jQuery);

function deleteAttachMent(id,comId){
    $.juiceDialog.confirm("您确定要删除选中的记录?",function(yes){
        if(yes){
            submitFormData(null,(CONTEXT_NAME||"")+"/fileUpload/deleteAttachment.do?attachmentId=" + id,null,"",function(){
                FileUploadManager.getFileUpload(comId).reload();
            });

        }
    }) ;
}

function upload(id,notNeedBiz){
    var uploader = FileUploadManager.getFileUpload(id);
    var obj = $("#"+id);
    if(!uploader.getBizId()&&!notNeedBiz){
        window.top.$.juiceDialog.warn('请先保存主数据，再添加附件!');
        return;
    }
    if(!uploader.getDomain()){
        window.top.$.juiceDialog.warn('缺少Domain，请联系开发人员检查配置!');
        return;
    }
    var juiId =  window.top.$.jui.getId();
    var url = (CONTEXT_NAME||"")+"/fileUpload/fileUploadInit.do"+"?bizId="+ uploader.getBizId()+"&juiId="+juiId+"&docId="+ uploader.getDocId()+"&domain="+uploader.getDomain()+"&methodName="+obj.attr("methodName");
    var settings = {
        height: 300,
        url: url,
        id: juiId,
        title:"附件上传",
        close:true,
        width: 700,
        onClose:function(docId){
            if(docId){
                uploader.setDocId(docId);
                uploader.loadData(docId);
            }
            this.jiframe[0].contentWindow.document.write('');
            this.jiframe[0].contentWindow.document.document.clear();
        },
        buttons:[
            { text: "上传", onclick: function (item, dialog) {
                var frameWin = dialog.jiframe[0].contentWindow|| dialog.jiframe[0].contentWindow.document;
                frameWin.document.forms[0].submit()
            } },
            { text: '取消', onclick: function (item, dialog) { dialog.close(); } }
        ]
    };
    $.juiceDialog.open(settings);
}