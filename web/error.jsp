<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang.exception.ExceptionUtils"%>
<%@page import="org.apache.commons.lang.StringUtils"%>

<%
try {
	// 获取错误状态码
	String statusCode = StringUtils.defaultIfEmpty(String.valueOf(request.getAttribute("javax.servlet.error.status_code")), "");

	if(statusCode.equals("404")){
		out.println("您所访问的页面不存在，请和管理员联系!");
		return;
	} else if(statusCode.equals("500")){
		out.println("您所访问的页面出现了异常，请和管理员联系!");
	} else {
		// The Servlet spec guarantees this attribute will be available
		Throwable exception = (Throwable) request.getAttribute("javax.servlet.error.exception");

		if (exception != null && exception instanceof ServletException) {
			// It's a ServletException: we should extract the root cause
			ServletException sex = (ServletException) exception;
			Throwable rootCause = sex.getRootCause();
			if (rootCause == null)
				rootCause = sex;
			out.println("系统处理过程中出现了异常，异常消息如下: "+ rootCause.getMessage());

			rootCause.printStackTrace(new java.io.PrintWriter(out));

			rootCause.printStackTrace();
		}
		else {
			if(exception != null){
				exception.printStackTrace();
				out.println("系统处理过程中出现了异常，异常消息如下: "+ ExceptionUtils.getRootCause(exception).getLocalizedMessage());
			} else {
				out.println("系统处理过程中出现了异常，请和管理员联系!");
			}
		}
	}
} catch(Exception e)
{
	out.println("系统处理过程中出现了异常，异常消息如下: "+ e.getMessage());
	e.printStackTrace();
}
%>
