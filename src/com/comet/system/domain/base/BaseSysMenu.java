package com.comet.system.domain.base;

import com.comet.system.domain.SysMenu;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;


/**
 * This is an object that contains data related to the SYS_MENU table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 系统菜单
 * SyncTemplatepatterns : tree\w*
 * SyncDao : false
 * TableName : 系统菜单
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : true
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class
 *  table="SYS_MENU"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseSysMenu  implements Serializable,com.comet.core.entity.Treeable{

	public static String REF = "SysMenu";
	public static String PROP_JS_EVENT = "jsEvent";
	public static String PROP_MENU_LEVEL = "menuLevel";
	public static String PROP_PRIVILEGE = "privilege";
	public static String PROP_PARAM = "param";
	public static String PROP_TREE_ID = "treeId";
	public static String PROP_PARENT = "parent";
	public static String PROP_URL = "url";
	public static String PROP_IS_LEAF = "isLeaf";
	public static String PROP_NAME = "name";
	public static String PROP_TARGET = "target";
	public static String PROP_ID = "id";
	public static String PROP_IS_VALID = "isValid";
    public static String PROP_ICON = "icon";


	// constructors
	public BaseSysMenu () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysMenu (Long id) {
		this.setId(id);
		initialize();
	}

	/**
	 * Constructor for required fields
	 */
	public BaseSysMenu (
		Long id,
		String name,
		String privilege) {

		this.setId(id);
		this.setName(name);
		this.setPrivilege(privilege);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*名称*/
    /*名称*/
	private String name;
	
    /*权限编码*/
    /*权限编码*/
	private String privilege;
	
    /*层次*/
    /*层次*/
	private Integer menuLevel;
	
    /*链接地址*/
    /*链接地址*/
	private String url;
	
    /*事件*/
    /*事件*/
	private String jsEvent;
	
    /*叶节点*/
    /*叶节点*/
	private Boolean isLeaf;
	
    /*树形层次*/
    /*树形层次*/
	private String treeId;
	
    /*是否有效*/
    /*是否有效*/
	private Boolean isValid;
	
    /*参数*/
    /*参数*/
	private String param;
	
    /*目标窗口*/
    /*目标窗口*/
	private String target;

    /* 显示图标 */
    private String icon;
    
	// many to one
	private SysMenu parent;



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
	 * Return the value associated with the column: PRIVILEGE
	 */
	public String getPrivilege () {
		return privilege;
	}

	/**
	 * Set the value related to the column: PRIVILEGE
	 * @param privilege the PRIVILEGE value
	 */
	public void setPrivilege (String privilege) {
		this.privilege = privilege;
	}


	/**
	 * Return the value associated with the column: MENU_LEVEL
	 */
	public Integer getMenuLevel () {
		return menuLevel;
	}

	/**
	 * Set the value related to the column: MENU_LEVEL
	 * @param menuLevel the MENU_LEVEL value
	 */
	public void setMenuLevel (Integer menuLevel) {
		this.menuLevel = menuLevel;
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
	 * Return the value associated with the column: JS_EVENT
	 */
	public String getJsEvent () {
		return jsEvent;
	}

	/**
	 * Set the value related to the column: JS_EVENT
	 * @param jsEvent the JS_EVENT value
	 */
	public void setJsEvent (String jsEvent) {
		this.jsEvent = jsEvent;
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
	 * Return the value associated with the column: PARAM
	 */
	public String getParam () {
		return param;
	}

	/**
	 * Set the value related to the column: PARAM
	 * @param param the PARAM value
	 */
	public void setParam (String param) {
		this.param = param;
	}


	/**
	 * Return the value associated with the column: TARGET
	 */
	public String getTarget () {
		return target;
	}

	/**
	 * Set the value related to the column: TARGET
	 * @param target the TARGET value
	 */
	public void setTarget (String target) {
		this.target = target;
	}

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
	 * Return the value associated with the column: PARENT_ID
	 */
	public SysMenu getParent () {
		return parent;
	}

	/**
	 * Set the value related to the column: PARENT_ID
	 * @param parent the PARENT_ID value
	 */
	public void setParent (SysMenu parent) {
		this.parent = parent;
	}

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof SysMenu)) return false;
		else {
			SysMenu sysMenu = (SysMenu) obj;
			if (null == this.getId() || null == sysMenu.getId()) return false;
			else return (this.getId().equals(sysMenu.getId()));
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
		builder.append(name);
		builder.append(privilege);
		builder.append(menuLevel);
		builder.append(url);
		builder.append(jsEvent);
		builder.append(isLeaf);
		builder.append(treeId);
		builder.append(isValid);
		builder.append(param);
		builder.append(target);
        builder.append(icon);
		return builder.toString();
	}


}