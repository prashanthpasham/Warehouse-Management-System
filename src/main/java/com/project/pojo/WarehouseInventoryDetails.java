package com.project.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "WAREHOUSE_INVENTORY_DETAILS")
public class WarehouseInventoryDetails {
	private Integer detailsId;
	private Stock stock;
	private WarehouseInventory inventory;
	private Double goodQty;
	private Double damagedQty;
	private Double returnQty;
	private List<InventorySerialDetails> inventorySerialList;
	private List<InventoryBatchDetails> inventoryBatchList;

	@Id
	@Column(name = "INVENTORY_DETAILS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_wh_inevntory_details")
	@SequenceGenerator(allocationSize = 1,name = "seq_wh_inevntory_details",sequenceName = "seq_wh_inevntory_details")
	public Integer getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}

	@JoinColumn(name = "STOCK_ID")
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@JoinColumn(name = "INVENTORY_ID")
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	public WarehouseInventory getInventory() {
		return inventory;
	}

	public void setInventory(WarehouseInventory inventory) {
		this.inventory = inventory;
	}

	@Column(name = "GOOD_QTY")
	public Double getGoodQty() {
		return goodQty;
	}

	public void setGoodQty(Double goodQty) {
		this.goodQty = goodQty;
	}

	@Column(name = "DAMAGED_QTY")
	public Double getDamagedQty() {
		return damagedQty;
	}

	public void setDamagedQty(Double damagedQty) {
		this.damagedQty = damagedQty;
	}

	@Column(name = "RETURN_QTY")
	public Double getReturnQty() {
		return returnQty;
	}

	public void setReturnQty(Double returnQty) {
		this.returnQty = returnQty;
	}
    @Transient
	public List<InventorySerialDetails> getInventorySerialList() {
		return inventorySerialList;
	}

	public void setInventorySerialList(List<InventorySerialDetails> inventorySerialList) {
		this.inventorySerialList = inventorySerialList;
	}
	@Transient
	public List<InventoryBatchDetails> getInventoryBatchList() {
		return inventoryBatchList;
	}

	public void setInventoryBatchList(List<InventoryBatchDetails> inventoryBatchList) {
		this.inventoryBatchList = inventoryBatchList;
	}

	
}
