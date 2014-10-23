<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx}/js/jui/skins/Aqua/css/jui-all.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/skin/default.css" rel="stylesheet" type="text/css" />
<script src="${ctx}/js/jquery/jquery-1.7.2.js" type="text/javascript"></script>
<script src="${ctx}/js/jui/js/jui-all.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery-validation/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery-validation/messages_cn.js" type="text/javascript"></script>
<script src="${ctx}/js/application/common.js" type="text/javascript"></script>
<script type="text/javascript">
    var CONTEXT_NAME = "${ctx}";
    if(window.self != window.top){
        $(document).bind("click",function(){
            if(window.top.$.jui.get("topmenu")&&window.top.$.jui.get("topmenu").actionMenu){
                window.top.$.jui.get("topmenu").actionMenu.hide();
            }
            window.top.$("#userMenuBody").hide();
        });
    }
</script>