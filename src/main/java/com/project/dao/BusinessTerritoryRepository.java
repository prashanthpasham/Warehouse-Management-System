package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.pojo.BusinessTerritory;

@Repository
public interface BusinessTerritoryRepository extends CrudRepository<BusinessTerritory, Integer> {
	@Query("select count(*) from BusinessTerritory a where a.parentId=:parent and lower(a.businessName)=:name")
	public int findBsNameinTerritory(@Param("name") String businessName, @Param("parent") int parentId);

	@Query("from BusinessTerritory a where a.parentId=:id")
	public List<BusinessTerritory> getBusinessTerritoryById(@Param("id") int id);
	
	@Query("from BusinessTerritory a where a.masterLookUp.masterId=:id")
	public List<BusinessTerritory> getBusinessTerritoryByLookUpId(@Param("id") int id);

	public BusinessTerritory findByBsIds(String businessId);
}
