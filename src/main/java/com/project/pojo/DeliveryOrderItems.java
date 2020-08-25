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

@Table(name = "DELIVERY_ORDER_ITEMS")
@Entity
public class DeliveryOrderItems {
	private Integer deliveryItemId;
	private Stock stock;
	private String pack;
	private Double packQty;
	private Deliveries deliveries;

	@Id
	@Column(name = "DELIVERY_ITEM_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getDeliveryItemId() {
		return deliveryItemId;
	}

	public void setDeliveryItemId(Integer deliveryItemId) {
		this.deliveryItemId = deliveryItemId;
	}

	@JoinColumn(name = "STOCK_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
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

	@JoinColumn(name = "DELIVERY_ORDER_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	public Deliveries getDeliveries() {
		return deliveries;
	}

	public void setDeliveries(Deliveries deliveries) {
		this.deliveries = deliveries;
	}

}
