<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">
<div id="mainLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="菜单树" id="accordion1">
        <ul id="sysMenuTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center">
        <div id="query" title="查询条件"  icon="search">
            <form id="sysMenuForm" action="">
                <table style="width: 100%">
                    <input type="hidden" value="" id="treeId" name="treeId" op="leftLike" entity="t" />
                    <tr>
                        <td nowrap="nowrap" width="200">
                            名称:&nbsp;&nbsp;
                            <input type="text" id="name" name="name" value="" class="table_input" op="like" entity="t" dtype="String" isCapital="false" style="width: 100px"/>
                        </td>
                        <td>
                            &nbsp;<input type="button" value="查询" onclick="javascript:search('sysMenuGrid','sysMenuForm');"/>&nbsp;
                            <%--<input type="button" value="新增" class="btn_Add" onClick="javascript:doAdd();"/>--%>
                            <%--<a href="javascript:void(0);" onclick="toggleDiv('advanced_condition', '0')">高级查询</a>--%>
                        </td>
                    </tr>
                </table>
            </form>
            <input type="hidden" value="" id="id" name="id"/>
        </div>
        <div id="sysMenuGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysMenu/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#mainLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("sysMenuGrid").setWidth(width);
            $.jui.get("query").setWidth(width);
        }
        });
        //查询框初始化
        $("#query").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
                { text: '增加', click: function(){doAdd($("#id").val());}, icon: 'add' },
                { line: true },
                { text: '修改', click: doEdit, icon: 'modify' },
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };


        //grid
        $("#sysMenuGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', width: 50,hide:true },
                { display: '名称', name: 'name',width:"10%"},
                { display: '权限编码', name: 'privilege',width:"10%" },
                { display: '链接地址', name: 'url',width:"20%"  },
                { display: '事件', name: 'jsEvent',width:"10%" },
                { display: '叶节点', name: 'isLeaf',width:"5%",render:booleanFormatter },
                { display: '是否有效', name: 'isValid',width:"5%",render:booleanFormatter },
                { display: '参数', name: 'param',width:"10%" },
                { display: '显示图标', name: 'icon',width:"10%" } ,
                { display: '目标窗口', name: 'target',width:"10%" }
            ]
//        enabledEdit: true
        });

        createTree("sysMenuTree",{ url:"${ctx}/sysMenu/tree.do",createMenu:createMenu,onSelect:treeNodeClick});

    });

    function treeNodeClick(treeNode){
        $("#treeId").val(treeNode.data.treeId);
        $("#id").val(treeNode.data.uid);
        search('sysMenuGrid','sysMenuForm');
    }

    function createMenu(treeNode){
        var menu;
        var data = treeNode.data;
        if(data.uid == 'root') {
            menu =  $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加', icon:'add', click:function(item){doAdd(treeNode.data.uid)}},
                        { line: true },
                        { text: '刷新', click:function(item){refreshNode();}}
                    ]
            });
        } else {
           menu = $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加', icon:'add', click:function(item){doAdd(treeNode.data.uid)}},
                        { text: '修改', click:function(item){doEdit(treeNode.data.uid)}},
                        { line: true },
                        { text: '上移', click:function(item){doMoveup(treeNode)}},
                        { text: '下移', click:function(item){doMovedown(treeNode)}},
                        { line: true },
                        { text: '刷新', click:function(item){refreshNode();}}
                    ]
            });
        }
        return menu;
    }

    function doMoveup() {
        var tree = $.jui.get("sysMenuTree");
        moveUp(tree, "com.comet.system.domain.SysMenu");
    }

    function doMovedown(treeNode) {
        var tree = $.jui.get("sysMenuTree");
        moveDown(tree, "com.comet.system.domain.SysMenu");
    }

    function doView(id) {
        openWindow("sysMenuWindow", "查看", "${ctx}/sysMenu/view.do?id=" + id, true, "sysMenuGrid");
    }

    function doDelete(){
        commonDelete("sysMenuGrid","${ctx}/sysMenu/delete.do",refreshNode);
    }

    function doAdd(parentId){
        url = "${ctx}/sysMenu/initAdd.do";

        if(parentId&&parentId != 'root'){
            url += "?parent="+parentId;
        }
        commonAddOrUpdate(url,"sysMenuGrid",null,"sysMenuEditForm",{title:"新增系统菜单",height:530,width:600,callback:refreshNode});
    }

    function doEdit(id){
        var url = "${ctx}/sysMenu/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"sysMenuGrid");
        }
        if(url){
            commonAddOrUpdate(url,"sysMenuGrid",null,"sysMenuEditForm",{title:"修改系统菜单",height:530,width:600,callback:refreshPNode});
        }
    }

    function refreshNode(){
        var tree = $.jui.get("sysMenuTree");
        refreshCurrentNode(tree);
    }

    function refreshPNode(){
        var tree = $.jui.get("sysMenuTree");
        refreshParentNode(tree);
    }
</script>