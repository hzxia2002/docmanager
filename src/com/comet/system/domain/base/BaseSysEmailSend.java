package com.comet.system.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the sys_email_send table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : Email发送记录
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : sys_email_send
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="sys_email_send"
 */

public abstract class BaseSysEmailSend  implements Serializable{

	public static String REF = "SysEmailSend";
	public static String PROP_RECEIVER = "receiver";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_SEND_RESULT = "sendResult";
	public static String PROP_USER = "user";
	public static String PROP_SEND_TIME = "sendTime";
	public static String PROP_CONTENT = "content";
	public static String PROP_ID = "id";
	public static String PROP_SEND_STATUS = "sendStatus";


	// constructors
	public BaseSysEmailSend () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysEmailSend (Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseSysEmailSend (
		Long id,
		com.comet.system.domain.SysUser user) {

		this.setId(id);
		this.setUser(user);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*CONTENT*/
    /*短信内容*/
	private String content;
	
    /*RECEIVER*/
    /*接收人*/
	private String receiver;
	
    /*CREATE_TIME*/
    /*创建时间*/
	private java.sql.Timestamp createTime;
	
    /*CREATE_USER*/
    /*创建人(记录帐号）*/
	private String createUser;
	
    /*SEND_TIME*/
    /*发送时间*/
	private java.sql.Timestamp sendTime;
	
    /*SEND_RESULT*/
    /*发送结果*/
	private String sendResult;
	
    /*SEND_STATUS*/
    /*发送状态*/
	private String sendStatus;

    private String title;


	// many to one
	private com.comet.system.domain.SysUser user;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="com.justonetech.core.orm.hibernate.LongIdGenerator"
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

	/**
	 * Return the value associated with the column: CONTENT
	 */
	public String getContent () {
		return content;
	}

	/**
	 * Set the value related to the column: CONTENT
	 * @param content the CONTENT value
	 */
	public void setContent (String content) {
		this.content = content;
	}


	/**
	 * Return the value associated with the column: RECEIVER
	 */
	public String getReceiver () {
		return receiver;
	}

	/**
	 * Set the value related to the column: RECEIVER
	 * @param receiver the RECEIVER value
	 */
	public void setReceiver (String receiver) {
		this.receiver = receiver;
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
	 * Return the value associated with the column: SEND_TIME
	 */
	public java.sql.Timestamp getSendTime () {
		return sendTime;
	}

	/**
	 * Set the value related to the column: SEND_TIME
	 * @param sendTime the SEND_TIME value
	 */
	public void setSendTime (java.sql.Timestamp sendTime) {
		this.sendTime = sendTime;
	}


	/**
	 * Return the value associated with the column: SEND_RESULT
	 */
	public String getSendResult () {
		return sendResult;
	}

	/**
	 * Set the value related to the column: SEND_RESULT
	 * @param sendResult the SEND_RESULT value
	 */
	public void setSendResult (String sendResult) {
		this.sendResult = sendResult;
	}


	/**
	 * Return the value associated with the column: SEND_STATUS
	 */
	public String getSendStatus () {
		return sendStatus;
	}

	/**
	 * Set the value related to the column: SEND_STATUS
	 * @param sendStatus the SEND_STATUS value
	 */
	public void setSendStatus (String sendStatus) {
		this.sendStatus = sendStatus;
	}


	/**
	 * Return the value associated with the column: USER_ID
	 */
	public com.comet.system.domain.SysUser getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: USER_ID
	 * @param user the USER_ID value
	 */
	public void setUser (com.comet.system.domain.SysUser user) {
		this.user = user;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.comet.system.domain.SysEmailSend)) return false;
		else {
			com.comet.system.domain.SysEmailSend sysEmailSend = (com.comet.system.domain.SysEmailSend) obj;
			if (null == this.getId() || null == sysEmailSend.getId()) return false;
			else return (this.getId().equals(sysEmailSend.getId()));
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
		builder.append(content);
		builder.append(receiver);
		builder.append(createTime);
		builder.append(createUser);
		builder.append(sendTime);
		builder.append(sendResult);
		builder.append(sendStatus);
		return builder.toString();
	}


}