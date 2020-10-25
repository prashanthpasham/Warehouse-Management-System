package com.project.dto;

import java.util.List;

public class StockRecieptSkusDto {
	private int stockId;
	private double quantity;
	private String pack;
	private int packQty;
    private List<SerialBatchDto> trackDetails;
	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	public int getPackQty() {
		return packQty;
	}

	public void setPackQty(int packQty) {
		this.packQty = packQty;
	}

	public List<SerialBatchDto> getTrackDetails() {
		return trackDetails;
	}

	public void setTrackDetails(List<SerialBatchDto> trackDetails) {
		this.trackDetails = trackDetails;
	}
	

}
