package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "DELIVERY_ITEMS_TRACK")
public class DeliveryOrdersTrack {

	private Integer trackId;
	private DeliveryOrderItems items;
	private String serialOrBatchNo;
	private Double quantity;
	private String pack;
	private Double packQty;
	private String managedBy;

	@Id
	@Column(name = "ORDER_TRACK_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getTrackId() {
		return trackId;
	}

	public void setTrackId(Integer trackId) {
		this.trackId = trackId;
	}

	@JoinColumn(name = "ORDER_ITEMS_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	public DeliveryOrderItems getItems() {
		return items;
	}

	public void setDeliveryOrderItems(DeliveryOrderItems items) {
		this.items = items;
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
