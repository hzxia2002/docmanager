package com.comet.core.utils;

import com.comet.eai.ReceiveMessage;
import com.comet.eai.bean.Message;
import com.comet.eai.ext.MessageContextHandler;
import com.comet.eai.ext.MessageContextHandlerRepository;
import com.comet.eai.ext.MessageMappingInfo;
import com.comet.eai.ext.ReceiveMessageContext;
import com.comet.eai.util.XmlUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-1-20
 * Time: 下午7:31
 */
public class MessageHandler implements MessageContextHandler {
    private static final Logger logger = Logger.getLogger(MessageHandler.class);

    public void handle(ReceiveMessageContext ctx) {
        if(logger.isDebugEnabled()) {
            logger.debug("MessageHandler begin：");
        }

        ReceiveMessage msg = ctx.getReceiveMessage();

        if(msg == null) {
            logger.error("无法获取msg信息！");
            ctx.getReceiveResult().setResultCode(-1);
            ctx.getReceiveResult().setResultMessage("无法获取msg信息！");
            return;
        }

        // 对消息体进行解析
        List list = XmlUtils.parseAsList(msg.getBody());

        for(int i=0; i<list.size(); i++) {
            Message tmp = (Message)list.get(i);

            MessageContextHandlerRepository repository = (MessageContextHandlerRepository)ctx.getDataMap().get("handlerRepository");

            MessageMappingInfo mappingInfo =
                    repository.getMessageMappingInfoCache().getMessageMappingInfo(tmp.getMessageType());

            if(mappingInfo == null) {
                logger.error("无法获取MessageMappingInfo信息！");
                ctx.getReceiveResult().setResultCode(-1);
                ctx.getReceiveResult().setResultMessage("无法获取MessageMappingInfo信息！");
                continue;
            }

            try {
                // 根据定义，调用方法
                String className = mappingInfo.getBusinessModule();
                String methodName = mappingInfo.getBusinessFunction();

                Class objClass = Class.forName(className);
                // Object object = objClass.newInstance();
                Object object = SpringUtils.getApplicationContext().getBean(Class.forName(className));
                Method method = objClass.getMethod(methodName, new Class[] {Message.class});

                Object result = method.invoke(object, new Object[]{tmp});

                logger.info(result);
            } catch (Exception e) {
                logger.error("error", e);
            }
        }
    }
}
