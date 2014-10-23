<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form id="modifyPasswordForm" name="modifyPasswordForm" action="" method="post">
    <table cellpadding=3>
        <tr>
            <td colspan="2"><span id="msg"></span></td>
        </tr>
        <tr height="30px">
            <td width="90" align="right">原密码：</td>
            <td>
                <input id="password" name="password" type="Password" ltype="text" validate="{required:true}"/>
            </td>
        </tr>
        <tr height="30px">
            <td align="right">新密码：</td>
            <td><input id="password1" name="password1" type="Password" ltype="text" validate="{required:true}"/></td>
        </tr>
        <tr height="30px">
            <td align="right">确认新密码：</td>
            <td><input id="password2" name="password2" type="Password" ltype="text" validate="{required:true}"/></td>
        </tr>
    </table>
</form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#modifyPasswordForm').validate();
    });
</script>