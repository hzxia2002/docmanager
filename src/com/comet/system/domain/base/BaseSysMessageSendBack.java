package com.comet.system.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the sys_message_send_back table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * Treeable : false
 * SyncDao : false
 * SyncTemplatepatterns : list\w*
 * Projectable : false
 * SyncJsp : true
 * SyncBoolean : get
 *
 * @hibernate.class
 *  table="sys_message_send_back"
 */

public abstract class BaseSysMessageSendBack  implements Serializable{

	public static String REF = "SysMessageSendBack";
	public static String PROP_FD_MESSAGE_SEND_TIME = "fdMessageSendTime";
	public static String PROP_FD_MESSAGE_BODY = "fdMessageBody";
	public static String PROP_FD_MESSAGE_CODE = "fdMessageCode";
	public static String PROP_FD_MESSAGE_STATE_INFO = "fdMessageStateInfo";
	public static String PROP_FD_MESSAGE_STATE = "fdMessageState";
	public static String PROP_FD_MESSAGE_OPERATE_INFO = "fdMessageOperateInfo";
	public static String PROP_FD_MESSAGE_GROUP_NAME = "fdMessageGroupName";
	public static String PROP_FD_MESSAGE_OPERATE_VERSION = "fdMessageOperateVersion";
	public static String PROP_FD_MESSAGE_OPERATE_TIME = "fdMessageOperateTime";
	public static String PROP_ID = "id";


	// constructors
	public BaseSysMessageSendBack () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysMessageSendBack (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
	private String fdMessageCode;
	
	private byte[] fdMessageBody;
	
	private Integer fdMessageState;
	
	private String fdMessageStateInfo;
	
	private java.sql.Timestamp fdMessageSendTime;
	
	private String fdMessageGroupName;
	
	private java.sql.Timestamp fdMessageOperateTime;
	
	private Integer fdMessageOperateVersion;
	
	private String fdMessageOperateInfo;
	



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="com.justonetech.core.orm.hibernate.LongIdGenerator"
     *  column="FD_MESSAGE_ID"
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
	 * Return the value associated with the column: FD_MESSAGE_CODE
	 */
	public String getFdMessageCode () {
		return fdMessageCode;
	}

	/**
	 * Set the value related to the column: FD_MESSAGE_CODE
	 * @param fdMessageCode the FD_MESSAGE_CODE value
	 */
	public void setFdMessageCode (String fdMessageCode) {
		this.fdMessageCode = fdMessageCode;
	}


	/**
	 * Return the value associated with the column: FD_MESSAGE_BODY
	 */
	public byte[] getFdMessageBody () {
		return fdMessageBody;
	}

	/**
	 * Set the value related to the column: FD_MESSAGE_BODY
	 * @param fdMessageBody the FD_MESSAGE_BODY value
	 */
	public void setFdMessageBody (byte[] fdMessageBody) {
		this.fdMessageBody = fdMessageBody;
	}


	/**
	 * Return the value associated with the column: FD_MESSAGE_STATE
	 */
	public Integer getFdMessageState () {
		return fdMessageState;
	}

	/**
	 * Set the value related to the column: FD_MESSAGE_STATE
	 * @param fdMessageState the FD_MESSAGE_STATE value
	 */
	public void setFdMessageState (Integer fdMessageState) {
		this.fdMessageState = fdMessageState;
	}


	/**
	 * Return the value associated with the column: FD_MESSAGE_STATE_INFO
	 */
	public String getFdMessageStateInfo () {
		return fdMessageStateInfo;
	}

	/**
	 * Set the value related to the column: FD_MESSAGE_STATE_INFO
	 * @param fdMessageStateInfo the FD_MESSAGE_STATE_INFO value
	 */
	public void setFdMessageStateInfo (String fdMessageStateInfo) {
		this.fdMessageStateInfo = fdMessageStateInfo;
	}


	/**
	 * Return the value associated with the column: FD_MESSAGE_SEND_TIME
	 */
	public java.sql.Timestamp getFdMessageSendTime () {
		return fdMessageSendTime;
	}

	/**
	 * Set the value related to the column: FD_MESSAGE_SEND_TIME
	 * @param fdMessageSendTime the FD_MESSAGE_SEND_TIME value
	 */
	public void setFdMessageSendTime (java.sql.Timestamp fdMessageSendTime) {
		this.fdMessageSendTime = fdMessageSendTime;
	}


	/**
	 * Return the value associated with the column: FD_MESSAGE_GROUP_NAME
	 */
	public String getFdMessageGroupName () {
		return fdMessageGroupName;
	}

	/**
	 * Set the value related to the column: FD_MESSAGE_GROUP_NAME
	 * @param fdMessageGroupName the FD_MESSAGE_GROUP_NAME value
	 */
	public void setFdMessageGroupName (String fdMessageGroupName) {
		this.fdMessageGroupName = fdMessageGroupName;
	}


	/**
	 * Return the value associated with the column: FD_MESSAGE_OPERATE_TIME
	 */
	public java.sql.Timestamp getFdMessageOperateTime () {
		return fdMessageOperateTime;
	}

	/**
	 * Set the value related to the column: FD_MESSAGE_OPERATE_TIME
	 * @param fdMessageOperateTime the FD_MESSAGE_OPERATE_TIME value
	 */
	public void setFdMessageOperateTime (java.sql.Timestamp fdMessageOperateTime) {
		this.fdMessageOperateTime = fdMessageOperateTime;
	}


	/**
	 * Return the value associated with the column: FD_MESSAGE_OPERATE_VERSION
	 */
	public Integer getFdMessageOperateVersion () {
		return fdMessageOperateVersion;
	}

	/**
	 * Set the value related to the column: FD_MESSAGE_OPERATE_VERSION
	 * @param fdMessageOperateVersion the FD_MESSAGE_OPERATE_VERSION value
	 */
	public void setFdMessageOperateVersion (Integer fdMessageOperateVersion) {
		this.fdMessageOperateVersion = fdMessageOperateVersion;
	}


	/**
	 * Return the value associated with the column: FD_MESSAGE_OPERATE_INFO
	 */
	public String getFdMessageOperateInfo () {
		return fdMessageOperateInfo;
	}

	/**
	 * Set the value related to the column: FD_MESSAGE_OPERATE_INFO
	 * @param fdMessageOperateInfo the FD_MESSAGE_OPERATE_INFO value
	 */
	public void setFdMessageOperateInfo (String fdMessageOperateInfo) {
		this.fdMessageOperateInfo = fdMessageOperateInfo;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.comet.system.domain.SysMessageSendBack)) return false;
		else {
			com.comet.system.domain.SysMessageSendBack sysMessageSendBack = (com.comet.system.domain.SysMessageSendBack) obj;
			if (null == this.getId() || null == sysMessageSendBack.getId()) return false;
			else return (this.getId().equals(sysMessageSendBack.getId()));
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
		builder.append(fdMessageCode);
		builder.append(fdMessageBody);
		builder.append(fdMessageState);
		builder.append(fdMessageStateInfo);
		builder.append(fdMessageSendTime);
		builder.append(fdMessageGroupName);
		builder.append(fdMessageOperateTime);
		builder.append(fdMessageOperateVersion);
		builder.append(fdMessageOperateInfo);
		return builder.toString();
	}


}