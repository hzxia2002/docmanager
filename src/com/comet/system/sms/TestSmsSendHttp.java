package com.comet.system.sms;

import com.project.entity.request.SubmitReqHttpEntity;
import com.project.entity.response.SubmitRespEntity;
import com.project.http.HttpSmsSendClient;

public class TestSmsSendHttp {

    public static void main(String[] args) throws Exception {

        String httpPath = "http://192.168.3.217:8022/proxyclient/sendSms.ashx";
//创建接口发送对象
        HttpSmsSendClient sendClient = new HttpSmsSendClient();
//设置请求URL
        sendClient.setHttpPath(httpPath);
//创建请求对象
        SubmitReqHttpEntity submitReqEntity = new SubmitReqHttpEntity();
//设置客户端id 必录
        submitReqEntity.setCid("8080");
//设置客户端密码 必录
        submitReqEntity.setPwd("8080");
//发送内容 必录
        submitReqEntity.setContent("发送测试短信");
//手机号码 必录
        submitReqEntity.setMobile("13916271322");
//messageFormat 默认设置 15  必录
        submitReqEntity.setFormat((byte)15);
//客户端唯一流水号码 必录 用于匹配状态报告
        submitReqEntity.setSsid(123456789012l);
//扩展字段
        submitReqEntity.setCustom("扩展字段");
//长号码 非必填
        submitReqEntity.setLcode("10082");
//签名非必填
        submitReqEntity.setSign("签名");
//发送下行请求  获取请求状态
        SubmitRespEntity submitRespEntity = sendClient.sendSMS(submitReqEntity);
//输出请求状态码
        System.out.println("发送请求状态码" + submitRespEntity.getStatus());

    }
}
