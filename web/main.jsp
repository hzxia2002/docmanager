<%@ page import="com.comet.system.utils.UserSessionUtils" %>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>后台管理系统</title>
    <link href="js/jui/skins/Aqua/css/jui-all.css" rel="stylesheet" type="text/css" />
    <%--<link href="skin/defaulsyst.css" rel="stylesheet" type="text/css" />--%>
    <script src="js/jquery/jquery-1.7.2.js" type="text/javascript"></script>
    <script src="js/jui/js/jui-all.js" type="text/javascript"></script>
    <script src="js/jquery-validation/jquery.validate.js" type="text/javascript"></script>
    <script src="js/jquery-validation/jquery.metadata.js" type="text/javascript"></script>
    <script src="js/jquery-validation/messages_cn.js" type="text/javascript"></script>
    <script src="js/application/common.js" type="text/javascript"></script>
    <style>.l-menu{width: 150px !important;}
    .userMenuDrop{width:20px; height:20px; background:url('${ctx}/js/jui/skins/Aqua/images/grid/icons.gif') no-repeat;background-position:0px -180px;margin-right: -4px}
    .selfMenu .l-menu-item:hover{
        background-color: #d9ffde;
    }
    .selfMenu .l-menu-item a{
        text-decoration:none;
    }
    </style>
    <script type="text/javascript">
        var tab = null;
        var accordion = null;
        var tree = null;
        var v = null;

        $(function ()
        {
            //布局
            $("#layout1").juiceLayout({ leftWidth: 190, height: '100%',heightDiff:-8,space:4,onHeightChanged: f_heightChanged });

            var height = $(".l-layout-center").height();

            //Tab
            $("#framecenter").juiceTab({ height: height,onAfterSelectTabItem:function(item){
//                if(tree.getSelected()&&tree.getSelected().data.id!=this.getSelectedTabItemID()){
//                    tree.selectNode(this.getSelectedTabItemID() );
//                }else if(!tree.getSelected()){
//                    tree.selectNode(this.getSelectedTabItemID() );
//                }
            }, onafterRemoveTabItem:function(item){
//                if(this.getSelectedTabItemID()!="home"){
//                    tree.selectNode(this.getSelectedTabItemID() );
//                }else{
////                    tree.selectNode
//                }
            }});

            //面板
            $("#accordion1").height(height-24);
            $("#accordion1").find(".l-scroll").height(height-24);

            $(".l-link").hover(function ()
            {
                $(this).addClass("l-link-over");
            }, function ()
            {
                $(this).removeClass("l-link-over");
            });
            <%--//树--%>
            <%--$.ajax({--%>
            <%--url: '${ctx}/sysMenu/getMenu.do',--%>
            <%--dataType: "json",--%>
            <%--success:function(datas){--%>
            <%--$("#tree1").juiceTree({--%>
            <%--data : datas,--%>
            <%--checkbox: false,--%>
            <%--slide: false,--%>
            <%--idFieldName :'id',--%>
            <%--parentIDFieldName :'pid',--%>
            <%--nodeWidth: 120,--%>
            <%--attribute: ['nodename', 'url'],--%>
            <%--onSelect: function (node)--%>
            <%--{--%>
            <%--if (!node.data.url) return;--%>
            <%--var tabid = $(node.target).attr("tabid");--%>
            <%--if (!tabid)--%>
            <%--{--%>
            <%--tabid = node.data.id;--%>
            <%--$(node.target).attr("tabid", tabid)--%>
            <%--}--%>
            <%--f_addTab(tabid, node.data.text, node.data.url);--%>
            <%--}--%>
            <%--});--%>
            <%--tree = $("#tree1").juiceGetTreeManager();--%>
            <%--}--%>
            <%--});--%>

            //菜单
            $.ajax({
                url: '${ctx}/sysMenu/getJuiMenu.do',
                dataType: "json",
                success:function(datas){
                    $("#topmenu").juiceMenuBar(eval("("+datas+")"));
                }
            });

            tab = $("#framecenter").juiceGetTabManager();
//            accordion = $("#accordion1").juiceGetAccordionManager();

            $("#pageloading").hide();

            $('#editpass').click(function() {
                var settings = {
                    title: "修改密码",
                    height: 200,
                    width: 400,
                    url:"${ctx}/view/sys_new/sysUserModifyPwd.jsp",
                    buttons:[
                        { text: '确定', onclick: function (item, dialog) {
                            var g = this;
                            var frameWin = dialog.jiframe[0].contentWindow|| dialog.jiframe[0].contentWindow.document ;

                            if(frameWin&&frameWin.v.form()) {
                                submitFormData("modifyPasswordForm", "${ctx}/sysUser/modifyPassword.do", dialog);
                            }
                        }},
                        { text: '取消', onclick: function (item, dialog) {
                            dialog.close();
                        }
                        }
                    ]
                };

                $.juiceDialog.open(settings);
            });

            $('#loginOut').click(function() {
                $.juiceDialog.confirm('您确定要退出系统吗?', '系统提示', function(r) {
                    if (r) {
//                        log("/j_spring_security_logout", "4");
                        location.href = '${ctx}/j_spring_security_logout';
                    }
                });
            });

            $('#viewInfo').click(function() {
                openView("${ctx}/sysUser/viewLogin.do",{title:"查看个人资料", height: 300, width: 600 });
            });

            $("#userMenu").bind("click",function(){
                $("#userMenuBody").show();
            });

            $(document).bind("click",function(e){
                if($(e.target).parent().andSelf().index($("#userMenu"))<0){
                    $("#userMenuBody").hide();
                }
            });

        });

        function f_heightChanged(options)
        {
//            if (tab)
//                tab.addHeight(options.diff);
//            if (accordion && options.middleHeight - 24 > 0)
            $("#accordion1").height(options.middleHeight - 24)
            $("#accordion1").find(".l-scroll").height(options.middleHeight - 24);
        }
        function f_addTab(tabid,text, url)
        {
            if(tab.isTabItemExist(tabid)){
                if(tab.getItemUrl(tabid)!=url) {
                    tab.tabItemLoad(tabid,url);
                }
                tab.selectTabItem(tabid);
            }else{
                tab.addTabItem({ tabid : tabid,text: text, url: url });
            }
        }

        function f_removeTab(tabid) {
            tab.removeTabItem(tabid);
        }

        function menuItemClick(item){
            if(item.url){
                tab.addTabItem({ tabid : item.id,text: item.text, url: item.url });
//                tree.selectNode(item.id) ;
            }
        }
    </script>
