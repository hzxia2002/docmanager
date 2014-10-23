<%@ page contentType="text/html;charset=UTF-8" language="java"
%><%@page import="com.justonetech.core.utils.FileDownloadUtil"
%><%@page import="com.comet.translation.domain.DocAttachments"
%><%@ page import="java.io.File" %><%
    DocAttachments bean = (DocAttachments)request.getAttribute("bean");
    if(bean != null) {
        String filePath = request.getRealPath("uploads") + bean.getFilePath();

        FileDownloadUtil.download(filePath, bean.getName(), pageContext);
    }
%>