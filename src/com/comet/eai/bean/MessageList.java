package com.comet.eai.bean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 13-1-20
 * Time: 下午7:40
 * To change this template use File | Settings | File Templates.
 */
public class MessageList extends Message {
    private List<Message> list;

    public List<Message> getList() {
        return list;
    }

    public void setList(List<Message> list) {
        this.list = list;
    }

    public void add(Message message) {
        if(list == null) {
            list = new ArrayList();
        }

        list.add(message);
    }
}
