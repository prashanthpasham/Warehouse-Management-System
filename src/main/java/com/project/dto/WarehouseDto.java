package com.project.dto;

import java.util.Date;

import com.project.pojo.Address;

public class WarehouseDto {
	private Integer warehouseId;
	private String warehouseCode;
	private String warehouseName;
	private String description;
	private String status;
	private Date createdDate;
	private Date modifiedDate;
	private int warehouseTypeId;
	private int businessTerritoryId;
	private Address address;

	public Integer getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(Integer warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getWarehouseName() {
		return warehouseName;
	}

	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getWarehouseTypeId() {
		return warehouseTypeId;
	}

	public void setWarehouseTypeId(int warehouseTypeId) {
		this.warehouseTypeId = warehouseTypeId;
	}

	public int getBusinessTerritoryId() {
		return businessTerritoryId;
	}

	public void setBusinessTerritoryId(int businessTerritoryId) {
		this.businessTerritoryId = businessTerritoryId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
