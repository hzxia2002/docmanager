<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
    <script charset="utf-8" src="${ctx}/js/kindeditor/kindeditor-all-min.js" type="text/javascript"></script>
    <script charset="utf-8" src="${ctx}/js/kindeditor/lang/zh_CN.js"></script>
</head>
<body>
<form:form id="cmsArticleEditForm" modelAttribute="bean" name="cmsArticleEditForm" action="${ctx}/cmsArticle/save.do" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" id="id" value="${bean.id}" />
    <div>
        <table border="0" cellspacing="1" width="100%" id="viewTable" class="inputTable">
            <tr class="inputTr">
                <td>
                    <span class="errors">*</span>所在分类:
                </td>
                <td class="container">
                    <input type="hidden" id="category" name="category" value="${bean.category.id}" />
                    ${bean.category.name}
                </td>
            </tr>
            <tr class="inputTr">
                <td style="height: 28px;">
                    <span class="errors">*</span>标题:
                </td>
                <td class="container">
                    <input type="text" name="title" id="title" value="${bean.title}" required="true" class="input_table" size="100" maxlength="200"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td>
                    <span class="errors">*</span>内容:
                </td>
                <td class="container" height="310" valign="top">
                    <textarea id="content" name="content" style="width:800px;height: 300px"><c:out value="${bean.content}" /></textarea>
                </td>
            </tr>
            <tr class="inputTr">
                <td>
                    文件:
                </td>
                <td class="container" valign="middle">
                    <input type="file" id="file" name="file">
                    <input type="file" id="file2" name="file2">
                    <input type="file" id="file3" name="file3">
                        <span id="fileSpan">
                        <c:if test="${not empty bean.attachPath}">
                            <a href="${ctx}${bean.attachPath}" target="_blank">附件1</a>
                            &nbsp;
                            <a href="javascript:void(0);" onclick="javascript:deleteFile('0');">删除</a>
                        </c:if>
                             </span>
                         <span id="fileSpan2">
                              <c:if test="${not empty bean.attachPath2}">
                                  <a href="${ctx}${bean.attachPath2}" target="_blank">附件2</a>
                                  &nbsp;
                                  <a href="javascript:void(0);" onclick="javascript:deleteFile('1');">删除</a>
                              </c:if>
                          </span>
                          <span id="fileSpan3">
                              <c:if test="${not empty bean.attachPath3}">
                                  <a href="${ctx}${bean.attachPath3}" target="_blank">附件3</a>
                                  &nbsp;
                                  <a href="javascript:void(0);" onclick="javascript:deleteFile('2');">删除</a>
                              </c:if>
                        </span>
                </td>
            </tr>
            <tr class="inputTr" height="28">
                <td>
                    接收人:
                    <input type="hidden" id="userIds" name="userIds" value="${bean.userIds}">
                    <input type="hidden" id="roleIds" name="roleIds" value="${bean.roleIds}">
                    <input type="hidden" id="roleNames" name="roleNames" value="${bean.roleNames}">
                    <input type="hidden" id="userNames" name="userNames" value="${bean.userNames}">
                </td>
                <td class="container" valign="middle">
                    <table width="100%">
                        <tr height="25">
                            <td rowspan="2" style="width: 70px;" align="center"><input type="button" id="selectReceiver" value="请选择"></td>
                            <td><b>已选角色：</b><span id="roleNamesView"   >${bean.roleNames}</span></td>
                        </tr>
                        <tr height="25">
                            <%--<td><b>已选用户：</b><input id="userNames" name="userNames" value="${bean.userNames}" readonly /></td>--%>
                            <td><b>已选用户：</b><span id="userNamesView" >${bean.userNames}</span></td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr class="inputTr" height="28">
                <td>
                    发布时间:
                </td>
                <td class="container">
                    <input type="text" id="publishDate" name="publishDate" class="Wdate" value="${bean.publishDate}" onFocus="WdatePicker({isShowClear:false,readOnly:true})" style="width: 120px"/>&nbsp;
                    是否置顶:
                    <form:checkbox path="isTop" />

                    是否有效:
                    <form:checkbox path="isValid" />

                    关键字：
                    <input type="text" name="keyword" id="keyword" value="${bean.keyword}" class="input_table" size="50" maxlength="100"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr" height="28">
                <td>
                    外部链接:
                </td>
                <td class="container">
                    <input type="text" name="linkUrl" id="linkUrl" value="${bean.linkUrl}" class="input_table" size="100" maxlength="500"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td colspan="2" valign="middle" align="center" height="30">
                    <input type="button" id="formCmsArticleButtonNew" value="新增" >&nbsp;
                    <input type="button" id="formCmsArticleButtonSave" value="保存" >&nbsp;
                    <input type="button" value="取消" onclick="doClose();">
                </td>
            </tr>
        </table>

    </div>
