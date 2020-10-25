package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.StockRecieptSkus;
@Repository
public interface StockRecieptSkusRepository extends CrudRepository<StockRecieptSkus, Integer> {

}
