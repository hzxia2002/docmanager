<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">
<div id="mainLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="center">
        <div id="query" title="查询条件"  icon="search">
            <form id="sysLogForm" action="">
                <table style="width: 100%">
                    <input type="hidden" value="" id="treeId" name="treeId" op="like" entity="t" />
                    <tr>
                        <td width="60px">日志类型:</td>
                        <td nowrap="nowrap" align="left" width="160px">
                            <input type="text"
                                   url="${ctx}/sysCode/getCodeDetailList.do?code=LOG_TYPE"
                                   textField="name"
                                   valueField="code"
                                   valueFieldID="target"
                                   class= "jui-comboBox"/>
                        </td>
                        <td  width="50" nowrap="nowrap" align="left">
                           <input type="text" id="enterTime" jui="label:'登入时间'" class="jui-dateEditor" name="enterTime" value="" op="greatAndEq" entity="t" dtype="String"/>
                        </td>
                        <td nowrap="nowrap" align="center" width="10px">
                            ~
                        </td>
                        <td  nowrap="nowrap" align="left" width="50" >
                            <input type="text"  id="enterTime1" name="enterTime" class="jui-dateEditor" value="" op="lessAndEq" entity="t" dtype="String"/>
                        </td>
                        <td>
                            &nbsp;<input type="button" value="查询" onclick="javascript:search('sysLogGrid','sysLogForm');"/>&nbsp;
                            <%--<input type="button" value="新增" class="btn_Add" onClick="javascript:doAdd();"/>--%>
                            <%--<a href="javascript:void(0);" onclick="toggleDiv('advanced_condition', '0')">高级查询</a>--%>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="sysLogGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysLog/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        $.jui.parse();

        //布局
        $("#mainLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("sysLogGrid").setWidth(width);
            $.jui.get("query").setWidth(width);
        }
        });
        //查询框初始化
        $("#query").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
//                { text: '增加', click: doAdd, icon: 'add' },
//                { line: true },
                { text: '查看', click: doView, icon: 'modify' },
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };

        //grid
        $("#sysLogGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', width: 50,hide:true },
                { display: '用户标识', name: 'user.loginName',width:"10%"},
                { display: 'IP地址', name: 'ipAddress',width:"10%" },
                { display: '进入时间', name: 'enterTime',width:"13%"  },
                { display: '退出时间', name: 'outTime',width:"13%" },
                { display: '访问页面', name: 'pageUrl',width:"15%" },
                { display: '日志类型', name: 'logType.name',width:"5%"},
                { display: '浏览器版本', name: 'ieVersion',width:"29%" }
            ]
//        enabledEdit: true
        });
    });

    function doView() {
        commonView("${ctx}/sysLog/view.do", "sysLogGrid", {height:400, width:600});
    }

    function doDelete(){
        commonDelete("sysLogGrid","${ctx}/sysLog/delete.do");
    }

    function doAdd(){
        commonAddOrUpdate("${ctx}/sysLog/initAdd.do","sysLogGrid",null,"sysLogEditForm",{title:"新增人员信息",height:530,width:600});
    }

    function doEdit(){
        var url = "${ctx}/sysLog/init.do";
        url = initUrl(url,"sysLogGrid");
        if(url){
            commonAddOrUpdate(url,"sysLogGrid",null,"sysLogEditForm");
        }
    }

</script>