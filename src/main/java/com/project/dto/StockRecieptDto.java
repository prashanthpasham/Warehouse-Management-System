package com.project.dto;

import java.util.List;

public class StockRecieptDto {
	private int warehouseId;
	private String salesPerson;
	private List<StockRecieptSkusDto> skus;

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

	public List<StockRecieptSkusDto> getSkus() {
		return skus;
	}

	public void setSkus(List<StockRecieptSkusDto> skus) {
		this.skus = skus;
	}

}
