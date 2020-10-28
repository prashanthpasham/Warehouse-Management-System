package com.project.dao;

import java.util.List;

import org.json.simple.JSONObject;

import com.project.pojo.Sales;

public interface SalesDaoIntf {
   public List<Sales> findSalesList(JSONObject filters);
}
