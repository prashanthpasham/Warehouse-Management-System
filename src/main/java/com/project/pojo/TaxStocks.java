package com.project.pojo;

import java.util.Date;

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
@Table(name = "TAX_STOCK_ITEMS")
public class TaxStocks {
	private Integer taxStockId;
	private Stock stock;
	private Date assignedDate;

	@Id
	@Column(name = "TAX_STOCK_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getTaxStockId() {
		return taxStockId;
	}

	public void setTaxStockId(Integer taxStockId) {
		this.taxStockId = taxStockId;
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

	@Column(name = "ASSIGNED_DATE")
	public Date getAssignedDate() {
		return assignedDate;
	}

	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

}
