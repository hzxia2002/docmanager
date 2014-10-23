<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="sysDeptEditForm" modelAttribute="bean" name="sysDeptEditForm" action="${ctx}/sysDept/save.do" method="post">
    <input type="hidden" name="id" value="${bean.id}">
    <table border="0" cellspacing="1" width="100%" class="inputTable">
        <tr class="inputTr">
            <td  align="right">
                上级单位/部门:
            </td>
            <td  class="container">
                <input type="hidden" name="parent" value="${bean.parent.id}" />
                <input type="text" name="parent_name" value="${bean.parent.name}" readonly="true" class="table_input" disabled="true"/>&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                <span class="needInfo">*</span>单位代码:
            </td>
            <td  class="container">
                <input type="text" name="code" value="${bean.code}" class="table_input" validate="{required:true}"/>&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                <span class="needInfo">*</span>单位名称:
            </td>
            <td  class="container">
                <input type="text" name="name" value="${bean.name}" class="table_input" validate="{required:true}"/>&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                <span class="needInfo">*</span>简称:
            </td>
            <td  class="container">
                <input type="text" name="shortName" value="${bean.shortName}" class="table_input" required="true"/>&nbsp;
            </td>
        </tr>

        <tr class="inputTr">
            <td  align="right">
                <span class="needInfo">*</span>机构代码证:
            </td>
            <td  class="container">
                <input type="text" name="cardNo" value="${bean.cardNo}" class="table_input" required="true"/>&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                公司类型:
            </td>
            <td  class="container">
                <select name="type">
                    <c:forEach items="${companyTypeItems}" var="item">
                        <option value="${item.key}" <c:if test="${(item.key==bean.type)}">selected="true"</c:if>>${item.value}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                联系人:
            </td>
            <td  class="container">
                <input type="text" name="linker" value="${bean.linker}" class="table_input" style="width: 140"/>&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                电话:
            </td>
            <td  class="container">
                <input type="text" name="telephone" value="${bean.telephone}" class="table_input" style="width: 140px" />&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                email:
            </td>
            <td  class="container">
                <input type="text" name="email" value="${bean.email}" class="table_input"   validate="{email:true}"/>&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                网址:
            </td>
            <td  class="container">
                <input type="text" name="netSite" value="${bean.netSite}" class="table_input" validate="{vUrl:true}"/>&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                传真:
            </td>
            <td  class="container">
                <input type="text" name="fax" value="${bean.fax}" class="table_input" style="width: 140px"/>&nbsp;
            </td>
        </tr>

        <tr class="inputTr">
            <td  align="right">
                单位标志:
            </td>
            <td  class="container">
                <input type="text" name="isTag" value="${bean.isTag}" class="table_input"/>&nbsp;
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                是否有效:
            </td>
            <td  class="container">
                <form:checkbox path="isValid"/>
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                备注:
            </td>
            <td  class="container">
                <textarea rows="4" cols="60" class="textarea_table" name="memo" id="memo">${bean.memo}</textarea>
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                创建时间/创建人:
            </td>
            <td  class="container">
                    ${bean.createTime}/${bean.createUser}
            </td>
        </tr>
        <tr class="inputTr">
            <td  align="right">
                更新时间/更新人:
            </td>
            <td  class="container">
                    ${bean.updateTime}/${bean.updateUser}
            </td>
        </tr>

    </table>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(function(){
        $.metadata.setType("attr", "validate");
        v = $('#sysDeptEditForm').validate();
    });
</script>