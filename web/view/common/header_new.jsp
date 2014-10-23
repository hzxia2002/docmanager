<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx}/js/jui/skins/Aqua/css/jui-all.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skin/default.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js/jquery/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${ctx}/js/jui/js/jui-all.js" type="text/javascript"></script>
<script src="${ctx}/js/jui/js/format.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery-validation/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script src="${ctx}/js/application/common.js" type="text/javascript"></script>
<script src="${ctx}/js/application/validate.plugin.js" type="text/javascript"></script>
<script src="${ctx}/js/application/fileUpload.js" type="text/javascript"></script>
<script src="${ctx}/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript">
    var CONTEXT_NAME = "${ctx}";
    if(window.self != window.top){
        $(document).bind("click",function(){
            if(window.top.$.jui.get("topmenu")&&window.top.$.jui.get("topmenu").actionMenu){
                window.top.$.jui.get("topmenu").actionMenu.hide();
            }
        });

    }

    $.juiceDefaults.Grid.showAddButton = false;
    $.jui.core.UIComponent.prototype. _copyProperty = function(p,element){
        for(var key  in  p){
            if(element.attr(key)){
                if(key.indexOf("on")==0){
                    p[key] = eval(element.attr(key));
                }else if(typeof p[key]=="boolean"){
                    p[key] = eval(element.attr(key));
                }else{
                    if(element.attr(key).indexOf(";")>=0){
                        p[key] = element.attr(key);
                    }else{
                        p[key] = parseFloat(element.attr(key))?parseFloat(element.attr(key)):element.attr(key) ;
                    }
                }
            }
        }
    }
</script>