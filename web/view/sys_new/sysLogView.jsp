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
                日志ID:
            </td>
            <td class="container">
                ${bean.user.loginName}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right">
                IP:
            </td>
            <td class="container">
                ${bean.ipAddress}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right">
                登录时间：
            </td>
            <td class="container">
                ${bean.enterTime}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right">
                退出时间：
            </td>
            <td class="container">
                ${bean.outTime}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right">
                页面URL:
            </td>
            <td class="container">
                ${bean.pageUrl}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right">
                日志类型:
            </td>
            <td class="container">
                ${bean.logType.name}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right">
                浏览器版本:
            </td>
            <td  class="container">
                ${bean.ieVersion}
            </td>
        </tr>
        <tr class="inputTr">
            <td align="right">
                SessionId:
            </td>
            <td  class="container">
                ${bean.sessionid}
            </td>
        </tr>
    </table>
</div>
</body>
</html>