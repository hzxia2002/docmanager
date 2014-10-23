package com.comet.system.domain.base;

import com.comet.system.domain.SysRole;
import com.comet.system.domain.SysRolePrivilege;
import com.comet.core.entity.Auditable;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Set;


/**
 * This is an object that contains data related to the SYS_ROLE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 角色管理
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class
 *  table="SYS_ROLE"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseSysRole  implements Serializable, Auditable {

	public static String REF = "SysRole";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_CODE = "code";
	public static String PROP_ROLE_NAME = "roleName";
	public static String PROP_ID = "id";
	public static String PROP_UPDATE_USER = "updateUser";
	
	// constructors
	public BaseSysRole () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysRole (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*角色编码*/
    /*角色编码*/
	private String code;
	
    /*角色名称*/
    /*角色名称*/
	private String roleName;
	
    /*描述*/
    /*项目标识*/
	private String description;
	
    /*创建时间*/
    /*创建时间*/
	private java.sql.Timestamp createTime;
	
    /*更新时间*/
    /*更新时间*/
	private java.sql.Timestamp updateTime;
	
    /*创建人*/
    /*创建人*/
	private String createUser;
	
    /*更新人*/
    /*更新人(记录帐号）*/
	private String updateUser;

    @JsonIgnore
    private Set<SysRolePrivilege> sysRolePrivileges;

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="com.comet.core.orm.hibernate.LongIdGenerator"
     *  column="ID"
     */
	public Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 * @deprecated
	 */
	public void setId (Long id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: CODE
	 */
	public String getCode () {
		return code;
	}

	/**
	 * Set the value related to the column: CODE
	 * @param code the CODE value
	 */
	public void setCode (String code) {
		this.code = code;
	}


	/**
	 * Return the value associated with the column: ROLE_NAME
	 */
	public String getRoleName () {
		return roleName;
	}

	/**
	 * Set the value related to the column: ROLE_NAME
	 * @param roleName the ROLE_NAME value
	 */
	public void setRoleName (String roleName) {
		this.roleName = roleName;
	}


	/**
	 * Return the value associated with the column: DESCRIPTION
	 */
	public String getDescription () {
		return description;
	}

	/**
	 * Set the value related to the column: DESCRIPTION
	 * @param description the DESCRIPTION value
	 */
	public void setDescription (String description) {
		this.description = description;
	}


	/**
	 * Return the value associated with the column: CREATE_TIME
	 */
	public java.sql.Timestamp getCreateTime () {
		return createTime;
	}

	/**
	 * Set the value related to the column: CREATE_TIME
	 * @param createTime the CREATE_TIME value
	 */
	public void setCreateTime (java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}


	/**
	 * Return the value associated with the column: UPDATE_TIME
	 */
	public java.sql.Timestamp getUpdateTime () {
		return updateTime;
	}

	/**
	 * Set the value related to the column: UPDATE_TIME
	 * @param updateTime the UPDATE_TIME value
	 */
	public void setUpdateTime (java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}


	/**
	 * Return the value associated with the column: CREATE_USER
	 */
	public String getCreateUser () {
		return createUser;
	}

	/**
	 * Set the value related to the column: CREATE_USER
	 * @param createUser the CREATE_USER value
	 */
	public void setCreateUser (String createUser) {
		this.createUser = createUser;
	}


	/**
	 * Return the value associated with the column: UPDATE_USER
	 */
	public String getUpdateUser () {
		return updateUser;
	}

	/**
	 * Set the value related to the column: UPDATE_USER
	 * @param updateUser the UPDATE_USER value
	 */
	public void setUpdateUser (String updateUser) {
		this.updateUser = updateUser;
	}

    @JsonIgnore
    public Set<SysRolePrivilege> getSysRolePrivileges() {
        if(sysRolePrivileges == null){
            sysRolePrivileges = new java.util.LinkedHashSet<SysRolePrivilege>();
        }

        return sysRolePrivileges;
    }

    public void setSysRolePrivileges(Set<SysRolePrivilege> sysRolePrivileges) {
        this.sysRolePrivileges = sysRolePrivileges;
    }

    public void addTosysRolePrivileges (SysRolePrivilege sysRolePrivilege) {
        if (null == getSysRolePrivileges()) {
            setSysRolePrivileges(new java.util.LinkedHashSet<SysRolePrivilege>());
        }

        this.sysRolePrivileges.add(sysRolePrivilege);
    }

    public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof SysRole)) return false;
		else {
			SysRole sysRole = (SysRole) obj;
			if (null == this.getId() || null == sysRole.getId()) return false;
			else return (this.getId().equals(sysRole.getId()));
		}
	}

	public int hashCode () {
		if (Integer.MIN_VALUE == this.hashCode) {
			if (null == this.getId()) return super.hashCode();
			else {
				String hashStr = this.getClass().getName() + ":" + this.getId().hashCode();
				this.hashCode = hashStr.hashCode();
			}
		}
		return this.hashCode;
	}


	public String toString () {
		org.apache.commons.lang.builder.ToStringBuilder builder = new org.apache.commons.lang.builder.ToStringBuilder(this);
		builder.append(id);
		builder.append(code);
		builder.append(roleName);
		builder.append(description);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(createUser);
		builder.append(updateUser);
		return builder.toString();
	}


}