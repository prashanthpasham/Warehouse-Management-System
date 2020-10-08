package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.pojo.StockLookUp;

@Repository
public interface StockLookUpRepository extends CrudRepository<StockLookUp, Integer> {
	@Query("select count(*) from StockLookUp a where a.parentId.stockLookUpId=:parent and lower(a.name)=:name")
	public int findByLookupName(@Param("name") String name, @Param("parent") int parentId);

	@Query("from StockLookUp a where a.masterLookUp.masterId=:id")
	public List<StockLookUp> getStockByLookUpId(@Param("id") int id);

	@Query("from StockLookUp a where a.parentId.stockLookUpId=:id")
	public List<StockLookUp> getStockByLookUpByparentId(@Param("id") int id);
}
