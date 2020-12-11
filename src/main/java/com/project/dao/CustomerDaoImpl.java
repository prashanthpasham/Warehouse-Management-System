package com.project.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.project.dto.CustomerDto;
import com.project.pojo.Customer;

@Repository
public class CustomerDaoImpl implements CustomerDaoIntf {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<CustomerDto> customersList(JSONObject filters) {
		List<CustomerDto> customers = new ArrayList<CustomerDto>();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String sql = " from Customer c where 1=1 ";
			if (filters != null) {
				if (filters.get("custCodeName") != null && filters.get("custCodeName").toString().trim().length() > 0) {
					sql += " and (lower(c.customerCode) like '"
							+ filters.get("custCodeName").toString().trim().toLowerCase()
							+ "%' or lower(c.customerName) like '"
							+ filters.get("custCodeName").toString().trim().toLowerCase() + "%')";
				}
				if (filters.get("status") != null && filters.get("status").toString().trim().length() > 0) {
					sql += " and c.status='" + filters.get("status").toString().trim() + "'";
				}
				if (filters.get("customerType") != null && filters.get("customerType").toString().trim().length() > 0) {
					sql += " and c.customerTypes.customerTypeId in ( "+ filters.get("customerType").toString().trim()+")";
				}
				if (filters.get("bsIds") != null && filters.get("bsIds").toString().trim().length() > 0) {
					sql += " and c.businessTerritory.bsIds like '" + filters.get("bsIds").toString().trim() + "%' ";
				}
			}
			Query qry = entityManager.createQuery(sql);
			if (filters != null) {
				
				if (filters.get("first") != null)
					qry.setFirstResult(Integer.parseInt(filters.get("first").toString()) - 1);
				if (filters.get("size") != null)
					qry.setMaxResults(Integer.parseInt(filters.get("size").toString()));
			}
			List<Customer> ls = qry.getResultList();
			if (!ls.isEmpty()) {
				for (Customer cu : ls) {
					CustomerDto dto = new CustomerDto();
					BeanUtils.copyProperties(cu, dto);
					dto.setCreatedDate(sdf.format(cu.getCreatedDate()));
					dto.setBusinessId(cu.getBusinessTerritory().getBsIds());
					dto.setCustomerType(cu.getCustomerTypes().getCustomerType());
					customers.add(dto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customers;
	}

}
