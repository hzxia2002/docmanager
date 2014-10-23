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
            <form id="sysOperationTableLogForm" action="">
                <table style="width: 100%">
                    <input type="hidden" value="" id="treeId" name="treeId" op="like" entity="t" />
                    <tr>
                        <td width="200" nowrap="nowrap" align="left">
                            表名:
                            <input type="text" id="tableName" name="tableName" value="" class="title_input"
                                   op="like" entity="t_table" dtype="String" isCapital="false" style="width: 100px;"/>
                        </td>
                        <td width="100" nowrap="nowrap" align="right">
                            <input type="text" id="createTime" jui="label:'操作时间'" class="jui-dateEditor" name="createTime" value="" op="greatAndEq" entity="t" dtype="String"/>
                        </td>
                        <td width="10" align="center">~</td>
                        <td width="140" nowrap="nowrap" align="left">
                            <input type="text" id="createTime1" class="jui-dateEditor" name="createTime" value="" op="lessAndEq" entity="t" dtype="String"/>
                        </td>
                        <td>
                            &nbsp;<input type="button" value="查询" onclick="javascript:search('sysOperationTableLogGrid','sysOperationTableLogForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="sysOperationTableLogGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysOperationTableLog/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        $.jui.parse();

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
        $("#sysOperationTableLogGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', width: 50,hide:true },
                { display: '数据表', name: 'table.tableName',width:"10%"},
                { display: '操作类型', name: 'ipAddress',width:"10%" },
                { display: 'IP地址', name: 'logType',width:"20%"  },
                { display: '操作人', name: 'createUser',width:"20%" },
                { display: '操作时间', name: 'createTime',width:"20%"}
            ]
//        enabledEdit: true
        });
    });


    function doView(id) {
        openWindow("sysOperationTableLogWindow", "查看", "${ctx}/sysOperationTableLog/view.do?id=" + id, true, "sysOperationTableLogGrid");
    }

    function doDelete(){
        commonDelete("sysOperationTableLogGrid","${ctx}/sysOperationTableLog/delete.do");
    }

</script>