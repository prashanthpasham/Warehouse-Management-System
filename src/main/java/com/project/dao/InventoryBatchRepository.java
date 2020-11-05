package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.InventoryBatchDetails;

@Repository
public interface InventoryBatchRepository extends CrudRepository<InventoryBatchDetails, Integer> {
	@Query("from InventoryBatchDetails a where a.inventoryDetails.stock.stockId=?1 and a.batchNo=?2")
	public InventoryBatchDetails findBatchDetailsBySkuAndBatchNo(int skuId, String batchNo);
	
	@Query("from InventoryBatchDetails a where a.inventoryDetails.detailsId=?1 and a.availableQty>0")
	public List<InventoryBatchDetails> findBatchByInventoryId(int detailsId);
	
	
}
