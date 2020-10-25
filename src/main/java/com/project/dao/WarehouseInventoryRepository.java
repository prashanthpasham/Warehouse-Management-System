package com.project.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.WarehouseInventory;

@Repository
public interface WarehouseInventoryRepository extends CrudRepository<WarehouseInventory, Integer> {
	@Query("from WarehouseInventory a where a.warehouse.warehouseId=?1")
	public WarehouseInventory findByWarehouseId(int warehouseId);
}
