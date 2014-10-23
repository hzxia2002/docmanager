<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.comet.core.security.util.SpringSecurityUtils" %>
<%@ page import="com.comet.core.security.user.BaseUser" %>

<%
BaseUser loginedUser = (BaseUser) SpringSecurityUtils.getCurrentUser();

if(loginedUser == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
}
%>