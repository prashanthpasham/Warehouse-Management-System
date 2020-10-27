package com.project.dto;

import java.util.List;

public class SalesItemsDto {
	private int stockId;
	private Double orderQty;
	private String pack;
	private Double packQty;
	private int salesId;
    private List<SalesItemsTrackDto> trackDetails;
	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public Double getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Double orderQty) {
		this.orderQty = orderQty;
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

	public int getSalesId() {
		return salesId;
	}

	public void setSalesId(int salesId) {
		this.salesId = salesId;
	}

	public List<SalesItemsTrackDto> getTrackDetails() {
		return trackDetails;
	}

	public void setTrackDetails(List<SalesItemsTrackDto> trackDetails) {
		this.trackDetails = trackDetails;
	}
	
}
