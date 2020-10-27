package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
