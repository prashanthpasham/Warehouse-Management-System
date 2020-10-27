package com.project.service.intf;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.project.dto.CustomerDto;
import com.project.dto.DeliveryAddressDto;

public interface CustomerServiceIntf {
	public String createCustomerType(JSONArray types);

	public JSONArray getCustomerTypes();

	public String createCustomer(CustomerDto dto);

	public List<CustomerDto> customers(JSONObject filters);

	public CustomerDto customerByCode(String customerCode);

	public String createCustomerDeliveryAddress(DeliveryAddressDto dto);

	public List<DeliveryAddressDto> customerDeliveryAddressList(int custId);
}
