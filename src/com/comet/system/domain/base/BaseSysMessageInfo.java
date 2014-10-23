package com.comet.system.domain.base;

import java.io.Serializable;


/**
 * This is an object that contains data related to the sys_message_info table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * Treeable : false
 * SyncDao : false
 * SyncTemplatepatterns : list\w*
 * Projectable : false
 * SyncJsp : true
 * SyncBoolean : get
 *
 * @hibernate.class
 *  table="sys_message_info"
 */

public abstract class BaseSysMessageInfo  implements Serializable{

	public static String REF = "SysMessageInfo";
	public static String PROP_FD_BUSINESS_PARA_FOUR = "fdBusinessParaFour";
	public static String PROP_FD_BUSINESS_PARA_SIX = "fdBusinessParaSix";
	public static String PROP_FD_MESSAGE_STATE = "fdMessageState";
	public static String PROP_FD_MESSAGE_TYPE = "fdMessageType";
	public static String PROP_FD_BUSINESS_PARA_ONE = "fdBusinessParaOne";
	public static String PROP_FD_BUSINESS_PARA_THREE = "fdBusinessParaThree";
	public static String PROP_FD_BUSINESS_PARA_FIVE = "fdBusinessParaFive";
	public static String PROP_FD_MESSAGE_DESC = "fdMessageDesc";
	public static String PROP_FD_BUSINESS_PARA_TWO = "fdBusinessParaTwo";
	public static String PROP_FD_MESSAGE_GROUP_NAME = "fdMessageGroupName";
	public static String PROP_FD_BUSINESS_MODULE = "fdBusinessModule";
	public static String PROP_FD_MESSAGE_HANDLER_NAME = "fdMessageHandlerName";
	public static String PROP_ID = "id";
	public static String PROP_FD_BUSINESS_FUNCTION = "fdBusinessFunction";


