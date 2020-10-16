package com.project.pojo;

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

@Table(name = "STOCK_RECIEPT_SKUS_TRACK")
@Entity
public class StockRecieptSkusTrack {
	private Integer trackId;
	private StockRecieptSkus stockRecieptSkus;
	private String serialOrBatchNo;
	private Double quantity;
	private String pack;
	private Double packQty;
	private String managedBy;

	@Id
	@Column(name = "TRACK_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_STOCK_RECIEPT_SKUS_TRACK")
	@SequenceGenerator(allocationSize = 1, name = "SEQ_STOCK_RECIEPT_SKUS_TRACK", sequenceName = "SEQ_STOCK_RECIEPT_SKUS_TRACK")
	public Integer getTrackId() {
		return trackId;
	}

	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}

	@JoinColumn(name = "STOCK_RECIEPT_SKUS")
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	public StockRecieptSkus getStockRecieptSkus() {
		return stockRecieptSkus;
	}

	public void setStockRecieptSkus(StockRecieptSkus stockRecieptSkus) {
		this.stockRecieptSkus = stockRecieptSkus;
	}

	@Column(name = "SERIAL_BATCH_NO")
	public String getSerialOrBatchNo() {
		return serialOrBatchNo;
	}

	public void setSerialOrBatchNo(String serialOrBatchNo) {
		this.serialOrBatchNo = serialOrBatchNo;
	}

	@Column(name = "QUANTITY")
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "PACK")
	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	@Column(name = "PACK_QTY")
	public Double getPackQty() {
		return packQty;
	}

	public void setPackQty(Double packQty) {
		this.packQty = packQty;
	}

	@Column(name = "MANAGED_BY")
	public String getManagedBy() {
		return managedBy;
	}

	public void setManagedBy(String managedBy) {
		this.managedBy = managedBy;
	}
}
