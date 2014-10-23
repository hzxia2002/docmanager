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
        <div id="query" title="查询条件" icon="search">
            <form id="sysDeptForm" action="">
                <table style="width: 100%">
                    <input type="hidden" value="" id="treeId" name="treeId" op="like" entity="t" />
                    <tr>
                        <td width="10%" align="right">
                            单位代码：
                        </td>
                        <td width="25%">
                            <input type="text" value="" class="table_input" id="code" name="code" op="like" entity="t"/>
                        </td>

                        <td width="10%" align="right">
                            单位名称：
                        </td>
                        <td width="25%">
                            <input type="text" value="" class="table_input" id="name" name="name" op="like" entity="t" />
                        </td>
                        <td>
                            <input type="button" value="查询" onclick="javascript:search('maingrid2','sysDeptForm');"/>&nbsp;
                            <%--<a href="javascript:void(0);" onclick="toggleDiv('advanced_condition', '0')">高级查询</a>--%>
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="maingrid2" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysDept/selectGrid.do?type=5' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#mainLayout").juiceLayout({onAfterResize:function(width){
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
//                { text: '修改', click: doEdit, icon: 'modify' },
//                { line: true },
//                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };
        //grid
        $("#maingrid2").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', width: 50, type: 'int',hide:true },
                { display: '单位代码', name: 'code',width:"10%"},
                { display: '名称', name: 'name',width:"20%" },
                { display: '简称', name: 'shortName',width:"10%" },
//                { display: '机构代码证', name: 'cardNo',width:"10%" },
                { display: '省市', name: 'provinceName',width:"10%" },
                { display: '城市', name: 'cityName',width:"10%" },
                { display: '注册地址', name: 'address',width:"20%" },
                { display: '有效', name: 'isValid',render:isValid }
            ]
//        enabledEdit: true
        });
    });

    function treeNodeClick(treeNode){
        $("#treeId").val(treeNode.data.treeId);
        search('maingrid2','sysDeptForm');
    }

    function f_select(){
        return $.jui.get("maingrid2").getSelectedRow();
    }
</script>