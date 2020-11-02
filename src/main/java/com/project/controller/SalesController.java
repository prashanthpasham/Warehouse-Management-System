package com.project.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.SalesDto;
import com.project.service.intf.StockServiceIntf;

@RestController
@RequestMapping(value = "/sales")
public class SalesController {
	@Autowired
	private StockServiceIntf stockServiceIntf;

	@PostMapping(value = "/create-sales", consumes = "application/json")
	public ResponseEntity<String> createSales(@RequestBody SalesDto sales) {
		String result = "";
		try {
			result = stockServiceIntf.createSales(sales);
			if (result.equals("success")) {
				return new ResponseEntity<String>(result, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@PostMapping(value = "/sales-list", consumes = "application/json", produces = "application/json")
	public JSONArray salesList(@RequestBody String filters) {
		try {
			JSONObject obj = (JSONObject) new JSONParser().parse(filters);
			return stockServiceIntf.salesList(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping(value = "/sales-list/{id}", produces = "application/json")
	public JSONArray salesItemsById(@PathVariable("id") int salesId) {
		try {
			return stockServiceIntf.getSalesItemsById(salesId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
