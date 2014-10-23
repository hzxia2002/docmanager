package com.comet.cms.domain.base;

import com.comet.cms.domain.DocAttachments;
import com.comet.cms.domain.DocDocument;

import java.io.Serializable;


/**
 * This is an object that contains data related to the doc_attachments table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 文档附件
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : doc_attachments
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="doc_attachments"
 */

public abstract class BaseDocAttachments  implements Serializable{

	public static String REF = "DocAttachments";
	public static String PROP_FILE_PATH = "filePath";
	public static String PROP_REMARK = "remark";
	public static String PROP_DOC = "doc";
	public static String PROP_UPLOAD_DATE = "uploadDate";
	public static String PROP_NAME = "name";
	public static String PROP_ORGIN_NAME = "orginName";
	public static String PROP_ID = "id";


	// constructors
	public BaseDocAttachments () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseDocAttachments (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
    /*NAME*/
    /*文件名称*/
	private String name;
	
    /*ORGIN_NAME*/
    /*文件原始名称*/
	private String orginName;
	
    /*UPLOAD_DATE*/
    /*上传时间*/
	private String uploadDate;
	
    /*REMARK*/
    /*备注*/
	private String remark;
	
    /*FILE_PATH*/
	private String filePath;
	

	// many to one
	private DocDocument doc;



	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="com.justonetech.core.orm.hibernate.LongIdGenerator"
     *  column="ID"
     */
	public java.lang.Long getId () {
		return id;
	}

	/**
	 * Set the unique identifier of this class
	 * @param id the new ID
	 * @deprecated
	 */
	public void setId (java.lang.Long id) {
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
	 * Return the value associated with the column: ORGIN_NAME
	 */
	public String getOrginName () {
		return orginName;
	}

	/**
	 * Set the value related to the column: ORGIN_NAME
	 * @param orginName the ORGIN_NAME value
	 */
	public void setOrginName (String orginName) {
		this.orginName = orginName;
	}


	/**
	 * Return the value associated with the column: UPLOAD_DATE
	 */
	public String getUploadDate () {
		return uploadDate;
	}

	/**
	 * Set the value related to the column: UPLOAD_DATE
	 * @param uploadDate the UPLOAD_DATE value
	 */
	public void setUploadDate (String uploadDate) {
		this.uploadDate = uploadDate;
	}


	/**
	 * Return the value associated with the column: REMARK
	 */
	public String getRemark () {
		return remark;
	}

	/**
	 * Set the value related to the column: REMARK
	 * @param remark the REMARK value
	 */
	public void setRemark (String remark) {
		this.remark = remark;
	}


	/**
	 * Return the value associated with the column: FILE_PATH
	 */
	public String getFilePath () {
		return filePath;
	}

	/**
	 * Set the value related to the column: FILE_PATH
	 * @param filePath the FILE_PATH value
	 */
	public void setFilePath (String filePath) {
		this.filePath = filePath;
	}


	/**
	 * Return the value associated with the column: DOC_ID
	 */
	public DocDocument getDoc () {
		return doc;
	}

	/**
	 * Set the value related to the column: DOC_ID
	 * @param doc the DOC_ID value
	 */
	public void setDoc (DocDocument doc) {
		this.doc = doc;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof DocAttachments)) return false;
		else {
			DocAttachments docAttachments = (DocAttachments) obj;
			if (null == this.getId() || null == docAttachments.getId()) return false;
			else return (this.getId().equals(docAttachments.getId()));
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
		builder.append(orginName);
		builder.append(uploadDate);
		builder.append(remark);
		builder.append(filePath);
		return builder.toString();
	}


}