package com.comet.system.domain;

import com.comet.system.domain.base.BaseSysMenu;

import java.util.List;


public class SysMenu extends BaseSysMenu {
	private static final long serialVersionUID = 1L;

    public List<SysMenu> children;

/*[CONSTRUCTOR MARKER BEGIN]*/
	public SysMenu () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysMenu (java.lang.Long id) {
		super(id);
	}

	/**
	 * Constructor for required fields
	 */
	public SysMenu (
		java.lang.Long id,
		java.lang.String name,
		java.lang.String privilege) {

		super (
			id,
			name,
			privilege);
	}

/*[CONSTRUCTOR MARKER END]*/

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }
}