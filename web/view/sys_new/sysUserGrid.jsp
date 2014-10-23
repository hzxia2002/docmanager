<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">
<div id="mainLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="left"  title="单位部门树" id="accordion1">
        <ul id="deptTree" style="margin-top:3px;"></ul>
    </div>
    <div position="center">
        <div id="query" title="查询条件"  icon="search">
            <form id="sysUserForm" action="">
                <table style="width: 100%">
                    <input type="hidden" id="dept_treeId" name="dept_treeId" value="" op="leftLike" entity="t1"/>
                    <tr>
                        <td width="20%" nowrap="nowrap" align="left">
                            登录名:
                            <input type="text" id="loginName" name="loginName" value="" class="table_input" op="like" entity="t" dtype="String" style="width: 100px;"/>
                        </td>
                        <td width="20%" nowrap="nowrap" align="left">
                            姓名:
                            <input type="text" id="displayName" name="displayName" value="" class="table_input"  op="like" entity="t" dtype="String" style="width: 100px;"/>
                        </td>
                        <td width="20%" nowrap="nowrap" align="left">
                            身份证号:
                            <input type="text" id="card" name="card" value="" class="table_input"  op="like" entity="t.person" dtype="String" style="width: 140px;"/>
                        </td>
                        <td width="20%" nowrap="nowrap" align="left">
                            手机号码:
                            <input type="text" id="mobile" name="mobile" value="" class="table_input"  op="like" entity="t.person" dtype="String" style="width: 100px;"/>
                        </td>
                        <td width="15%" nowrap="nowrap" align="left">
                            账户状态:<select id="status" name="status" op="eq" entity="t">
                            <option value="">全部</option>
                            <option value="1">有效</option>
                            <option value="0">无效</option>
                            <option value="2">锁定</option>
                            <option value="4">未激活</option>
                        </select>
                        </td>
                        <td>
                            <input type="button" value="查询" onclick="javascript:search('sysUserGrid','sysUserForm');"/>&nbsp;
                            <%--<a href="javascript:void(0);" onclick="toggleDiv('advanced_condition', '0')">高级查询</a>--%>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div id="sysUserGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysUser/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>
    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){
        //布局
        $("#mainLayout").juiceLayout({ leftWidth: 190 , onAfterResize:function(width){
            $.jui.get("sysUserGrid").setWidth(width);
            $.jui.get("query").setWidth(width);
            }
        });
        //查询框初始化
        $("#query").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
//                { text: '增加', click: doAdd, icon: 'add' },
//                { line: true },
                { text: '修改', click: doEdit, icon: 'modify' },
                { line: true },
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };

        //grid
        $("#sysUserGrid").juiceGrid({
            toolbar:toolbar,
            columns: [
                { display: '主键', name: 'id', width: 50, type: 'int',hide:true },
                { display: '登录名', name: 'loginName',width:"15%"},
                { display: '姓名', name: 'displayName',width:"15%" },
                { display: '身份证', name: 'person.card',width:"12%" },
                { display: '会员卡号', name: 'userId',width:"10%" },
                { display: '手机', name: 'person.mobile',width:"10%" },
                { display: '状态', name: 'status',width:"6%",render:statusFormatter},
//                { display: '公司名称', name: 'deptName',width:"25%" },
                { display: '角色', name: 'roleNames',width:"20%" }
//                { display: '用户类型', name: 'userTypeName',width:"10%" },
//                { display: '截止日期', name: 'closeDate',width:"10%" }
            ]
//        enabledEdit: true
        });
        //创建树
        createTree("deptTree",{ url:"${ctx}/sysDept/tree.do",createMenu:createMenu,onSelect:treeNodeClick});
    });
    
    //树的右键菜单
    function createMenu(node){
        var menu;
        var treeNode = node.data;
        if(node.data.uid != 'root') {
            menu = $.juiceMenu({ top: 100, left: 100, width: 120, items:
                    [
                        { text: '增加', icon:'add', click:function(item){doAdd(treeNode.uid)}}
                    ]
            });
        }
        return menu;
    }
    
    function treeNodeClick(treeNode){
        $("#dept_treeId").val(treeNode.data.treeId);
        search('sysUserGrid','sysUserForm');
    }

    function doView(id) {
        openWindow("sysDeptWindow", "查看", "${ctx}/sysUser/view.do?id=" + id, true, "sysUserGrid");
    }

    function doDelete(){
        commonDelete("sysUserGrid","${ctx}/sysUser/delete.do");
    }


    function doAdd(id){
        commonAddOrUpdate("${ctx}/sysUser/initAdd.do?deptId="+id,"sysUserGrid",null,"sysUserEditForm",{title:"新增用户信息",height:600,width:800});
    }

    function doEdit(){
        var url = "${ctx}/sysUser/init.do";
        url = initUrl(url,"sysUserGrid");
        if(url){
            commonAddOrUpdate(url,"sysUserGrid",null,"sysUserEditForm",{title:"修改用户信息",height:600,width:800});
        }
    }

    function statusFormatter(item,index,value){
        if(value=="1"){
            return "正常";
        }else if(value=="0"){
            return "失效";
        }else if(value=="2"){
            return "锁定";
        }else if(value=="4"){
            return "未激活";
        }
    }
</script>