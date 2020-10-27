package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.Sales;

@Repository
public interface SalesRepository extends CrudRepository<Sales, Integer> {

}
