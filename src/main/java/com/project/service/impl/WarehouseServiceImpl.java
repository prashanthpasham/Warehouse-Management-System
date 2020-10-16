package com.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.dao.BusinessTerritoryRepository;
import com.project.dao.MasterLookUpRepository;
import com.project.dao.UsersRepository;
import com.project.dao.WarehouseRepository;
import com.project.dao.WarehouseUserMapRepository;
import com.project.dto.WarehouseDto;
import com.project.pojo.Users;
import com.project.pojo.Warehouse;
import com.project.pojo.WarehouseUserMap;
import com.project.service.intf.WarehouseServiceIntf;

@Repository
public class WarehouseServiceImpl implements WarehouseServiceIntf {
	@Autowired
	private WarehouseRepository warehouseRepo;
	@Autowired
	private BusinessTerritoryRepository businessRepo;
	@Autowired
	private MasterLookUpRepository masterLookupRepo;
	@Autowired
	private WarehouseUserMapRepository whUserMapRepo;
	@Autowired
	private UsersRepository usersRepository;

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
		JSONArray results = new JSONArray();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			List<Warehouse> ls = warehouseRepo.findByWarehouseList(code, status, btIds);
			for (Warehouse war : ls) {
				JSONObject obj = new JSONObject();
				obj.put("warehouseCode", war.getWarehouseCode());
				obj.put("warehouseName", war.getWarehouseName());
				obj.put("warehouseId", war.getWarehouseId());
				obj.put("status", war.getStatus());
				obj.put("description", war.getDescription());
				obj.put("createdDate", sdf.format(war.getCreatedDate()));
				obj.put("ModifiedDate", war.getModifiedDate() != null ? sdf.format(war.getModifiedDate()) : "");
				obj.put("warehouseTypeId", war.getWarehouseType().getMasterId());
				obj.put("businessTerritoryId", war.getBusinessTerritory().getBusinessId());
				obj.put("bsIds", war.getBusinessTerritory().getBsIds());
				obj.put("addressId", war.getAddress() != null ? war.getAddress().getAddressId() : 0);
				results.add(obj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public String saveWarehouseUserMap(JSONArray data) {
		String result = "";
		try {
			for (int k = 0; k < data.size(); k++) {
				JSONObject obj = (JSONObject) data.get(k);
				int whId = Integer.parseInt(obj.get("warehouseId").toString());
				int userId = Integer.parseInt(obj.get("userId").toString());
				WarehouseUserMap whMap = whUserMapRepo.findByWarehouseAndUserId(userId, whId);
				if (whMap == null) {
					WarehouseUserMap map = new WarehouseUserMap();
					map.setWarehouse(warehouseRepo.findById(whId).get());
					map.setSalesPerson(usersRepository.findById(userId).get());
					map.setMappedDate(new Date());
					whUserMapRepo.save(map);
				}
				result = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = "fail";
		}
		return result;
	}

	@Override
	public JSONArray findWarehouseUsers(int whId) {
		JSONArray results = new JSONArray();
		try {
			List<WarehouseUserMap> ls = whUserMapRepo.findByWarehouseId(whId);
			if (!ls.isEmpty()) {
				for (WarehouseUserMap map : ls) {
					JSONObject ob = new JSONObject();
					Warehouse wh = map.getWarehouse();
					ob.put("warehouseId", wh.getWarehouseId());
					ob.put("warehouseCode", wh.getWarehouseCode());
					ob.put("warehouseName", wh.getWarehouseName());
					Users user = map.getSalesPerson();
					ob.put("userId", user.getUserId());
					ob.put("userCode", user.getUserCode());
					ob.put("userName", user.getUserName());
					results.add(ob);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

}
