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
            <form id="sysMessageReceiveForm" action="">
                <table style="width: 100%">
                    <input type="hidden" value="" id="treeId" name="treeId" op="like" entity="t" />
                    <tr>
                        <td width="180">
                            消息编码:<input type="text" value="" class="table_input"  id="fdMessageCode" name="fdMessageCode" op="like" entity="t" style="width:100px"/>
                        </td>
                        <td width="180">
                            消息内容:<input type="text" value="" class="table_input"  id="fdMessageBody" name="fdMessageBody" op="like" entity="t" style="width:100px"/>
                        </td>
                        <td width="150" align="center">
                            状态:<input type="text" value="" class="table_input" id="name" name="fdMessageState" op="like" entity="t" dType="Number" style="width:100px"/>
                        </td>
                        <td width="100" align="right">接收日期 :</td>
                        <td width="100" align="center">
                            <input type="text" id="fdMessageReceiveTime1"  name="fdMessageReceiveTime"
                                   style="width: 100px;" op="greatAndEq" dType="date"  entity="t"
                                   class="Wdate" onFocus="WdatePicker({isShowClear:true,readOnly:true})"/>
                        </td>
                        <td width="10" align="center">
                            ~
                        </td>
                        <td align="left" width="110">
                            <input type="text" id="fdMessageReceiveTime2"  name="fdMessageReceiveTime"
                                   style="width: 100px;" op="lessAndEq" dType="date"  entity="t"
                                   class="Wdate" onFocus="WdatePicker({isShowClear:true,readOnly:true})"/>
                        </td>
                        <td>
                            &nbsp;<input type="button" value="查询" onclick="javascript:search('sysMessageReceiveGrid','sysMessageReceiveForm');"/>&nbsp;
                        </td>
                    </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">
                </div>
            </form>
        </div>
        <div id="sysMessageReceiveGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysMessageReceive/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        $.jui.parse();

//        $("#fdMessageReceiceTime").juiceDateEditor({initValue: '2011-03-07' });

        //查询框初始化
        $("#query").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
                { text: '查看', click: doView, icon: 'search' },
                { line: true },
                { text: '解析', click: doParse, icon:'modify' },
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };

        //grid
        $("#sysMessageReceiveGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', width:"5%",hide:false },
                { display: '消息编码', name: 'fdMessageCode',width:"10%"},
                { display: '消息内容', name: 'messageBody',width:"30%", showAsXml: true },
                { display: '状态', name: 'fdMessageState',width:"5%"  },
                { display: '描述', name: 'fdMessageStateInfo',width:"25%"  },
                { display: '消息处理时间', name: 'fdMessageReceiveTime',width:"15%" }
            ]
//        enabledEdit: true
        });
    });

    function doView(id) {
        var url = "${ctx}/sysMessageReceive/view.do";

        commonView(url, "sysMessageReceiveGrid");
    }

    function doDelete(){
        commonDelete("sysMessageReceiveGrid","${ctx}/sysMessageReceive/delete.do");
    }

    /**
     * 消息重新解析
     */
    function doParse(){
        var gridId = "sysMessageReceiveGrid";
        var url = "${ctx}/sysMessageReceive/doParse.do";

        var rows = getGridRows(gridId);
        var id = "";

        var sendCallback = function(data){
            if(!data.success) {
                window.top.$.juiceDialog.error(data.msg);
            } else {
                var grid = $.jui.get(gridId);
                if(grid){
                    grid.loadData(true);
                }
                window.top.$.juiceDialog.success('重新解析成功');
            }
        };

        if(rows.length>0){
            if(rows.length > 1) {
                window.top.$.juiceDialog.success('请选择一条消息进行重新解析!');
                return;
            }

            $.juiceDialog.confirm("您确定要重新解析选中的消息?",function(yes){
                if(yes){
                    id += rows[0].id;

                    submitFormData(null,url+"?id=" + id,null,"", sendCallback);
                }
            });
        }else{
            window.top.$.juiceDialog.warn('请选择要重新解析的消息!');
        }
    }
</script>