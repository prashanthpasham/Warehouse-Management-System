package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Company")
public class Company {
	private Integer companyId;
	private String name;
	private String dateFormat;
	private String logo;
	private String address;
	private Integer decimalPoints;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "seq_company_info")
	@SequenceGenerator(allocationSize = 1,sequenceName = "seq_company_info",name = "seq_company_info")
	@Column(name = "COMPANY_ID")
	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@Column(name = "COMPANY_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "DATE_FORMAT")
	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Column(name = "COMPANY_LOGO")
	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "DECIMAL_POINTS")
	public Integer getDecimalPoints() {
		return decimalPoints;
	}

	public void setDecimalPoints(Integer decimalPoints) {
		this.decimalPoints = decimalPoints;
	}

}
