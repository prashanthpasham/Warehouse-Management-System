package com.project.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.pojo.Users;
import com.project.service.intf.LoginServiceInf;
import com.project.util.AESEncryption;

@RestController
@RequestMapping(value="/login")
public class UserController {
	@Autowired
	private LoginServiceInf loginServiceInf;
	@PostMapping(value="/validate")
	public ResponseEntity loginValidation(@RequestBody String users) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(users, new TypeReference<Map<String, Object>>() {
			});
			ObjectNode node = mapper.createObjectNode();
			String status = "fail";
			StringBuffer errorMsg=new StringBuffer();
			if (map != null) {
				if (map.get("userName") != null && map.get("userName").toString().trim().length() > 0) {
					if (map.get("password") != null && map.get("password").toString().trim().length() > 0) {
						// validating users credentials
						String password = map.get("userName").toString().trim()+"#"+map.get("password").toString().trim();
						AESEncryption.init();
						Users user = loginServiceInf.loginValidation(map.get("userName").toString().trim(),
								AESEncryption.getInstance().encode(password));
						if (user == null) {
							errorMsg.append("Bad credentials");
						} else {
							errorMsg.append("");
							status = "success";
						}
					} else {
						errorMsg.append("password must be required");
					}
				} else {
					errorMsg.append("userName must be required");
				}
				node.put("result", status);
				node.put("error_message",errorMsg.toString());
				return new ResponseEntity(node, HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(HttpStatus.OK);

	}
}
