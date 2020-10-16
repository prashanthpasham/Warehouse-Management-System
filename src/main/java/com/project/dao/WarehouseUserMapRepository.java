package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.pojo.WarehouseUserMap;

@Repository
public interface WarehouseUserMapRepository extends JpaRepository<WarehouseUserMap, Integer> {
   @Query("from WarehouseUserMap a where a.salesPerson.userId=:userId and a.warehouse.warehouseId=:warehouseId")
	public WarehouseUserMap findByWarehouseAndUserId(@Param("userId") int userId,@Param("warehouseId") int whId); 
   @Query("from WarehouseUserMap a where a.warehouse.warehouseId=:warehouseId")
   public List<WarehouseUserMap> findByWarehouseId(@Param("warehouseId") int whId);
}
