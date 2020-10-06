package com.project.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "UOM_CONFIG")
public class UOMConfiguration {
	private Integer uomConfigId;
	private String uomName;
	private String childUomIds;
	private String childUomNames;
	private int childUOMId;
	private Date createdDate;
	private int quantity;

	@Id
	@Column(name = "UOM_CONFIG_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_uom_config")
	@SequenceGenerator(allocationSize = 1,sequenceName = "seq_uom_config",name = "seq_uom_config")
	public Integer getUomConfigId() {
		return uomConfigId;
	}

	public void setUomConfigId(Integer uomConfigId) {
		this.uomConfigId = uomConfigId;
	}

	@Column(name = "UOM_NAME")
	public String getUomName() {
		return uomName;
	}

	public void setUomName(String uomName) {
		this.uomName = uomName;
	}

	@Column(name = "CHILD_UOM_IDS")
	public String getChildUomIds() {
		return childUomIds;
	}

	public void setChildUomIds(String childUomIds) {
		this.childUomIds = childUomIds;
	}

	@Column(name = "CHILD_UOM")
	public String getChildUomNames() {
		return childUomNames;
	}

	public void setChildUomNames(String childUomNames) {
		this.childUomNames = childUomNames;
	}
	@Column(name = "CHILD_UOM_ID")
	public int getChildUOMId() {
		return childUOMId;
	}
	public void setChildUOMId(int childUOMId) {
		this.childUOMId = childUOMId;
	}

	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.DATE)
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "QUANTITY")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
