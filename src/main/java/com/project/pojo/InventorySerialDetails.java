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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "INVENTORY_SERIAL_DETAILS")
public class InventorySerialDetails {
	private Integer serialId;
	private WarehouseInventoryDetails inventoryDetails;
	private String serialNo;
	private String status;
	private Date createdDate;
	private Date manfacturedDate;
	private Date expireDate;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "SERIAL_ID")
	public Integer getSerialId() {
		return serialId;
	}

	public void setSerialId(Integer serialId) {
		this.serialId = serialId;
	}

	@JoinColumn(name = "INVENTORY_DETAILS_ID")
	@ManyToOne
	public WarehouseInventoryDetails getInventoryDetails() {
		return inventoryDetails;
	}

	public void setInventoryDetails(WarehouseInventoryDetails inventoryDetails) {
		this.inventoryDetails = inventoryDetails;
	}

	@Column(name = "Serial_No")
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
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

	@Temporal(TemporalType.DATE)
	@Column(name = "MANFACTURE_DATE")
	public Date getManfacturedDate() {
		return manfacturedDate;
	}

	public void setManfacturedDate(Date manfacturedDate) {
		this.manfacturedDate = manfacturedDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRE_DATE")
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

}
