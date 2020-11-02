package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.SalesItems;

@Repository
public interface SalesItemsRepository extends CrudRepository<SalesItems, Integer> {
	@Query("from SalesItems a where a.sales.salesId=?1")
	public List<SalesItems> getSalesItemsById(int salesId);
}
