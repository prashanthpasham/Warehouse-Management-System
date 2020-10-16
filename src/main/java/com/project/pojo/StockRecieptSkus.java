package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "STOCK_RECIEPT_SKUS")
public class StockRecieptSkus {
	private Integer stockRecieptSkuId;
	private StockReciept stockReciept;
	private Stock stock;
	private double quantity;
	private String pack;
	private int packQty;

	@Id
	@Column(name = "stock_reciept_sku_id")
	@GeneratedValue(generator = "seq_stock_reciept_skus", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "seq_stock_reciept_skus", allocationSize = 1, sequenceName = "seq_stock_reciept_skus")
	public Integer getStockRecieptSkuId() {
		return stockRecieptSkuId;
	}

	public void setStockRecieptSkuId(Integer stockRecieptSkuId) {
		this.stockRecieptSkuId = stockRecieptSkuId;
	}

	@ManyToOne
	@JoinColumn(name = "stock_reciept_id")
	public StockReciept getStockReciept() {
		return stockReciept;
	}

	public void setStockReciept(StockReciept stockReciept) {
		this.stockReciept = stockReciept;
	}

	@ManyToOne
	@JoinColumn(name = "stock_id")
	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Column(name = "quantity")
	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "pack")
	public String getPack() {
		return pack;
	}

	public void setPack(String pack) {
		this.pack = pack;
	}

	@Column(name = "pack_qty")
	public int getPackQty() {
		return packQty;
	}

	public void setPackQty(int packQty) {
		this.packQty = packQty;
	}

}
