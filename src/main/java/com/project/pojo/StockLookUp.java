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
@Table(name = "STOCK_LOOKUP")
public class StockLookUp {
	private Integer stockLookUpId;
	private String stockIds;
	private String stockNames;
	private MasterLookUp masterLookUp;

	@Id
	@Column(name = "Id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getStockLookUpId() {
		return stockLookUpId;
	}

	public void setStockLookUpId(Integer stockLookUpId) {
		this.stockLookUpId = stockLookUpId;
	}

	@Column(name = "STOCK_IDS")
	public String getStockIds() {
		return stockIds;
	}

	public void setStockIds(String stockIds) {
		this.stockIds = stockIds;
	}

	@Column(name = "STOCK_NAMES")
	public String getStockNames() {
		return stockNames;
	}

	public void setStockNames(String stockNames) {
		this.stockNames = stockNames;
	}

	@JoinColumn(name = "MASTER_LOOKUP_ID")
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	public MasterLookUp getMasterLookUp() {
		return masterLookUp;
	}

	public void setMasterLookUp(MasterLookUp masterLookUp) {
		this.masterLookUp = masterLookUp;
	}

}
