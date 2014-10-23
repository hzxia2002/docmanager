package com.comet.system.menu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2011-11-06
 * Time: 14:17:44
 * To change this template use File | Settings | File Templates.
 */
public class UlTag {

    private String id;
    private String text;
    private List<LiTag> liList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<LiTag> getLiList() {
        if(liList==null){
            liList = new ArrayList();
        }
        return liList;
    }

    public void setLiList(List<LiTag> liList) {
        this.liList = liList;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
