package com.comet.eai.util;

import com.comet.eai.bean.Message;
import com.comet.eai.bean.MessageList;
import com.comet.eai.converters.MapConverter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import com.thoughtworks.xstream.mapper.Mapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.Iterator;
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
public class XmlUtils {
    protected static final Log log = LogFactory.getLog(XmlUtils.class);

    protected static final String PREFIX_CDATA = "<![CDATA[";

    protected static final String SUFFIX_CDATA = "]]>";

    protected static final String DEFAULT_ENCODING = "UTF-8";

	/**
	 * 将JavaBean转换成xml
	 *
	 * @param message
	 *            消息类型
	 * @param encoding
	 *            编码
     * @param isTrim
     *            空格去掉空格
     * @param isFormat 是否格式化显示
	 * @return XML字符串
	 */
	public static String generateXml(Message message, String encoding, boolean isTrim, boolean isFormat) {
		StringBuffer ret = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>");
		ret.append("<messages>");

		XStream xstream = new XStream(new XppDriver(new XmlFriendlyReplacer("_-", "_")));
        Mapper mapper = xstream.getMapper();

        xstream.registerLocalConverter(ArrayList.class, "messages",
                new CollectionConverter(mapper));

        MapConverter mapConverter = new MapConverter(mapper);
        xstream.registerConverter(mapConverter);

        xstream.alias("message", Message.class);
        xstream.alias("messageBody", Map.class);

        try {
            String xml = xstream.toXML(message);

            if(!isFormat) {
                xml = xml.replaceAll("\n", "");
            }

            if(isTrim) {
                xml = xml.replaceAll(" ", "");
            }

            ret.append(xml);
        } catch (Exception e) {
            log.error("error", e);
        }

        ret.append("</messages>");

		return ret.toString();
	}

    /**
     * 生成XML, 默认不去掉空格，不格式化
     *
     * @param message
     * @return
     */
    public static String generateXml(Message message) {
        return generateXml(message, "UTF-8", false, false);
    }

    /**
     * 生成XML字符串
     *
     * @param messages 消息数组
     * @param encoding 文件编码
     * @param isTrim 是否去掉空格
     * @param isFormat 是否格式化
     * @return
     */
    public static String generateXml(Message[] messages, String encoding, boolean isTrim, boolean isFormat) {
        StringBuffer ret = new StringBuffer(
                "<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>");
        ret.append("<messages>");

        XStream xstream = new XStream(new XppDriver(new XmlFriendlyReplacer("_-", "_")));
        Mapper mapper = xstream.getMapper();

        xstream.registerLocalConverter(ArrayList.class, "messages",
                new CollectionConverter(mapper));

        MapConverter mapConverter = new MapConverter(mapper);
        xstream.registerConverter(mapConverter);

        xstream.alias("message", Message.class);
        xstream.alias("messageBody", Map.class);

        String xml = "";

        for(Message message : messages) {
            xml += xstream.toXML(message);
        }

        if(!isFormat) {
            xml = xml.replaceAll("\n", "");
        }

        if(isTrim) {
            xml = xml.replaceAll(" ", "");
        }

        ret.append(xml);
        ret.append("</messages>");

        return ret.toString();
    }

    public static String generateXml(Message... messages) {
        return generateXml(messages, "UTF-8", true, false);
    }

    public static String generateXml(MessageList messageList, String encoding, boolean isTrim, boolean isFormat) {
        StringBuffer ret = new StringBuffer(
                "<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>");
        ret.append("<messages>");

        XStream xstream = new XStream(new XppDriver(new XmlFriendlyReplacer("_-", "_")));
        Mapper mapper = xstream.getMapper();

        xstream.registerLocalConverter(ArrayList.class, "messages",
                new CollectionConverter(mapper));

        MapConverter mapConverter = new MapConverter(mapper);
        xstream.registerConverter(mapConverter);

        xstream.alias("message", Message.class);
        xstream.alias("messageBody", Map.class);

        String xml = "";

        for(Message message : messageList.getList()) {
            xml += xstream.toXML(message);
        }

        if(!isFormat) {
            xml = xml.replaceAll("\n", "");
        }

        if(isTrim) {
            xml = xml.replaceAll(" ", "");
        }

        ret.append(xml);
        ret.append("</messages>");

        return ret.toString();
    }

    public static String generateXml(MessageList messageList) {
        return generateXml(messageList, "UTF-8", false, false);
    }

    /**
     * 将List数组生成XML
     *
     * @param list
     * @param listAlias
     * @param objectAlias
     * @return
     */
    public static String generateXml(List<Map> list, String listAlias, String objectAlias) {
        StringBuffer ret = new StringBuffer();

        XStream xstream = new XStream(new XppDriver(new XmlFriendlyReplacer("_-", "_")));
        Mapper mapper = xstream.getMapper();

//        xstream.registerLocalConverter(ArrayList.class, listAlias,
//                new CollectionConverter(mapper));

        MapConverter mapConverter = new MapConverter(mapper);
        xstream.registerConverter(mapConverter);

        xstream.alias(objectAlias, Map.class);

        String xml = "";

        for(Map message : list) {
            xml += xstream.toXML(message);
        }

//        if(!isFormat) {
            xml = xml.replaceAll("\n", "");
//        }

//        if(isTrim) {
            xml = xml.replaceAll(" ", "");
//        }

        ret.append(xml);

        return ret.toString();
    }

    /**
     * 作为MessageBean解析
     *
     * @param xml
     * @param alias 别名
     * @return
     */
    public static Message parseAsMessage(String xml, Map<String, Class> alias) {
        try {
            XStream xstream = new XStream();

            Mapper mapper = xstream.getMapper();
            xstream.registerLocalConverter(ArrayList.class, "messages",
                    new CollectionConverter(mapper));

            MapConverter mapConverter = new MapConverter(mapper);
            xstream.registerConverter(mapConverter);

            xstream.alias("messages", ArrayList.class);
            xstream.alias("message", Message.class);
            xstream.alias("messageBody", Map.class);

            // 注册别名
            registerAlias(alias, xstream);

            Object obj = xstream.fromXML(xml);

            List list = (List)obj;

            if(list != null && list.size() > 0) {
                return (Message)list.get(0);
            }
        } catch (Exception e) {
            log.error("error", e);

            return null;
        }

        return null;
    }

    /**
     * 作为MessageBean解析
     *
     * @param xml
     * @return
     */
    public static Message parseAsMessage(String xml) {
        return parseAsMessage(xml, null);
    }

    /**
     * 将XML数据解析为List，List中的对象类型为Map
     *
     * @param xml
     * @param alias 别名列表
     * @return
     */
    public static List<Message> parseAsList(String xml, Map<String, Class> alias) {
        XStream xstream = new XStream();

        Mapper mapper = xstream.getMapper();
        xstream.registerLocalConverter(ArrayList.class, "messages",
                new CollectionConverter(mapper));

        MapConverter mapConverter = new MapConverter(mapper);
        xstream.registerConverter(mapConverter);

        xstream.alias("messages", ArrayList.class);
        xstream.alias("message", Message.class);
        xstream.alias("messageBody", Map.class);

        // 注册别名
        registerAlias(alias, xstream);

        Object obj = xstream.fromXML(xml);

        return (List)obj;
    }

    /**
     * 将XML数据解析为List，List中的对象类型为Map
     *
     * @param xml
     * @return
     */
    public static List<Message> parseAsList(String xml) {
        return parseAsList(xml, null);
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
}
