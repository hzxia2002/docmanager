package com.comet.system.domain.base;

import com.comet.system.domain.SysSequence;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_SEQUENCE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 序列
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 序列
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class
 *  table="SYS_SEQUENCE"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseSysSequence  implements Serializable{

	public static String REF = "SysSequence";
	public static String PROP_LASTID = "lastid";
	public static String PROP_ID = "id";


	// constructors
	public BaseSysSequence () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysSequence (String id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseSysSequence (
		String id,
		Long lastid) {

		this.setId(id);
		this.setLastid(lastid);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private String id;

	// fields
    /*下一标识*/
	private Long lastid;
	



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="com.comet.core.orm.hibernate.LongIdGenerator"
     *  column="CODE"
     */
	public String getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 * @deprecated
	 */
	public void setId (String id) {
		this.id = id;
		this.hashCode = Integer.MIN_VALUE;
	}




	/**
	 * Return the value associated with the column: LASTID
	 */
	public Long getLastid () {
		return lastid;
	}

	/**
	 * Set the value related to the column: LASTID
	 * @param lastid the LASTID value
	 */
	public void setLastid (Long lastid) {
		this.lastid = lastid;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof SysSequence)) return false;
		else {
			SysSequence sysSequence = (SysSequence) obj;
			if (null == this.getId() || null == sysSequence.getId()) return false;
			else return (this.getId().equals(sysSequence.getId()));
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
		builder.append(lastid);
		return builder.toString();
	}


}