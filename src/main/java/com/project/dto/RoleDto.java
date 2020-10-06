package com.project.dto;

import java.util.List;

public class RoleDto {
	private int id;
	private String roleName;
	private String description;
	private List<RoleMenuDto> roleMenus;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<RoleMenuDto> getRoleMenus() {
		return roleMenus;
	}

	public void setRoleMenus(List<RoleMenuDto> roleMenus) {
		this.roleMenus = roleMenus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
