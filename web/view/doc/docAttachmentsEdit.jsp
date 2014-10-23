<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="docAttachmentsEditForm" modelAttribute="bean" name="docAttachmentsEditForm" action="${ctx}/docAttachments/save.do" method="post">
   <input type="hidden" name="id" value="${bean.id}" />
    <table border="0" cellspacing="1" width="100%" class="inputTable">

                <tr class="inputTr">
                    <td  align="right">
                        文档ID:
                    </td>
                    <td  class="container">
                        <input type="text" name="doc" value="${bean.doc}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        文件名称:
                    </td>
                    <td  class="container">
                        <input type="text" name="name" value="${bean.name}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        文件原始名称:
                    </td>
                    <td  class="container">
                        <input type="text" name="orginName" value="${bean.orginName}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        上传时间:
                    </td>
                    <td  class="container">
                        <input type="text" name="uploadDate" value="${bean.uploadDate}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        备注:
                    </td>
                    <td  class="container">
                        <input type="text" name="remark" value="${bean.remark}" />&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  align="right">
                        :
                    </td>
                    <td  class="container">
                        <input type="text" name="filePath" value="${bean.filePath}" />&nbsp;
                    </td>
                </tr>
    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#docAttachmentsEditForm').validate();
    });
</script>