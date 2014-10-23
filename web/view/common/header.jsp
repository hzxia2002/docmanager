<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta name="viewport" content="width=device-width" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>演示环境</title>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="<%=request.getContextPath()%>"/>

<link rel="stylesheet" type="text/css" href="${ctx}/skin/main.css" />

<script src="${ctx}/js/jquery/jquery-1.7.2.js" type="text/javascript"></script>
<%--<script src="${ctx}/js/jquery/form/jquery.validform.4.0.js" type="text/javascript"></script>--%>
<script src="${ctx}/js/jquery/dialog/jquery.window.js" type="text/javascript"></script>
<%--<script src="${ctx}/js/jquery/jo/jquery.JOWindow.js" type="text/javascript"></script>--%>
<%--<script src="${ctx}/js/jquery/jquery-calendar.js" type="text/javascript"></script>--%>
<script src="${ctx}/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript">
    /*jquery-layout */
    var outerLayout, middleLayout;

    /*
    var layoutSettings = {
        north: {
            size: 75,
            spacing_open:0,
            closeable: false,
            resizable: false,
            slidable: false,
            paneSelector: "ui-layout-north"
        },
        center: {
            minWidth: 1000,
            minHeight:200,
            paneSelector: "ui-layout-center"
        },
        south: {
            minSize:            0
            ,   size:				30
            ,   spacing_open:			0
            ,	spacing_closed:			0
            ,   resizable:              false
            ,   paneSelector: "ui-layout-south"
        }
    };
    */

    function getFont(treeId, node) {
        return node.font ? node.font : {};
    }

    function getTest() {
        $(".ui-layout-center").html("");
        $(".ui-layout-center").load("${ctx}/view/sp/grid.jsp");
    }

    $(document).ready(function () {
        outerLayout = $('body').layout({
            center: {
                minWidth: 1000,
                minHeight:200
            },
            north: {
                size: 75,
                spacing_open:0,
                closeable: false,
                resizable: false,
                slidable: false
            },
            south: {
                minSize:            0
                ,   size:				30
                ,   spacing_open:			0
                ,	spacing_closed:			0
                ,   resizable:              false
            }
        });

        // $("body").layout(layoutSettings);

        // $(".ui-layout-center").layout(innnerLayoutSettings);

        initMenu();
        getDate('sysDate');

        $(".ui-layout-center").html("");
        $(".ui-layout-center").load("${ctx}/view/sp/tree_grid.jsp");
    });

    function initMenu(){
        $.ajax({
            url: '${ctx}/sysMenu/getFgMenu.do',
            dataType: "json",
            success:function(datas){
                $("#menu").html(datas);

                // 绑定菜单事件
                var divs = $("a[class^='fg-button']");

                $.each(divs,function(){
                    var element = this.nextSibling;
                    var html = "<" + element.localName + ">" + element.innerHTML + "</" + element.localName + ">"

                    $("#"+this.id).menu({content:html,flyOut:true,onChoose:loadUrl});
                });

//                var html = "";
//                $.each(datas,function(){
//                    html +='<a tabindex="0" class="fg-button fg-button-icon-right ui-widget ui-state-default ui-corner-all"'+'id="'+ this.id+'">'
//                            +'<span class="ui-icon ui-icon-triangle-1-s"></span>'+ this.text+'</a>';
//                });
//                $("#menu").html(html);
//                $.each(datas,function(){
//                    $("#"+this.id).menu({content:this.children,flyOut:true,onChoose:loadUrl});
//                });
            }
        });
    }

    /**
     * 加载菜单URL
     * @param item
     */
    function loadUrl(item){
        // JS时间处理
        if($(item).attr("jsEvent") != null) {
            eval($(item).attr("jsEvent"));
        } else {
            var target = $(item).attr("target");

            if(target == null || target.toLowerCase() == "_self") {
                $(".ui-layout-center").html("");
                $(".ui-layout-center").load("${ctx}/" + $(item).attr("href"));
                // window.location.href = "${ctx}/" + $(item).attr("href");
            } else if(target.toLowerCase() == '_blank') {
                window.open($(item).attr("href"));
            } else if(target.toLowerCase() == '_top' || target.toLowerCase() == '_parent') {
                window.location.href = $(item).attr("href");
            }
        }
    }
</script>