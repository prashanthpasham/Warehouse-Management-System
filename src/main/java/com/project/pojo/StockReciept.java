package com.project.pojo;

import java.util.Date;

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
@Table(name = "STOCK_RECIEPT")
public class StockReciept {
	private Integer stockRecieptId;
	private Warehouse warehouse;
	private Date createdDate;
	private String salesPerson;

	@Id
	@GeneratedValue(generator = "seq_stock_reciept", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(sequenceName = "seq_stock_reciept", allocationSize = 1, name = "seq_stock_reciept")
	@Column(name = "STOCK_RECIEPT_ID")
	public Integer getStockRecieptId() {
		return stockRecieptId;
	}

	public void setStockRecieptId(Integer stockRecieptId) {
		this.stockRecieptId = stockRecieptId;
	}

	@ManyToOne
	@JoinColumn(name = "WAREHOUSE_ID")
	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "SALES_PERSON")
	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

}
