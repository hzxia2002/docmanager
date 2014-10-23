package com.comet.system.domain.base;

import com.comet.system.domain.SysPrivilege;
import com.comet.system.domain.SysRole;
import com.comet.system.domain.SysRolePrivilege;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_ROLE_PRIVILEGE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 角色与权限关联
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 角色与权限关联
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class
 *  table="SYS_ROLE_PRIVILEGE"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseSysRolePrivilege  implements Serializable{

	public static String REF = "SysRolePrivilege";
	public static String PROP_PRIVILEGE = "privilege";
	public static String PROP_ROLE = "role";
	public static String PROP_ID = "id";


	// constructors
	public BaseSysRolePrivilege () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysRolePrivilege (Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseSysRolePrivilege (
		Long id,
		SysRole role,
		SysPrivilege privilege) {

		this.setId(id);
		this.setRole(role);
		this.setPrivilege(privilege);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// many to one
	private SysRole role;
	private SysPrivilege privilege;



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
	 * Return the value associated with the column: ROLE_ID
	 */
	public SysRole getRole () {
		return role;
	}

	/**
	 * Set the value related to the column: ROLE_ID
	 * @param role the ROLE_ID value
	 */
	public void setRole (SysRole role) {
		this.role = role;
	}


	/**
	 * Return the value associated with the column: PRIVILEGE_ID
	 */
	public SysPrivilege getPrivilege () {
		return privilege;
	}

	/**
	 * Set the value related to the column: PRIVILEGE_ID
	 * @param privilege the PRIVILEGE_ID value
	 */
	public void setPrivilege (SysPrivilege privilege) {
		this.privilege = privilege;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof SysRolePrivilege)) return false;
		else {
			SysRolePrivilege sysRolePrivilege = (SysRolePrivilege) obj;
			if (null == this.getId() || null == sysRolePrivilege.getId()) return false;
			else return (this.getId().equals(sysRolePrivilege.getId()));
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
		return builder.toString();
	}


}