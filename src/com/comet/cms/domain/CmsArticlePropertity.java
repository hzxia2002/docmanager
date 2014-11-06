package com.comet.cms.domain;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-11-6
 * Time: 下午8:49
 * To change this template use File | Settings | File Templates.
 */
public class CmsArticlePropertity extends CmsArticle {
    private static final long serialVersionUID = 1L;

    private String roleNames="";
    private String userNames="";
    private String userIds="";
    private String roleIds="";

    public String getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(String roleNames) {
        this.roleNames = roleNames;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }
}
