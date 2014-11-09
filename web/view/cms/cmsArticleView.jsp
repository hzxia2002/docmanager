<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.comet.cms.domain.CmsArticle" %>
<%@ include file="/common/taglibs.jsp" %>
<%
    CmsArticle bean = (CmsArticle) request.getAttribute("bean");
    request.getAttribute("success") ;
    String fileName = "";
    String fileName2 = "";
    String fileName3 = "";
    if (StringUtils.isNotEmpty(bean.getAttachPath())) {
        String attachPath = bean.getAttachPath();
        fileName = attachPath.substring(attachPath.indexOf("_") + 1);
    }
    if (StringUtils.isNotEmpty(bean.getAttachPath2())) {
        String attachPath2 = bean.getAttachPath2();
        fileName2 = attachPath2.substring(attachPath2.indexOf("_") + 1);
    }
    if (StringUtils.isNotEmpty(bean.getAttachPath3())) {
        String attachPath3 = bean.getAttachPath3();
        fileName3 = attachPath3.substring(attachPath3.indexOf("_") + 1);
    }
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td height="120" align="center">
                <table class="main_text" width="95%" border="0">
                    <tr>
                        <td align="center">
                            <strong style="font-weight: bold">${bean.title}</strong>
                        </td>
                    </tr>
                    <tr>
                        <td align="left" valign="top" height="350">
                            ${bean.content}
                        </td>
                    </tr>
                    <tr>
                        <td align="left" valign="top">
                            关键字：${bean.keyword}
                        </td>
                    </tr>
                    <tr>
                        <td align="left" height="32" style="vertical-align: middle;">
                        <%
                            if (StringUtils.isNotEmpty(fileName)) {
                        %>
                            <img src="<c:url value="/skin/icons/page_attach.png"/>"><b>附件：</b><a
                                href="${ctx}${bean.attachPath}" target="_blank" title="点击下载"><%=fileName%>
                        </a>
                        <%}%>
                        <%
                            if (StringUtils.isNotEmpty(fileName2)) {
                        %>
                            <img src="<c:url value="/skin/icons/page_attach.png"/>"><b>附件：</b><a
                                href="${ctx}${bean.attachPath2}" target="_blank" title="点击下载"><%=fileName2%>
                        </a>
                        <%}%>
                        <%
                            if (StringUtils.isNotEmpty(fileName3)) {
                        %>
                            <img src="<c:url value="/skin/icons/page_attach.png"/>"><b>附件：</b><a
                                href="${ctx}${bean.attachPath3}" target="_blank" title="点击下载"><%=fileName3%>
                        </a>
                        <%}%>
                        </td>
                    </tr>
                    <tr><td align="left">接收者：${bean.userNames}</td></tr>
                    <tr><td align="left">外部链接：${bean.linkUrl}</td></tr>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>