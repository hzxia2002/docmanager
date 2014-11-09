<%@ page import="java.util.List" %>
<%@ page import="com.comet.system.domain.SysRole" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body>
<form:form id="cmsArticleEditForm" modelAttribute="bean" name="cmsArticleEditForm" action="${ctx}/cmsArticle/save.do" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${bean.id}" />
    <div>
        <table border="0" cellspacing="1" width="100%" id="viewTable" class="inputTable">
            <tr class="inputTr">
                <td>
                    选择角色:
                </td>
                <td class="container">
                    <table width="100%" border="0" cellpadding="0" cellspacing="0">
                        <%
                            int i=0;

                            // ~取得用户角色集合
                            List roleList = (List)request.getAttribute("selectedRoles");
                        %>
                        <c:forEach var="role" items="${roleList}">
                            <%if(i%5 == 0) {%>
                            <tr class="inputTr_1">
                                <%}
                                    SysRole role = (SysRole)pageContext.getAttribute("role");

                                    String isChecked = "";

                                    if(roleList != null) {
                                        for (int k = 0; k < roleList.size(); k++) {
                                            SysRole sysRole = (SysRole) roleList.get(k);

                                            Long roleId = sysRole.getId();
                                            if (roleId == role.getId()) {
                                                isChecked = "checked";

                                                break;
                                            }
                                        }
                                    }
                                %>
                                <td class="container">
                                    <input type="checkbox" name="ids" id="ids" value="<c:out value="${role.id}"/>" label="${role.roleName}" <%=isChecked%>>
                                    <c:out value="${role.roleName}"/>
                                </td>
                                <%if(i%5 == 4) {%>
                            </tr>
                            <%} %>
                            <%i++; %>
                        </c:forEach>
                        <%
                            int j = i%5;
                            if(j > 0){
                                for(int m=0; m<(5-j); m++){
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
            <tr class="inputTr">
                <td colspan="2">
                    选择用户:
                </td>
            </tr>
            <tr class="inputTr">
                <td colspan="2">
                    <table width="100%">
                        <tr>
                            <td width="700">
                                <div id="sysUserGrid" rownumbers="true" onDblClickRow="onDblClickRow" height="100%"  url='${ctx}/sysUser/grid.do' root="rows" record="records"  width="500"  colDraggable="true" >
                                </div>
                            </td>
                            <td>
                                <div id="sysSelectedUserGrid" rownumbers="true" onDblClickRow="ondbclick"  height="100%"  url='' root="rows" record="records"  width="500"  colDraggable="true" >
                                </div>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>

    </div>
</form:form>
</body>

</html>

<script type="text/javascript">
    $(document).ready(function () {
        $("#sysUserGrid").juiceGrid({
            title: "可选用户（通过双击行选择）",
            columns: [
                { display: '主键', name: 'id', width: 50, type: 'int',hide:true },
                { display: '登录名', name: 'loginName',width:"20%"},
                { display: '姓名', name: 'displayName',width:"30%" }
            ]
        });

        $("#sysSelectedUserGrid").juiceGrid({
            title: "已选用户（通过双击行移除）",
            columns: [
                { display: '主键', name: 'id', width: 50, type: 'int',hide:true },
                { display: '登录名', name: 'loginName',width:"20%"},
                { display: '姓名', name: 'displayName',width:"30%" }
            ]
        });
    });

    function onDblClickRow(data, rowindex, rowobj)
    {
        addNewRow(data);
    }

    function addNewRow(data) {
        var manager = $("#sysSelectedUserGrid").juiceGetGridManager();

        var data1 = manager.getData();

        for(var i=0; i<data1.length; i++) {
            if(data1[i]["id"] == data["id"]) {
                return;
            }
        }

        manager.addRow({
            id: data["id"],
            loginName: data["loginName"],
            displayName: data["displayName"]
        });
    }

    /**
     * 获取选中的用户值
     *
     */
    function user_select(){
        return $.jui.get("sysSelectedUserGrid").getData();
    }

    /**
     * 获取选中的角色值
     *
     */
    function role_select(){
        var val = '';
        var label = '';
        var ret = new Array();

        $('input[name="ids"]:checked').each(function(){
            val += $(this).val()+',';
            label += $(this).attr('label')+',';
        });

        if (val.length > 0) {
            val = val.substring(0,val.length - 1);
        }

        if (label.length > 0) {
            label = label.substring(0,label.length - 1);
        }

        ret[0] = val;
        ret[1] = label;

        return ret;
    }

    function ondbclick(data, rowindex, rowobj)
    {
        var manager = $("#sysSelectedUserGrid").juiceGetGridManager();
        var row = manager.getSelectedRow();
        manager.deleteRow(row);
    }
</script>