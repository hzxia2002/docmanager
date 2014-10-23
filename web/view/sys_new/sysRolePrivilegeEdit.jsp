<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<%--<html xmlns="http://www.w3.org/1999/xhtml">--%>
<%--<head>--%>
    <%--<%@ include file="../common/header_new.jsp" %>--%>
<%--</head>--%>
<body>
<form:form id="sysRolePrivilegeEditForm" name="sysRolePrivilegeEditForm" action="${ctx}/sysRolePrivilege/save.do" method="post">
    <input type="hidden" name="ids" id="ids">
    <input type="hidden" name="roleId" id="roleId" value="${roleId}">
    <div id="tools" style="vertical-align: top">
        <input type="button" id="btnSave" class="btn_Ok" value="保存">
    </div>
    <div id="p"  style="width:500px;height:450px;" tools="#tools" >
        <div id="sysRolePrivilegeTree" title="请选择授权资源"></div>
    </div>
</form:form>
</body>


<script type="text/javascript">
    $(function(){

        createTree("sysRolePrivilegeTree",{ url:"${ctx}/sysRolePrivilege/tree.do?roleId=${roleId}",checkbox:true});

        $("#btnSave").click(function(){
            var roleId = $("#roleId").val();

            if(roleId == null || roleId == "") {
                ("请先选择角色再进行授权!");
                return;
            }

            var checkNodes = tree.getCheckedNodes();
            var ids = "";

            if(checkNodes != null && checkNodes.length > 0) {
                for(i=0; i<checkNodes.length; i++) {
                    ids += checkNodes[i].id + ",";
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
                        showInfoMsg(data.msg,null);
                    } else {
                        showErrorMsg(data.msg);
                    }
                },
                error: function(xmlR, status, e) {
                    showErrorMsg("[" + e + "]" + xmlR.responseText);
                }
            });
        });




    });
</script>