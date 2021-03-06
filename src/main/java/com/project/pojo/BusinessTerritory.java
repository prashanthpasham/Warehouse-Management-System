package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "BUSINESS_TERRITORY")
public class BusinessTerritory {
	private Integer businessId;
	private String bsIds;
	private String bsNames;
	private MasterLookUp masterLookUp;
	private int parentId;
	private String businessName;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seq_business")
	@SequenceGenerator(allocationSize = 1,sequenceName = "seq_business_territory",name = "seq_business")
	@Column(name = "BUSINESS_ID")
	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	@Column(name = "BS_IDS")
	public String getBsIds() {
		return bsIds;
	}

	public void setBsIds(String bsIds) {
		this.bsIds = bsIds;
	}

	@Column(name = "BS_NAMES")
	public String getBsNames() {
		return bsNames;
	}

	public void setBsNames(String bsNames) {
		this.bsNames = bsNames;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MASTER_LOOKUP_ID")
	@Fetch(FetchMode.JOIN)
	public MasterLookUp getMasterLookUp() {
		return masterLookUp;
	}

	public void setMasterLookUp(MasterLookUp masterLookUp) {
		this.masterLookUp = masterLookUp;
	}
    @Column(name="PARENT_BUSINESS_ID")
	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	@Column(name="BUSINESS_NAME")
	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	

}
