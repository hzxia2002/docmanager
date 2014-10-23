package com.comet.system.domain.base;

import com.comet.system.domain.ConfigTable;
import com.comet.system.domain.SysOperationTableLog;
import com.comet.core.orm.log.TableLog;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_OPERATION_TABLE_LOG table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 数据表操作日志
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 数据表操作日志
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="SYS_OPERATION_TABLE_LOG"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseSysOperationTableLog extends TableLog implements Serializable{

	public static String REF = "SysOperationTableLog";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_IP_ADDRESS = "ipAddress";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_LOG_XML = "logXml";
    public static String PROP_LOG_TYPE = "logType";
	public static String PROP_TABLE = "table";
	public static String PROP_ID = "id";
	public static String PROP_UPDATE_USER = "updateUser";


	// constructors
	public BaseSysOperationTableLog () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysOperationTableLog (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*操作日志*/
    /*操作日志*/
	private String logXml;

    /* 日志类型 */
    private String logType;
	
    /*IP地址*/
    /*IP地址*/
	private String ipAddress;
	
    /*创建时间*/
    /*创建时间*/
	private java.sql.Timestamp createTime;
	
    /*更新时间*/
    /*更新时间*/
	private java.sql.Timestamp updateTime;
	
    /*更新人*/
    /*更新人(记录帐号）*/
	private String updateUser;
	
    /*创建人*/
    /*创建人*/
	private String createUser;
	

	// many to one
	private ConfigTable table;



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
	 * Return the value associated with the column: LOG_XML
	 */
	public String getLogXml () {
		return logXml;
	}

	/**
	 * Set the value related to the column: LOG_XML
	 * @param logXml the LOG_XML value
	 */
	public void setLogXml (String logXml) {
		this.logXml = logXml;
	}

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
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


	/**
	 * Return the value associated with the column: TABLE_ID
	 */
	public ConfigTable getTable () {
		return table;
	}

	/**
	 * Set the value related to the column: TABLE_ID
	 * @param table the TABLE_ID value
	 */
	public void setTable (ConfigTable table) {
		this.table = table;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof SysOperationTableLog)) return false;
		else {
			SysOperationTableLog sysOperationTableLog = (SysOperationTableLog) obj;
			if (null == this.getId() || null == sysOperationTableLog.getId()) return false;
			else return (this.getId().equals(sysOperationTableLog.getId()));
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
		builder.append(logXml);
		builder.append(ipAddress);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(updateUser);
		builder.append(createUser);
		return builder.toString();
	}


}