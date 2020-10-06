package com.project.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
}
