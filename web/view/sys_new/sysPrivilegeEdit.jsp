<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="sysPrivilegeEditForm" name="sysPrivilegeEditForm" action="${ctx}/sysPrivilege/save.do" method="post">
    <input type="hidden" id="id" name="id" value="${bean.id}" />
    <input type="hidden" id="parent" name="parent" value="${bean.parent.id}" />
    <div>
        <table border="0" cellspacing="1" width="100%" id="viewTable" class="inputTable">
            <tr class="inputTr">
                <td  align="right">
                    父节点:
                </td>
                <td  class="container">
                    <input type="text" name="parent_name" value="${bean.parent.name}" class="table_input" disabled="true"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    <span class="needInfo">*</span>权限编码:
                </td>
                <td  class="container">
                    <input type="text" name="code" value="${bean.code}" validate="{required:true}" maxlength="100" class="table_input"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    <span class="needInfo">*</span>权限名称:
                </td>
                <td  class="container">
                    <input type="text" name="name" value="${bean.name}" validate="{required:true}" maxlength="80" class="table_input"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    权限类型:
                </td>
                <td  class="container">
                    <input type="text"
                           url="${ctx}/sysCode/getCodeDetailList.do?code=PRIVILEGE_TYPE&isNeed=true"
                           textField="name"
                           valueField="id"
                           valueFieldID="type"
                           class= "jui-comboBox"
                           initText="${bean.type.name}"
                           initValue="${bean.type.id}"/>
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    TAG:
                </td>
                <td  class="container">
                    <input type="text" name="tag" value="${bean.tag}" class="table_input"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    页面地址:
                </td>
                <td  class="container">
                    <input type="text" name="url" value="${bean.url}" size="150" maxlength="200" class="table_input"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    定义:
                </td>
                <td  class="container">
                    <textarea rows="4" cols="60" class="textarea_table" name="definition" id="definition">${bean.definition}</textarea>
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
            <%--<tr class="inputTr">--%>
            <%--<td  align="right">--%>
            <%--是否叶节点:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--${bean.isLeaf}--%>
            <%--<input type="text" name="isLeaf" value="${bean.isLeaf}" />--%>
            <%--</td>--%>
            <%--</tr>--%>
            <%--<tr class="inputTr">--%>
            <%--<td  align="right">--%>
            <%--树形层次:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text" name="treeId" value="${bean.treeId}" />&nbsp;--%>
            <%--</td>--%>
            <%--</tr>--%>
         
        </table>

    </div>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#sysPrivilegeEditForm').validate();

        $.jui.parse();
    });
</script>