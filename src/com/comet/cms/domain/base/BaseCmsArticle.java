package com.comet.cms.domain.base;

import com.comet.cms.domain.CmsArticle;
import com.comet.cms.domain.CmsCategory;
import com.comet.core.entity.Auditable;
import com.comet.system.domain.SysCodeDetail;
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
public abstract class BaseCmsArticle implements Serializable, Auditable {
	public static String REF = "OaPublicInfo";
    public static String PROP_ID = "id";
    public static String PROP_CATEGORY_ID = "category";
	public static String PROP_TITLE = "title";
	public static String PROP_CONTENT = "content";
	public static String PROP_PUBLISH_DATE = "publishDate";
	public static String PROP_VISIT_TIMES = "visitTimes";
	public static String PROP_IS_VALID = "isValid";
	public static String PROP_IS_TOP = "isTop";
	public static String PROP_ATTACH_PATH = "attachPath";
	public static String PROP_ATTACH_PATH2 = "attachPath2";
	public static String PROP_ATTACH_PATH3 = "attachPath3";
	public static String PROP_ATTACH_PATH4 = "attachPath4";
	public static String PROP_ATTACH_PATH5 = "attachPath5";
	public static String PROP_THUMB_PATH = "thumbPath";
	public static String PROP_LINK_URL = "linkUrl";
	public static String PROP_KEYWORD = "keyword";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_UPDATE_USER = "updateUser";

	// constructors
	public BaseCmsArticle() {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseCmsArticle(Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	private String title;

	private String content;

	private Date publishDate;

    private Long visitTimes;

    private Boolean isValid;

    private Boolean isTop;

    private String attachPath;
    private String attachPath2;
    private String attachPath3;
    private String attachPath4;
    private String attachPath5;

    private String thumbPath;

    private String linkUrl;

    private String keyword;

    /*创建时间*/
    /*创建时间*/
    private java.sql.Timestamp createTime;

    /*更新时间*/
    /*更新时间*/
    private java.sql.Timestamp updateTime;

    /*创建人*/
    /*创建人(记录帐号）*/
    private String createUser;

    /*更新人*/
    /*更新人(记录帐号）*/
    private String updateUser;

	// many to one
	private CmsCategory category;
	private SysCodeDetail accessRange;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Long getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(Long visitTimes) {
        this.visitTimes = visitTimes;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean valid) {
        isValid = valid;
    }

    public Boolean getIsTop() {
        return isTop;
    }

    public void setIsTop(Boolean top) {
        isTop = top;
    }

    public String getAttachPath() {
        return attachPath;
    }

    public void setAttachPath(String attachPath) {
        this.attachPath = attachPath;
    }

    public String getAttachPath2() {
        return attachPath2;
    }

    public void setAttachPath2(String attachPath2) {
        this.attachPath2 = attachPath2;
    }

    public String getAttachPath3() {
        return attachPath3;
    }

    public void setAttachPath3(String attachPath3) {
        this.attachPath3 = attachPath3;
    }

    public String getAttachPath4() {
        return attachPath4;
    }

    public void setAttachPath4(String attachPath4) {
        this.attachPath4 = attachPath4;
    }

    public String getAttachPath5() {
        return attachPath5;
    }

    public void setAttachPath5(String attachPath5) {
        this.attachPath5 = attachPath5;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public CmsCategory getCategory() {
        return category;
    }

    public void setCategory(CmsCategory category) {
        this.category = category;
    }

    public SysCodeDetail getAccessRange() {
        return accessRange;
    }

    public void setAccessRange(SysCodeDetail accessRange) {
        this.accessRange = accessRange;
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
		builder.append(category);
		builder.append(title);
		builder.append(content);
		builder.append(publishDate);
		builder.append(visitTimes);
		builder.append(isValid);
		builder.append(isTop);
		builder.append(attachPath);
		builder.append(attachPath2);
		builder.append(attachPath3);
		builder.append(attachPath4);
		builder.append(attachPath5);
		builder.append(thumbPath);
		builder.append(linkUrl);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(createUser);
		builder.append(updateUser);

		return builder.toString();
	}


}