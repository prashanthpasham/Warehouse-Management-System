package com.project.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Customer_Types")
public class CustomerTypes {
	private Integer customerTypeId;
	private String customerType;
	private Date createdDate;

	@Id
	@Column(name = "CUST_TYPE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_customer_types")
	@SequenceGenerator(allocationSize = 1, name = "seq_customer_types", sequenceName = "seq_customer_types")
	public Integer getCustomerTypeId() {
		return customerTypeId;
	}

	public void setCustomerTypeId(Integer customerTypeId) {
		this.customerTypeId = customerTypeId;
	}

	@Column(name = "CUSTOMER_TYPE")
	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
