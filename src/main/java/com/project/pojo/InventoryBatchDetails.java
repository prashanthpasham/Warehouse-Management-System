package com.project.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Table(name = "INVENTORY_BATCH_DETAILS")
@Entity
public class InventoryBatchDetails {
	private Integer inventoryId;
	private String batchNo;
	private String batchDetails;
	private Double availableQty;
	private Date manfactureDate;
	private Date expireDate;
	private WarehouseInventoryDetails inventoryDetails;

	@Id
	@Column(name = "INVENTORY_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_inventory_batch_details")
	@SequenceGenerator(allocationSize = 1,name = "seq_inventory_batch_details",sequenceName = "seq_inventory_batch_details")
	public Integer getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}

	@Column(name = "BATCH_NO")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	@Column(name = "BATCH_DETAILS")
	public String getBatchDetails() {
		return batchDetails;
	}

	public void setBatchDetails(String batchDetails) {
		this.batchDetails = batchDetails;
	}

	@Column(name = "AVAILABLE_QTY")
	public Double getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(Double availableQty) {
		this.availableQty = availableQty;
	}

	@Column(name = "MANFACTURE_DATE")
	public Date getManfactureDate() {
		return manfactureDate;
	}

	public void setManfactureDate(Date manfactureDate) {
		this.manfactureDate = manfactureDate;
	}

	@Column(name = "EXPIRE_DATE")
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	@JoinColumn(name = "INVENTORY_DETAILS_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	public WarehouseInventoryDetails getInventoryDetails() {
		return inventoryDetails;
	}

	public void setInventoryDetails(WarehouseInventoryDetails inventoryDetails) {
		this.inventoryDetails = inventoryDetails;
	}

}
