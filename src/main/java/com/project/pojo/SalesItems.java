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

@Table(name = "SALES_ITEMS")
@Entity
public class SalesItems {
	private Integer salesItemsId;
	private Stock stock;
	private Double orderQty;
	private String pack;
	private Double packQty;
	private Sales sales;

	@Id
	@Column(name = "SALES_ITEMS_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getSalesItemsId() {
		return salesItemsId;
	}

	public void setSalesItemsId(Integer salesItemsId) {
		this.salesItemsId = salesItemsId;
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

	@Column(name = "ORDER_QTY")
	public Double getOrderQty() {
		return orderQty;
	}

	public void setOrderQty(Double orderQty) {
		this.orderQty = orderQty;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SALES_ID")
	@Fetch(FetchMode.JOIN)
	public Sales getSales() {
		return sales;
	}

	public void setSales(Sales sales) {
		this.sales = sales;
	}

}
