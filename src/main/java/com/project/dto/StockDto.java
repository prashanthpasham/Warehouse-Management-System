package com.project.dto;

import java.util.List;


public class StockDto {
	private Integer stockId;
	private String skuCode;
	private String skuDescription;
	private String status;
	private Double price;
	private Double unitPrice;
	private String managedBy;
	private int uomConfigId;
	private String defaultPackSize;
	private Double defaultPackQty;
	private List<String> images;
	private int stockLookupId;

	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public String getSkuDescription() {
		return skuDescription;
	}

	public void setSkuDescription(String skuDescription) {
		this.skuDescription = skuDescription;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(String managedBy) {
		this.managedBy = managedBy;
	}

	public int getUomConfigId() {
		return uomConfigId;
	}

	public void setUomConfigId(int uomConfigId) {
		this.uomConfigId = uomConfigId;
	}

	public String getDefaultPackSize() {
		return defaultPackSize;
	}

	public void setDefaultPackSize(String defaultPackSize) {
		this.defaultPackSize = defaultPackSize;
	}

	public Double getDefaultPackQty() {
		return defaultPackQty;
	}

	public void setDefaultPackQty(Double defaultPackQty) {
		this.defaultPackQty = defaultPackQty;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public int getStockLookupId() {
		return stockLookupId;
	}

	public void setStockLookupId(int stockLookupId) {
		this.stockLookupId = stockLookupId;
	}
	

}
