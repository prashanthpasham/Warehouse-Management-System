package com.project.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "WAREHOUSE")
public class Warehouse {
	private Integer warehouseId;
	private String warehouseCode;
	private String warehouseName;
	private String description;
	private String status;
	private Date createdDate;
	private Date modifiedDate;
	private MasterLookUp warehouseType;
	private BusinessTerritory businessTerritory;
	private Address address;

	@Id
	@Column(name = "WAREHOUSE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	@Column(name = "WAREHOUSE_CODE",unique=true)
	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	@Column(name = "WAREHOUSE_NAME")
	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	@Column(name = "Description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "CREATED_ON")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "MODIFIED_ON")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@JoinColumn(name = "WAREHOUSE_TYPE_ID")
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	public MasterLookUp getWarehouseType() {
		return warehouseType;
	}

	public void setWarehouseType(MasterLookUp warehouseType) {
		this.warehouseType = warehouseType;
	}

	@JoinColumn(name = "BUSINESS_ID")
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	public BusinessTerritory getBusinessTerritory() {
		return businessTerritory;
	}

	public void setBusinessTerritory(BusinessTerritory businessTerritory) {
		this.businessTerritory = businessTerritory;
	}

	@JoinColumn(name = "ADDRESS_ID")
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
