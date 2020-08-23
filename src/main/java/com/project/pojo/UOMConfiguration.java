package com.project.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "UOM_CONFIG")
public class UOMConfiguration {
	private Integer uomConfigId;
	private String uomName;
	private String childUom;
	private String childUOMId;
	private Date createdDate;

	@Id
	@Column(name = "UOM_CONFIG_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
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

	@Column(name = "CHILD_UOM")
	public String getChildUom() {
		return childUom;
	}

	public void setChildUom(String childUom) {
		this.childUom = childUom;
	}

	@Column(name = "CHILD_UOM_ID")
	public String getChildUOMId() {
		return childUOMId;
	}

	public void setChildUOMId(String childUOMId) {
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

}
