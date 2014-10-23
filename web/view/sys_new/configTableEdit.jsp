<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="configTableEditForm" modelAttribute="bean" name="configTableEditForm" action="${ctx}/configTable/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <div>
        <table border="0" cellspacing="1" width="100%"  class="inputTable">

            <tr class="inputTr">
                <td  align="right">
                    <span class="needInfo">*</span>表名:
                </td>
                <td  class="container">
                    <input type="text" name="tableName" value="${bean.tableName}" class="table_input" maxlength="100" validate="{required:true}"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    <span class="needInfo">*</span>Class名:
                </td>
                <td  class="container">
                    <input type="text" name="className" value="${bean.className}" class="table_input" maxlength="100" validate="{required:true}" />&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    扩展XML:
                </td>
                <td  class="container">
                    <textarea rows="4" cols="60" class="textarea_table" name="extendXml" id="extendXml">${bean.extendXml}</textarea>
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    记录日志:
                </td>
                <td  class="container">
                    <form:checkbox path="isLog"/>
                </td>
            </tr>
        </table>
    </div>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#configTableEditForm').validate();
    });
</script>