package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.InventorySerialDetails;
@Repository
public interface InventorySerialRepository extends CrudRepository<InventorySerialDetails, Integer>{
   @Query("from InventorySerialDetails a where a.inventoryDetails.stock.stockId=?1 and a.status='pending'")
	public List<InventorySerialDetails> findSerialNoBySkuId(String skuId);
}
