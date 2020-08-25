package com.project.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Access_Hierarchy")
public class UserAccessHierarchy {

	private Integer id;
	private String userName;
	private String accessHierarchy;
	private Date createdDate;
	private Date endDate;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_AH_HISTORY_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "ACCESS_HIERARCHY")
	public String getAccessHierarchy() {
		return accessHierarchy;
	}

	public void setAccessHierarchy(String accessHierarchy) {
		this.accessHierarchy = accessHierarchy;
	}

	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
