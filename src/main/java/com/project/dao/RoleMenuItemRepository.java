package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.pojo.RoleMenuItem;

@Repository
public interface RoleMenuItemRepository extends CrudRepository<RoleMenuItem, Integer> {
	@Query("from RoleMenuItem r where r.role.roleId=:Role")
	public List<RoleMenuItem> fetchMenusByRole(@Param("Role") int roleId);
}
