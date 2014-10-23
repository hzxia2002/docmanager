<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<html>
<head>
    <%@ include file="../common/header_new.jsp" %>
</head>
<body style="width: 100%">

<div id="sysValidateCodeLayout" style="width:100%;overflow-y: hidden;overflow-x: hidden;margin:0; padding:0;">
    <div position="center">
        <div id="sysValidateCodeQuery" title="查询窗口"  icon="search">
            <form id="sysValidateCodeForm" action="">
                <table style="width: 100%">
                    <tr>
                        <td width="15%" nowrap="nowrap" align="left">
                            昵称:
                            <input type="text" id="nickName" name="nickName" value="" class="table_input" op="like" entity="t" dtype="String" style="width: 100px;"/>
                        </td>
                        <td width="15%" nowrap="nowrap" align="left">
                            Email:
                            <input type="text" id="email" name="email" value="" class="table_input"  op="like" entity="t" dtype="String" style="width: 100px;"/>
                        </td>
                        <td width="15%" nowrap="nowrap" align="left">
                            手机:
                            <input type="text" id="mobile" name="mobile" value="" class="table_input"  op="like" entity="t" dtype="String" style="width: 100px;"/>
                        </td>
                        <td width="15%" nowrap="nowrap" align="left">
                            状态:<select id="valid" name="valid" op="eq" entity="t" style="width: 80px;">
                            <option value="">全部</option>
                            <option value="1">有效</option>
                            <option value="0">无效</option>
                        </select>
                        </td>
					    <td align="left">
                            <input type="button" value="查询" onclick="javascript:search('sysValidateCodeGrid','sysValidateCodeForm');"/>&nbsp;
                        </td>
					 </tr>
                </table>
                <div style="display: none;height:30px" id="advanced_condition">

                </div>
            </form>
        </div>
        <div id="sysValidateCodeGrid" checkbox="true" rownumbers="true"  height="100%"  url='${ctx}/sysValidateCode/grid.do' root="rows" record="records"  width="100%"  colDraggable="true" >
        </div>

    </div>
</div>
</body>
</html>

<script type="text/javascript">
    $(function(){

        //查询框初始化
        $("#sysValidateCodeQuery").juicePanel({ collapse:false,height: 50,closeAble:false});
        //grid工具栏
        var toolbar={
            align:"right",
            items: [
                { text: '删除', click: doDelete, icon:'delete' }
            ]
        };
        //grid
        $("#sysValidateCodeGrid").juiceGrid({
            toolbar:toolbar,
            sortnameParmName: 'orderBy',
            sortorderParmName: 'order',
            columns: [
				{display: '', name: 'id', width: 50,hide:true},
				{display: '昵称', name: 'nickName', width: 150},
				{display: '邮件', name: 'email', width: 200},
				{display: '移动电话', name: 'mobile', width: 100},
				{display: '检验码', name: 'validateCode', width: 50},
				{display: '校验码类型', name: 'validateCodeType', width: 100,render:typeFormatter},
				{display: '是否有效', name: 'valid', width: 50,render:statusFormatter},
				{display: '创建人', name: 'createUser', width: 100},
                {display: '创建时间', name: 'createTime', width: 150}
            ]
//        enabledEdit: true
        });
        //创建树
    });

    function typeFormatter(item,index,value){
        if(value=="1"){
            return "找回密码";
        }else if(value=="0"){
            return "用户注册";
        }else if(value=="2"){
            return "账户激活";
        }
    }

    function statusFormatter(item,index,value){
        if(value=="1"){
            return "<font color='red'>是</font>";
        }else if(value=="0"){
            return "否";
        }
    }

     function doView(id) {
        var url = "${ctx}/sysValidateCode/view.do";

        commonView(url, "sysValidateCodeGrid");
    }

    function doDelete(){
        commonDelete("sysValidateCodeGrid","${ctx}/sysValidateCode/delete.do");
    }

    function doAdd(){        
        var url = "${ctx}/sysValidateCode/init.do";
        commonAddOrUpdate(url,"sysValidateCodeGrid",null,"sysValidateCodeGridForm",{title:"新增用户注册校验码临时表",height:530,width:600});
    }

    function doEdit(id){
        var url = "${ctx}/sysValidateCode/init.do";
        if(typeof id == "string"){
            url += "?id=" + id;
        }else{
            url = initUrl(url,"sysValidateCodeGrid");
        }
        if(url){
            commonAddOrUpdate(url,"sysValidateCodeGrid",null,"sysValidateCodeForm",{title:"编辑用户注册校验码临时表",height:530,width:600});
        }
    }
</script>

