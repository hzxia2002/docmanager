package com.comet.system.domain.base;

import com.comet.system.domain.SysCodeDetail;
import com.comet.system.domain.SysLog;
import com.comet.system.domain.SysUser;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_LOG table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 用户日志
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 用户日志
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class
 *  table="SYS_LOG"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseSysLog  implements Serializable{

	public static String REF = "SysLog";
	public static String PROP_PAGE_URL = "pageUrl";
	public static String PROP_IP_ADDRESS = "ipAddress";
	public static String PROP_OUT_TIME = "outTime";
	public static String PROP_ENTER_TIME = "enterTime";
	public static String PROP_USER = "user";
	public static String PROP_LOG_TYPE = "logType";
	public static String PROP_IE_VERSION = "ieVersion";
	public static String PROP_ID = "id";
	public static String PROP_SESSIONID = "sessionid";


	// constructors
	public BaseSysLog () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysLog (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*IP地址*/
    /*IP地址*/
	private String ipAddress;
	
    /*进入时间*/
    /*记录时间：记录进入当前页面时间*/
	private java.sql.Timestamp enterTime;
	
    /*完成时间*/
    /*完成时间：记录退出当前页面时间*/
	private java.sql.Timestamp outTime;
	
    /*访问页面*/
    /*访问页面*/
	private String pageUrl;
	
    /*浏览器版本*/
    /*浏览器版本*/
	private String ieVersion;
	
    /*SESSIONID*/
    /*SESSION_ID*/
	private String sessionid;
	

	// many to one
	private SysUser user;
	private SysCodeDetail logType;



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
	 * Return the value associated with the column: IP_ADDRESS
	 */
	public String getIpAddress () {
		return ipAddress;
	}

	/**
	 * Set the value related to the column: IP_ADDRESS
	 * @param ipAddress the IP_ADDRESS value
	 */
	public void setIpAddress (String ipAddress) {
		this.ipAddress = ipAddress;
	}


	/**
	 * Return the value associated with the column: ENTER_TIME
	 */
	public java.sql.Timestamp getEnterTime () {
		return enterTime;
	}

	/**
	 * Set the value related to the column: ENTER_TIME
	 * @param enterTime the ENTER_TIME value
	 */
	public void setEnterTime (java.sql.Timestamp enterTime) {
		this.enterTime = enterTime;
	}


	/**
	 * Return the value associated with the column: OUT_TIME
	 */
	public java.sql.Timestamp getOutTime () {
		return outTime;
	}

	/**
	 * Set the value related to the column: OUT_TIME
	 * @param outTime the OUT_TIME value
	 */
	public void setOutTime (java.sql.Timestamp outTime) {
		this.outTime = outTime;
	}


	/**
	 * Return the value associated with the column: PAGE_URL
	 */
	public String getPageUrl () {
		return pageUrl;
	}

	/**
	 * Set the value related to the column: PAGE_URL
	 * @param pageUrl the PAGE_URL value
	 */
	public void setPageUrl (String pageUrl) {
		this.pageUrl = pageUrl;
	}


	/**
	 * Return the value associated with the column: IE_VERSION
	 */
	public String getIeVersion () {
		return ieVersion;
	}

	/**
	 * Set the value related to the column: IE_VERSION
	 * @param ieVersion the IE_VERSION value
	 */
	public void setIeVersion (String ieVersion) {
		this.ieVersion = ieVersion;
	}


	/**
	 * Return the value associated with the column: SESSIONID
	 */
	public String getSessionid () {
		return sessionid;
	}

	/**
	 * Set the value related to the column: SESSIONID
	 * @param sessionid the SESSIONID value
	 */
	public void setSessionid (String sessionid) {
		this.sessionid = sessionid;
	}


	/**
	 * Return the value associated with the column: USER_ID
	 */
	public SysUser getUser () {
		return user;
	}

	/**
	 * Set the value related to the column: USER_ID
	 * @param user the USER_ID value
	 */
	public void setUser (SysUser user) {
		this.user = user;
	}


	/**
	 * Return the value associated with the column: LOG_TYPE
	 */
	public SysCodeDetail getLogType () {
		return logType;
	}

	/**
	 * Set the value related to the column: LOG_TYPE
	 * @param logType the LOG_TYPE value
	 */
	public void setLogType (SysCodeDetail logType) {
		this.logType = logType;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof SysLog)) return false;
		else {
			SysLog sysLog = (SysLog) obj;
			if (null == this.getId() || null == sysLog.getId()) return false;
			else return (this.getId().equals(sysLog.getId()));
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
		builder.append(ipAddress);
		builder.append(enterTime);
		builder.append(outTime);
		builder.append(pageUrl);
		builder.append(ieVersion);
		builder.append(sessionid);
		return builder.toString();
	}


}