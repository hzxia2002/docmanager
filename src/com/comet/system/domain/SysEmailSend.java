package com.comet.system.domain;

import com.comet.system.domain.base.BaseSysEmailSend;



public class SysEmailSend extends BaseSysEmailSend {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public SysEmailSend () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysEmailSend (Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public SysEmailSend (
		Long id,
		com.comet.system.domain.SysUser user) {

		super (
			id,
			user);
	}

/*[CONSTRUCTOR MARKER END]*/


}