package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MASTER_LOOKUP")
public class MasterLookUp {
	private Integer masterId;
	private String name;
	private Integer parentMasterId;
	private String type;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "MASTER_ID")
	public Integer getMasterId() {
		return masterId;
	}

	public void setMasterId(Integer masterId) {
		this.masterId = masterId;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "PARENT_ID")
	public Integer getParentMasterId() {
		return parentMasterId;
	}

	public void setParentMasterId(Integer parentMasterId) {
		this.parentMasterId = parentMasterId;
	}

	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
