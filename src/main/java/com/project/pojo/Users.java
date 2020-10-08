package com.project.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "USERS")
public class Users {
	private Integer userId;
	private String userCode;
	private String userName;
	private String password;
	private String status;
	private String email;
	private String phoneNo;
	private Role role;
	private Integer accessHierarchy;
	private BusinessTerritory businessTerritory;
	private Integer address;
	private Date lastLoginTime;
	private Date createdDate;
	private Date modifiedDate;

	@Id
	@Column(name = "USER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "seq_users")
	@SequenceGenerator(allocationSize = 1,sequenceName = "seq_users",name = "seq_users")
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name = "USER_CODE")
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name = "PHONE_NO")
	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	@JoinColumn(name = "ROLE")
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	@Column(name = "ACCESS_HIERARCHY_ID")
	public Integer getAccessHierarchy() {
		return accessHierarchy;
	}

	public void setAccessHierarchy(Integer accessHierarchy) {
		this.accessHierarchy = accessHierarchy;
	}
	@JoinColumn(name = "BUSINESS_TERRITORY_ID")
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	public BusinessTerritory getBusinessTerritory() {
		return businessTerritory;
	}

	public void setBusinessTerritory(BusinessTerritory businessTerritory) {
		this.businessTerritory = businessTerritory;
	}
	@Column(name = "Address_Id")
	public Integer getAddress() {
		return address;
	}

	public void setAddress(Integer address) {
		this.address = address;
	}
	@Column(name = "LAST_LOGIN_TIME")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "MODIFIED_DATE")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	

}
