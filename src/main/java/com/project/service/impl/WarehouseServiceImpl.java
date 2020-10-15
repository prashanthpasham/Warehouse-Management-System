package com.project.service.impl;

import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.dao.BusinessTerritoryRepository;
import com.project.dao.MasterLookUpRepository;
import com.project.dao.WarehouseRepository;
import com.project.dto.WarehouseDto;
import com.project.pojo.Warehouse;
import com.project.service.intf.WarehouseServiceIntf;

@Repository
public class WarehouseServiceImpl implements WarehouseServiceIntf {
	@Autowired
	private WarehouseRepository warehouseRepo;
	@Autowired
	private BusinessTerritoryRepository businessRepo;
	@Autowired
	private MasterLookUpRepository masterLookupRepo;

	@Override
	public String saveWarehouse(WarehouseDto dto) {
		try {
			List<Warehouse> ls = warehouseRepo.findByWarehouseCodeOrWarehouseName(dto.getWarehouseCode().toLowerCase(),
					dto.getWarehouseName().toLowerCase());
			if (!ls.isEmpty()) {
				for (Warehouse wh : ls) {
					if (wh.getWarehouseCode().equalsIgnoreCase(dto.getWarehouseCode()))
						return "Warehouse Code " + dto.getWarehouseCode() + " already exist";
					else
						return "Warehouse Name " + dto.getWarehouseName() + " already exist";
				}
			}
			Warehouse warehouse = new Warehouse();
			BeanUtils.copyProperties(dto, warehouse);
			warehouse.setCreatedDate(new Date());
			warehouse.setBusinessTerritory(businessRepo.findById(dto.getBusinessTerritoryId()).get());
			warehouse.setWarehouseType(masterLookupRepo.findById(dto.getWarehouseTypeId()).get());
			warehouseRepo.save(warehouse);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	@Override
	public JSONArray warehouseList(String code, String status, String btIds, String user) {
		// TODO Auto-generated method stub
		return null;
	}

}
