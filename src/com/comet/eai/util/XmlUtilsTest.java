package com.comet.eai.util;

import com.comet.eai.bean.Message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * XML处理工具类
 * </p>
 *
 * @author <a href="mailto:hzxia@qq.com">XiaHongzhong</a>
 * @version 1.0
 */
public class XmlUtilsTest {
    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><messages><message><messageMethod>query</messageMethod><messageCode>01</messageCode><icomId>01</icomId><messageBody><equipId>21</equipId><temperature>21</temperature><humidity>21</humidity><CO2>12</CO2><formaldehyde>12</formaldehyde></messageBody></message></messages>";

        List list = XmlUtils.parseAsList(xml);

        Message message = new Message();
        message.setMessageType("AAAA01");
        message.setEquipId("X01-001");
        message.setIcomboId("AA-BB-01");

        Map map = new HashMap();
        map.put("temp", "27.5");
        map.put("liquid", "29.5");
        message.setMessageBody(map);

        xml = XmlUtils.generateXml(message, message);
        System.out.println(xml);
    }
}
