package com.comet.system.menu;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2011-11-06
 * Time: 14:19:42
 * To change this template use File | Settings | File Templates.
 */
public class LiTag {
    private String id;
    private ATag a;
    private List<UlTag> ulList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ATag getA() {
        return a;
    }

    public void setA(ATag a) {
        this.a = a;
    }

    public List<UlTag> getUlList() {
        return ulList;
    }

    public void setUlList(List<UlTag> ulList) {
        this.ulList = ulList;
    }
}
