package com.project.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.WarehouseInventoryDetails;

@Repository
public interface WarehouseInventoryDetailsRepository extends CrudRepository<WarehouseInventoryDetails, Integer> {
	@Query("from WarehouseInventoryDetails a where a.stock.stockId=?1 and a.inventory.inventoryId=?2")
	public WarehouseInventoryDetails findByStockIdAndInventoryId(int stockId, int inventory);
}
