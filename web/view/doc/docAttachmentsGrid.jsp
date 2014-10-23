<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="docAttachmentsLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="文档附件树" id="accordion1">
        <ul id="docAttachmentsTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center">
        <div id="docAttachmentsQuery" title="查询条件"  icon="search">
            <form id="docAttachmentsForm" action="">
                <table style="width: 100%">
                    <tr>
					  <td>
                         <input type="button" value="查询" onclick="javascript:search('docAttachmentsGrid','docAttachmentsForm');"/>&nbsp;
                      </td>
					 </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="docAttachmentsGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/docAttachments/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#docAttachmentsLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("docAttachmentsGrid").setWidth(width);
            $.jui.get("docAttachmentsQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#docAttachmentsQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
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
        $("#docAttachmentsGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
				{display: 'ID', name: 'id', width: 50,hide:true },
				{display: '文档ID', name: 'doc', width: 50,hide:true },
				{display: '文件名称', name: 'name', width: 50,hide:true },
				{display: '文件原始名称', name: 'orginName', width: 50,hide:true },
				{display: '上传时间', name: 'uploadDate', width: 50,hide:true },
				{display: '备注', name: 'remark', width: 50,hide:true },
				{ display: '', name: 'filePath', width: 50,hide:true }
            ]
//        enabledEdit: true
        });
        //创建树

    });
    //树的右键菜单
    function createMenu(treeNode){
        var menu;
        if(treeNode.data.uid == 'root') {
            menu = $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加', icon:'add', click:function(item){doAdd(treeNode.data.uid)}},
//                        { text: '修改', click:function(item){doEdit(treeNode.uid)}},
//                        { text: '查看', icon:'add', click:function(item){doView(treeNode.uid)}}
                { line: true },
                { text: '刷新', click:function(item){refreshNode();}}
            ]
            });
        } else {
            menu = $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加', icon:'add', click:function(item){doAdd(treeNode.data.uid)}},
                        { text: '修改', click:function(item){doEdit(treeNode.data.uid)}},
//                        { text: '查看', icon:'view', click:function(item){doView(treeNode.data.uid)}},
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


     function doView(id) {
        var url = "${ctx}/docAttachments/view.do";

        commonView(url, "docAttachmentsGrid");
    }

    function doDelete(){
        commonDelete("docAttachmentsGrid","${ctx}/docAttachments/delete.do",refreshRootNode);
    }

    function doAdd(){        
        var url = "${ctx}/docAttachments/init.do";
        commonAddOrUpdate(url,"docAttachmentsGrid",null,"docAttachmentsGridForm",{title:"新增文档附件",height:530,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/docAttachments/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"docAttachmentsGrid");
        }
        if(url){
            commonAddOrUpdate(url,"docAttachmentsGrid",null,"docAttachmentsForm",{title:"编辑文档附件",height:530,width:600});
        }
    }


</script>

