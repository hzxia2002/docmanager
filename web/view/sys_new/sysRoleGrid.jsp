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
            <form id="sysRoleForm" action="">
                <table style="width: 100%">
                    <input type="hidden" value="" id="treeId" name="treeId" op="like" entity="t" />
                    <tr>
                        <td width="25%" nowrap="nowrap">
                            角色编码：<input type="text" value="" class="table_input"
                                        id="code" name="code" op="like" entity="t"/>
                        </td>
                        <td width="25%" nowrap="nowrap">
                            角色名称：<input type="text" value="" class="table_input"
                                        id="roleName" name="roleName" op="like" entity="t" dType="Number"/>
                        </td>
                        <td>
                            &nbsp;<input type="button" value="查询" onclick="javascript:search('sysRoleGrid','sysRoleForm');"/>&nbsp;
                            <%--<input type="button" value="新增" class="btn_Add" onClick="javascript:doAdd();"/>--%>
                            <%--<a href="javascript:void(0);" onclick="toggleDiv('advanced_condition', '0')">高级查询</a>--%>
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="sysRoleGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysRole/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#mainLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("sysRoleGrid").setWidth(width);
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
        $("#sysRoleGrid").juiceGrid({
            toolbar:toolbar,
            dateFormat:"yyyy-MM-dd",
            columns: [
                { display: '主键', name: 'id', width: 50,hide:true },
                { display: '角色编码', name: 'code',width:"20%"},
                { display: '角色名称', name: 'roleName',width:"10%" },
                { display: '描述', name: 'description',width:"20%"  },
                { display: '创建时间', name: 'createTime',width:"10%" },
                { display: '更新时间', name: 'updateTime',width:"10%"},
                { display: '创建人', name: 'createUser',width:"10%" },
                { display: '更新人', name: 'updateUser',width:"10%" }
            ]
//        enabledEdit: true
        });

    });


    function doView(id) {
        openWindow("sysRoleWindow", "查看", "${ctx}/sysRole/view.do?id=" + id, true, "sysRoleGrid");
    }

    function doDelete(){
        commonDelete("sysRoleGrid","${ctx}/sysRole/delete.do");
    }

    function doAdd(){
        commonAddOrUpdate("${ctx}/sysRole/initAdd.do","sysRoleGrid",null,"sysRoleEditForm",{title:"新增角色",height:350,width:600});
    }

    function doEdit(){
        var url = "${ctx}/sysRole/init.do";
        url = initUrl(url,"sysRoleGrid");
        if(url){
            commonAddOrUpdate(url,"sysRoleGrid",null,"sysRoleEditForm",{title:"修改角色",height:350,width:600});
        }
    }

</script>