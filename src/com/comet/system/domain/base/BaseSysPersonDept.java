package com.comet.system.domain.base;

import com.comet.system.domain.SysDept;
import com.comet.system.domain.SysPerson;
import com.comet.system.domain.SysPersonDept;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_PERSON_DEPT table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 单位与人员的关联表
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 单位与人员的关联表
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class
 *  table="SYS_PERSON_DEPT"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseSysPersonDept  implements Serializable{

	public static String REF = "SysPersonDept";
	public static String PROP_ORDER_NO = "orderNo";
	public static String PROP_DEPT = "dept";
	public static String PROP_IS_MANAGER = "isManager";
	public static String PROP_POSITION = "position";
	public static String PROP_PERSON = "person";
	public static String PROP_ID = "id";
	public static String PROP_IS_VALID = "isValid";


	// constructors
	public BaseSysPersonDept () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysPersonDept (Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseSysPersonDept (
		Long id,
		SysPerson person,
		SysDept dept) {

		this.setId(id);
		this.setPerson(person);
		this.setDept(dept);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*职务*/
    /*职务*/
	private String position;
	
    /*序号*/
    /*序号*/
	private Long orderNo;
	
    /*是否有效*/
    /*是否有效*/
	private Boolean isValid;
	
    /*是否单位负责人*/
    /*是否单位负责人*/
	private Boolean isManager;
	

	// many to one
	private SysPerson person;
	private SysDept dept;



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
	 * Return the value associated with the column: POSITION
	 */
	public String getPosition () {
		return position;
	}

	/**
	 * Set the value related to the column: POSITION
	 * @param position the POSITION value
	 */
	public void setPosition (String position) {
		this.position = position;
	}


	/**
	 * Return the value associated with the column: ORDER_NO
	 */
	public Long getOrderNo () {
		return orderNo;
	}

	/**
	 * Set the value related to the column: ORDER_NO
	 * @param orderNo the ORDER_NO value
	 */
	public void setOrderNo (Long orderNo) {
		this.orderNo = orderNo;
	}


	/**
	 * Return the value associated with the column: IS_VALID
	 */
	public Boolean getIsValid () {
		return isValid;
	}

	/**
	 * Set the value related to the column: IS_VALID
	 * @param isValid the IS_VALID value
	 */
	public void setIsValid (Boolean isValid) {
		this.isValid = isValid;
	}


	/**
	 * Return the value associated with the column: IS_MANAGER
	 */
	public Boolean getIsManager () {
		return isManager;
	}

	/**
	 * Set the value related to the column: IS_MANAGER
	 * @param isManager the IS_MANAGER value
	 */
	public void setIsManager (Boolean isManager) {
		this.isManager = isManager;
	}


	/**
	 * Return the value associated with the column: PERSON_ID
	 */
	public SysPerson getPerson () {
		return person;
	}

	/**
	 * Set the value related to the column: PERSON_ID
	 * @param person the PERSON_ID value
	 */
	public void setPerson (SysPerson person) {
		this.person = person;
	}


	/**
	 * Return the value associated with the column: DEPT_ID
	 */
	public SysDept getDept () {
		return dept;
	}

	/**
	 * Set the value related to the column: DEPT_ID
	 * @param dept the DEPT_ID value
	 */
	public void setDept (SysDept dept) {
		this.dept = dept;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof SysPersonDept)) return false;
		else {
			SysPersonDept sysPersonDept = (SysPersonDept) obj;
			if (null == this.getId() || null == sysPersonDept.getId()) return false;
			else return (this.getId().equals(sysPersonDept.getId()));
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
		builder.append(position);
		builder.append(orderNo);
		builder.append(isValid);
		builder.append(isManager);
		return builder.toString();
	}


}