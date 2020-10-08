package com.project.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.Stock;

@Repository
public interface StockRepository extends CrudRepository<Stock, Integer> {
	public List<Stock> findBySkuCodeIgnoreCase(String code);
}
