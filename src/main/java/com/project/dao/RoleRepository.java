package com.project.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
	public Role findByRoleNameIgnoreCase(String roleName);
    @Query(value="from Role r")
	public List<Role> fetchRoleList(Pageable page);
}
