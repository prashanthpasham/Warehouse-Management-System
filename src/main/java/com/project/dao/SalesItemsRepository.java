package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.SalesItems;

@Repository
public interface SalesItemsRepository extends CrudRepository<SalesItems, Integer> {

}
