package com.comet.cms.domain.base;

import com.comet.cms.domain.CmsArticle;
import com.comet.cms.domain.CmsCategory;
import com.comet.core.entity.Auditable;
import com.comet.system.domain.SysCodeDetail;
import com.comet.system.domain.SysRole;
import com.comet.system.domain.SysUser;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * This is an object that contains data related to the SYS_CODE table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 系统代码
 * SyncTemplatepatterns : tree\w*
 * SyncDao : false
 * TableName : 公共信息发布
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : true
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class
 *  table="Oa_Public_Info"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseCmsReceiver implements Serializable, Auditable {

	// constructors
	public BaseCmsReceiver() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsReceiver(Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

    /*创建时间*/
    /*创建时间*/
    private Timestamp createTime;

    /*更新时间*/
    /*更新时间*/
    private Timestamp updateTime;

    /*创建人*/
    /*创建人(记录帐号）*/
    private String createUser;

    /*更新人*/
    /*更新人(记录帐号）*/
    private String updateUser;

	// many to one
	private CmsArticle article;
	private SysRole role;
	private SysUser user;

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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public CmsArticle getArticle() {
        return article;
    }

    public void setArticle(CmsArticle article) {
        this.article = article;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof CmsArticle)) return false;
		else {
            CmsArticle cmsArticle = (CmsArticle) obj;
			if (null == this.getId() || null == cmsArticle.getId()) return false;
			else return (this.getId().equals(cmsArticle.getId()));
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
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(createUser);
		builder.append(updateUser);

		return builder.toString();
	}


}