</head>

<body style="padding:0;background:#EAEEF5;overflow: hidden;">
<div  class="l-topmenu">
    <table border="0" cellpadding="0" cellspacing="0" style="height: 62px;width:100% ">
        <tr>
            <td style="height: 42px; padding-top: 2px;"><img src="${ctx}/skin/images/title.png" width="229" style="height:40px;" /></td>
            <td align="right" class="selfMenu">
                <table cellpadding=3 width="260">
                    <tr>
                        <td style="text-align: right;padding-left: 5px;width: 180px;" nowrap>欢迎使用:<span style="color: red"><%=UserSessionUtils.getInstance().getLoginedUser().getDisplayName()%></span></td>
                        <td align="left" style="vertical-align: middle;">
                            <div id="userMenu" class="l-panel-btn">
                                <div class="userMenuDrop"></div>
                                <div class="l-menubar-item-down"></div>
                            </div>
                        </td>
                        <td width="60">&nbsp;</td>
                        <%--<td style="text-align: left;padding-left: 5px;width: 60px;"><a id="editpass" href="javascript:void(0);">修改密码</a>&nbsp;&nbsp;</td>--%>
                        <%--<td style="text-align: right; width: 5px;"><img src="${ctx}/skin/icons/tc.png"/></td>--%>
                        <%--<td style="text-align: left;padding-left: 5px;"><a id="loginOut" href="javascript:void(0);">退出登录</a>&nbsp;&nbsp;</td>--%>
                    </tr>
                </table>
                <div class="l-menu" id="userMenuBody" style="right: 40px; top:30px; width:110px !important; display: none;" >
                    <div class="l-menu-yline"></div>
                    <div class="l-menu-over" style="top: -24px;">
                        <div class="l-menu-over-l"></div>
                        <div class="l-menu-over-r"></div>
                    </div>
                    <div class="l-menu-inner">
                        <div class="l-menu-item"  id="viewInfo">
                            <div class="l-menu-item-text">
                                <a  href="javascript:void(0);">个人资料</a>
                            </div>
                        </div>
                        <div class="l-menu-item" id="editpass">
                            <div class="l-menu-item-text">
                                <a  href="javascript:void(0);">修改密码</a>
                            </div>
                        </div>
                        <div class="l-menu-item" id="loginOut">
                            <div class="l-menu-item-text">
                                <a  href="javascript:void(0);">退出登录</a>
                            </div>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="2"  id="topmenu"></td>
        </tr>
    </table>
</div>
<div id="layout1" style="width:99.2%; margin:0 auto; margin-top:0px; ">
    <%--<div position="left"  title="菜单导航" id="accordion1" >--%>
    <%--<div title="功能列表" class="l-scroll" style="height: 100%">--%>
    <%--<ul id="tree1" style="margin-top:3px;"></ul>--%>
    <%--</div>--%>
    <%--</div>--%>
    <div position="center" id="framecenter">
        <div tabid="home" title="我的主页" style="height:300px" >
            <iframe frameborder="0" name="home" id="home" src="welcome.jsp"></iframe>
        </div>
    </div>
</div>
</body>
</html>