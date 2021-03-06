package com.project.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.project.dto.EmailDto;
import com.project.dto.UsersDto;
import com.project.pojo.MenuGroup;
import com.project.pojo.MenuItem;
import com.project.pojo.RoleMenuItem;
import com.project.pojo.Users;
import com.project.security.JwtTokenUtil;
import com.project.service.intf.LoginServiceInf;
import com.project.util.AESEncryption;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/login")
public class UserController {
	@Autowired
	private LoginServiceInf loginServiceInf;
	@Autowired
	private JwtTokenUtil tokenUtil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private EmailController emailController;

	@ApiOperation("To Authenticate users and grant jwt token")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Request Reached"),
			@ApiResponse(code = 401, message = "Unauthorized") })
	@PostMapping(value = "/authenticate", produces = "application/json")
	public ResponseEntity<String> loginValidation(@RequestBody String users) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode node = mapper.createObjectNode();
		ArrayNode menuGroups = mapper.createArrayNode();
		try {
			Map<String, Object> map = mapper.readValue(users, new TypeReference<Map<String, Object>>() {
			});
			String status = "fail";
			StringBuffer errorMsg = new StringBuffer();
			if (map != null) {
				System.out.println(map.toString());
				if (map.get("userName") != null && map.get("userName").toString().trim().length() > 0) {
					if (map.get("password") != null && map.get("password").toString().trim().length() > 0) {
						// validating users credentials
						String password = map.get("userName").toString().trim().toLowerCase() + "#"
								+ map.get("password").toString().trim();
						AESEncryption.init();
						Users user = loginServiceInf.loginValidation(map.get("userName").toString().trim(),
								AESEncryption.getInstance().encode(password));
						if (user == null) {
							errorMsg.append("Bad credentials");
						} else if(user.getStatus().equalsIgnoreCase("active")){
							// authenticate(user.getUserName(),
							// encoder.encode(map.get("password").toString().trim()));
							String token = tokenUtil.generateToken(user.getUserName());
							node.put("token", token);
							// Fetch menus based on user role
							List<RoleMenuItem> roleMenus = loginServiceInf.fetchMenusByRole(user.getRole().getRoleId());
							Collections.sort(roleMenus, new Comparator<RoleMenuItem>() {

								@Override
								public int compare(RoleMenuItem o1, RoleMenuItem o2) {
									// TODO Auto-generated method stub
									return o1.getMenuItem().getMenuGroup().getMenuGroupOrder()
											- o2.getMenuItem().getMenuGroup().getMenuGroupOrder();
								}
							});
							Map<String, ObjectNode> menuMap = new LinkedHashMap<String, ObjectNode>();
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
							ObjectNode itemNode = mapper.createObjectNode();
							itemNode.put("userId", user.getUserId());
							itemNode.put("userName", user.getUserName());
							node.put("user", itemNode);
						}else {
							errorMsg.append("User is inactive");
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

			System.out.println("node>>" + node.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity(node.toString(), HttpStatus.OK);

	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@PostMapping(value = "/create-user",consumes = "application/json")
	public String createUser(@RequestBody UsersDto dto) {
		String result="";
		try {
			String pwd="AO_"+((int)(Math.random()*9000)+1000);
			dto.setPassword(pwd);
			 result=loginServiceInf.createUser(dto);
			if(result.equals("success")) {
				EmailDto email = new EmailDto();
				email.setEmail(dto.getEmail());
				email.setSubject("Login Credentials");
				email.setBody("Hi,<br/>"+"UserName : "+dto.getUserName()+"<br/> Your  Password :"+pwd);
				emailController.sendMailWithoutAttachement(email);
			}
		}catch (Exception e) {
          e.printStackTrace();
		}
		return result;
	}
	@PostMapping(value = "/edit-user",consumes = "application/json")
	public String editUser(@RequestBody UsersDto dto) {
		String result="";
		try {
			 result=loginServiceInf.createUser(dto);
		}catch (Exception e) {
          e.printStackTrace();
		}
		return result;
	}
	@GetMapping(value = "/users-list",produces = "application/json")
	public JSONArray usersList(@RequestParam Map<String, String> filters) {
		System.out.println(filters);
		return loginServiceInf.usersList((String)filters.get("code"),(String)filters.get("status"),(String)filters.get("name"));
	}
	
}
