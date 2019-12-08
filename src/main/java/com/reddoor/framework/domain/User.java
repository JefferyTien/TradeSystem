package com.reddoor.framework.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.bind.annotation.InitBinder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.reddoor.framework.common.json.JsonDateSerializer;

public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LOGIN_NAME")
    private String loginName;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "SALT")
    private String salt;

    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "GENDER")
    private Short gender;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "ICON")
    private String icon;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "STATE")
    private String state;

    @Column(name = "LOGIN_COUNT")
    private Integer loginCount;

    // @DateTimeFormat 用于将前台日期字符串转换为后台日期, 与BaseController中@InitBinder对Date绑定了Editor是同样作用
//    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  
    // 方法一: 用于SpringMVC的@ResponseBody转换模型到json时,将日期转换为字符串
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")  
    // 方法二: 用于SpringMVC的@ResponseBody转换模型到json时,将日期转换为字符串, 自定义的转换类
//    @JsonSerialize(using=JsonDateSerializer.class)
    @Column(name = "PREVIOUS_VISIT")
    private Date previousVisit;

    @Column(name = "LAST_VISIT")
    private Date lastVisit;

    @Column(name = "DEL_FLAG")
    private String delFlag;

    @Column(name = "DESCRIPTION")
    private String description;
    
    @Transient
    private String plainPassword;

    @Transient
    private Set<UserRole> userRoles = new HashSet<UserRole>(0);
    
    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return LOGIN_NAME
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return PASSWORD
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return SALT
     */
    public String getSalt() {
        return salt;
    }

    /**
     * @param salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * @return BIRTHDAY
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return GENDER
     */
    public Short getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(Short gender) {
        this.gender = gender;
    }

    /**
     * @return EMAIL
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return PHONE
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return ICON
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return CREATE_DATE
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return STATE
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return LOGIN_COUNT
     */
    public Integer getLoginCount() {
        return loginCount;
    }

    /**
     * @param loginCount
     */
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    /**
     * @return PREVIOUS_VISIT
     */
    public Date getPreviousVisit() {
        return previousVisit;
    }

    /**
     * @param previousVisit
     */
    public void setPreviousVisit(Date previousVisit) {
        this.previousVisit = previousVisit;
    }

    /**
     * @return LAST_VISIT
     */
    public Date getLastVisit() {
        return lastVisit;
    }

    /**
     * @param lastVisit
     */
    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    /**
     * @return DEL_FLAG
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * @return DESCRIPTION
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public String getPlainPassword() {
		return plainPassword;
	}

	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
}