	// constructors
	public BaseSysMessageInfo () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysMessageInfo (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    private String fdMessageCode;

	private String fdMessageHandlerName;
	
	private Integer fdMessageState;
	
	private String fdMessageDesc;
	
	private String fdMessageGroupName;
	
	private String fdMessageType;
	
	private String fdBusinessModule;
	
	private String fdBusinessFunction;
	
	private String fdBusinessParaOne;
	
	private String fdBusinessParaTwo;
	
	private String fdBusinessParaThree;
	
	private String fdBusinessParaFour;
	
	private String fdBusinessParaFive;
	
	private String fdBusinessParaSix;

	/**
	 * Return the unique identifier of this class
     * @hibernate.id
     *  generator-class="com.justonetech.core.orm.hibernate.LongIdGenerator"
     *  column="FD_MESSAGE_CODE"
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

    public String getFdMessageCode() {
        return fdMessageCode;
    }

    public void setFdMessageCode(String fdMessageCode) {
        this.fdMessageCode = fdMessageCode;
    }

    /**
	 * Return the value associated with the column: FD_MESSAGE_HANDLER_NAME
	 */
	public String getFdMessageHandlerName () {
		return fdMessageHandlerName;
	}

	/**
	 * Set the value related to the column: FD_MESSAGE_HANDLER_NAME
	 * @param fdMessageHandlerName the FD_MESSAGE_HANDLER_NAME value
	 */
	public void setFdMessageHandlerName (String fdMessageHandlerName) {
		this.fdMessageHandlerName = fdMessageHandlerName;
	}


	/**
	 * Return the value associated with the column: FD_MESSAGE_STATE
	 */
	public Integer getFdMessageState () {
		return fdMessageState;
	}

	/**
	 * Set the value related to the column: FD_MESSAGE_STATE
	 * @param fdMessageState the FD_MESSAGE_STATE value
	 */
	public void setFdMessageState (Integer fdMessageState) {
		this.fdMessageState = fdMessageState;
	}


	/**
	 * Return the value associated with the column: FD_MESSAGE_DESC
	 */
	public String getFdMessageDesc () {
		return fdMessageDesc;
	}

	/**
	 * Set the value related to the column: FD_MESSAGE_DESC
	 * @param fdMessageDesc the FD_MESSAGE_DESC value
	 */
	public void setFdMessageDesc (String fdMessageDesc) {
		this.fdMessageDesc = fdMessageDesc;
	}


	/**
	 * Return the value associated with the column: FD_MESSAGE_GROUP_NAME
	 */
	public String getFdMessageGroupName () {
		return fdMessageGroupName;
	}

	/**
	 * Set the value related to the column: FD_MESSAGE_GROUP_NAME
	 * @param fdMessageGroupName the FD_MESSAGE_GROUP_NAME value
	 */
	public void setFdMessageGroupName (String fdMessageGroupName) {
		this.fdMessageGroupName = fdMessageGroupName;
	}


	/**
	 * Return the value associated with the column: FD_MESSAGE_TYPE
	 */
	public String getFdMessageType () {
		return fdMessageType;
	}

	/**
	 * Set the value related to the column: FD_MESSAGE_TYPE
	 * @param fdMessageType the FD_MESSAGE_TYPE value
	 */
	public void setFdMessageType (String fdMessageType) {
		this.fdMessageType = fdMessageType;
	}


	/**
	 * Return the value associated with the column: FD_BUSINESS_MODULE
	 */
	public String getFdBusinessModule () {
		return fdBusinessModule;
	}

	/**
	 * Set the value related to the column: FD_BUSINESS_MODULE
	 * @param fdBusinessModule the FD_BUSINESS_MODULE value
	 */
	public void setFdBusinessModule (String fdBusinessModule) {
		this.fdBusinessModule = fdBusinessModule;
	}


	/**
	 * Return the value associated with the column: FD_BUSINESS_FUNCTION
	 */
	public String getFdBusinessFunction () {
		return fdBusinessFunction;
	}

	/**
	 * Set the value related to the column: FD_BUSINESS_FUNCTION
	 * @param fdBusinessFunction the FD_BUSINESS_FUNCTION value
	 */
	public void setFdBusinessFunction (String fdBusinessFunction) {
		this.fdBusinessFunction = fdBusinessFunction;
	}


	/**
	 * Return the value associated with the column: FD_BUSINESS_PARA_ONE
	 */
	public String getFdBusinessParaOne () {
		return fdBusinessParaOne;
	}

	/**
	 * Set the value related to the column: FD_BUSINESS_PARA_ONE
	 * @param fdBusinessParaOne the FD_BUSINESS_PARA_ONE value
	 */
	public void setFdBusinessParaOne (String fdBusinessParaOne) {
		this.fdBusinessParaOne = fdBusinessParaOne;
	}


	/**
	 * Return the value associated with the column: FD_BUSINESS_PARA_TWO
	 */
	public String getFdBusinessParaTwo () {
		return fdBusinessParaTwo;
	}

	/**
	 * Set the value related to the column: FD_BUSINESS_PARA_TWO
	 * @param fdBusinessParaTwo the FD_BUSINESS_PARA_TWO value
	 */
	public void setFdBusinessParaTwo (String fdBusinessParaTwo) {
		this.fdBusinessParaTwo = fdBusinessParaTwo;
	}


	/**
	 * Return the value associated with the column: FD_BUSINESS_PARA_THREE
	 */
	public String getFdBusinessParaThree () {
		return fdBusinessParaThree;
	}

	/**
	 * Set the value related to the column: FD_BUSINESS_PARA_THREE
	 * @param fdBusinessParaThree the FD_BUSINESS_PARA_THREE value
	 */
	public void setFdBusinessParaThree (String fdBusinessParaThree) {
		this.fdBusinessParaThree = fdBusinessParaThree;
	}


	/**
	 * Return the value associated with the column: FD_BUSINESS_PARA_FOUR
	 */
	public String getFdBusinessParaFour () {
		return fdBusinessParaFour;
	}

	/**
	 * Set the value related to the column: FD_BUSINESS_PARA_FOUR
	 * @param fdBusinessParaFour the FD_BUSINESS_PARA_FOUR value
	 */
	public void setFdBusinessParaFour (String fdBusinessParaFour) {
		this.fdBusinessParaFour = fdBusinessParaFour;
	}


	/**
	 * Return the value associated with the column: FD_BUSINESS_PARA_FIVE
	 */
	public String getFdBusinessParaFive () {
		return fdBusinessParaFive;
	}

	/**
	 * Set the value related to the column: FD_BUSINESS_PARA_FIVE
	 * @param fdBusinessParaFive the FD_BUSINESS_PARA_FIVE value
	 */
	public void setFdBusinessParaFive (String fdBusinessParaFive) {
		this.fdBusinessParaFive = fdBusinessParaFive;
	}


	/**
	 * Return the value associated with the column: FD_BUSINESS_PARA_SIX
	 */
	public String getFdBusinessParaSix () {
		return fdBusinessParaSix;
	}

	/**
	 * Set the value related to the column: FD_BUSINESS_PARA_SIX
	 * @param fdBusinessParaSix the FD_BUSINESS_PARA_SIX value
	 */
	public void setFdBusinessParaSix (String fdBusinessParaSix) {
		this.fdBusinessParaSix = fdBusinessParaSix;
	}



	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof com.comet.system.domain.SysMessageInfo)) return false;
		else {
			com.comet.system.domain.SysMessageInfo sysMessageInfo = (com.comet.system.domain.SysMessageInfo) obj;
			if (null == this.getId() || null == sysMessageInfo.getId()) return false;
			else return (this.getId().equals(sysMessageInfo.getId()));
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
		builder.append(fdMessageHandlerName);
		builder.append(fdMessageState);
		builder.append(fdMessageDesc);
		builder.append(fdMessageGroupName);
		builder.append(fdMessageType);
		builder.append(fdBusinessModule);
		builder.append(fdBusinessFunction);
		builder.append(fdBusinessParaOne);
		builder.append(fdBusinessParaTwo);
		builder.append(fdBusinessParaThree);
		builder.append(fdBusinessParaFour);
		builder.append(fdBusinessParaFive);
		builder.append(fdBusinessParaSix);
		return builder.toString();
	}


}