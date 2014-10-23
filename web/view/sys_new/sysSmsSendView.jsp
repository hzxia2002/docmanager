<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<div>
    <table border="0" cellspacing="1" width="100%" class="inputTable">

        <tr class="inputTr">
            <td align="right" width="20%" nowrap="true">
                接收人员:
            </td>
            <td class="container">
                ${bean.user.person.name}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right" width="20%" nowrap="true">
                手机号:
            </td>
            <td class="container">
                ${bean.receiver}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right" width="20%" nowrap="true">
                短信内容:
            </td>
            <td class="container">
                ${bean.content}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right" width="20%" nowrap="true">
                发送时间:
            </td>
            <td class="container">
                ${bean.sendTime}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right" width="20%" nowrap="true">
                发送结果:
            </td>
            <td class="container">
                ${bean.sendResult}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right" width="20%" nowrap="true">
                发送状态:
            </td>
            <td class="container">
                ${bean.sendStatus}
            </td>
        </tr>
    </table>
</div>
</body>
</html>