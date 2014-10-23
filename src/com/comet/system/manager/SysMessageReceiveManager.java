package com.comet.system.manager;

import com.comet.core.utils.DateTimeHelper;
import com.comet.core.utils.SpringUtils;
import com.comet.eai.bean.Message;
import com.comet.eai.util.XmlUtils;
import com.comet.system.daoservice.SysMessageReceiveService;
import com.comet.system.domain.SysMessageInfo;
import com.comet.system.domain.SysMessageReceive;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Project:comet.web
 * <p/>
 * <p>
 * 消息发送逻辑处理类
 * </p>
 * <p/>
 * Create On 2009-12-19 下午04:24:35
 *
 * @author <a href="mailto:hzxia2002@gmail.com">XiaHongzhong</a>
 * @version 1.0
 */
@Service
public class SysMessageReceiveManager {
    private static final Log log = LogFactory.getLog(SysMessageReceiveManager.class);

    @Autowired
    private SysMessageReceiveService sysMessageReceiveService;

    @Autowired
    private SysMessageInfoManager sysMessageInfoManager;

    /**
     * 对消息进行解析
     *
     * @param id
     */
    public void parse(Long id) throws Exception {
        SysMessageReceive message = sysMessageReceiveService.get(id);

        if(message != null) {
            String code = message.getFdMessageCode();

            List list = XmlUtils.parseAsList(message.getMessageBody());

            for(int i=0; i<list.size(); i++) {
                Message tmp = (Message)list.get(i);
                SysMessageInfo messageInfo = sysMessageInfoManager.getByCode(tmp.getMessageType());

                if(messageInfo == null) {
                    throw new Exception("消息[" + tmp.getMessageType() + "]定义信息不存在，请先定义消息!");
                }

                String className = messageInfo.getFdBusinessModule();
                String methodName = messageInfo.getFdBusinessFunction();

                Class objClass = Class.forName(className);
//                Object object = objClass.newInstance();
                Object object = SpringUtils.getApplicationContext().getBean(Class.forName(className));
                Method method = objClass.getMethod(methodName, new Class[] {Message.class});

                Object result = method.invoke(object, new Object[]{tmp});

                log.info(result);
            }

            message.setFdMessageState(1);
            message.setFdMessageStateInfo("解析成功!");
            message.setFdMessageOperateTime(DateTimeHelper.getTimestamp());

            sysMessageReceiveService.save(message);
        }
    }
}
