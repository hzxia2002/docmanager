<%@ page language="java" pageEncoding="UTF-8"%><%@ page import="com.comet.core.utils.FileDownloadUtil"%><%@ page import="java.io.File" %><%
String filePath = (String)request.getAttribute("filePath");
String fileName = (String)request.getAttribute("fileName");

FileDownloadUtil.download(filePath + File.separator + fileName, fileName, pageContext);
%>