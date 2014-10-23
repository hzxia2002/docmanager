<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.comet.system.domain.SysUser" %>
<%@ page import="java.util.Set" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="com.comet.system.domain.SysRole" %>
<%@ page import="com.comet.core.utils.CollectionUtil" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.comet.system.domain.SysUserRole" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="sysUserEditForm" modelAttribute="bean" name="sysUserEditForm" action="${ctx}/sysUser/save.do" method="post">
<input type="hidden" name="id" id="id" value="${bean.id}" />
<input type="hidden" name="person.id" id="person.id" value="${bean.person.id}" />
<div>
<table border="0" cellspacing="1" width="100%" id="viewTable" class="inputTable">
<tr class="inputTr">
    <td  align="right">
        <span class="needInfo">*</span>登录名:
    </td>
    <td  class="container" colspan="3">
        <input type="text" name="loginName" value="${bean.loginName}" class="table_input" validate="{required:true}" style="width:180px"/>&nbsp;
    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        密码:
    </td>
    <td  class="container">
        <input type="password" name="password" id="password" value="${bean.password}" class="table_input" style="width:180px"/>
    </td>
    <td  align="right">
        密码确认:
    </td>
    <td  class="container">
        <input type="password" name="password2" value="${bean.password2}" class="table_input" validType="equalTo['#password']" style="width:180px"/>
    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        <span class="needInfo">*</span>真实姓名:
    </td>
    <td  class="container">
        <input type="text" name="displayName" value="${bean.displayName}" class="table_input" validate="{required:true}" style="width:180px"/>&nbsp;
    </td>
    <td  align="right">
        性别:
    </td>
    <td  class="container">
        <form:radiobutton path="person.sex" value="true"></form:radiobutton>男
        <form:radiobutton path="person.sex" value="false"></form:radiobutton>女
    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        所在公司:
    </td>
    <td class="container">
        <input type="text" value="${bean.deptName}" id="popTreeValue" name="popTreeValue" readonly="true" class="table_input"/>
        <input type="hidden" value="${bean.deptId}" id="deptId" name="deptId"/>
        <input type="button" name="deptSelect" value="选择" onclick="new PopTree({url:'${ctx}/sysDept/tree.do',title:'请选择公司',targetId:'deptId',targetValueId:'popTreeValue', modal:true, openRoot:true});"/>
    </td>
    <td  align="right">
        身份证号:
    </td>
    <td  class="container">
        <input type="text" name="person.card" value="${bean.person.card}" class="table_input" style="width: 180px" maxlength="18"/>&nbsp;
    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        移动电话:
    </td>
    <td  class="container">
        <input type="text" name="person.mobile" value="${bean.person.mobile}" class="table_input"/>&nbsp;
    </td>
    <td  align="right">
        固定电话:
    </td>
    <td  class="container">
        <input type="text" name="person.officeTel" value="${bean.person.officeTel}" class="table_input"/>&nbsp;
    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        传真:
    </td>
    <td  class="container">
        <input type="text" name="person.faxTel" value="${bean.person.faxTel}" class="table_input"/>&nbsp;
    </td>
    <td  align="right">
        邮件:
    </td>
    <td  class="container">
        <input type="text" name="person.email" value="${bean.person.email}" validType="email" class="table_input"/>&nbsp;
    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        联系地址:
    </td>
    <td  class="container">
        <input type="text" name="person.address" value="${bean.person.address}" class="table_input" style="width: 280px" maxlength="6"/>
    </td>
    <td  align="right">
        邮政编码:
    </td>
    <td  class="container">
        <input type="text" name="person.zipcode" value="${bean.person.zipcode}" class="table_input" style="width: 60px" maxlength="6"/>&nbsp;
    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        MSN:
    </td>
    <td  class="container">
        <input type="text" name="person.msnCode" value="${bean.person.msnCode}" class="table_input"/>&nbsp;
    </td>
    <td  align="right">
        QQ:
    </td>
    <td  class="container">
        <input type="text" name="person.qqCode" value="${bean.person.qqCode}" class="table_input"/>&nbsp;
    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        开通日期:
    </td>
    <td  class="container">
        <input type="text" name="openDate" class="Wdate" value="${bean.openDate}" onFocus="WdatePicker({isShowClear:false,readOnly:true})"/>&nbsp;
    </td>
    <td  align="right">
        截止日期:
    </td>
    <td  class="container">
        <input type="text" name="closeDate" class="Wdate" value="${bean.closeDate}" onFocus="WdatePicker({isShowClear:false,readOnly:true})"/>&nbsp;

    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        帐号状态:
    </td>
    <td  class="container" colspan="3">
        <form:select path="status">
            <form:option value="1">正常</form:option>
            <form:option value="0">锁定</form:option>
            <form:option value="2">失效</form:option>
        </form:select>
    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        失效/锁定原因:
    </td>
    <td  class="container" colspan="3">
        <textarea rows="4" cols="60" class="textarea_table" name="reasonDesc" id="reasonDesc">${bean.reasonDesc}</textarea>
    </td>
</tr>
<tr class="inputTr">
    <td  align="right">
        用户角色:
    </td>
    <td  class="container" colspan="3">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <%
                int i=0;

                // ~取得用户角色集合
                SysUser user = (SysUser)request.getAttribute("bean");
                Set set = user.getSysUserRoles();
                String roleNames = StringUtils.defaultIfEmpty(user.getRoleNames(), "");
                String[] ids = roleNames.split(",");
            %>
            <c:forEach var="role" items="${roleList}">
                <%if(i%3 == 0) {%>
                <tr class="inputTr_1">
                    <%}
                        SysRole role = (SysRole)pageContext.getAttribute("role");

                        String isChecked = "";

                        if(ids != null && ids.length > 0){
                            if (CollectionUtil.isContains(ids, role.getId().toString())){
                                isChecked = "checked";
                            }
                        } else if (set != null && set.size() > 0){
                            Iterator it = set.iterator();
                            while(it.hasNext()){
                                SysUserRole sysUserRole = (SysUserRole)it.next();
                                Long roleId = sysUserRole.getRole().getId();
                                if(roleId == role.getId()){
                                    isChecked = "checked";
                                    set.remove(sysUserRole);
                                    break;
                                }
                            }
                        }
                    %>
                    <td class="container">
                        <input type="checkbox" name="ids" value="<c:out value="${role.id}"/>" <%=isChecked%>>
                        <c:out value="${role.roleName}"/>
                    </td>
                    <%if(i%3 == 2) {%>
                </tr>
                <%} %>
                <%i++; %>
            </c:forEach>
            <%
                int j = i%3;
                if(j > 0){
                    for(int m=0; m<(3-j); m++){
            %>
            <td class="container">&nbsp;</td>
            <%
                }
            %>
            </tr>
            <%
                }
            %>
        </table>
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
        v = $('#sysUserEditForm').validate();
    });
</script>