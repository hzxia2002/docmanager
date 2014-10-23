package com.comet.system.domain;

import com.comet.system.domain.base.BaseSysSmsSend;



public class SysSmsSend extends BaseSysSmsSend {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public SysSmsSend () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysSmsSend (Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public SysSmsSend (
		Long id,
		com.comet.system.domain.SysUser user) {

		super (
			id,
			user);
	}

/*[CONSTRUCTOR MARKER END]*/


}