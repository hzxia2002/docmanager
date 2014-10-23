<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="sysMessageInfoEditForm" modelAttribute="bean" name="sysMessageInfoEditForm" action="${ctx}/sysMessageInfo/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <div>
        <table border="0" cellspacing="1" width="100%" class="inputTable">
            <tr class="inputTr">
                <td align="right">
                    <span class="needInfo">*</span>消息编码:
                </td>
                <td class="container">
                    <input type="text" name="fdMessageCode" value="${bean.fdMessageCode}" class="table_input" style="width: 400px" validate="{required:true}" maxlength="100"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    <span class="needInfo">*</span>类型:
                </td>
                <td class="container">
                    <form:select path="fdMessageType">
                        <form:option value="0">MQTT</form:option>
                        <form:option value="1">HTTP</form:option>
                    </form:select>
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    <span class="needInfo">*</span>处理器名称:
                </td>
                <td class="container">
                    <input type="text" name="fdMessageHandlerName" value="${bean.fdMessageHandlerName}" class="table_input" style="width: 400px" validate="{required:true}" maxlength="100"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    描述:
                </td>
                <td class="container">
                    <textarea rows="4" cols="60" class="textarea_table" name="fdMessageDesc" id="fdMessageDesc">${bean.fdMessageDesc}</textarea>
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    处理程序名称:
                </td>
                <td class="container">
                    <input type="text" name="fdBusinessModule" value="${bean.fdBusinessModule}" style="width: 400px" class="table_input" maxlength="200"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    处理程序方法名:
                </td>
                <td class="container">
                    <input type="text" name="fdBusinessFunction" value="${bean.fdBusinessFunction}" style="width: 400px" class="table_input" maxlength="200"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    状态:
                </td>
                <td  class="container">
                <c:choose>
                    <c:when test="${bean.fdMessageState == 1}">
                        <input type="radio" name="fdMessageState" value="1" checked="true">有效
                        <input type="radio" name="fdMessageState" value="0">无效
                    </c:when>
                    <c:otherwise>
                        <input type="radio" name="fdMessageState" value="1">有效
                        <input type="radio" name="fdMessageState" value="0" checked="true">无效
                    </c:otherwise>
                </c:choose>
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
        v = $('#sysMessageInfoEditForm').validate();
    });
</script>