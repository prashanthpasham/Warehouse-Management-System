package com.project.controller;

import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.WarehouseDto;
import com.project.service.intf.WarehouseServiceIntf;

@RequestMapping("/warehouse")
@RestController
public class WarehouseController {
	@Autowired
	private WarehouseServiceIntf warehouseServiceIntf;

	@PostMapping(value = "/create-warehouse", consumes = "application/json")
	public String createWarehouse(@RequestBody WarehouseDto dto) {
		try {
			return warehouseServiceIntf.saveWarehouse(dto);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}
	@GetMapping(value = "/warehouse-list", produces = "application/json")
	public JSONArray warehouseList(@RequestParam Map<String, String> filters) {
		JSONArray results = new JSONArray();
		try {
			results= warehouseServiceIntf.warehouseList((String)filters.get("code"),(String)filters.get("status") , (String)filters.get("bsIds"), (String)filters.get("user"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;

	}
	@PostMapping(value = "/warehouse-user-map", consumes = "application/json")
	public String createWarehouseUserMap(@RequestBody String data) {
		try {
			JSONParser parser = new JSONParser();
			JSONArray obj=(JSONArray)parser.parse(data);
			return warehouseServiceIntf.saveWarehouseUserMap(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

	}
	@GetMapping(value = "/warehouse-users", produces = "application/json")
	public JSONArray warehouseUsersList(@RequestParam("warehouseId") String whId) {
		JSONArray results = new JSONArray();
		try {
			results= warehouseServiceIntf.findWarehouseUsers(Integer.parseInt(whId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;

	}
}
