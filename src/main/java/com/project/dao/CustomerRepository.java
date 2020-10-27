package com.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	public Customer findByCustomerCodeIgnoreCase(String code);
}
