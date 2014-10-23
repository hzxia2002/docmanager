<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="sysMenuEditForm" modelAttribute="bean" name="sysMenuEditForm" action="${ctx}/sysMenu/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <div>
        <table border="0" cellspacing="1" width="100%" class="inputTable">
            <tr class="inputTr">
                <td  align="right">
                    <span class="needInfo">*</span>名称:
                </td>
                <td  class="container">
                    <input type="text" name="name" value="${bean.name}" size="60" class="table_input" validate="{required:true}"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    权限编码:
                </td>
                <td  class="container">
                    <input type="text" id="privilege" name="privilege" value="${bean.privilege}" size="60" class="table_input"/><b>说明：</b>权限代码为空时，不进行授权
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    层次:
                </td>
                <td  class="container">
                    <input type="text" name="menuLevel" class="table_input" value="${bean.menuLevel}" />&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    链接地址:
                </td>
                <td  class="container">
                    <input type="text" name="url" value="${bean.url}" size="150" style="width: 400px;" class="table_input"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    事件:
                </td>
                <td  class="container">
                    <textarea rows="5" cols="60" class="textarea_table" name="jsEvent" id="jsEvent">${bean.jsEvent}</textarea>
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    父节点:
                </td>
                <td  class="container">
                    <input type="hidden" name="parent" value="${bean.parent.id}" />
                    <input type="text" name="parent_name" value="${bean.parent.name}" readonly="true" size="60" class="table_input"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    叶节点:
                </td>
                <td  class="container">
                    <form:checkbox path="isLeaf" disabled="true"/>
                </td>
            </tr>
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
                    参数:
                </td>
                <td  class="container">
                    <input type="text" name="param" value="${bean.param}" size="60" class="table_input"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    显示图标:
                </td>
                <td  class="container">
                    <input type="text" name="icon" value="${bean.icon}" size="60" class="table_input"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    <span class="needInfo">*</span>目标窗口:
                </td>
                <td class="container">
                    <input type="text"
                           url="${ctx}/sysCode/getCodeDetailList.do?code=TARGET_TYPE"
                           textField="name"
                           valueField="code"
                           valueFieldID="target"
                           class= "jui-comboBox"
                           initText = "${bean.target}"
                           initValue="${bean.target}"/>

                        <%--<input class="easyui-combobox"--%>
                        <%--name="target"--%>
                        <%--url="${ctx}/sysCode/getCodeDetailList.do?code=TARGET_TYPE"--%>
                        <%--valueField="code"--%>
                        <%--textField="name"--%>
                        <%--panelHeight="auto"--%>
                        <%--value="${bean.target}">--%>
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
        v = $('#sysMenuEditForm').validate();

        $.jui.parse();
    });
</script>