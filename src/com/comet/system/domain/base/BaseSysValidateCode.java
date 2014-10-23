package com.comet.system.domain.base;

import com.comet.system.domain.SysValidateCode;

import java.io.Serializable;
import java.sql.Timestamp;


/**
 * This is an object that contains data related to the biz_validate_code table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 用户注册校验码临时表
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : BIZ_VALIDATE_CODE
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="biz_validate_code"
 */

public abstract class BaseSysValidateCode  implements Serializable{

	public static String REF = "SysValidateCode";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_EMAIL = "email";
	public static String PROP_MOBILE = "mobile";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_NICK_NAME = "nickName";
	public static String PROP_VALIDATE_CODE = "validateCode";
	public static String PROP_IS_VALID = "valid";
	public static String PROP_MAC = "mac";
	public static String PROP_ID = "id";
	public static String PROP_UPDATE_USER = "updateUser";
	public static String PROP_VALIDATE_CODE_TYPE = "validateCodeType";


	// constructors
	public BaseSysValidateCode () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysValidateCode (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*NICK_NAME*/
    /*昵称*/
	private String nickName;
	
    /*EMAIL*/
    /*邮件*/
	private String email;

    /*MAC*/
	private String mac;
	
    /*MOBILE*/
    /*移动电话*/
	private String mobile;
	
    /*VALIDATE_CODE*/
    /*检验码*/
	private String validateCode;
	
	private String sessionId;

	private Boolean valid;

    /*VALIDATE_CODE_TYPE*/
    /*校验码类型(0-用户注册;1-找回密码)*/
	private String validateCodeType;
	
    /*CREATE_TIME*/
    /*创建时间*/
	private java.sql.Timestamp createTime;
	
    /*UPDATE_TIME*/
    /*更新时间*/
	private java.sql.Timestamp updateTime;
	
    /*UPDATE_USER*/
    /*更新人(记录帐号）*/
	private String updateUser;
	
    /*CREATE_USER*/
    /*创建人(记录帐号）*/
	private String createUser;

    private Boolean sendResult;

    private String resultCode;

    private String resultMsg;

    private Timestamp expireTime;

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




	/**
	 * Return the value associated with the column: NICK_NAME
	 */
	public String getNickName () {
		return nickName;
	}

	/**
	 * Set the value related to the column: NICK_NAME
	 * @param nickName the NICK_NAME value
	 */
	public void setNickName (String nickName) {
		this.nickName = nickName;
	}


	/**
	 * Return the value associated with the column: MAC
	 */
	public String getMac () {
		return mac;
	}

	/**
	 * Set the value related to the column: NICK_NAME
	 * @param mac the NICK_NAME value
	 */
	public void setMac (String mac) {
		this.mac = mac;
	}


	/**
	 * Return the value associated with the column: EMAIL
	 */
	public String getEmail () {
		return email;
	}

	/**
	 * Set the value related to the column: EMAIL
	 * @param email the EMAIL value
	 */
	public void setEmail (String email) {
		this.email = email;
	}


	/**
	 * Return the value associated with the column: MOBILE
	 */
	public String getMobile () {
		return mobile;
	}

	/**
	 * Set the value related to the column: MOBILE
	 * @param mobile the MOBILE value
	 */
	public void setMobile (String mobile) {
		this.mobile = mobile;
	}


	/**
	 * Return the value associated with the column: VALIDATE_CODE
	 */
	public String getValidateCode () {
		return validateCode;
	}

	/**
	 * Set the value related to the column: VALIDATE_CODE
	 * @param validateCode the VALIDATE_CODE value
	 */
	public void setValidateCode (String validateCode) {
		this.validateCode = validateCode;
	}


	/**
	 * Return the value associated with the column: VALIDATE_CODE_TYPE
	 */
	public String getValidateCodeType () {
		return validateCodeType;
	}

	/**
	 * Set the value related to the column: VALIDATE_CODE_TYPE
	 * @param validateCodeType the VALIDATE_CODE_TYPE value
	 */
	public void setValidateCodeType (String validateCodeType) {
		this.validateCodeType = validateCodeType;
	}

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Boolean getSendResult() {
        return sendResult;
    }

    public void setSendResult(Boolean sendResult) {
        this.sendResult = sendResult;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }

    public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof SysValidateCode)) return false;
		else {
			SysValidateCode sysValidateCode = (SysValidateCode) obj;
			if (null == this.getId() || null == sysValidateCode.getId()) return false;
			else return (this.getId().equals(sysValidateCode.getId()));
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
		builder.append(nickName);
		builder.append(mac);
		builder.append(email);
		builder.append(mobile);
		builder.append(validateCode);
		builder.append(validateCodeType);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(updateUser);
		builder.append(createUser);
		return builder.toString();
	}
}