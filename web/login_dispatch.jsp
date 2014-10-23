<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%
    String url = StringUtils.defaultIfEmpty((String)request.getAttribute("url"), "/index.jsp");

    if(url.indexOf("index.jsp") > -1) {
        response.sendRedirect(request.getContextPath() + url);
    } else {
        response.sendRedirect(request.getContextPath() + url);
    }
%>
