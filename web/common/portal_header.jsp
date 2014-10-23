<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="com.comet.core.security.user.BaseUser" %>
<%@ page import="com.comet.core.security.util.SpringSecurityUtils" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%
String urlType = StringUtils.defaultString((String)pageContext.getAttribute("urlType"), "all");
String bg = "bgcolor=\"#ff1e28\" ";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <%--<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />--%>
    <meta name="description" content="中信信托, 中信, 中信信托官方网站, 中信信托官网, China CITIC Trust, 中信嘉丽泽, 信托, 理财收益公告, 金融同业, 中信股票, 股票, 基金通, 外汇通, 纸黄金, 国债, 金融商城, 信福年金, 信托产品" />
    <link href="${ctx}/images/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/js/jquery/jquery.bxslider.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/images/css/style.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/images/css/bootstrap.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/js/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/bootstrap/bootstrap.js" type="text/javascript"></script>
    <script src="${ctx}/js/jquery/jquery.validate.js" type="text/javascript"></script>
    <script src="${ctx}/js/jquery/jquery.bxSlider.min.js" type="text/javascript"></script>
    <script src="${ctx}/js/application/page.js" type="text/javascript"></script>
    <script type="text/javascript">
        var CONTEXT_NAME = "${ctx}";
        function checkSession(){
            if(window.parent.length>0){window.parent.location="login.jsp";}
        }
        checkSession();
    </script>
    <title>中信信托</title>
</head>

<body>
<div class="title_taitou">
    <div class="Header_taitou">
        <table width="100%" border="0" style="height: 30px;">
            <tr>
                <td width="40">反馈</td>
                <td width="40">收藏</td>
                <td width="70">帮助中心</td>
                <td>&nbsp;</td>
                <%
                    BaseUser loginUser = SpringSecurityUtils.getCurrentUser();
                    if(loginUser != null) {
                %>
                <td>欢迎您，<a href="${ctx}/userProdOrder/list.do"><%=loginUser.getRealName()%></a>!&nbsp;&nbsp;
                    <a href="${ctx}/j_spring_security_logout">退出登录</a>
                </td>
                <%
                    } else {
                %>
                <td>
                    <a href="${ctx}/login.jsp">登录</a>&nbsp;&nbsp;
                    <a href="${ctx}/userReg/init.do">免费注册</a>
                </td>
                <%
                    }
                %>
                <td align="right" width="200"><img src="${ctx}/images/tel.jpg" width="22" height="20" />
                服务热线：400-707-8555</td>
            </tr>
        </table>
    </div>
    <div class="clr"></div>
</div>
<div class="Header">
    <div class="title">
        <ul>
            <li><img src="${ctx}/images/logo_zx.jpg" width="156" height="61" /></li>
            <li class="tt_middle" style="">
                <%--<img src="${ctx}/images/lb.jpg" width="31" height="27" />--%>
            </li>
            <li class="tt_right">
                <%--<input name="textfield6" type="text" id="textfield6" class="tt_right_input" value="请输入检索关键字" />--%>
            </li>
        </ul>
        <div class="clr"></div>
    </div>
</div>
<div class="title_taitou_nav">
    <div class="Header_taitou">
        <table width="980" height="33" border="0" cellpadding="0" cellspacing="0">
            <tr>
                <%--<td width="180" align="center" class="zcd" <%=(urlType.equals("all")?bg:"")%>><a href="#">全部菜单分类</a></td>--%>
                <td width="120" align="center" class="zcd" <%=(urlType.equals("index")?bg:"")%>><a href="${ctx}/index.jsp">首页</a></td>
                <td width="120" align="center" class="zcd" <%=(urlType.equals("news")?bg:"")%>><a href="${ctx}/news/list.do">新闻中心</a></td>
                <td width="120" align="center" class="zcd" <%=(urlType.equals("product")?bg:"")%>><a href="${ctx}/product/index.do">产品中心</a></td>
                <td width="120" align="center" class="zcd" <%=(urlType.equals("consume")?bg:"")%>><a href="${ctx}/userProdOrder/list.do">权益消费</a></td>
                <td width="120" align="center" class="zcd" <%=(urlType.equals("aboutus")?bg:"")%>><a href="${ctx}/portal/cms/aboutus.jsp">关于我们</a></td>
                <td width="200">&nbsp;</td>
            </tr>
        </table>
    </div>
    <div class="clr"></div>
</div>
