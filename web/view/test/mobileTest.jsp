<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
    <link href="${ctx}/skin/xmlTree.css" rel="stylesheet" type="text/css" />
    <script language="javascript" src="${ctx}/js/jquery/jquery.xmlTree.js"></script>
</head>
<body>
<form id="mobileTestForm" name="mobileTestForm" action="${ctx}/mobile/dispatch.do" method="post">
    <table border="0" cellspacing="1" width="100%" class="inputTable">
                <tr class="inputTr">
                    <td  align="right" width="15%">
                        消息类型<font color="red">*</font>
                    </td>
                    <td  class="container" colspan="2">
                        <input type="text"
                               url="${ctx}/sysMessageInfo/getList.do?fdMessageType=1"
                               textField="fdMessageDesc"
                               valueField="fdMessageCode"
                               valueFieldID="messageType"
                               class= "jui-comboBox"
                               initText = "请选择消息类型"
                               joinStr = "-"
                               initValue=""
                                width="250"/>
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        发送消息<font color="red">*</font>
                    </td>
                    <td  class="container">
                        <textarea rows="15" cols="80" id="msg" name="msg"></textarea>
                    </td>
                    <td align="left">
                        <b>示例消息：</b><br/>
                        &lt;?xml version="1.0" encoding="utf-8"?&gt;<br/>
                        &lt;messages&gt;<br/>
                        &lt;message&gt;<br/>
                        &lt;messageMethod&gt;query&lt;/messageMethod&gt;<br/>
                        &lt;messageType&gt;01&lt;/messageType&gt;<br/>
                        &lt;icomboId&gt;01&lt;/icomboId&gt;<br/>
                        &lt;messageTime&gt;2013-01-05 21:14:32&lt;/messageTime&gt;<br/>
                        &lt;clientId&gt;01-001-01&lt;/clientId&gt;<br/>
                        &lt;messageBody&gt;<br/>
                        &lt;equipId&gt;21&lt;/equipId&gt;<br/>
                        &lt;temperature&gt;21&lt;/temperature&gt;<br/>
                        &lt;humidity&gt;21&lt;/humidity&gt;<br/>
                        &lt;CO2&gt;12&lt;/CO2&gt;<br/>
                        &lt;formaldehyde>12&lt;/formaldehyde&gt;<br/>
                        &lt;/messageBody&gt;<br/>
                        &lt;/message&gt;<br/>
                        &lt;/messages&gt;<br/>
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        返回消息
                    </td>
                    <td  class="container">
                        <textarea rows="10" cols="80" id="returnMsg" name="returnMsg"></textarea>
                    </td>
                    <td>
                        <div id="tree"></div>
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                    </td>
                    <td  class="container" colspan="2">
                        <input id="submitButton" type="button" value="提交" class="btn_Ok">
                        <input id="resetButton" type="reset" value="重置" class="btn_Del">
                    </td>
                </tr>
    </table>
</form>
</body>

</html>

<script type="text/javascript">
    $(document).ready(function () {
        $.jui.parse();

        $("#submitButton").click(function(){
            var messageType = $("#messageType").val();
            var msg = $("#msg").val();

            if(messageType == null || messageType == '') {
                alert('请输入消息类型!');
                return;
            }

            if(msg == null || msg == '') {
                alert('请输入消息体!');
                return;
            }

            var sendData = {"messageType": messageType, "msg": msg};

            $.ajax({
                type: 'POST',
                url: "${ctx}/mobile/dispatch.do",
                data: sendData,
//                dataType: 'json',
                success: function(data) {
                    $("#returnMsg").val(data);

                    var xmlDom = parseXML(data);   //通过字符串方式解析
                    var node = xmlDom.documentElement;
                    var html = getNode(node);
                    $("#tree").html(html);
                    $('ul:first').xmlTree({
                        expanded:'li:first'
                    });

                    $("li a").each(function () {
                        var valUlLiA = $(this).text();
                        if (valUlLiA=="") {
                            $(this).remove();
                        }
                    });
                },
                error: function(xmlR, status, e) {
                    $("#returnMsg").val(xmlR.responseText);
                }
            });
        });
    });
</script>