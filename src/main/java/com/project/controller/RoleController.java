package com.project.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.dto.RoleDto;
import com.project.service.intf.LoginServiceInf;

@RestController
@RequestMapping(value = "/role")
public class RoleController {
	@Autowired
	private LoginServiceInf loginServiceIntf;

	@PostMapping(consumes = "application/json", value = "/create-role")
	public String saveRole(@RequestBody RoleDto dto) {
		try {
			return loginServiceIntf.saveRole(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	@PostMapping(consumes = "application/json", value = "/edit-role")
	public String editRole(@RequestBody RoleDto dto) {
		try {
			return loginServiceIntf.editRole(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	@PostMapping(consumes = "application/json", value = "/role-list")
	public JSONArray roleList(@RequestBody String dto) {
		JSONArray results = new JSONArray();
		try {
			JSONObject filters = (JSONObject) new JSONParser().parse(dto);
			results = loginServiceIntf.roleList(filters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
}
