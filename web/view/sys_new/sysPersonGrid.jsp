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
            <form id="sysPersonForm" action="">
                <table style="width: 100%">
                    <input type="hidden" value="" id="treeId" name="treeId" op="like" entity="t" />
                    <tr>
                        <td width="180">
                            工号：<input type="text" value="" class="table_input"
                                      id="code" name="code" op="like" entity="t" isCapital="false" style="width:100px;"/>
                        </td>
                        <td width="180">
                            姓名：<input type="text" value="" class="table_input"
                                      id="name" name="name" op="like" entity="t" isCapital="false" style="width:100px;"/>
                        </td>
                        <td width="220">
                            身份证：<input type="text" value="" class="table_input"
                                       id="card" name="card" op="like" entity="t" dType="Number" style="width:140px;"/>
                        </td>
                        <td>
                            <input type="button" value="查询" onclick="javascript:search('sysPersonGrid','sysPersonForm');"/>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="sysPersonGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysPerson/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
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
        $("#sysPersonGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', hide:true },
                { display: '工号', name: 'code',width:"10%"},
                { display: '名称', name: 'name',width:"10%" },
                { display: '性别', name: 'sex',width:"5%",render:genderFormatter  },
                { display: '身份证', name: 'card',width:"25%" },
                { display: '移动电话', name: 'mobile',width:"20%" },
                { display: '所属企业', name: 'deptName',width:"25%" }
            ]
//        enabledEdit: true
        });

    });


    function doView(id) {
        openWindow("sysPersonWindow", "查看", "${ctx}/sysPerson/view.do?id=" + id, true, "sysPersonGrid");
    }

    function doDelete(){
        commonDelete("sysPersonGrid","${ctx}/sysPerson/delete.do");
    }

    function doAdd(){
        commonAddOrUpdate("${ctx}/sysPerson/initAdd.do","sysPersonGrid",null,"sysPersonEditForm",{title:"新增人员信息",height:520,width:500});
    }

    function doEdit(){
        var url = "${ctx}/sysPerson/init.do";
        url = initUrl(url,"sysPersonGrid");
        if(url){
            commonAddOrUpdate(url,"sysPersonGrid",null,"sysPersonEditForm",{title:"修改人员信息",height:520,width:500});
        }
    }

</script>