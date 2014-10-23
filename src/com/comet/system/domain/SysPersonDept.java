package com.comet.system.domain;

import com.comet.system.domain.base.BaseSysPersonDept;


public class SysPersonDept extends BaseSysPersonDept {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public SysPersonDept () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysPersonDept (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public SysPersonDept (
		java.lang.Long id,
		SysPerson person,
		SysDept dept) {

		super (
			id,
			person,
			dept);
	}

/*[CONSTRUCTOR MARKER END]*/


}