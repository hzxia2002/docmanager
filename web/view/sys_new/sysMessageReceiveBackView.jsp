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
                    ID:
                </td>
                <td class="container">
                    ${bean.id}
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    消息编码:
                </td>
                <td class="container">
                    ${bean.fdMessageCode}
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    消息内容：
                </td>
                <td class="container">
                    <c:out value="${bean.messageBody}" escapeXml="true" />
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    消息状态：
                </td>
                <td class="container">
                    ${bean.fdMessageState}
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    状态描述:
                </td>
                <td class="container">
                    <c:out value="${bean.fdMessageStateInfo}" escapeXml="true" />
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    消息接收时间:
                </td>
                <td class="container">
                    ${bean.fdMessageReceiveTime}
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    最后处理时间:
                </td>
                <td  class="container">
                    ${bean.fdMessageOperateTime}
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    处理版本:
                </td>
                <td  class="container">
                    ${bean.fdMessageOperateVersion}
                </td>
            </tr>
            <tr class="inputTr">
                <td align="right">
                    处理信息:
                </td>
                <td  class="container">
                    ${bean.fdMessageOperateInfo}
                </td>
            </tr>
        </table>
    </div>
</body>
</html>