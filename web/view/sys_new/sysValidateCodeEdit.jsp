<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="sysValidateCodeEditForm" modelAttribute="bean" name="sysValidateCodeEditForm" action="${ctx}/sysValidateCode/save.do" method="post">
   <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

                <tr class="inputTr">
                    <td  align="right">
                        昵称:
                    </td>
                    <td  class="container">
                        <input type="text" name="nickName" value="${bean.nickName}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        邮件:
                    </td>
                    <td  class="container">
                        <input type="text" name="email" value="${bean.email}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        移动电话:
                    </td>
                    <td  class="container">
                        <input type="text" name="mobile" value="${bean.mobile}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        检验码:
                    </td>
                    <td  class="container">
                        <input type="text" name="validateCode" value="${bean.validateCode}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        校验码类型(0-用户注册;1-找回密码):
                    </td>
                    <td  class="container">
                        <input type="text" name="validateCodeType" value="${bean.validateCodeType}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        创建时间:
                    </td>
                    <td  class="container">
						 <input type="text"  name="createTime" class="jui-dateEditor">&nbsp;

                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        更新时间:
                    </td>
                    <td  class="container">
						 <input type="text"  name="updateTime" class="jui-dateEditor">&nbsp;

                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        更新人(记录帐号）:
                    </td>
                    <td  class="container">
                        <input type="text" name="updateUser" value="${bean.updateUser}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        创建人(记录帐号）:
                    </td>
                    <td  class="container">
                        <input type="text" name="createUser" value="${bean.createUser}" />&nbsp;
                    </td>
                </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#sysValidateCodeEditForm').validate();
    });
</script>