package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.pojo.RoleMenuItem;

@Repository
public interface RoleMenuItemRepository extends CrudRepository<RoleMenuItem, Integer> {
	@Query("from RoleMenuItem r where r.role.roleId=:Role order by r.menuItem.menuOrder")
	public List<RoleMenuItem> fetchMenusByRole(@Param("Role") int roleId);

	@Modifying
	@Query("delete from RoleMenuItem r where r.role.roleId=:role")
	public int deleteByRoleId(@Param("role") int id);
}
