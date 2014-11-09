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
            <form id="cmsTaskInfoForm" action="">
                <table style="width: 100%">
                    <input type="hidden" value="" id="treeId" name="treeId" op="like" entity="t" />
                    <tr>

                        <td width="200" align="center">
                            是否已读:<select id="isRead" name="isRead" op="eq" entity="t">
                            <option value="">全部</option>
                            <option value="1">未读</option>
                            <option value="0">已读</option>
                        </select>
                        <td>
                            &nbsp;<input type="button" value="查询" onclick="javascript:search('cmsTaskInfoGrid','cmsTaskInfoForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="cmsTaskInfoGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/cmsTask/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#mainLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("cmsTaskInfoGrid").setWidth(width);
            $.jui.get("query").setWidth(width);
        }
        });
        //查询框初始化
        $("#query").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
//            align:"right",
//            items: [
//                { text: '增加', click: doAdd, icon: 'add' },
//                { line: true },
//                { text: '修改', click: doEdit, icon: 'modify' },
//                { line: true },
//                { text: '删除', click: doDelete, icon:'delete' }
//            ]
        };

        //grid
        $("#cmsTaskInfoGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', width: 50,hide:true },
                { display: '公文编码', name: 'article',width:"50",hide:true},
                { display: '当前用户', name: 'user',width:"50",hide:true},
                { display: '公文编码', name: 'article',width:"10%",hide:true},
                { display: '建议', name: 'handlingSuggestion',width:"15%"},
                { display: '处理结果', name: 'handlingResult',width:"10%" },
                { display: '是否已读', name: 'isRead',width:"5%", render: statusFormatter},
                { display: '读取时间', name: 'readTime',width:"15%" },
                { display: '发布人', name: 'createUser',width:"15%"  },
                { display: '发布时间', name: 'createTime',width:"15%" }
            ]
//        enabledEdit: true
        });
    });

    function getSelected() {
        var value = this.getValue();
        $(this).val(value);
    }
    function statusFormatter(item,index,value){
        if(value=="1"){
            return "未读";
        }else if(value=="0"){
            return "已读";
        }
    }

    function doView(id) {
        openWindow("sysMessageInfoWindow", "查看", "${ctx}/sysMessageInfo/view.do?id=" + id, true, "cmsTaskInfoGrid");
    }

    function doDelete(){
        commonDelete("cmsTaskInfoGrid","${ctx}/sysMessageInfo/delete.do");
    }

    function doAdd(){
        commonAddOrUpdate("${ctx}/sysMessageInfo/init.do","cmsTaskInfoGrid",null,"sysMessageInfoEditForm",{title:"新增消息信息",height:350,width:600});
    }

    function doEdit(){
        var url = "${ctx}/sysMessageInfo/init.do";
        url = initUrl(url,"cmsTaskInfoGrid");
        if(url){
            commonAddOrUpdate(url,"cmsTaskInfoGrid",null,"sysMessageInfoEditForm",{title:"修改消息信息",height:350,width:600});
        }
    }

</script>