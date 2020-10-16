package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.pojo.Warehouse;
import com.sun.istack.Nullable;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer> {
	@Query("from Warehouse w where lower(w.warehouseCode) like :code||'%' or lower(w.warehouseName) like :name||'%'")
	public List<Warehouse> findByWarehouseCodeOrWarehouseName(String code, String name);
  @Query("from Warehouse w where (lower(w.warehouseCode) like :code||'%' or w.status=:status or w.businessTerritory.bsIds like :bsIds||'%')")
  public List<Warehouse> findByWarehouseList(@Nullable @Param("code") String code,@Param("status") String status,@Param("bsIds") String bsIds);
}
