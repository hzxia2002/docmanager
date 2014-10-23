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
            <form id="sysMessageSendForm" action="">
                <table style="width: 100%">
                    <input type="hidden" value="" id="treeId" name="treeId" op="like" entity="t" />
                    <tr>
                        <td width="200">
                            消息编码：<input type="text" value="" class="table_input"  id="fdMessageCode" name="fdMessageCode" op="like" entity="t" style="width:100px"/>
                        </td>
                        <td width="200">
                            消息内容：<input type="text" value="" class="table_input"  id="fdMessageBody" name="fdMessageBody" op="like" entity="t" style="width:100px"/>
                        </td>
                        <td width="150" align="center">
                            状态：<input type="text" value="" class="table_input" id="name" name="fdMessageHandlerName" op="like" entity="t" dType="Number" style="width:100px"/>
                        </td>
                        <td width="80" nowrap="true" align="right">
                            发送日期：
                        </td>
                        <td width="100" align="center">
                            <input type="text" id="fdMessageSendTime1"  name="fdMessageSendTime"
                                   style="width: 100px;" op="greatAndEq" dType="date"  entity="t"
                                   class="Wdate" onFocus="WdatePicker({isShowClear:true,readOnly:true})"/>
                        </td>
                        <td width="10" align="center">
                            ~
                        </td>
                        <td align="left" width="110">
                            <input type="text" id="fdMessageSendTime2"  name="fdMessageSendTime"
                                   style="width: 100px;" op="lessAndEq" dType="date"  entity="t"
                                   class="Wdate" onFocus="WdatePicker({isShowClear:true,readOnly:true})"/>
                        </td>
                        <td>
                            &nbsp;<input type="button" value="查询" onclick="javascript:search('sysMessageSendGrid','sysMessageSendForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">
                </div>
            </form>
        </div>
        <div id="sysMessageSendGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysMessageSend/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
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
                { text: '查看', click: doView, icon: 'search' },
                { line: true },
                { text: '重发', click: doResend, icon:'modify' },
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };

        //grid
        $("#sysMessageSendGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', width: 50,hide:true },
                { display: '消息编码', name: 'fdMessageCode',width:"10%"},
                { display: '内容', name: 'messageBody',width:"20%",showAsXml:true},
                { display: '发送时间', name: 'fdMessageSendTime',width:"15%"},
                { display: '状态', name: 'fdMessageState',width:"5%"  },
                { display: '状态描述', name: 'fdMessageStateInfo',width:"15%"  },
                { display: '处理时间', name: 'fdMessageOperateTime',width:"15%" },
                { display: '处理信息', name: 'fdMessageOperateInfo',width:"25%" }
            ]
//        enabledEdit: true
        });
    });

    function doView(id) {
        var url = "${ctx}/sysMessageSend/view.do";

        commonView(url, "sysMessageSendGrid");
    }

    function doDelete(){
        commonDelete("sysMessageSendGrid","${ctx}/sysMessageSend/delete.do");
    }

    function doResend(){
        var gridId = "sysMessageSendGrid";
        var url = "${ctx}/sysMessageSend/resend.do";

        var rows = getGridRows(gridId);
        var id = "";

        var sendCallback = function(){
            var grid = $.jui.get(gridId);
            if(grid){
                grid.loadData(true);
            }
            window.top.$.juiceDialog.success('重发成功');
        };

        if(rows.length>0){
            $.juiceDialog.confirm("您确定要重发选中的消息?",function(yes){
                if(yes){
                    for(var i=0;i<rows.length;i++){
                        id += rows[i].id + ",";
                    }

                    submitFormData(null,url+"?ids=" + id,null,"", sendCallback);
                }
            }) ;
        }else{
            window.top.$.juiceDialog.warn('请选择要重发的消息!');
        }
    }
</script>