</form:form>
</body>

</html>

<script type="text/javascript">
    var editor;

    $(document).ready(function () {
        $.metadata.setType("attr", "validate");
        v = $('#cmsArticleEditForm').validate();

        <%--KindEditor.ready(function(K) {--%>
            <%--editor = K.create('#content'--%>
                    <%--,{--%>
                        <%--uploadJson : '${ctx}/js/kindeditor/jsp/upload_json.jsp',--%>
                        <%--fileManagerJson : '${ctx}/js/kindeditor/jsp/file_manager_json.jsp',--%>
                        <%--allowFileManager : true--%>
                    <%--}--%>
            <%--);--%>
        <%--});--%>

        $('#formCmsArticleButtonSave').click(function() {
            if (!v) {
                return;
            }

//            editor.sync();
            document.getElementById("cmsArticleEditForm").submit();
        });

        $('#formCmsArticleButtonNew').click(function() {
            if (!v) {
                return;
            }

            $("#id").val();
//            editor.sync();

            $("#cmsArticleEditForm").submit();
        });

        $('#selectReceiver').click(function() {
            var id = $("#id").val();
            var url = '${ctx}/cmsReceiver/initSelect.do';
            if(id) {
                url += "?articleId=" + id;
            }

            window.top.$.juiceDialog.open({ title: '接收人选择', name:'receiverSelect',width: 1024, height: 600, url: url, buttons: [
                { text: '确定', onclick: selectOK },
                { text: '取消', onclick: dialogCancel }
            ]
            });
            return false;

        });
    });

    function selectOK(item, dialog){
        var fn = dialog.frame.user_select || dialog.frame.window.user_select;
        var fn2 = dialog.frame.role_select || dialog.frame.window.role_select;
        var data = fn();

        var userIds = "";
        var userNames = "";

        if (data)
        {
            // 拼接选中的用户
            for(var i=0; i<data.length; i++) {
                var tmp = data[i];
                userIds += tmp["id"] + ",";
                userNames += tmp["displayName"] + ",";
            }

            if (userIds.length > 0) {
                userIds = userIds.substring(0,userIds.length - 1);
            }

            if (userNames.length > 0) {
                userNames = userNames.substring(0,userNames.length - 1);
            }

            $("#userIds").val(userIds);
            $("#userNames").val(userNames);
            $("#userNamesView").html(userNames);
        }

        var data2 = fn2();

        $("#roleIds").val(data2[0]);
        $("#roleNames").val(data2[1]);
        $("#roleNamesView").html(data2[1]);

        dialog.close();
    }

    function dialogCancel(item, dialog){
        dialog.close();
    }

    function deleteFile(delType) {
        var id = $("#id").val();

        $.ajax({
            type: 'POST',
            url: '${ctx}/cmsArticle/deleteFile.do',
            data: {id:id, delType:delType},
            success: function(data){
                showInfoMsg(data.msg,null);
                if(delType == '0') {
                    $("#fileSpan").html("");
                } else if(delType == '1') {
                    $("#fileSpan2").html("");
                } else if(delType == '2') {
                    $("#fileSpan3").html("");
                }
            },
            dataType: 'json'
        });
    }

    var dialogJuiId = '<%=request.getParameter("dialogJuiId")%>';

    function doClose() {
        parent.tab.removeTabItem("cmsArticleNew");
//        var obj = window.top.$.jui.get(dialogJuiId);
//        if(obj != null) {
//            obj.close();
//        }
    }
</script>