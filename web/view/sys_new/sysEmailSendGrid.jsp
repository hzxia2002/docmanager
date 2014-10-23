<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="sysEmailSendLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="center">
        <div id="sysEmailSendQuery" title="查询条件"  icon="search">
            <form id="sysEmailSendForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="20%" nowrap="nowrap" align="left">
                            接收人:
                            <input type="text" id="receiver" name="receiver" value="" class="table_input" op="like" entity="t" dtype="String" style="width: 200px;"/>
                        </td>
                        <td  width="50" nowrap="nowrap" align="left">
                            <input type="text" id="sendTime" jui="label:'发送时间'" class="jui-dateEditor" name="sendTime" value="" op="greatAndEq" entity="t" dtype="String"/>
                        </td>
                        <td nowrap="nowrap" align="center" width="10px">
                            ~
                        </td>
                        <td  nowrap="nowrap" align="left" width="50" >
                            <input type="text"  id="sendTime1" name="sendTime" class="jui-dateEditor" value="" op="lessAndEq" entity="t" dtype="String"/>
                        </td>
                        <td>
                            <input type="button" value="查询" onclick="javascript:search('sysEmailSendGrid','sysEmailSendForm');"/>&nbsp;
                            <%--<a href="javascript:void(0);" onclick="toggleDiv('advanced_condition', '0')">高级查询</a>--%>
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="sysEmailSendGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysEmailSend/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        $.jui.parse();
        //布局
        $("#sysEmailSendLayout").juiceLayout({onAfterResize:function(width){
            $.jui.get("sysEmailSendGrid").setWidth(width);
            $.jui.get("sysEmailSendQuery").setWidth(width);
        }
        });
        //查询框初始化
        $("#sysEmailSendQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
                { text: '发送邮件', click: doAdd, icon: 'add' },
                { line: true },
                { text: '查看', click: doEdit, icon: 'view' },
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };
        //grid
        $("#sysEmailSendGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                {display: '标识', name: 'id', width: 50,hide:true },
                {display: '收件人', name: 'user.person.name',width:"12%"},
                {display: '收件箱', name: 'receiver', width: "12%"},
                {display: '邮件内容', name: 'content', width: "36%"},
                {display: '发送时间', name: 'sendTime', width: "12%"},
                {display: '发送结果', name: 'sendResult', width: "12%"},
                { display: '发送状态', name: 'sendStatus',width: "12%"}
            ]
//        enabledEdit: true
        });
        //创建树

    });

    function doView(id) {
        var url = "${ctx}/sysEmailSend/view.do";

        commonView(url, "sysEmailSendGrid",{height:350,width:600});
    }

    function doDelete(){
        commonDelete("sysEmailSendGrid","${ctx}/sysEmailSend/delete.do",refreshRootNode);
    }

    function doAdd(){
        var url = "${ctx}/sysEmailSend/init.do";
        commonAddOrUpdate(url,"sysEmailSendGrid",null,"sysEmailSendForm",{title:"新增Email发送记录",height:400,width:535});
    }

    function doEdit(id){
        var url = "${ctx}/sysEmailSend/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"sysEmailSendGrid");
        }
        if(url){
            commonAddOrUpdate(url,"sysEmailSendGrid",null,"sysEmailSendForm",{title:"编辑Email发送记录",height:530,width:600});
        }
    }


</script>

