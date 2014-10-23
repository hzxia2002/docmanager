package com.comet.system.manager;

import com.comet.system.daoservice.SysMessageSendService;
import com.comet.system.domain.SysMessageSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Project:bcscmis
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
public class SysMessageSendManager {
    @Autowired
    private SysMessageSendService sysMessageSendService;

    /**
     * 重发消息
     *
     * @param ids
     */
    public void resend(String[] ids) {
        for(String id : ids) {
            resend(new Long(id));
        }
    }

    /**
     * 重发消息
     *
     * @param ids
     */
    public void resend(Long[] ids) {
        for(Long id : ids) {
            resend(id);
        }
    }

    /**
     * 重发消息
     * @param id
     */
    public void resend(Long id) {
        changeState(id, 0);
    }

    /**
     * 改变消息状态
     *
     * @param id
     * @param state
     */
    public void changeState(Long id, Integer state) {
        SysMessageSend message = sysMessageSendService.get(id);
        message.setFdMessageState(state);

        sysMessageSendService.save(message);
    }
}
