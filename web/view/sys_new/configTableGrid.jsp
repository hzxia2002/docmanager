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
            <form id="configTableForm" action="">
                <table style="width: 100%">
                    <input type="hidden" value="" id="treeId" name="treeId" op="like" entity="t" />
                    <tr>
                        <td width="8%" nowrap="nowrap" align="left">
                            表名:  <input type="text" id="tableName" name="tableName" value="" class="table_input" op="like" entity="t" isCapital="false" dtype="String"/>
                        </td>
                        <td width="8%" nowrap="nowrap" align="left">
                            Class名: <input type="text" id="className" name="className" value="" class="table_input" op="like" entity="t" isCapital="false" dtype="String"/>
                        </td>
                        <td valign="middle" style="padding-left: 5px">
                            <input type="button" value="查询" onclick="javascript:search('configTableGrid','configTableForm');"/>&nbsp;
                            <input type="button" value="" class="btn_Refresh" onClick="javascript:doRefresh();"/>
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="configTableGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/configTable/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#mainLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("configTableGrid").setWidth(width);
            $.jui.get("query").setWidth(width);
        }
        });
        //查询框初始化
        $("#query").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
                { text: '增加', click: doAdd, icon: 'add' },
                { line: true },
                { text: '修改', click: doEdit, icon: 'modify' },
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };

        //grid
        $("#configTableGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', width: 50,hide:true },
                { display: '表名', name: 'tableName',width:"10%"},
                { display: 'Class名', name: 'className',width:"20%" },
                { display: '扩展XML', name: 'extendXml',width:"20%"  },
                { display: '记录日志', name: 'isLog',width:"10%",render:booleanFormatter },
                { display: '创建时间', name: 'createTime',width:"10%"},
                { display: '创建人', name: 'createUser',width:"10%"}
            ]
//        enabledEdit: true
        });

    });


    function doView(id) {
        openWindow("configTableWindow", "查看", "${ctx}/configTable/view.do?id=" + id, true, "configTableGrid");
    }

    function doDelete(){
        commonDelete("configTableGrid","${ctx}/configTable/delete.do");
    }

    function doAdd(){
        commonAddOrUpdate("${ctx}/configTable/init.do","configTableGrid",null,"configTableEditForm",{title:"新增表操作参数配置",height:300,width:600});
    }

    function doEdit(){
        var url = "${ctx}/configTable/init.do";
        url = initUrl(url,"configTableGrid");
        if(url){
            commonAddOrUpdate(url,"configTableGrid",null,"configTableEditForm",{title:"修改表操作参数配置",height:300,width:600});
        }
    }

    function doRefresh() {
        var msg = "您确定要刷新表日志配置信息缓存吗?";

        $.juiceDialog.confirm( msg, function(r) {
            if (r) {
                $.ajax({
                    type: 'POST',
                    url: "${ctx}/configTable/refresh.do",
//                    data: sendData,
                    dataType: 'json',
                    success: function(data) {
                        if (data.success) {
                            $.juiceDialog.success(data.msg,null);
                        } else {
                            $.juiceDialog.success(data.msg);
                        }
                    },
                    error: function(xmlR, status, e) {
                        $.juiceDialog.error("[" + e + "]" + xmlR.responseText);
                    }
                });
            }
        });
    }
</script>