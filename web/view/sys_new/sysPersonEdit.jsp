<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>

<script type="text/javascript">
    $(document).ready(function () {
        /**
         * 进行校核绑定
         */
        $("#deptName").juiceComboBox({
            width: 180,
            selectBoxWidth: 200,
            selectBoxHeight: 200,
            valueFieldID: 'deptId',
            valueField: 'uid',
            textField: 'text',
            treeLeafOnly:true,
            valuePrefix:"SysDept_",
            initValue:"${bean.deptId}",
            initText:"${bean.deptName}",
            tree: { url: '${ctx}/sysDept/tree.do', checkbox: false }
        });


        $.metadata.setType("attr", "validate");
        v = $('#sysPersonEditForm').validate();

    });
</script>

    <form:form id="sysPersonEditForm" modelAttribute="bean" name="sysPersonEditForm" action="${ctx}/sysPerson/save.do" method="post">
	<input type="hidden" name="id" value="${bean.id}" />
        <div>
            <table border="0" cellspacing="1" width="100%" id="viewTable" class="inputTable">
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        <span class="needInfo">*</span>工号:
                    </td>
                    <td  class="container">
                        <input type="text" name="code" value="${bean.code}"  class="table_input" validate="{required:true}"/>&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        <span class="needInfo">*</span>姓名:
                    </td>
                    <td  class="container">
                        <input type="text" name="name" value="${bean.name}"  class="table_input" validate="{required:true}"/>&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        性别:
                    </td>
                    <td  class="container">
                        <form:radiobutton path="sex" value="true"></form:radiobutton>男
                        <form:radiobutton path="sex" value="false"></form:radiobutton>女
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        选择单位:
                    </td>
                    <td class="container">
                        <%--<input type="text" value="${bean.deptName}${bean.workSiteName}" id="popTreeValue" name="popTreeValue" readonly="true" class="table_input"/>--%>
                        <input type="text"  id="deptName" />
                        <%--<input type="button" name="deptSelect" value="单位" onclick="new PopTree({url:'${ctx}/sysDept/tree.do',title:'请选择集团',targetId:'deptId',targetValueId:'popTreeValue', modal:true, openRoot:true});"/>--%>
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        职务:
                    </td>
                    <td  class="container">
                        <input type="text" name="position" value="${bean.position}" class="table_input" title="选择公司后，职务信息才会保存!"/>&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        身份证号:
                    </td>
                    <td  class="container">
                        <input type="text" name="card" value="${bean.card}" class="table_input" style="width: 180px" maxlength="18"/>&nbsp;
                    </td>
                </tr>
                <%--<tr class="inputTr">--%>
                    <%--<td  class="csslabel">--%>
                        <%--出生年月:--%>
                    <%--</td>--%>
                    <%--<td  class="container">--%>
						<%--<input type="text" name="bornDate" class="Wdate" value="${bean.bornDate}" onFocus="WdatePicker({isShowClear:false,readOnly:true})"/>&nbsp;--%>

                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr class="inputTr">--%>
                    <%--<td  class="csslabel">--%>
                        <%--籍贯:--%>
                    <%--</td>--%>
                    <%--<td  class="container">--%>
                        <%--<input type="text" name="bornPlace" value="${bean.bornPlace}" class="table_input"/>&nbsp;--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        移动电话:
                    </td>
                    <td  class="container">
                        <input type="text" name="mobile" value="${bean.mobile}" class="table_input"/>&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        固定电话:
                    </td>
                    <td  class="container">
                        <input type="text" name="officeTel" value="${bean.officeTel}" class="table_input"/>&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        传真:
                    </td>
                    <td  class="container">
                        <input type="text" name="faxTel" value="${bean.faxTel}" class="table_input"/>&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        邮件:
                    </td>
                    <td  class="container">
                        <input type="text" name="email" value="${bean.email}"  validate="{email:true}" class="table_input"/>&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        邮政编码:
                    </td>
                    <td  class="container">
                        <input type="text" name="zipcode" value="${bean.zipcode}" class="table_input" style="width: 60px" maxlength="6"/>&nbsp;
                    </td>
                </tr>
                <%--<tr class="inputTr">--%>
                    <%--<td  class="csslabel">--%>
                        <%--学历:--%>
                    <%--</td>--%>
                    <%--<td  class="container">--%>
                        <%--<input type="text" name="education" value="${bean.education}" class="table_input"/>&nbsp;--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr class="inputTr">--%>
                    <%--<td  class="csslabel">--%>
                        <%--工作年限:--%>
                    <%--</td>--%>
                    <%--<td  class="container">--%>
                        <%--<input type="text" name="workYear" value="${bean.workYear}" class="table_input" style="width: 60px" maxlength="3"/>&nbsp;--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        MSN:
                    </td>
                    <td  class="container">
                        <input type="text" name="msnCode" value="${bean.msnCode}" class="table_input"/>&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        QQ:
                    </td>
                    <td  class="container">
                        <input type="text" name="qqCode" value="${bean.qqCode}" class="table_input"/>&nbsp;
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        负责人:
                    </td>
                    <td  class="container">
                        <form:checkbox path="isManager"/>
                    </td>
                </tr>
                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        是否有效:
                    </td>
                    <td class="container">
                        <form:checkbox path="isValid"/>
                    </td>
                </tr>

                <tr class="inputTr">
                    <td  class="csslabel" align="right">
                        备注:
                    </td>
                    <td  class="container">
                        <textarea rows="4" cols="60" class="textarea_table" name="memo" id="memo">${bean.memo}</textarea>
                    </td>
                </tr>
                <%--<tr class="inputTr">--%>
                    <%--<td  class="csslabel">--%>
                        <%--创建时间/创建人:--%>
                    <%--</td>--%>
                    <%--<td  class="container">--%>
                        <%--${bean.createTime}/${bean.createUser}--%>
                    <%--</td>--%>
                <%--</tr>--%>
                <%--<tr class="inputTr">--%>
                    <%--<td  class="csslabel">--%>
                        <%--更新时间/更新人:--%>
                    <%--</td>--%>
                    <%--<td  class="container">--%>
                        <%--${bean.updateTime}/${bean.updateUser}--%>
                    <%--</td>--%>
                <%--</tr>--%>
            </table>
      
        </div>
    </form:form>
</body>

</html>