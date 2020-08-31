package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.BusinessTerritory;

@Repository
public interface BusinessTerrorityRepository extends CrudRepository<BusinessTerritory, Integer>{

}
