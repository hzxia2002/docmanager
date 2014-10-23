<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="sysUserEditForm" modelAttribute="bean" name="sysUserEditForm" action="${ctx}/sysUser/save.do" method="post">
<input type="hidden" name="id" id="id" value="${bean.id}" />
<input type="hidden" name="person.id" id="person.id" value="${bean.person.id}" />
<div>
<table border="0" cellspacing="1" width="100%" id="viewTable" class="inputTable">
<tr class="inputTr">
    <td  align="right" width="15%">
        登录名:
    </td>
    <td  class="container" width="35%">
        ${bean.loginName}
    </td>
    <td  align="right" width="15%">
        会员卡号:
    </td>
    <td  class="container">
        ${bean.userId}
    </td>
</tr>

<tr class="inputTr">
    <td  align="right">
        <span class="needInfo">*</span>真实姓名:
    </td>
    <td  class="container">
        ${bean.displayName}
    </td>
    <td  align="right">
        性别:
    </td>
    <td  class="container">
        <form:radiobutton path="person.sex" value="true"  disabled="true" ></form:radiobutton>男
        <form:radiobutton path="person.sex" value="false"  disabled="true" ></form:radiobutton>女
    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        所在公司:
    </td>
    <td class="container">
        ${bean.deptName}
    </td>
    <td  align="right">
        身份证号:
    </td>
    <td  class="container">
        ${bean.person.card}
    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        移动电话:
    </td>
    <td  class="container">
        ${bean.person.mobile}
    </td>
    <td  align="right">
        固定电话:
    </td>
    <td  class="container">
        ${bean.person.officeTel}
    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        传真:
    </td>
    <td  class="container">
        ${bean.person.faxTel}
    </td>
    <td  align="right">
        Email:
    </td>
    <td  class="container">
        ${bean.person.email}
    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        联系地址:
    </td>
    <td  class="container">
        ${bean.person.address}
    </td>
    <td  align="right">
        邮政编码:
    </td>
    <td  class="container">
        ${bean.person.zipcode}
    </td>
</tr>
</table>

</div>
</form:form>
</body>

</html>
