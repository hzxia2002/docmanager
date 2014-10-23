<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.comet.core.utils.DateTimeHelper" %>
<%@ page import="com.comet.core.security.util.SpringSecurityUtils" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<style type="text/css">
<!--
.STYLE1 {color: #FFFFFF}
-->
</style>
<head id="Head1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>后台管理系统</title>
    <%@include file="./common/header.jsp" %>
    <%
        String loginUser = "";
        if(SpringSecurityUtils.getCurrentUser() != null) {
            loginUser = SpringSecurityUtils.getCurrentUser().getRealName();
        }

        String initDate = DateTimeHelper.getCurDate("YYYY:MM:DD:HH24:MI:SS");
    %>
    <script type="text/javascript">
//        jQuery.ajaxSetup({cache:false});//ajax不缓存
        var errAjaxMsg = "<div class=\"errMsg\">数据出错或服务器正忙，请重新尝试！</div>";

        //修改密码
        function serverLogin() {
            var $newpass = $('#txtNewPass');
            var $rePass = $('#txtRePass');

            if ($newpass.val() == '') {
                msgShow('系统提示', '请输入密码！', 'warning');
                return false;
            }
            if ($rePass.val() == '') {
                msgShow('系统提示', '请在一次输入密码！', 'warning');
                return false;
            }

            if ($newpass.val() != $rePass.val()) {
                msgShow('系统提示', '两次密码不一致！请重新输入', 'warning');
                return false;
            }

            $.post('/ajax/editpassword.ashx?newpass=' + $newpass.val(), function(msg) {
                msgShow('系统提示', '恭喜，密码修改成功！<br>您的新密码为：' + msg, 'info');
                $newpass.val('');
                $rePass.val('');
                close();
            })
        }

        //初始化服务器时间
        var init_dateTime = "<%=initDate%>";

        $(function() {
            $('#winOpen').window({
                title: '修改密码',
                width: 400,
                modal: true,
                shadow: false,
                closed: true,
                height: 200
            });

            $('#editpass').click(function() {
                $("#winOpen").window('open');
            });

            $('#btnEp').click(function(){
                $("#winOpen").window('close');
                showInfoMsg("密码修改成功!");
            });

            $('#btnCancel').click(function(){
                $("#winOpen").window('close');
            });

            $('#loginOut').click(function() {
                $.messager.confirm('系统提示', '您确定要退出系统吗?', function(r) {
                    if (r) {
                        log("/j_spring_security_logout", "4");
                        location.href = '../j_spring_security_logout';
                    }
                });
            });

            //显示时间
            $('#pageClock').timer({format: "yy年mm月dd日 W HH:MM:ss"});

            //右下角弹出提示框
//            $.messager.show({
//                title:'系统提示',
//                msg:'您有5条任务等待处理！<a href="javascript:alert(\'查看任务\')">查看</a>',
//                timeout:5000
//            });

            // $("#mainContent").html("");
            // $("#mainContent").load("${ctx}/view/tree_grid.jsp");

            $("#contentFrame").attr("src", "${ctx}/view/sys/sysUserGrid.jsp");

            //判断是否safri浏览器
            checkIsIpad();

            initMenu();
        });

        function initMenu(){
            $.ajax({
                url: '${ctx}/sysMenu/getMenu.do',
                dataType: "json",
                success:function(datas){
                    $("#topmenu").ligerMenuBar(eval("(" + datas + ")"));
                }
            });
        }

        function itemClick(node) {
            // JS时间处理
            if(node.jsEvent&&node.jsEvent != null && node.jsEvent != "null") {
                eval(node.jsEvent);
            } else {
                var target = node.target;
                if(target == null || target == "null" || target.toLowerCase() == "_self") {
                    <%--$("#mainContent").html("");--%>
                    <%--$("#mainContent").load("${ctx}/" + node.url);--%>
                    $("#contentFrame").attr("src", "${ctx}/" + node.url);

                    $("#mainLocationDesc").html("当前位置：" + node.parentName + node.text);
                } else if(target.toLowerCase() == '_blank') {
                    window.open(node.url);
                } else if(target.toLowerCase() == '_top' || target.toLowerCase() == '_parent') {
                    window.location.href = node.url;
                }
            }
        }
    </script>
</head>

<body class="easyui-layout" style="overflow-y: hidden;margin-top:0px" scroll="no">
<noscript>
    <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
        <img src="${ctx}/skin/jquery/easyui/images/noscript.gif" alt='浏览器不支持脚本！'/>
    </div>
</noscript>
<div region="north" split="false" border="false" style="height:110px;
        background: url(${ctx}/skin/jquery/easyui/images/title_1.jpg) repeat-x center;
        overflow: hidden;margin-top:0px">
    <table width="100%" border="0" cellpadding="0" cellspacing="0" height="102">
        <tr>
            <td height="62"><img src="${ctx}/skin/images/title_1_1.gif" width="229" height="50" /></td>
            <td align="right">
                        <table cellpadding=3 width="200">
                <tr>
                    <td><img src="${ctx}/skin/jquery/easyui/images/xgmm.png"/></td>
                    <td><a href="javascript:void(0);" id="editpass">修改密码</a>&nbsp;&nbsp;</td>
                     <td><img src="${ctx}/skin/jquery/easyui/images/tc.png"/></td>
                    <td><a id="loginOut" href="javascript:void(0);">退出登录</a>&nbsp;&nbsp;</td>
                </tr>
            </table>
            </td>
        </tr>
        <tr>
            <td colspan="2" height="20" id="topmenu"></td>
      </tr>
        <tr>
          <td colspan="2" height="20" id="mainLocationDesc" class="mainLocation">当前位置：主页</td>
        </tr>
    </table>
</div>
<!-- 主页面区域 -->
<div id="mainPanle" region="center" style="background: #eee; overflow: hidden">
   <%-- <div id="hiddenDiv" class="hiddenDiv" style="height:0px; width: 100%; left: 5px;"></div>--%>
    <%--<div id="mainContent" style="width:100%;height:100%;">--%>
    <iframe id="contentFrame" frameborder="0" width="100%" height="100%"></iframe>
    </div>
</div>

<!-- 底部区域 -->
<div region="south" split="false" border="false" style="height: 30px; background: #D2E0F2; ">
    <div>
        <div id="sysUser" style="float: left; margin-left: 10px">当前用户：<%=loginUser%></div>
        <div id="pageClock" style="float: right; margin-right: 10px"></div>
    </div>
</div>


<!--修改密码窗口-->
<div id="winOpen" class="easyui-window" title="修改密码" iconCls="icon-save" style="width:300px;height:80px;padding:5px;">
    <div class="easyui-layout" fit="true">
        <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <table cellpadding=3>
                <tr>
                    <td>新密码：</td>
                    <td><input id="txtNewPass" type="Password" class="txt01"/></td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td><input id="txtRePass" type="Password" class="txt01"/></td>
                </tr>
            </table>
        </div>
        <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px; overflow: hidden">
            <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)">确定</a>
            <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)">取消</a>
        </div>
    </div>
</div>

</body>
</html>