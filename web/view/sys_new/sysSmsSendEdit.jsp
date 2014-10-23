<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="sysSmsSendForm" modelAttribute="bean" name="sysSmsSendForm" action="${ctx}/sysSmsSend/send.do" method="post">
   <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">
        <tr class="inputTr">
            <td  align="right">
                接收人:
            </td>
            <td  class="container">
                <textarea name="receiver" cols="40" rows="4">${bean.receiver}</textarea><br/>
                <span class="error">提示</span>：不同的手机号通过逗号(,)进行分隔
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                短信内容:
            </td>
            <td  class="container">
                <textarea name="content" cols="40" rows="6">${bean.content}</textarea>
            </td>
        </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#sysSmsSendForm').validate();
    });
</script>