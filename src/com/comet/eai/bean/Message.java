package com.comet.eai.bean;

import java.sql.Timestamp;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-1-20
 * Time: 下午7:40
 * To change this template use File | Settings | File Templates.
 */
public class Message {
    private String messageMethod;
    private String messageType;
    private String icomboId;
    private String equipId;
    private Timestamp messageTime;
    private String clientId;
    private String guid;
    private String userName;
    private Map messageBody;

    public String getMessageMethod() {
        return messageMethod;
    }

    public void setMessageMethod(String messageMethod) {
        this.messageMethod = messageMethod;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getIcomboId() {
        return icomboId;
    }

    public void setIcomboId(String icomboId) {
        this.icomboId = icomboId;
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
    }

    public Timestamp getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(Timestamp messageTime) {
        this.messageTime = messageTime;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Map getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(Map messageBody) {
        this.messageBody = messageBody;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
