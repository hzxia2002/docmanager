<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="comet" uri="/WEB-INF/tags/comet.tlds" %>
<c:set var="ctx" value="<%=request.getContextPath()%>"/>
<script type="text/javascript">
    var CONTEXT_PATH = "${ctx}";
</script>