package com.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.CustomerDto;
import com.project.dto.DeliveryAddressDto;
import com.project.service.intf.CustomerServiceIntf;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
	@Autowired
	private CustomerServiceIntf customerServiceIntf;

	@PostMapping(value = "/create-customer-type", consumes = "application/json")
	public String createCustomerType(@RequestBody String type) {
		String result = "";
		try {
			JSONParser parser = new JSONParser();
			JSONArray types = (JSONArray) parser.parse(type);
			result = customerServiceIntf.createCustomerType(types);
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
		}
		return result;
	}

	@GetMapping(value = "/customer-type", produces = "application/json")
	public JSONArray customerTypes() {
		JSONArray results = new JSONArray();
		try {
			results = customerServiceIntf.getCustomerTypes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@PostMapping(value = "/create-customer", consumes = "application/json", produces = "text/plain")
	public String createCustomer(@RequestBody CustomerDto dto) {
		String result = "";
		try {
			result = customerServiceIntf.createCustomer(dto);
			/*if (result.equals("success")) {
				return new ResponseEntity<String>(result, HttpStatus.CREATED);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return new ResponseEntity<String>(result, HttpStatus.OK);
		return result;
	}

	@PostMapping(value = "/customers", consumes = "application/json", produces = "application/json")
	public List<CustomerDto> customers(@RequestBody String filters) {
		try {
			JSONObject obj = (JSONObject) new JSONParser().parse(filters);
			return customerServiceIntf.customers(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CustomerDto>();
	}

	@GetMapping(value = "/{code}", produces = "application/json")
	public CustomerDto customersByCode(@PathVariable("code") String customerCode) {
		try {
			return customerServiceIntf.customerByCode(customerCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@PostMapping(value = "/create-delivery-address", consumes = "application/json", produces = "text/plain")
	public ResponseEntity<String> createCustomerDeliveryAddress(@RequestBody DeliveryAddressDto dto) {
		String result = "";
		try {
			result = customerServiceIntf.createCustomerDeliveryAddress(dto);
			if (result.equals("success")) {
				return new ResponseEntity<String>(result, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/delivery-address", produces = "application/json")
	public List<DeliveryAddressDto> deliveryAddressList(@RequestParam("custId") int custId) {
		List<DeliveryAddressDto> ls = new ArrayList<DeliveryAddressDto>();
		try {
			ls = customerServiceIntf.customerDeliveryAddressList(custId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}
}
