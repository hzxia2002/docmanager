package com.comet.system.domain.base;

import com.comet.system.domain.ConfigTable;
import com.comet.core.orm.log.TableLogConfig;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the CONFIG_TABLE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 表日志配置
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 表日志配置
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="CONFIG_TABLE"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseConfigTable implements TableLogConfig, Serializable{

	public static String REF = "ConfigTable";
	public static String PROP_IS_LOG = "isLog";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_TABLE_NAME = "tableName";
    public static String PROP_CLASS_NAME = "className";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_EXTEND_XML = "extendXml";
	public static String PROP_ID = "id";
	public static String PROP_UPDATE_USER = "updateUser";


	// constructors
	public BaseConfigTable () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseConfigTable (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*表名*/
    /*表名*/
	private String tableName;

    /* 类名 */
    private String className;
	
    /*是否记录日志*/
    /*是否记录日志*/
	private Boolean isLog;
	
    /*备用*/
    /*备用*/
	private String extendXml;
	
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
	 * Return the value associated with the column: TABLE_NAME
	 */
	public String getTableName () {
		return tableName;
	}

	/**
	 * Set the value related to the column: TABLE_NAME
	 * @param tableName the TABLE_NAME value
	 */
	public void setTableName (String tableName) {
		this.tableName = tableName;
	}

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    /**
	 * Return the value associated with the column: IS_LOG
	 */
	public Boolean getIsLog () {
		return isLog;
	}

	/**
	 * Set the value related to the column: IS_LOG
	 * @param isLog the IS_LOG value
	 */
	public void setIsLog (Boolean isLog) {
		this.isLog = isLog;
	}


	/**
	 * Return the value associated with the column: EXTEND_XML
	 */
	public String getExtendXml () {
		return extendXml;
	}

	/**
	 * Set the value related to the column: EXTEND_XML
	 * @param extendXml the EXTEND_XML value
	 */
	public void setExtendXml (String extendXml) {
		this.extendXml = extendXml;
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



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof ConfigTable)) return false;
		else {
			ConfigTable configTable = (ConfigTable) obj;
			if (null == this.getId() || null == configTable.getId()) return false;
			else return (this.getId().equals(configTable.getId()));
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
		builder.append(tableName);
		builder.append(isLog);
		builder.append(extendXml);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(updateUser);
		builder.append(createUser);
		return builder.toString();
	}


}