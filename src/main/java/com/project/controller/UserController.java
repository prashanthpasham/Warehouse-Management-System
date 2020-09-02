package com.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.pojo.MenuGroup;
import com.project.pojo.MenuItem;
import com.project.pojo.RoleMenuItem;
import com.project.pojo.Users;
import com.project.service.intf.LoginServiceInf;
import com.project.util.AESEncryption;

@RestController
@RequestMapping(value = "/login")
public class UserController {
	@Autowired
	private LoginServiceInf loginServiceInf;

	@PostMapping(value = "/validate")
	public ResponseEntity<ObjectNode> loginValidation(@RequestBody String users) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		ArrayNode menuGroups = mapper.createArrayNode();
		try {
			Map<String, Object> map = mapper.readValue(users, new TypeReference<Map<String, Object>>() {
			});
			String status = "fail";
			StringBuffer errorMsg = new StringBuffer();
			if (map != null) {
				if (map.get("userName") != null && map.get("userName").toString().trim().length() > 0) {
					if (map.get("password") != null && map.get("password").toString().trim().length() > 0) {
						// validating users credentials
						String password = map.get("userName").toString().trim() + "#"
								+ map.get("password").toString().trim();
						AESEncryption.init();
						Users user = loginServiceInf.loginValidation(map.get("userName").toString().trim(),
								AESEncryption.getInstance().encode(password));
						if (user == null) {
							errorMsg.append("Bad credentials");
						} else {
							// Fetch menus based on user role
							List<RoleMenuItem> roleMenus = loginServiceInf.fetchMenusByRole(user.getRole().getRoleId());

							Map<String, ObjectNode> menuMap = new HashMap<String, ObjectNode>();
							if (!roleMenus.isEmpty()) {
								for (RoleMenuItem roleMenu : roleMenus) {
									// Every has mapped menuitem
									MenuItem menuItem = roleMenu.getMenuItem();
									MenuGroup menuGroup = menuItem.getMenuGroup();
									String key = menuGroup.getGroupName();
									ArrayNode menuItems = null;
									ObjectNode grpNode = null;
									if (menuMap.containsKey(key)) {
										grpNode = menuMap.get(key);
										menuItems = (ArrayNode) grpNode.get("menuLinks");
									} else {
										grpNode = mapper.createObjectNode();
										grpNode.put("groupName", menuGroup.getGroupName());
										menuItems = mapper.createArrayNode();
									}
									ObjectNode itemNode = mapper.createObjectNode();
									itemNode.put("menuItemId", menuItem.getMenuItemId());
									itemNode.put("menuItem", menuItem.getMenuName());
									menuItems.add(itemNode);
									grpNode.put("menuLinks", menuItems);
									menuMap.put(key, grpNode);
								}
								for (Entry<String, ObjectNode> grp : menuMap.entrySet()) {
									menuGroups.add(grp.getValue());
								}
								node.put("menus", menuGroups);
							}
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
				node.put("error_message", errorMsg.toString());

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity(node, HttpStatus.OK);

	}
}
