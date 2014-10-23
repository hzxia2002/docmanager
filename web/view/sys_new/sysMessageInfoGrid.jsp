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
            <form id="sysMessageInfoForm" action="">
                <table style="width: 100%">
                    <input type="hidden" value="" id="treeId" name="treeId" op="like" entity="t" />
                    <tr>
                        <td width="30%">
                            消息编码：<input type="text" value="" class="table_input"  id="fdMessageCode" name="fdMessageCode" op="like" entity="t"/>
                        </td>
                        <td width="30%" align="center">
                            处理器名称：<input type="text" value="" class="table_input" id="name" name="fdMessageHandlerName" op="like" entity="t" dType="Number"/>
                        </td>
                        <td>
                            &nbsp;<input type="button" value="查询" onclick="javascript:search('sysMessageInfoGrid','sysMessageInfoForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="sysMessageInfoGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysMessageInfo/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#mainLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("sysMessageInfoGrid").setWidth(width);
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
        $("#sysMessageInfoGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', width: 50,hide:true },
                { display: '消息编码', name: 'fdMessageCode',width:"10%"},
                { display: '消息类型', name: 'fdMessageType',width:"5%", render: typeFormatter},
                { display: '处理器名称', name: 'fdMessageHandlerName',width:"20%" },
                { display: '状态', name: 'fdMessageState',width:"5%", render: statusFormatter},
                { display: '描述', name: 'fdMessageDesc',width:"20%"  },
                { display: '处理程序名称名', name: 'fdBusinessModule',width:"25%" },
                { display: '方法名', name: 'fdBusinessFunction',width:"15%" }
            ]
//        enabledEdit: true
        });

    });

    function typeFormatter(item,index,value){
        if(value=="0"){
            return "MQTT";
        }else if(value=="1"){
            return "HTTP";
        }
    }

    function statusFormatter(item,index,value){
        if(value=="1"){
            return "有效";
        }else if(value=="0"){
            return "无效";
        }
    }

    function doView(id) {
        openWindow("sysMessageInfoWindow", "查看", "${ctx}/sysMessageInfo/view.do?id=" + id, true, "sysMessageInfoGrid");
    }

    function doDelete(){
        commonDelete("sysMessageInfoGrid","${ctx}/sysMessageInfo/delete.do");
    }

    function doAdd(){
        commonAddOrUpdate("${ctx}/sysMessageInfo/init.do","sysMessageInfoGrid",null,"sysMessageInfoEditForm",{title:"新增消息信息",height:350,width:600});
    }

    function doEdit(){
        var url = "${ctx}/sysMessageInfo/init.do";
        url = initUrl(url,"sysMessageInfoGrid");
        if(url){
            commonAddOrUpdate(url,"sysMessageInfoGrid",null,"sysMessageInfoEditForm",{title:"修改消息信息",height:350,width:600});
        }
    }

</script>