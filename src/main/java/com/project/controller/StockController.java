package com.project.controller;

import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.pojo.StockLookUp;
import com.project.pojo.UOMConfiguration;
import com.project.service.intf.LoginServiceInf;
import com.project.service.intf.StockServiceIntf;

@RestController
@RequestMapping(value = "/stock")
public class StockController {
@Autowired
private StockServiceIntf stockServiceIntf;
@Autowired
private LoginServiceInf loginServiceIntf;
@PostMapping(value = "/create-stock-hierarchy", consumes = "application/json")
public ResponseEntity<String> saveStockLookup(@RequestBody String data) {

	String result = "";
	try {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(data);
		int parentId=Integer.parseInt(obj.get("parentId").toString());
		String name=obj.get("name").toString();
		   int count=stockServiceIntf.findByLookupName(name.toLowerCase(), parentId);
		   System.out.println("save");
			if (count == 0) {
				StockLookUp stock = new StockLookUp();
				stock.setMasterLookUp(loginServiceIntf
						.findByMasterLookupId(Integer.parseInt(obj.get("masterLookupId").toString())));
				stock.setParentId(stockServiceIntf.findByStockLookupId(parentId));
				stock.setName(name);
				System.out.println("save1");
				stockServiceIntf.saveStockLookup(stock);
				result = "success";
			}else {
				result = name+" already exist";
			}
		
	} catch (Exception e) {
		result = "fail";
		e.printStackTrace();
	}
	return ResponseEntity.ok(result);
}
@GetMapping(value = "/hierarchy-data", produces = "application/json")
public JSONArray fetchStockLookup() {
	JSONArray array = new JSONArray();
	try {
		array = stockServiceIntf.fetchStockLookup();
	} catch (Exception e) {
		e.printStackTrace();
	}
	return array;

}
@GetMapping(value = "/hierarchy-data/{id}", produces = "application/json")
public JSONObject fetchStockLookupById(@PathVariable("id") String id) {
	JSONObject obj = new JSONObject();
	try {
	  obj = stockServiceIntf.fetchStockLookupByParentId(Integer.parseInt(id));
	  System.out.println("obj>>"+obj.toJSONString());
	} catch (Exception e) {
		e.printStackTrace();
	}
	return obj;

}
@PostMapping(value = "/create-uom-config", consumes = "application/json")
public ResponseEntity<String> createUomConfiguration(@RequestBody String data) {
	String result = "";
	try {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(data);
		int childId = Integer.parseInt(obj.get("childId").toString());
		String name = obj.get("name").toString();
		int count = stockServiceIntf.findByUomName(name.toLowerCase());
		if (count == 0) {
			UOMConfiguration uom = stockServiceIntf.findUomConfigById(childId);
			UOMConfiguration config = new UOMConfiguration();
			config.setCreatedDate(new Date());
			config.setUomName(name);
			config.setChildUOMId(childId);
			config.setQuantity(Integer.parseInt(obj.get("quantity").toString()));
			String childs = name + "#" + config.getQuantity() + "@";
			if (childId > 0) {
				config.setChildUomIds(uom.getChildUomIds());
				config.setChildUomNames(uom.getChildUomNames() + childs);
			} else {
				config.setChildUomNames(childs);
			}
			result = stockServiceIntf.saveUomConfiguration(config);
		} else {
			result = name + " already exist";
		}

	} catch (Exception e) {
		result = "fail";
		e.printStackTrace();
	}
	return ResponseEntity.ok(result);
}


}