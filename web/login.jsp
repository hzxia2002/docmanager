<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.security.web.authentication.AbstractProcessingFilter"%>
<%@ page import="com.comet.core.utils.Constant" %>
<%@include file="common/taglibs.jsp"%>
<%
    String str = request.getContextPath();

    if(str.equals("/")){
        Constant.CONTEXT_PATH = str;
    } else {
        Constant.CONTEXT_PATH = str + "/";
    }
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="chrome=1">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <script src="${ctx}/js/jquery/jquery-1.7.2.js" type="text/javascript"></script>
    <link href="./skin/dialog.css" media="screen" rel="stylesheet" type="text/css">
    <link href="./skin/login.css" media="screen" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        function reloadImage(){
            document.getElementById("pic").src = "<c:url value="/common/imageCode.jsp"/>?" + Math.random();;
        }

        function submitForm(){
            $("#dlg_flash_msg").hide();
            var form = document.getElementById("loginForm");
            var loginName = document.getElementById("j_username").value;
            var password = document.getElementById("j_password").value;
            var validation_code = document.getElementById("j_validation_code").value;

            if(loginName == null || loginName == "" || loginName == "请输入用户名或邮箱"){
                alert("用户名必须输入！");
                document.getElementById("j_username").focus();
                return;
            }

            if(password == null || password == "" || password == "请输入登录密码"){
                alert("密码必须输入！");
                document.getElementById("j_password").focus();
                return;
            }

            if(validation_code == null || validation_code == ""){
                alert("验证码必须输入！");
                document.getElementById("j_validation_code").focus();
                return;
            }

            form.submit();
        }
    </script>
</head>

<body class="b-wk l-en">
<div class="dialog center">
    <%
        if (session.getAttribute(AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY) != null) {
    %>
    <div id="dlg_flash_msg" class="error">
        ${SPRING_SECURITY_LAST_EXCEPTION.message}
    </div>
    <%
        }
    %>
    <div class="dialog_content login">
        <div style="margin: 25px 0; text-align: center; clear:both;">
            <%--<img src="./skin/images/title_1_1.png" width="229" height="50">--%>
        </div>

        <div id="logins">
            <form id="loginForm" name="loginForm" action="j_spring_security_check" method="post">
            <%--<form action="#" id="signin_standard" method="post" name="signin_standard" style="">--%>
                <div class="content" style="height:110px;">
                    <p>
                        <label for="j_username" class="large" style="color:#10334D;line-height:24px;font-size:15px">用户名</label>
                        <input id="j_username" name="j_username" style="width: 200px;" tabindex="1" type="text" value="">
                    </p>

                    <p>
                        <label for="j_username" class="large" style="color:#10334D;line-height:24px;font-size:15px">密码</label>
                        <input id="j_password" name="j_password" style="width: 200px;" tabindex="2" type="password" value="">
                    </p>
                    <p>
                        <label for="j_validation_code" class="large" style="color:#10334D;line-height:24px;font-size:15px">验证码</label>
                        <input id="j_validation_code" name="j_validation_code" style="width: 80px;" tabindex="2" type="password" value="">
                        <img id="pic" src="<c:url value="/common/imageCode.jsp"/>" onclick="reloadImage();" height="20" alt="点击图片显示新的验证码" style="vertical-align: middle;">
                        <a href="#" onclick="reloadImage();">换一张</a>
                    </p>
                    <p style="margin-bottom: 5px">
                        <a href="#" class="indented large">忘记密码</a>&nbsp;
                        <a href="efmpx4a.apk" class="indented large">客户端下载</a>
                    </p>

                    <%--<p class="indented large">--%>
                        <%--<input class="checkbox" id="remember_me" name="remember_me" type="checkbox" value="1">--%>
                        <%--<label for="remember_me" class="checkbox">记住密码</label>--%>
                    <%--</p>--%>
                </div>
            </form>

        </div>

        <div class="dialog_buttons">
            <input class="button default" id="btn_signin" name="commit" style="font-size:17px; float:left" tabindex="3" onclick="submitForm();" type="submit" value="登录">
            <input type="button" onclick="javascript:void(0)" class="button" style="font-size:14px" value="取消">
        </div>
    </div>
</div>
</body>
</html>