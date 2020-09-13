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

@Table(name = "MENU_GROUP")
@Entity
public class MenuGroup {
	private Integer menuGroupId;
	private String groupName;
	private Date createdDate;
	private int menuGroupOrder;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO,generator = "seq_menu_group")
	@SequenceGenerator(allocationSize = 1,sequenceName = "seq_menu_group",name = "seq_menu_group")
	@Column(name = "MENU_GROUP_ID")
	public Integer getMenuGroupId() {
		return menuGroupId;
	}

	public void setMenuGroupId(Integer menuGroupId) {
		this.menuGroupId = menuGroupId;
	}

	@Column(name = "MENU_GROUP_NAME")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "MENU_GROUP_ORDER")
	public int getMenuGroupOrder() {
		return menuGroupOrder;
	}

	public void setMenuGroupOrder(int menuGroupOrder) {
		this.menuGroupOrder = menuGroupOrder;
	}
	

}
