<%@ include file="/common/portal_header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="ContentHome" style="margin: 0 auto; width: 1024px;margin-bottom:10px;">
    <div class="daohang">&gt; 权益消费&gt; 个人信息</div>
    <div style="float: left">
        <%@ include file="/portal/user/menu.jsp" %>
    </div>
    <div id="regist" style="width: 800px; float: right; margin-top: -19px;">
        <div style="height: 300px;background: url('${ctx}/images/error.png') no-repeat;margin-left: 200px;margin-top: 50px;padding-left: 100px;padding-top: 100px">
            <div style="">
                <div style="color: red;font-family: 宋体;font-size: 12px;">${msg}</div>
                <div style="margin-top: 30px;width: 50px;margin-left: 50px;">
                    <input name="del2" value="返回" type="button" class="btn" onclick="javascript:window.history.go(-2)"/>
                </div>
            </div>
        </div>
    </div>
    <div class="clr"></div>
</div>

<%@ include file="/common/footer.jsp" %>
