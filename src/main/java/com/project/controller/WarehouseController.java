package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
