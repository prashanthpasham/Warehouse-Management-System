package com.project.service.intf;

import org.json.simple.JSONArray;

import com.project.dto.WarehouseDto;

public interface WarehouseServiceIntf {
public String saveWarehouse(WarehouseDto dto);

public JSONArray warehouseList(String code,String status,String btIds,String user);

public String saveWarehouseUserMap(JSONArray data);

public JSONArray findWarehouseUsers(int whId);
}
