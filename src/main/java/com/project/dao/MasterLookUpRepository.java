package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.pojo.MasterLookUp;

@Repository
public interface MasterLookUpRepository extends CrudRepository<MasterLookUp, Integer> {
	@Query("from MasterLookUp a where a.type=:type order by a.parentMasterId asc")
	public List<MasterLookUp> fetchBusinessHierarchy(@Param("type") String type);
	@Query("select count(*) from MasterLookUp a where a.type=:type and a.parentMasterId=:id")
	public int findMasterLookupParentId(@Param("type") String type,@Param("id") int parentId);
}
