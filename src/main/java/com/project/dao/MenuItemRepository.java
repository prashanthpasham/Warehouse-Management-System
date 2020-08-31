package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.MenuItem;
@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {

}
