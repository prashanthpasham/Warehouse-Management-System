package com.project.service.intf;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.project.dto.SalesDto;
import com.project.dto.StockDto;
import com.project.dto.StockRecieptDto;
import com.project.pojo.StockLookUp;
import com.project.pojo.UOMConfiguration;
import com.project.pojo.Warehouse;
import com.project.pojo.WarehouseInventoryDetails;

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

	public String createStockReciept(StockRecieptDto dto);
	
	public String saveWarehouseInventory(Warehouse warehouse,List<WarehouseInventoryDetails> inventorySkus);

	public String createSales(SalesDto sales);

	public JSONArray salesList(JSONObject filters);

	public JSONArray getSalesItemsById(int salesId);

	public JSONArray stockBalances(int warehouseId);

	public double warehouseBalanceBySku(int whId, int skuId);

	public JSONArray serialBatchByWarehouseAndSku(int whId, int skuId);

}
