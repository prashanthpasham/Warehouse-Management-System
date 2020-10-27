package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.project.pojo.DeliveryAddress;

@Repository
public interface DeliveryAddressRepository extends CrudRepository<DeliveryAddress, Integer> {
	@Query("from DeliveryAddress a where a.customer.customerId=?1")
	public List<DeliveryAddress> findDeliveryAddressByCustId(int custId);

}
