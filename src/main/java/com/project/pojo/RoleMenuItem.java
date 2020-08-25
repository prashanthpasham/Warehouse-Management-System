package com.project.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Table(name = "ROLE_MENU_ITEM")
@Entity
public class RoleMenuItem {
	private Integer roleMenuId;
	private MenuItem menuItem;
	private Role role;
	private boolean addOperation;
	private boolean editOperation;
	private boolean deleteOperation;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_MENU_ID")
	public Integer getRoleMenuId() {
		return roleMenuId;
	}

	public void setRoleMenuId(Integer roleMenuId) {
		this.roleMenuId = roleMenuId;
	}

	@JoinColumn(name = "MENU_ITEM_ID")
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	public MenuItem getMenuItem() {
		return menuItem;
	}

	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}

	@JoinColumn(name = "ROLE_ID")
	@ManyToOne
	@Fetch(FetchMode.JOIN)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Column(name = "ADD_OPERATION",columnDefinition="Number")
	public boolean isAddOperation() {
		return addOperation;
	}

	public void setAddOperation(boolean addOperation) {
		this.addOperation = addOperation;
	}

	@Column(name = "EDIT_OPERATION",columnDefinition="Number(10,0) NOT NULL")
	public boolean isEditOperation() {
		return editOperation;
	}

	public void setEditOperation(boolean editOperation) {
		this.editOperation = editOperation;
	}

	@Column(name = "DELETE_OPERATION",columnDefinition="Number")
	public boolean isDeleteOperation() {
		return deleteOperation;
	}

	public void setDeleteOperation(boolean deleteOperation) {
		this.deleteOperation = deleteOperation;
	}

}
