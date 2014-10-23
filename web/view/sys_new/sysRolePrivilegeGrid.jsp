<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">
<div id="mainLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="角色树" id="accordion1">
        <ul id="sysRoleTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center" style="overflow: auto;" >
        <div id="toptoolbar"></div>
        <ul id="sysPrivillgeTree" style="margin-top:3px;" idFieldName="id" parentIDFieldName="pid" title="授权资源"></ul>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#mainLayout").juiceLayout({ leftWidth: 190  });
        //查询框初始化

        //创建树
        createTree("sysRoleTree",{ url:"${ctx}/sysRole/tree.do",onSelect:function(node){
            if(node.data.id!="root"){
                doAdd(node.data.uid);
            }
        }});

        $("#toptoolbar").juiceToolBar({ items: [
            { text: '保存', icon:'add',click:saveData}
        ]
        });
    });

    function doAdd(roleId) {
        if(!$.jui.get("sysPrivillgeTree")) {
            createTree("sysPrivillgeTree",{ url:"${ctx}/sysRolePrivilege/tree.do?roleId="+roleId,checkbox:true,onSuccess:function(){

            }});
        }else{
            $.jui.get("sysPrivillgeTree").clear();
            $.jui.get("sysPrivillgeTree").set("url","${ctx}/sysRolePrivilege/tree.do?roleId="+roleId);
        }
    }

    function saveData(){
       var selectedNode = $.jui.get("sysRoleTree").getSelected();
       var roleId =  selectedNode.data.uid;
        if(roleId!="root"){
            var checkedNodes = $.jui.get("sysPrivillgeTree").getChecked();
            var ids = "";

            if(checkedNodes != null && checkedNodes.length > 0) {
                for(var i=0; i<checkedNodes.length; i++) {
                    ids += checkedNodes[i].data.id + ",";
                }
            }

            $.ajax({
                type: 'POST',
                url: "${ctx}/sysRolePrivilege/save.do",
                cache: false,
                dataType: 'json',
                data:{ids: ids, roleId: roleId},
                success: function(data) {
                    if (data.success) {
                        $.juiceDialog.success(data.msg,null);
                    } else {
                        $.juiceDialog.success(data.msg);
                    }
                },
                error: function(xmlR, status, e) {
                    $.juiceDialog.error("[" + e + "]" + xmlR.responseText);
                }
            });
        }
    }

</script>