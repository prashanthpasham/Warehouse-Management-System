package com.project.pojo;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "STOCK")
public class Stock {
	private Integer stockId;
	private String skuCode;
	private String skuDescription;
	private String status;
	private Double price;
	private Double unitPrice;
	private String managedBy;
	private UOMConfiguration uomConfigId;
	private Date createdDate;
	private Date modifiedDate;
	private String defaultPackSize;
	private Double defaultPackQty;
	private List<Images> images;

	@Id
	@Column(name = "STOCK_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getStockId() {
		return stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	@Column(name = "SKU_CODE",unique=true)
	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	@Column(name = "SKU_DESCRIPTION")
	public String getSkuDescription() {
		return skuDescription;
	}

	public void setSkuDescription(String skuDescription) {
		this.skuDescription = skuDescription;
	}

	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "PRICE")
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Column(name = "UNIT_PRICE")
	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "MANAGED_BY")
	public String getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(String managedBy) {
		this.managedBy = managedBy;
	}

	@JoinColumn(name = "UOM_CONFIG_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.SUBSELECT)
	public UOMConfiguration getUomConfigId() {
		return uomConfigId;
	}

	public void setUomConfigId(UOMConfiguration uomConfigId) {
		this.uomConfigId = uomConfigId;
	}

	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "MODIFIED_DATE")
	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
    @Column(name="DEFAULT_PACK_SIZE")
	public String getDefaultPackSize() {
		return defaultPackSize;
	}

	public void setDefaultPackSize(String defaultPackSize) {
		this.defaultPackSize = defaultPackSize;
	}
	 @Column(name="DEFAULT_PACK_QTY")
	public Double getDefaultPackQty() {
		return defaultPackQty;
	}

	public void setDefaultPackQty(Double defaultPackQty) {
		this.defaultPackQty = defaultPackQty;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "stockId")
	@Fetch(FetchMode.JOIN)
	public List<Images> getImages() {
		return images;
	}

	public void setImages(List<Images> images) {
		this.images = images;
	}

}
