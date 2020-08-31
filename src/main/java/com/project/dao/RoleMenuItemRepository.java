package com.project.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.RoleMenuItem;
@Repository
public interface RoleMenuItemRepository extends CrudRepository<RoleMenuItem, Integer> {
	
}
