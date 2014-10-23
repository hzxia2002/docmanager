package com.comet.system.sms;

import com.thoughtworks.xstream.XStream;

import java.util.Iterator;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 12-12-2
 * Time: 下午9:33
 * To change this template use File | Settings | File Templates.
 */
public class XmlUtils {
    public static Object parseResult(String xml, Class bean) {
        XStream xstream = new XStream();
        xstream.alias("response", bean);

        Object obj = xstream.fromXML(xml);

        return obj;
    }

    /**
     * 解析短信发送结果
     *
     * @param xml
     * @return
     */
    public static SmsResult parseResult(String xml) {
        return (SmsResult)parseResult(xml, SmsResult.class);
    }

    /**
     * 设置别名
     *
     * @param alias
     * @param xStream
     */
    private static void registerAlias(Map alias, XStream xStream) {
        if(alias != null) {
            Iterator iterator = alias.keySet().iterator();

            while(iterator.hasNext()) {
                Object tmp = iterator.next();

                xStream.alias(String.valueOf(tmp), (Class)alias.get(tmp));
            }
        }
    }

    public static void main(String[] args) {
        String result = "<CSubmitState xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://tempuri.org/\">\n" +
                "  <State>0</State>\n" +
                "  <MsgID>11202205307122533</MsgID>\n" +
                "  <MsgState>提交成功</MsgState>\n" +
                "  <Reserve>0</Reserve>\n" +
                "</CSubmitState>";

        SmsResult smsResult = parseResult(result);

        System.out.println(smsResult.getRetCode());
    }
}
