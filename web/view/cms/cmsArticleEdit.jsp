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
    <input type="hidden" name="id" value="${bean.id}" />
    <div>
        <table border="0" cellspacing="1" width="100%" id="viewTable">
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
                <td>
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
                <td class="container" height="340" valign="top">
                    <textarea id="content" name="content" style="width:800px;height: 340px"><c:out value="${bean.content}" /></textarea>
                </td>
            </tr>
            <tr class="inputTr">
                <td>
                    图片:
                </td>
                <td class="container" valign="middle">
                    <input type="file" id="image" name="image">
                        <span id="picSpan">
                        <c:if test="${not empty bean.thumbPath}">
                            <a href="${ctx}${bean.thumbPath}" target="_blank">查看</a>&nbsp;
                            <a href="javascript:void(0);" onclick="javascript:deleteFile('0');">删除</a>
                        </c:if>
                        </span>
                    &nbsp;
                    <span class="errors">${msg}</span>
                    文件:<input type="file" id="file" name="file">
                        <span id="fileSpan">
                        <c:if test="${not empty bean.attachPath}">
                            <a href="${ctx}${bean.attachPath}" target="_blank">附件</a>
                            &nbsp;
                            <a href="javascript:void(0);" onclick="javascript:deleteFile('1');">删除</a>
                        </c:if>
                        </span>
                </td>
            </tr>
            <tr class="inputTr">
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
            <tr class="inputTr">
                <td>
                    外部链接:
                </td>
                <td class="container">
                    <input type="text" name="linkUrl" id="linkUrl" value="${bean.linkUrl}" class="input_table" size="100" maxlength="500"/>&nbsp;
                </td>
            </tr>
            <tr>
                <td colspan="2" valign="middle" align="center">
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

        KindEditor.ready(function(K) {
            editor = K.create('#content'
                    ,{
                        uploadJson : '${ctx}/js/kindeditor/jsp/upload_json.jsp',
                        fileManagerJson : '${ctx}/js/kindeditor/jsp/file_manager_json.jsp',
                        allowFileManager : true
                    }
            );
        });

        $('#formCmsArticleButtonSave').click(function() {
            if (!v) {
                return;
            }

            editor.sync();
            document.getElementById("cmsArticleEditForm").submit();
        });

        $('#formCmsArticleButtonNew').click(function() {
            if (!v) {
                return;
            }

            $("#id").val();
            editor.sync();

            $("#cmsArticleEditForm").submit();
        });
    });

    function deleteFile(delType) {
        var id = $("#id").val();

        $.ajax({
            type: 'POST',
            url: '${ctx}/cmsArticle/deleteFile.do',
            data: {id:id, delType:delType},
            success: function(data){
                showInfoMsg(data.msg,null);
                if(delType == '0') {
                    $("#picSpan").html("");
                } else if(delType == '1') {
                    $("#fileSpan").html("");
                }
            },
            dataType: 'json'
        });
    }

    var dialogJuiId = '<%=request.getParameter("dialogJuiId")%>';

    function doClose() {
        var obj = window.top.$.jui.get(dialogJuiId);

        if(obj != null) {
            obj.close();
        }
    }
</script>