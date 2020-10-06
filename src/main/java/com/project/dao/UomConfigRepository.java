package com.project.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.pojo.UOMConfiguration;
@Repository
public interface UomConfigRepository extends CrudRepository<UOMConfiguration, Integer> {
@Query("select count(*) from UOMConfiguration a where lower(a.uomName)=:name")
	public int findByUomName(@Param("name") String name);

}
