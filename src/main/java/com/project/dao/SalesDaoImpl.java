package com.project.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Repository;

import com.project.pojo.Sales;

@Repository
public class SalesDaoImpl implements SalesDaoIntf {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Sales> findSalesList(JSONObject filters) {
		List<Sales> results = new ArrayList<Sales>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			String sql = "from Sales s where 1=1";
			if (filters != null) {
				if (filters.get("salesOrderNo") != null && filters.get("salesOrderNo").toString().trim().length() > 0) {
					sql += " and s.salesOrderNo like '" + filters.get("salesOrderNo").toString().trim() + "%'";
				}
				if (filters.get("custNameCode") != null && filters.get("custNameCode").toString().trim().length() > 0) {
					sql += " and (lower(s.customer.customerCode) like :cust ||'%' or lower(s.customer.customerName) like :cust ||'%')";
				}
				if (filters.get("bsIds") != null && filters.get("bsIds").toString().trim().length() > 0) {
					sql += " and c.customer.businessTerritory.bsIds like '" + filters.get("bsIds").toString() + "%'";
				}
				if (filters.get("warehouseId") != null && filters.get("warehouseId").toString().trim().length() > 0) {
					sql += " and s.warehouse.warehouseId=" + Integer.parseInt(filters.get("warehouseId").toString());
				}
				if (filters.get("userId") != null && filters.get("userId").toString().trim().length() > 0) {
					sql += " and s.salesPerson.userId=" + Integer.parseInt(filters.get("userId").toString().trim());
				}
				if (filters.get("fromDate") != null && filters.get("toDate") != null) {
					sql += " and trunc(s.entryDate) between :fromDate and :toDate";
				}
			}
			Query qry = entityManager.createQuery(sql);
			if (filters != null) {
				if (filters.get("custNameCode") != null && filters.get("custNameCode").toString().trim().length() > 0) {
					qry.setParameter("cust", filters.get("custNameCode").toString().trim());
				}
				if (filters.get("fromDate") != null && filters.get("toDate") != null) {
					qry.setParameter("fromDate", sdf.parse(filters.get("fromDate").toString()));
					qry.setParameter("toDate", sdf.parse(filters.get("toDate").toString()));
				}
			}
           results = qry.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

}
