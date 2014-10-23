package com.comet.system.domain.base;

import com.comet.system.domain.SysCode;
import com.comet.system.domain.SysCodeDetail;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Set;


/**
 * This is an object that contains data related to the SYS_CODE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 系统代码
 * SyncTemplatepatterns : tree\w*
 * SyncDao : false
 * TableName : 系统代码
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : true
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class
 *  table="SYS_CODE"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseSysCode  implements Serializable,com.comet.core.entity.Treeable{

	public static String REF = "SysCode";
	public static String PROP_DESCRIPTION = "description";
	public static String PROP_TREE_ID = "treeId";
	public static String PROP_PARENT = "parent";
	public static String PROP_IS_RESERVED = "isReserved";
	public static String PROP_CODE = "code";
	public static String PROP_IS_LEAF = "isLeaf";
	public static String PROP_NAME = "name";
	public static String PROP_ID = "id";


	// constructors
	public BaseSysCode () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysCode (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*编码*/
    /*编码*/
	private String code;
	
    /*名称*/
    /*名称*/
	private String name;
	
    /*系统定义*/
    /*系统定义*/
	private Boolean isReserved;
	
    /*叶节点*/
    /*叶节点*/
	private Boolean isLeaf;
	
    /*树形层次*/
    /*树形层次*/
	private String treeId;
	
    /*备注*/
    /*备注*/
	private String description;
	

	// many to one
	private SysCode parent;

    @JsonIgnore
    private Set<SysCodeDetail> sysCodeDetails;

    public Set<SysCodeDetail> getSysCodeDetails() {
        return sysCodeDetails;
    }

    public void setSysCodeDetails(Set<SysCodeDetail> sysCodeDetails) {
        this.sysCodeDetails = sysCodeDetails;
    }



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
	 * Return the value associated with the column: IS_RESERVED
	 */
	public Boolean getIsReserved () {
		return isReserved;
	}

	/**
	 * Set the value related to the column: IS_RESERVED
	 * @param isReserved the IS_RESERVED value
	 */
	public void setIsReserved (Boolean isReserved) {
		this.isReserved = isReserved;
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
	 * Return the value associated with the column: PARENT_ID
	 */
	public SysCode getParent () {
		return parent;
	}

	/**
	 * Set the value related to the column: PARENT_ID
	 * @param parent the PARENT_ID value
	 */
	public void setParent (SysCode parent) {
		this.parent = parent;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof SysCode)) return false;
		else {
			SysCode sysCode = (SysCode) obj;
			if (null == this.getId() || null == sysCode.getId()) return false;
			else return (this.getId().equals(sysCode.getId()));
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
		builder.append(isReserved);
		builder.append(isLeaf);
		builder.append(treeId);
		builder.append(description);
		return builder.toString();
	}


}