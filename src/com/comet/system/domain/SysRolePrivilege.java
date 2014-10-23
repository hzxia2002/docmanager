package com.comet.system.domain;

import com.comet.system.domain.base.BaseSysRolePrivilege;


public class SysRolePrivilege extends BaseSysRolePrivilege {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public SysRolePrivilege () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysRolePrivilege (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public SysRolePrivilege (
		java.lang.Long id,
		SysRole role,
		SysPrivilege privilege) {

		super (
			id,
			role,
			privilege);
	}

/*[CONSTRUCTOR MARKER END]*/


}