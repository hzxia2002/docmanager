package com.comet.cms.domain.base;

import com.comet.cms.domain.DocDocument;

import java.io.Serializable;


/**
 * This is an object that contains data related to the doc_document table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 文档
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : doc_document
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * Projectable : false
 *
 * @hibernate.class
 *  table="doc_document"
 */

public abstract class BaseDocDocument  implements Serializable{

	public static String REF = "DocDocument";
	public static String PROP_PATH = "path";
	public static String PROP_CODE = "code";
	public static String PROP_ID = "id";


	// constructors
	public BaseDocDocument () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseDocDocument (java.lang.Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}



	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private java.lang.Long id;

	// fields
    /*CODE*/
    /*文档编码*/
	private String code;
	
    /*PATH*/
    /*文档路径*/
	private String path;
	



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
	 * Return the value associated with the column: PATH
	 */
	public String getPath () {
		return path;
	}

	/**
	 * Set the value related to the column: PATH
	 * @param path the PATH value
	 */
	public void setPath (String path) {
		this.path = path;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof DocDocument)) return false;
		else {
			DocDocument docDocument = (DocDocument) obj;
			if (null == this.getId() || null == docDocument.getId()) return false;
			else return (this.getId().equals(docDocument.getId()));
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
		builder.append(path);
		return builder.toString();
	}


}