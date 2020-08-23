package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "WAREHOUSE_INVENTORY")
public class WarehouseInventoryDetails {
	private Integer detailsId;
	private Stock stock;
	private WarehouseInventory inventory;
	private Double goodQty;
	private Double damagedQty;
	private Double returnQty;

	@Id
	@Column(name = "INVENTORY_DETAILS_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(Integer detailsId) {
		this.detailsId = detailsId;
	}

	@JoinColumn(name = "STOCK_ID")
	@ManyToOne
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@JoinColumn(name = "STOCK_ID")
	@ManyToOne
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

}
