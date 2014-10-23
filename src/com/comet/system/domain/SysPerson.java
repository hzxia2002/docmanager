package com.comet.system.domain;

import com.comet.system.domain.base.BaseSysPerson;

public class SysPerson extends BaseSysPerson {
	private static final long serialVersionUID = 1L;

    private Long workSiteId;

    private String workSiteName;

    private Long deptId;

    private String deptName;

    private Boolean isManager;

    private Boolean isValid;

    private String position;


    /*[CONSTRUCTOR MARKER BEGIN]*/
	public SysPerson () {
		super();
	}

	/**
	 * Constructor for primary key
	 */
	public SysPerson (Long id) {
		super(id);
	}

/*[CONSTRUCTOR MARKER END]*/

    public Long getWorkSiteId() {
        return workSiteId;
    }

    public void setWorkSiteId(Long workSiteId) {
        this.workSiteId = workSiteId;
    }

    public String getWorkSiteName() {
        return workSiteName;
    }

    public void setWorkSiteName(String workSiteName) {
        this.workSiteName = workSiteName;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}