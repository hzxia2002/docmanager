<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="sysRoleEditForm" modelAttribute="bean" name="sysRoleEditForm" action="${ctx}/sysRole/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}"  />
    <div>
        <table border="0" cellspacing="1" width="100%" class="inputTable">

            <tr class="inputTr">
                <td  align="right">
                    <span class="needInfo">*</span>角色编码:
                </td>
                <td  class="container">
                    <input type="text" name="code" value="${bean.code}" validate="{required:true}" class="table_input"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    <span class="needInfo">*</span>角色名称:
                </td>
                <td  class="container">
                    <input type="text" name="roleName" value="${bean.roleName}" validate="{required:true}" class="table_input"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    描述:
                </td>
                <td  class="container">
                    <textarea rows="4" cols="60" class="textarea_table" name="description" id="description">${bean.description}</textarea>
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    创建时间:
                </td>
                <td  class="container">
                    ${bean.createTime}
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    更新时间:
                </td>
                <td  class="container">
                    ${bean.updateTime}
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    创建人:
                </td>
                <td  class="container">
                    ${bean.createUser}
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    更新人:
                </td>
                <td  class="container">
                    ${bean.updateUser}
                </td>
            </tr>
           
        </table>

    </div>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#sysRoleEditForm').validate();
    });
</script>