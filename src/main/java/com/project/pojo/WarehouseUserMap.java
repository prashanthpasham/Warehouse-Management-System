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

@Table(name = "WAREHOUSE_USER_MAP")
@Entity
public class WarehouseUserMap {
	private Integer mappingId;
	private Warehouse warehouse;
	private Users salesPerson;
    private Date mappedDate;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_wh_users")
	@SequenceGenerator(allocationSize = 1, sequenceName = "seq_wh_users", name = "seq_wh_users")
	@Column(name = "MAPPING_ID")
	public Integer getMappingId() {
		return mappingId;
	}

	public void setMappingId(Integer mappingId) {
		this.mappingId = mappingId;
	}

	@ManyToOne
	@JoinColumn(name = "WAREHOUSE_ID")
	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@ManyToOne
	@JoinColumn(name = "SALES_PERSON")
	public Users getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(Users salesPerson) {
		this.salesPerson = salesPerson;
	}
   @Column(name="MAPPED_DATE")
	public Date getMappedDate() {
		return mappedDate;
	}

	public void setMappedDate(Date mappedDate) {
		this.mappedDate = mappedDate;
	}
	
	

}
