package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ORGANIZATION_STRUCTURE")
@Entity
public class OrganizationStructure {
	private Integer id;
	private String name;
	private String hierarchy;
	private String parentHierarchy;
	private String parentName;
	private String parentNames;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "HIERARCHY")
	public String getHierarchy() {
		return hierarchy;
	}

	public void setHierarchy(String hierarchy) {
		this.hierarchy = hierarchy;
	}

	@Column(name = "PARENT_HIERARCHY")
	public String getParentHierarchy() {
		return parentHierarchy;
	}

	public void setParentHierarchy(String parentHierarchy) {
		this.parentHierarchy = parentHierarchy;
	}

	@Column(name = "PARENT_AH_NAME")
	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	@Column(name = "PARENT_NAMES")
	public String getParentNames() {
		return parentNames;
	}

	public void setParentNames(String parentNames) {
		this.parentNames = parentNames;
	}

}
