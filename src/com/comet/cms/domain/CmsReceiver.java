package com.comet.cms.domain;

import com.comet.cms.domain.base.BaseCmsReceiver;

/**
 * Created with IntelliJ IDEA.
 * User: dell
 * Date: 12-5-14
 * Time: 下午9:06
 * To change this template use File | Settings | File Templates.
 */
public class CmsReceiver extends BaseCmsReceiver {
    private static final long serialVersionUID = 1L;

    private String roleNames;
    private String userNames;
    private String userIds;
    private String roleIds;

    /*[CONSTRUCTOR MARKER BEGIN]*/
    public CmsReceiver() {
        super();
    }

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
