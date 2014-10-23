<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="sysEmailSendForm" modelAttribute="bean" name="sysEmailSendForm" action="${ctx}/sysEmailSend/send.do" method="post">
   <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">
        <tr class="inputTr">
            <td  align="right">
                接收人:
            </td>
            <td  class="container">
                <textarea name="receiver" cols="60" rows="2">${bean.receiver}</textarea><br/>
                <span class="error">提示</span>：不同的手机号通过分号(;)进行分隔
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                主题:
            </td>
            <td  class="container">
                <input type="text" name="title" size="60" maxlength="100" value="${bean.title}" />
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                邮件内容:
            </td>
            <td  class="container">
                <textarea name="content" cols="60" rows="10">${bean.content}</textarea>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#sysEmailSendForm').validate();
    });
</script>