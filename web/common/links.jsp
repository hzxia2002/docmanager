<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ContentHome">
    <div class="fousfive">
        <div class="yqlj_left">友情链接</div>
        <div class="yqlj_right"></div>
        <div class="yqlj_middle">
            <comet:channel size="5" var="doc" showContent="false" channelCode="YQLJ" queryChildren="true">
                <a href="${doc.linkUrl}" target="_blank"><img src="${ctx}${doc.imagePath}" width="156" height="59" /></a>&nbsp;&nbsp;
            </comet:channel>
        </div>
    </div>
    <div class="clr"></div>
</div>