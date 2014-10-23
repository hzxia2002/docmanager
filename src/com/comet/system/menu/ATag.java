package com.comet.system.menu;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2011-11-06
 * Time: 14:20:34
 * To change this template use File | Settings | File Templates.
 */
public class ATag {
    private String href;
    private String func;
    private String text;

    public ATag() {

    }

    public ATag(String href, String func, String text) {
        this.href = href;
        this.func = func;
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getFunc() {
        return func;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

//    public String toString(){
//        return this.getText();
//    }
}
