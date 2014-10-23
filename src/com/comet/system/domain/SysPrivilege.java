package com.comet.system.domain;

import com.comet.system.domain.base.BaseSysPrivilege;
import com.comet.core.security.privilege.Privilege;


public class SysPrivilege extends BaseSysPrivilege implements Privilege {
	private static final long serialVersionUID = 1L;

    private String privilegeType;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public SysPrivilege () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysPrivilege (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public SysPrivilege (
		java.lang.Long id,
		java.lang.String code) {

		super (
			id,
			code);
	}

/*[CONSTRUCTOR MARKER END]*/


    /**
     * 取得权限类型
     *
     * @return
     */
//    @Override
    public String getPrivilegeType() {
//        if(this.getType() != null) {
//            return this.getType().getCode();
//        } else {
//            return "";
//        }

        try {
            if(this.privilegeType == null || this.privilegeType.equals("")) {
                setPrivilegeType();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.privilegeType;
    }

    /**
     * 设置权限类型
     */
    public void setPrivilegeType() {
        if (this.getType() != null) {
            this.privilegeType = this.getType().getCode();
        }
    }
}