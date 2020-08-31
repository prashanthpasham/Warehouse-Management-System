package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.MenuGroup;
@Repository
public interface MenuGroupRepository extends CrudRepository<MenuGroup, Integer> {

}
