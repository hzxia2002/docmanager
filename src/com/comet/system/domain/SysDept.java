package com.comet.system.domain;

import com.comet.system.domain.base.BaseSysDept;
import com.comet.system.utils.CommonUtils;


public class SysDept extends BaseSysDept {
	private static final long serialVersionUID = 1L;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public SysDept () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysDept (java.lang.Long id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/

    public String getTypeName() {
        return CommonUtils.getDeptTypeName(getType());
    }
}