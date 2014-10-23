<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="sysParameterEditForm" modelAttribute="bean" name="sysParameterEditForm" action="${ctx}/sysParameter/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}" />
    <div>
        <table border="0" cellspacing="1" width="100%" class="inputTable">

            <tr class="inputTr">
                <td  align="right">
                    <span class="needInfo">*</span>参数代码:
                </td>
                <td  class="container">
                    <input type="text" name="code" value="${bean.code}" class="table_input" validate="{required:true}" maxlength="50"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    <span class="needInfo">*</span>参数名称:
                </td>
                <td  class="container">
                    <input type="text" name="name" value="${bean.name}" class="table_input" validate="{required:true}" maxlength="50"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    参数值:
                </td>
                <td  class="container">
                    <input type="text" name="value" value="${bean.value}" class="table_input" maxlength="200"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    约束:
                </td>
                <td  class="container">
                    <input type="text" name="constraint" value="${bean.constraint}" class="table_input" maxlength="2000"/>&nbsp;
                </td>
            </tr>
            <tr class="inputTr">
                <td  align="right">
                    长参数:
                </td>
                <td  class="container">
                    <textarea rows="4" cols="60" class="textarea_table" name="clobvalue" id="clobvalue">${bean.clobvalue}</textarea>
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
        v = $('#sysParameterEditForm').validate();
    });
</script>