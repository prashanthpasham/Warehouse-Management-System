package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.CustomerTypes;

@Repository
public interface CustomerTypesRepository extends CrudRepository<CustomerTypes, Integer> {
	public CustomerTypes findByCustomerTypeIgnoreCase(String type);
	
	public CustomerTypes findByCustomerTypeId(int type);
	
}
