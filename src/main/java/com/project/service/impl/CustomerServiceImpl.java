package com.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.BusinessTerritoryRepository;
import com.project.dao.CustomerDaoIntf;
import com.project.dao.CustomerRepository;
import com.project.dao.CustomerTypesRepository;
import com.project.dao.DeliveryAddressRepository;
import com.project.dto.CustomerDto;
import com.project.dto.DeliveryAddressDto;
import com.project.pojo.Customer;
import com.project.pojo.CustomerTypes;
import com.project.pojo.DeliveryAddress;
import com.project.service.intf.CustomerServiceIntf;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
public class CustomerServiceImpl implements CustomerServiceIntf {
	@Autowired
	private CustomerTypesRepository custTypeRepo;
	@Autowired
	private CustomerRepository customerRepo;
	@Autowired
	private BusinessTerritoryRepository businessTerritoryRepo;
	@Autowired
	private CustomerDaoIntf custDaoIntf;
	@Autowired
	private DeliveryAddressRepository deliveryAddressRepo;

	@Override
	public String createCustomerType(JSONArray types) {
		String result = "";
		try {
			for (int k = 0; k < types.size(); k++) {
				String type = types.get(k).toString();
				CustomerTypes cust = custTypeRepo.findByCustomerTypeIgnoreCase(type.toLowerCase());
				if (cust == null) {
					CustomerTypes custType = new CustomerTypes();
					custType.setCreatedDate(new Date());
					custType.setCustomerType(type);
					custTypeRepo.save(custType);
				} else {
					result += " Customer Type " + type + " already exist ,";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
		}
		return result;
	}

	@Override
	public JSONArray getCustomerTypes() {
		JSONArray results = new JSONArray();
		try {
			Iterable<CustomerTypes> types = custTypeRepo.findAll();
			Iterator it = types.iterator();
			while (it.hasNext()) {
				CustomerTypes type = (CustomerTypes) it.next();
				results.add(type.getCustomerType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public String createCustomer(CustomerDto dto) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Customer cust = customerRepo.findByCustomerCodeIgnoreCase(dto.getCustomerCode());
			if (cust == null) {
				Customer customer = new Customer();
				BeanUtils.copyProperties(dto, customer);
				customer.setCreatedDate(sdf.parse(dto.getCreatedDate()));
				customer.setCustomerTypes(custTypeRepo.findByCustomerTypeIgnoreCase(dto.getCustomerType()));
				customer.setBusinessTerritory(businessTerritoryRepo.findByBsIds(dto.getBusinessId()));
				customerRepo.save(customer);
				result = "success";
			} else {
				result = "Customer Code " + dto.getCustomerCode() + " already exist";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
		}
		return result;
	}

	@Override
	public List<CustomerDto> customers(JSONObject filters) {
		try {
			return custDaoIntf.customersList(filters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CustomerDto>();
	}

	@Override
	public CustomerDto customerByCode(String customerCode) {
		CustomerDto dto = new CustomerDto();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Customer customer = customerRepo.findByCustomerCodeIgnoreCase(customerCode);
			BeanUtils.copyProperties(customer, dto);
			dto.setCreatedDate(sdf.format(customer.getCreatedDate()));
			dto.setBusinessId(customer.getBusinessTerritory().getBsIds());
			dto.setCustomerType(customer.getCustomerTypes().getCustomerType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public String createCustomerDeliveryAddress(DeliveryAddressDto dto) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			DeliveryAddress address = new DeliveryAddress();
			BeanUtils.copyProperties(dto, address);
			address.setCreatedDate(sdf.parse(dto.getCreatedDate()));
			address.setCustomer(customerRepo.findById(dto.getCustomerId()).get());
			deliveryAddressRepo.save(address);
			result = "success";
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
		}
		return result;
	}

	@Override
	public List<DeliveryAddressDto> customerDeliveryAddressList(int custId) {
		List<DeliveryAddressDto> ls = new ArrayList<DeliveryAddressDto>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			List<DeliveryAddress> list = deliveryAddressRepo.findDeliveryAddressByCustId(custId);
			if (!list.isEmpty()) {
				for (DeliveryAddress addr : list) {
					DeliveryAddressDto dto = new DeliveryAddressDto();
					BeanUtils.copyProperties(addr, dto);
					dto.setCreatedDate(sdf.format(addr.getCreatedDate()));
					dto.setCustomerId(addr.getCustomer().getCustomerId());
					ls.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

}
