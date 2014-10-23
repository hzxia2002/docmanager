package com.comet.system.mail;

import org.apache.commons.lang.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: hzxia
 * Date: 12-1-27
 * Time: 上午12:24
 */
public class MailSendService {
    private JavaMailSender mailSender;

    private String from;
    private String sendername;
    
    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSendername() {
		return sendername;
	}

	public void setSendername(String sendername) {
		this.sendername = sendername;
	}

	public void sendMailHtml(String to, String cc, String title, String mail)
            throws Exception {
        if (!StringUtils.isEmpty(to)) {
            Properties props = new Properties();
            Session ssn = Session.getDefaultInstance(props, null);
            MimeMessage mailMessage = new MimeMessage(ssn);
            InternetAddress fromAddress = new InternetAddress(from);
            mailMessage.setFrom(fromAddress);
            InternetAddress toAddress = new InternetAddress(to);
            mailMessage.addRecipient(Message.RecipientType.TO, toAddress);
            mailMessage.setSubject(title);
            mailMessage.setContent(mail, "text/html; charset=utf-8");
            mailMessage.saveChanges();
            mailSender.send(mailMessage);
        } else {
            System.out.println("Mail Address is Null!");
        }

    }
}
