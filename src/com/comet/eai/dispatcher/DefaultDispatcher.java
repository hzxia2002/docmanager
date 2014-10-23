package com.comet.eai.dispatcher;

import com.comet.eai.ReceiveMessage;
import com.comet.eai.ReceiveResult;
import com.comet.eai.ext.ReceiveMessageContext;
import com.comet.eai.ext.eaiimpl.LeiMessageDispatcher;
import com.comet.eai.handler.MessageHandler;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-1-20
 * Time: 下午8:37
 * To change this template use File | Settings | File Templates.
 */
public class DefaultDispatcher extends LeiMessageDispatcher {
    private static Logger logger = Logger.getLogger(DefaultDispatcher.class);

    /**
     * 根据消息号来转发得到的消息。
     *
     * @see com.comet.eai.ReceiveHandler#handle(com.comet.eai.ReceiveMessage)
     */
    public ReceiveResult handle(ReceiveMessage rcvMsg) {
        try{
            ReceiveMessageContext rcvMsgCtx = convert(rcvMsg);
            MessageHandler handler = new MessageHandler();

            if (handler != null) {
                // 执行业务逻辑
                handler.handle(rcvMsgCtx);

                return convert(rcvMsgCtx);
            } else {
                logger.error("Have no Handler with the messge" + rcvMsg);
                ReceiveResult result = new ReceiveResult();
                result.setResultCode(-1);
                result.setResultMessage("no handler for this message:" + rcvMsg
                        + " in application");

                // 没有处理逻辑
                return result;
            }
        } catch(Throwable th){ // FIXED 070903-01 当执行用户提供的handler时需要捕获各种错误 比如runtime exception以防止出现状态变为4的情况。
            logger.error("There are an Exception while doing handler:"+th.getMessage(),th);
            ReceiveResult result = new ReceiveResult();
            result.setResultCode(-1);
            result.setResultMessage("There are an Exception while doing handler:"+th.getMessage());

            return result;
        }
    }
}
