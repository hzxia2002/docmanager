<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">
<div id="mainLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="权限树" id="accordion1" style="height: 95%;overflow: auto;">
        <ul id="privilegeTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center">
        <div id="query" title="查询条件"  icon="search">
            <form id="sysPrivilegeForm" action="">
                <table style="width: 100%">
                    <input type="hidden" value="" id="treeId" name="treeId" op="like" entity="t" />
                    <tr>
                        <td width="8%" nowrap="nowrap">
                            权限编码：<input type="text" value="" class="table_input" id="code" name="code" op="like" entity="t" isCapital="false"/>
                        </td>
                        <td  width="8%" nowrap="nowrap">
                            权限名称：<input type="text" value="" class="table_input" id="name" name="name" op="like" entity="t" dType="String" isCapital="false"/>
                        </td>
                        <td>
                            &nbsp;<input type="button" value="查询" onclick="javascript:search('sysPrivilegeGrid','sysPrivilegeForm');"/>&nbsp;
                            <%--<input type="button" value="新增" class="btn_Add" onClick="javascript:doAdd();"/>--%>
                            <%--<a href="javascript:void(0);" onclick="toggleDiv('advanced_condition', '0')">高级查询</a>--%>
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="sysPrivilegeGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysPrivilege/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#mainLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("sysPrivilegeGrid").setWidth(width);
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
                { text: '修改', click: doEdit, icon: 'modify' },
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };

        //grid
        $("#sysPrivilegeGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', width: 50,hide:true },
                { display: '权限编码', name: 'code',width:"15%"},
                { display: '权限名称', name: 'name',width:"15%" },
                { display: '权限类型', name: 'type.name',width:"20%"  },
                { display: 'TAG', name: 'tag',width:"15%" },
                { display: '页面地址', name: 'url',width:"25%"}
            ]
//        enabledEdit: true
        });

        createTree("privilegeTree",{ url:"${ctx}/sysPrivilege/tree.do",createMenu:createMenu, onSelect:treeNodeClick});

    });

    //树的右键菜单
    function createMenu(node){
        var menu;
        var treeNode = node.data;
        if(treeNode.uid == 'root') {
            menu =   $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加', icon:'add', click:function(item){doAdd(treeNode.uid)}},
                        { line: true },
                        { text: '刷新', click:function(item){refreshNode()}}
                    ]
            });
        } else {
            menu=   $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加', icon:'add', click:function(item){doAdd(treeNode.uid)}},
//                        { text: '修改', click:function(item){doEdit(treeNode.uid)}},
                        { line: true },
                        { text: '刷新', click:function(item){refreshNode()}}
                    ]
            });
        }
        return menu;
    }

    function treeNodeClick(treeNode){
        $("#treeId").val(treeNode.data.treeId);
        search('sysPrivilegeGrid','sysPrivilegeForm');
    }

    function doView(id) {
        openWindow("sysPrivilegeWindow", "查看", "${ctx}/sysPrivilege/view.do?id=" + id, true, "sysPrivilegeGrid");
    }

    function doDelete(){
        commonDelete("sysPrivilegeGrid","${ctx}/sysPrivilege/delete.do", refreshNode);
    }

    function doAdd(parentId){
        if(parentId != undefined && parentId != null && parentId != 'root') {
            commonAddOrUpdate("${ctx}/sysPrivilege/init.do?parent="+parentId,"sysPrivilegeGrid",null,"sysPrivilegeEditForm",{title:"新增权限",height:530,width:600,callback:refreshNode});
        } else {
            commonAddOrUpdate("${ctx}/sysPrivilege/init.do","sysPrivilegeGrid",null,"sysPrivilegeEditForm",{title:"新增权限",height:530,width:600,callback:refreshNode});
        }
    }

    function doEdit(){
        var url = "${ctx}/sysPrivilege/init.do";
        url = initUrl(url,"sysPrivilegeGrid");
        if(url){
            commonAddOrUpdate(url,"sysPrivilegeGrid",null,"sysPrivilegeEditForm");
        }
    }

    function refreshNode(){
        var tree = $.jui.get("privilegeTree");
        refreshCurrentNode(tree);
    }
</script>