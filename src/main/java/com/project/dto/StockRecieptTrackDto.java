package com.project.dto;

public class StockRecieptTrackDto {
	private int stockRecieptSkus;
	private String serialOrBatchNo;
	private Double quantity;
	private String pack;
	private Double packQty;
	private String managedBy;

	public int getStockRecieptSkus() {
		return stockRecieptSkus;
	}

	public void setStockRecieptSkus(int stockRecieptSkus) {
		this.stockRecieptSkus = stockRecieptSkus;
	}

	public String getSerialOrBatchNo() {
		return serialOrBatchNo;
	}

	public void setSerialOrBatchNo(String serialOrBatchNo) {
		this.serialOrBatchNo = serialOrBatchNo;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public Double getPackQty() {
		return packQty;
	}

	public void setPackQty(Double packQty) {
		this.packQty = packQty;
	}

	public String getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(String managedBy) {
		this.managedBy = managedBy;
	}

}
