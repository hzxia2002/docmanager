package com.comet.system.manager;

import com.comet.system.daoservice.SysEmailSendService;
import com.comet.system.daoservice.SysMessageSendService;
import com.comet.system.domain.SysEmailSend;
import com.comet.system.domain.SysMessageSend;
import com.comet.system.mail.MailSendService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * Time: 下午5:08
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MailManager {
    private static Log log = LogFactory.getLog(MailManager.class);
    @Autowired
    private MailSendService mailSendService;

    @Autowired
    private SysEmailSendService sysEmailSendService;

    public boolean SendMail(String to,String cc,String title,String content){
        boolean sentRet = false;
        try {
            mailSendService.sendMailHtml(to,cc,title,content);
            SysEmailSend sysEmailSend = new SysEmailSend();
            sysEmailSend.setContent(content);
            sysEmailSend.setReceiver(to);
            sysEmailSend.setSendResult("发送成功");
            sysEmailSend.setTitle(title);
            sysEmailSend.setSendTime(new Timestamp(System.currentTimeMillis()));
            sysEmailSendService.save(sysEmailSend);
            sentRet = true;
            return sentRet;
        } catch (Exception e) {
            log.error("邮件发送失败："+e.toString());
            return sentRet;
        }
    }
}
