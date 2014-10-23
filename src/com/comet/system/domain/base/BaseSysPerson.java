package com.comet.system.domain.base;

import com.comet.system.domain.SysCodeDetail;
import com.comet.system.domain.SysPerson;
import com.comet.system.domain.SysPersonDept;
import com.comet.core.entity.Auditable;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * This is an object that contains data related to the SYS_PERSON table.
 * Do not modify this class because it will be overwritten if the configuration file
 * related to this class is modified.
 * TableComment : 人员
 * SyncTemplatepatterns : list\w*
 * SyncDao : false
 * TableName : 人员管理
 * SyncBoolean : get
 * SyncJsp : true
 * Treeable : false
 * SubSystem : system
 * Projectable : false
 *
 * @hibernate.class
 *  table="SYS_PERSON"
 */
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public abstract class BaseSysPerson  implements Serializable, Auditable {

	public static String REF = "SysPerson";
	public static String PROP_UPDATE_TIME = "updateTime";
	public static String PROP_SEX = "sex";
	public static String PROP_QQ_CODE = "qqCode";
	public static String PROP_OFFICE_TEL = "officeTel";
	public static String PROP_EDUCATION = "education";
	public static String PROP_CREATE_TIME = "createTime";
	public static String PROP_MEMO = "memo";
	public static String PROP_FAX_TEL = "faxTel";
	public static String PROP_AGE = "age";
	public static String PROP_ZIPCODE = "zipcode";
	public static String PROP_BORN_DATE = "bornDate";
	public static String PROP_CREATE_USER = "createUser";
	public static String PROP_EMAIL = "email";
	public static String PROP_MOBILE = "mobile";
	public static String PROP_CODE = "code";
	public static String PROP_MSN_CODE = "msnCode";
	public static String PROP_CARD = "card";
	public static String PROP_NAME = "name";
	public static String PROP_BORN_PLACE = "bornPlace";
	public static String PROP_WORK_YEAR = "workYear";
	public static String PROP_ID = "id";
	public static String PROP_UPDATE_USER = "updateUser";
	public static String PROP_COUNTRY = "country";
	public static String PROP_PROVINCE = "province";
	public static String PROP_CITY = "city";
	public static String PROP_ROOM = "room";
	public static String PROP_HALL = "hall";
	public static String PROP_TOILET = "toilet";
	public static String PROP_AREA = "area";
	public static String PROP_MEMBERS = "members";


	// constructors
	public BaseSysPerson () {
		initialize();
	}

	/**
	 * Constructor for primary key
	 */
	public BaseSysPerson (Long id) {
		this.setId(id);
		initialize();
	}

	protected void initialize () {}

	private int hashCode = Integer.MIN_VALUE;

	// primary key
	private Long id;

	// fields
    /*编号*/
    /*编号*/
	private String code;
	
    /*姓名*/
    /*姓名*/
	private String name;
	
    /*身份证号*/
    /*身份证号*/
	private String card;
	
    /*年龄*/
    /*年龄:如果输入身份证号码，年龄自动根据身份证进行计算*/
	private Integer age;
	
    /*性别*/
    /*性别*/
	private Boolean sex;
	
    /*出生年月*/
    /*出生年月*/
	private java.sql.Date bornDate;
	
    /*籍贯*/
    /*籍贯*/
	private String bornPlace;
	
    /*移动电话*/
    /*移动电话*/
	private String mobile;
	
    /*固定电话*/
    /*固定电话*/
	private String officeTel;
	
    /*传真*/
    /*传真*/
	private String faxTel;
	
    /*邮件*/
    /*邮件*/
	private String email;
	
    /*邮政编码*/
    /*邮政编码*/
	private String zipcode;
	
    /*工作年限*/
    /*工作年限*/
	private Integer workYear;
	
    /*MSN_CODE*/
    /*MSN*/
	private String msnCode;
	
    /*QQ_CODE*/
    /*QQ_CODE*/
	private String qqCode;
	
    /*备注*/
    /*备注*/
	private String memo;

	private String country;
	private String province;
	private String city;
	private BigDecimal room;
	private BigDecimal hall;
	private BigDecimal toilet;
	private BigDecimal area;
	private BigDecimal members;

    private Boolean isValidate;



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
	private SysCodeDetail education;

    @JsonIgnore
    private java.util.Set<SysPersonDept> sysPersonDepts;

    private SysCodeDetail cardType;
    private SysCodeDetail tradeType;
    private SysCodeDetail region;
    private SysCodeDetail nation;
    private String hukouAddress;
    private String address;

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
	 * Return the value associated with the column: CARD
	 */
	public String getCard () {
		return card;
	}

	/**
	 * Set the value related to the column: CARD
	 * @param card the CARD value
	 */
	public void setCard (String card) {
		this.card = card;
	}


	/**
	 * Return the value associated with the column: AGE
	 */
	public Integer getAge () {
		return age;
	}

	/**
	 * Set the value related to the column: AGE
	 * @param age the AGE value
	 */
	public void setAge (Integer age) {
		this.age = age;
	}


	/**
	 * Return the value associated with the column: SEX
	 */
	public Boolean getSex () {
		return sex;
	}

	/**
	 * Set the value related to the column: SEX
	 * @param sex the SEX value
	 */
	public void setSex (Boolean sex) {
		this.sex = sex;
	}


	/**
	 * Return the value associated with the column: BORN_DATE
	 */
	public java.sql.Date getBornDate () {
		return bornDate;
	}

	/**
	 * Set the value related to the column: BORN_DATE
	 * @param bornDate the BORN_DATE value
	 */
	public void setBornDate (java.sql.Date bornDate) {
		this.bornDate = bornDate;
	}


	/**
	 * Return the value associated with the column: BORN_PLACE
	 */
	public String getBornPlace () {
		return bornPlace;
	}

	/**
	 * Set the value related to the column: BORN_PLACE
	 * @param bornPlace the BORN_PLACE value
	 */
	public void setBornPlace (String bornPlace) {
		this.bornPlace = bornPlace;
	}


	/**
	 * Return the value associated with the column: MOBILE
	 */
	public String getMobile () {
		return mobile;
	}

	/**
	 * Set the value related to the column: MOBILE
	 * @param mobile the MOBILE value
	 */
	public void setMobile (String mobile) {
		this.mobile = mobile;
	}


	/**
	 * Return the value associated with the column: OFFICE_TEL
	 */
	public String getOfficeTel () {
		return officeTel;
	}

	/**
	 * Set the value related to the column: OFFICE_TEL
	 * @param officeTel the OFFICE_TEL value
	 */
	public void setOfficeTel (String officeTel) {
		this.officeTel = officeTel;
	}


	/**
	 * Return the value associated with the column: FAX_TEL
	 */
	public String getFaxTel () {
		return faxTel;
	}

	/**
	 * Set the value related to the column: FAX_TEL
	 * @param faxTel the FAX_TEL value
	 */
	public void setFaxTel (String faxTel) {
		this.faxTel = faxTel;
	}


	/**
	 * Return the value associated with the column: EMAIL
	 */
	public String getEmail () {
		return email;
	}

	/**
	 * Set the value related to the column: EMAIL
	 * @param email the EMAIL value
	 */
	public void setEmail (String email) {
		this.email = email;
	}


	/**
	 * Return the value associated with the column: ZIPCODE
	 */
	public String getZipcode () {
		return zipcode;
	}

	/**
	 * Set the value related to the column: ZIPCODE
	 * @param zipcode the ZIPCODE value
	 */
	public void setZipcode (String zipcode) {
		this.zipcode = zipcode;
	}


	/**
	 * Return the value associated with the column: WORK_YEAR
	 */
	public Integer getWorkYear () {
		return workYear;
	}

	/**
	 * Set the value related to the column: WORK_YEAR
	 * @param workYear the WORK_YEAR value
	 */
	public void setWorkYear (Integer workYear) {
		this.workYear = workYear;
	}


	/**
	 * Return the value associated with the column: MSN_CODE
	 */
	public String getMsnCode () {
		return msnCode;
	}

	/**
	 * Set the value related to the column: MSN_CODE
	 * @param msnCode the MSN_CODE value
	 */
	public void setMsnCode (String msnCode) {
		this.msnCode = msnCode;
	}


	/**
	 * Return the value associated with the column: QQ_CODE
	 */
	public String getQqCode () {
		return qqCode;
	}

	/**
	 * Set the value related to the column: QQ_CODE
	 * @param qqCode the QQ_CODE value
	 */
	public void setQqCode (String qqCode) {
		this.qqCode = qqCode;
	}


	/**
	 * Return the value associated with the column: MEMO
	 */
	public String getMemo () {
		return memo;
	}

	/**
	 * Set the value related to the column: MEMO
	 * @param memo the MEMO value
	 */
	public void setMemo (String memo) {
		this.memo = memo;
	}

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getRoom() {
        return room;
    }

    public void setRoom(BigDecimal room) {
        this.room = room;
    }

    public BigDecimal getHall() {
        return hall;
    }

    public void setHall(BigDecimal hall) {
        this.hall = hall;
    }

    public BigDecimal getToilet() {
        return toilet;
    }

    public void setToilet(BigDecimal toilet) {
        this.toilet = toilet;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMembers() {
        return members;
    }

    public void setMembers(BigDecimal members) {
        this.members = members;
    }

    public Boolean getIsValidate() {
        return isValidate;
    }

    public void setIsValidate(Boolean validate) {
        isValidate = validate;
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
	 * Return the value associated with the column: EDUCATION
	 */
	public SysCodeDetail getEducation () {
		return education;
	}

	/**
	 * Set the value related to the column: EDUCATION
	 * @param education the EDUCATION value
	 */
	public void setEducation (SysCodeDetail education) {
		this.education = education;
	}

    /**
     * Return the value associated with the column: sysPersons
     */
    @JsonIgnore
    public java.util.Set<SysPersonDept> getSysPersonDepts() {
        if (sysPersonDepts == null) {
            sysPersonDepts = new java.util.LinkedHashSet<SysPersonDept>();
        }
        return sysPersonDepts;
    }

    public SysCodeDetail getCardType() {
        return cardType;
    }

    public void setCardType(SysCodeDetail cardType) {
        this.cardType = cardType;
    }

    public SysCodeDetail getTradeType() {
        return tradeType;
    }

    public void setTradeType(SysCodeDetail tradeType) {
        this.tradeType = tradeType;
    }

    public SysCodeDetail getRegion() {
        return region;
    }

    public void setRegion(SysCodeDetail region) {
        this.region = region;
    }

    public SysCodeDetail getNation() {
        return nation;
    }

    public void setNation(SysCodeDetail nation) {
        this.nation = nation;
    }

    public String getHukouAddress() {
        return hukouAddress;
    }

    public void setHukouAddress(String hukouAddress) {
        this.hukouAddress = hukouAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Set the value related to the column: sysPersons
     *
     * @param sysPersonDepts the sysPersons value
     */
    public void setSysPersonDepts(java.util.Set<SysPersonDept> sysPersonDepts) {
        this.sysPersonDepts = sysPersonDepts;
    }

    public void addTosysPersonDepts(SysPersonDept sysPersonDept) {
        if (null == getSysPersonDepts()) setSysPersonDepts(new java.util.LinkedHashSet<SysPersonDept>());
        this.sysPersonDepts.add(sysPersonDept);
    }

	public boolean equals (Object obj) {
		if (null == obj) return false;
		if (!(obj instanceof SysPerson)) return false;
		else {
			SysPerson sysPerson = (SysPerson) obj;
			if (null == this.getId() || null == sysPerson.getId()) return false;
			else return (this.getId().equals(sysPerson.getId()));
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
		builder.append(card);
		builder.append(age);
		builder.append(sex);
		builder.append(bornDate);
		builder.append(bornPlace);
		builder.append(mobile);
		builder.append(officeTel);
		builder.append(faxTel);
		builder.append(email);
		builder.append(zipcode);
		builder.append(workYear);
		builder.append(msnCode);
		builder.append(qqCode);
		builder.append(memo);
		builder.append(createTime);
		builder.append(updateTime);
		builder.append(updateUser);
		builder.append(createUser);
		return builder.toString();
	}


}