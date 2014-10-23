<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="cmsCategoryEditForm" modelAttribute="bean" name="cmsCategoryEditForm" action="${ctx}/cmsCategory/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <input type="hidden" name="parent" value="${bean.parent.id}" />
    <div>
        <table border="0" cellspacing="1" width="100%" class="inputTable">
            <%--<tr class="inputTr">--%>
            <%--<td  align="right">--%>
            <%--父节点:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text" name="parent" value="${bean.parent}" />&nbsp;--%>
            <%--</td>--%>
            <%--</tr>--%>
            <tr class="inputTr">
                <td  align="right">
                    <span class="needInfo">*</span>编码:
                </td>
                <td  class="container">
                    <input type="text" name="code" value="${bean.code}" validate="{required:true}" class="table_input" maxlength="50"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    <span class="needInfo">*</span>名称:
                </td>
                <td  class="container">
                    <input type="text" name="name" value="${bean.name}" validate="{required:true}" class="table_input" maxlength="100"/>&nbsp;
                </td>
            </tr>
            <%--<tr class="inputTr">--%>
            <%--<td  align="right">--%>
            <%--叶节点:--%>
            <%--</td>--%>
            <%--<td  class="container">--%>
            <%--<input type="text" name="isLeaf" value="${bean.isLeaf}" />&nbsp;--%>
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

            <tr class="inputTr">
                <td  align="right">
                    是否有效:
                </td>
                <td  class="container">
                    <form:checkbox path="isValid"/>
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    备注:
                </td>
                <td  class="container">
                    <textarea rows="4" cols="60" class="textarea_table" name="description" id="description">${bean.description}</textarea>
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
        v = $('#cmsCategoryEditForm').validate();
    });
</script>