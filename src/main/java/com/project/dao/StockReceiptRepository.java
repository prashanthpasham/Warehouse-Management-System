package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.StockReciept;

@Repository
public interface StockReceiptRepository extends CrudRepository<StockReciept, Integer> {

}
