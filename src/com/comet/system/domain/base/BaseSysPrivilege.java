package com.comet.system.domain.base;

import com.comet.system.domain.SysCodeDetail;
import com.comet.system.domain.SysPrivilege;
import com.comet.core.entity.Auditable;
import com.comet.core.entity.Treeable;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_PRIVILEGE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 权限
 * SyncTemplatepatterns : tree\w*
 * SyncDao : false
 * TableName : 权限管理
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : true
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class
 *  table="SYS_PRIVILEGE"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseSysPrivilege implements Serializable, Treeable, Auditable{

	public static String REF = "SysPrivilege";
	public static String PROP_TYPE = "type";
	public static String PROP_DEFINITION = "definition";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_TREE_ID = "treeId";
	public static String PROP_PARENT = "parent";
	public static String PROP_CODE = "code";
	public static String PROP_URL = "url";
	public static String PROP_IS_LEAF = "isLeaf";
	public static String PROP_TAG = "tag";
	public static String PROP_NAME = "name";
	public static String PROP_ID = "id";


	// constructors
	public BaseSysPrivilege () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysPrivilege (Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseSysPrivilege (
		Long id,
		String code) {

		this.setId(id);
		this.setCode(code);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*权限编码*/
    /*权限编码*/
	private String code;
	
    /*权限名称*/
    /*权限名称*/
	private String name;
	
    /*TAG*/
    /*TAG*/
	private Long tag;
	
    /*页面地址*/
    /*页面地址*/
	private String url;
	
    /*定义*/
    /*定义*/
	private String definition;
	
    /*描述*/
    /*描述*/
	private String description;
	
    /*是否叶节点*/
    /*是否叶节点*/
	private Boolean isLeaf;
	
    /*树形层次*/
    /*树形层次*/
	private String treeId;
	

	// many to one
	private SysCodeDetail type;
	private SysPrivilege parent;



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
	 * Return the value associated with the column: NAME
	 */
	public String getName () {
		return name;
	}

	/**
	 * Set the value related to the column: NAME
	 * @param name the NAME value
	 */
	public void setName (String name) {
		this.name = name;
	}


	/**
	 * Return the value associated with the column: TAG
	 */
	public Long getTag () {
		return tag;
	}

	/**
	 * Set the value related to the column: TAG
	 * @param tag the TAG value
	 */
	public void setTag (Long tag) {
		this.tag = tag;
	}


	/**
	 * Return the value associated with the column: URL
	 */
	public String getUrl () {
		return url;
	}

	/**
	 * Set the value related to the column: URL
	 * @param url the URL value
	 */
	public void setUrl (String url) {
		this.url = url;
	}


	/**
	 * Return the value associated with the column: DEFINITION
	 */
	public String getDefinition () {
		return definition;
	}

	/**
	 * Set the value related to the column: DEFINITION
	 * @param definition the DEFINITION value
	 */
	public void setDefinition (String definition) {
		this.definition = definition;
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
	 * Return the value associated with the column: IS_LEAF
	 */
	public Boolean getIsLeaf () {
		return isLeaf;
	}

	/**
	 * Set the value related to the column: IS_LEAF
	 * @param isLeaf the IS_LEAF value
	 */
	public void setIsLeaf (Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}


	/**
	 * Return the value associated with the column: TREE_ID
	 */
	public String getTreeId () {
		return treeId;
	}

	/**
	 * Set the value related to the column: TREE_ID
	 * @param treeId the TREE_ID value
	 */
	public void setTreeId (String treeId) {
		this.treeId = treeId;
	}


	/**
	 * Return the value associated with the column: TYPE
	 */
	public SysCodeDetail getType () {
		return type;
	}

	/**
	 * Set the value related to the column: TYPE
	 * @param type the TYPE value
	 */
	public void setType (SysCodeDetail type) {
		this.type = type;
	}


	/**
	 * Return the value associated with the column: PARENT_ID
	 */
	public SysPrivilege getParent () {
		return parent;
	}

	/**
	 * Set the value related to the column: PARENT_ID
	 * @param parent the PARENT_ID value
	 */
	public void setParent (SysPrivilege parent) {
		this.parent = parent;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof SysPrivilege)) return false;
		else {
			SysPrivilege sysPrivilege = (SysPrivilege) obj;
			if (null == this.getId() || null == sysPrivilege.getId()) return false;
			else return (this.getId().equals(sysPrivilege.getId()));
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
		builder.append(name);
		builder.append(tag);
		builder.append(url);
		builder.append(definition);
		builder.append(description);
		builder.append(isLeaf);
		builder.append(treeId);
		return builder.toString();
	}


}