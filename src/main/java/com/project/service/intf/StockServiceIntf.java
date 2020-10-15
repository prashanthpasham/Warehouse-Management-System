package com.project.service.intf;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.project.dto.StockDto;
import com.project.pojo.StockLookUp;
import com.project.pojo.UOMConfiguration;

public interface StockServiceIntf {

	public int findByLookupName(String name, int parentId);

	public StockLookUp findByStockLookupId(int parentId);

	public String saveStockLookup(StockLookUp stock);

	public JSONArray fetchStockLookup();

	public JSONObject fetchStockLookupByParentId(int id);

	public int findByUomName(String name);

	public UOMConfiguration  findUomConfigById(int childId);

	public String saveUomConfiguration(UOMConfiguration config);

	public String createStock(StockDto dto);

	public JSONArray stockList();
	
	public JSONObject stockList(int id);

	public JSONArray uomConfigurationList();

	public JSONObject uomConfigurationList(int parseInt);

}
