package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.Role;
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {

}
