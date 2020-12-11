package com.project.service.impl;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.dao.BusinessTerritoryRepository;
import com.project.dao.MasterLookUpRepository;
import com.project.dao.MenuItemRepository;
import com.project.dao.RoleMenuItemRepository;
import com.project.dao.RoleRepository;
import com.project.dao.ScheduleJobsRepository;
import com.project.dao.UserRepository;
import com.project.dao.UsersRepository;
import com.project.dto.RoleDto;
import com.project.dto.RoleMenuDto;
import com.project.dto.UsersDto;
import com.project.pojo.BusinessTerritory;
import com.project.pojo.MasterLookUp;
import com.project.pojo.MenuGroup;
import com.project.pojo.MenuItem;
import com.project.pojo.Role;
import com.project.pojo.RoleMenuItem;
import com.project.pojo.ScheduleJobs;
import com.project.pojo.Users;
import com.project.service.intf.LoginServiceInf;
import com.project.util.AESEncryption;

@Service
@Transactional
public class LoginServiceImpl implements LoginServiceInf {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ScheduleJobsRepository scheduleJobsRepository;
	@Autowired
	private RoleMenuItemRepository roleMenuRepo;
	@Autowired
	private MasterLookUpRepository masterLookupRepo;
	@Autowired
	private BusinessTerritoryRepository businessTerroritoryRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private MenuItemRepository menuItemRepo;
	@Autowired
	private UsersRepository usersRepo;
	public Users loginValidation(String userName, String password) {

		return userRepository.findByUserNameAndPassword(userName, password);
	}

	public int findUsersCount() {
		// TODO Auto-generated method stub
		return userRepository.findUsersCount();
	}

	public List<ScheduleJobs> scheduleJobs(String status) {
		// TODO Auto-generated method stub
		return scheduleJobsRepository.fetchScheduleJobs(status);
	}

	public List<RoleMenuItem> fetchMenusByRole(int roleId) {
		// TODO Auto-generated method stub
		return roleMenuRepo.fetchMenusByRole(roleId);
	}

	@Override
	public MasterLookUp saveMasterLookUp(MasterLookUp lookUp) {
		// TODO Auto-generated method stub
		return masterLookupRepo.save(lookUp);
	}

	@Override
	public JSONArray fetchBusinessHierarchy(String type) {
		List<MasterLookUp> ls = masterLookupRepo.fetchBusinessHierarchy(type);
		JSONArray results = new JSONArray();
		if (!ls.isEmpty()) {
			for (MasterLookUp ld : ls) {
				JSONObject obj = new JSONObject();
				obj.put("name", ld.getName());
				obj.put("id", ld.getMasterId());
				obj.put("parentId", ld.getParentMasterId());
				if(ld.getParentMasterId()>0) {
				Optional<MasterLookUp> lok=masterLookupRepo.findById(ld.getParentMasterId());
				obj.put("parentName", lok.get().getName());
				}else {
					obj.put("parentName", "");
				}
				results.add(obj);
			}
		}
		return results;
	}

	@Override
	public Users getUserByName(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUserName(username);
	}

	@Override
	public MasterLookUp findByMasterLookupId(Integer id) {
		// TODO Auto-generated method stub
		Optional<MasterLookUp> ld=masterLookupRepo.findById(id);
		return ld.isPresent()?ld.get():null;
	}

	@Override
	public BusinessTerritory findByBusinessTerritoryId(Integer id) {
		// TODO Auto-generated method stub
		return businessTerroritoryRepo.findById(id).get();
	}

	@Override
	public int findBsNameinTerritory(String bsName, int parentId) {
		// TODO Auto-generated method stub
		return businessTerroritoryRepo.findBsNameinTerritory(bsName, parentId);
	}

	@Override
	public String saveBusinessTerritory(BusinessTerritory territory) {
		BusinessTerritory bt = businessTerroritoryRepo.save(territory);
		BusinessTerritory parent = null;
		if (bt.getParentId() > 0) {
			parent = findByBusinessTerritoryId(bt.getParentId());

		}
		MasterLookUp ms = bt.getMasterLookUp();
		bt.setBsNames((parent != null ? parent.getBsNames() : "") + ms.getName() + "#" + bt.getBusinessName() + "@");
		bt.setBsIds((parent != null ? parent.getBsIds() : "") + bt.getBusinessId()  + "@");
		System.out.println(bt.getBusinessId());
		return "success";
	}

	@Override
	public JSONArray fetchBusinessTerritory(String type) {
		JSONArray results = new JSONArray();
		try {
			List<MasterLookUp> ls = masterLookupRepo.fetchBusinessHierarchy("BT");
			if (!ls.isEmpty()) {
				for (MasterLookUp ld : ls) {
					JSONObject obj = new JSONObject();
					JSONArray items = new JSONArray();
					obj.put("name", ld.getName());
					obj.put("id", ld.getMasterId());
					obj.put("parentId", ld.getParentMasterId());
					obj.put("selectedValue", ld.getMasterId()+("all".equalsIgnoreCase(type)?"@0":"@-1"));
					if (ld.getParentMasterId() > 0) {
						Optional<MasterLookUp> lok = masterLookupRepo.findById(ld.getParentMasterId());
						obj.put("parentName", lok.get().getName());
					} else {
						List<BusinessTerritory> bs = getBusinessTerritoryByLookUpId(ld.getMasterId());
						if (!ls.isEmpty()) {
							JSONObject item1 = new JSONObject();
							item1.put("id", 0);
							item1.put("parentId", 0);
							item1.put("name", "all".equalsIgnoreCase(type)?"All":"Select");
							item1.put("bsid", ld.getMasterId()+("all".equalsIgnoreCase(type)?"@0":"@-1"));
							items.add(item1);
							for (BusinessTerritory bt : bs) {
								JSONObject item = new JSONObject();
								item.put("id", bt.getBusinessId());
								item.put("parentId", bt.getParentId());
								item.put("name", bt.getBusinessName());
								item.put("bsid", ld.getMasterId()+"@"+bt.getBusinessId());
								items.add(item);
							}
						}
						obj.put("parentName", "");
					}
					obj.put("items", items);
					results.add(obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	public List<BusinessTerritory> getBusinessTerritoryByLookUpId(int id){
		return businessTerroritoryRepo.getBusinessTerritoryByLookUpId(id);
	}
	public JSONObject getBusinessTerritoryByParentId(int parentId,String type){
		JSONObject ob = new JSONObject();
		JSONArray results = new JSONArray();
		try {
		List<BusinessTerritory> ls=businessTerroritoryRepo.getBusinessTerritoryById(parentId);
		int lookUpId=0;
		if(!ls.isEmpty()) {
			for(BusinessTerritory bt:ls) {
				if(results.size()==0) {
					JSONObject item1 = new JSONObject();
					item1.put("id", 0);
					item1.put("parentId", 0);
					item1.put("name", "all".equalsIgnoreCase(type)?"All":"select");
					item1.put("bsid", bt.getMasterLookUp().getMasterId()+("all".equalsIgnoreCase(type)?"@0":"@-1"));
					results.add(item1);
				}
				JSONObject obj = new JSONObject();
				obj.put("id", bt.getBusinessId());
				obj.put("parentId", bt.getParentId());
				obj.put("name", bt.getBusinessName());
				obj.put("bsid", bt.getMasterLookUp().getMasterId()+"@"+bt.getBusinessId());
				if(lookUpId==0) {
					lookUpId=bt.getMasterLookUp().getMasterId();
				}
				results.add(obj);
			}
		}
		ob.put("items", results);
		ob.put("masterLookupId", lookUpId);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ob;
	}
	public String saveRole(RoleDto dto) {
		try {
			Role role=roleRepo.findByRoleNameIgnoreCase(dto.getRoleName().toLowerCase());
			if(role==null) {
				Role r = new Role();
				r.setRoleName(dto.getRoleName());
				r.setCreatedDate(new Date());
				r.setDescription(dto.getDescription());
				Role rl =roleRepo.save(r);
				for(RoleMenuDto menu:dto.getRoleMenus()) {
					RoleMenuItem item = new RoleMenuItem();
					item.setRole(rl);
					item.setMenuItem(menuItemRepo.findById(menu.getMenuId()).get());
					item.setAddOperation(menu.isAdd());
					item.setEditOperation(menu.isEdit());
					item.setDeleteOperation(menu.isDelete());
					roleMenuRepo.save(item);
				}
				
			}else {
				return dto.getRoleName()+" already exist";
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}
	public String editRole(RoleDto dto) {
		try {
			Role role=roleRepo.findByRoleNameIgnoreCase(dto.getRoleName().toLowerCase());
			if(role==null || role.getRoleId()==dto.getId()) {
				//delete all role menus
				roleMenuRepo.deleteByRoleId(dto.getId());
				Role rl =roleRepo.findById(dto.getId()).get();
				rl.setRoleName(dto.getRoleName());
				rl.setDescription(dto.getDescription());
				roleRepo.save(rl);
				for(RoleMenuDto menu:dto.getRoleMenus()) {
					RoleMenuItem item = new RoleMenuItem();
					item.setRole(rl);
					item.setMenuItem(menuItemRepo.findById(menu.getMenuId()).get());
					item.setAddOperation(menu.isAdd());
					item.setEditOperation(menu.isEdit());
					item.setDeleteOperation(menu.isDelete());
					roleMenuRepo.save(item);
				}
			}else {
				return dto.getRoleName()+" already exist";
			}
		}catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	public String createUser(UsersDto dto) {
		try {
			List<Users> users = usersRepo.findByUserNameIgnoreCaseOrUserCode(dto.getUserName().toLowerCase(),
					dto.getUserCode());
			if (users != null && !users.isEmpty()) {
				for (Users us : users) {
					if (us.getUserId() != dto.getUserId()) {
						if (us.getUserName().equalsIgnoreCase(dto.getUserName()))
							return "UserName " + dto.getUserName() + " already exist";
						else
							return "User Code " + dto.getUserCode() + " already exist";
					}
				}
			}
				Users user = null;
				if (dto.getUserId() != null && dto.getUserId() > 0) {
					Optional<Users> us = usersRepo.findById(dto.getUserId());
					if (us.isPresent()) {
						user = us.get();
						user.setModifiedDate(new Date());
					}

				} else {
					user = new Users();
					user.setCreatedDate(new Date());
				}
				if (user != null) {
					user.setUserCode(dto.getUserCode());
					user.setUserName(dto.getUserName());
					user.setEmail(dto.getEmail());
					user.setPhoneNo(dto.getPhoneNo());
					user.setRole(roleRepo.findById(dto.getRoleId()).get());
					user.setBusinessTerritory(businessTerroritoryRepo.findById(dto.getBusinessId()).get());
					if (dto.getPassword() != null) {
						AESEncryption.init();
						user.setPassword(AESEncryption.getInstance()
								.encode(dto.getUserName().toLowerCase() + "#" + dto.getPassword()));
					}
					user.setStatus(dto.getStatus() != null ? dto.getStatus() : "active");
					usersRepo.save(user);
				} else {
					return "UserName " + dto.getUserName() + " doesn't exist";
				}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return "success";
	}

	@Override
	public JSONArray usersList(String code,String status,String name) {
		JSONArray results = new JSONArray();
		try {
			List<Object[]> users = usersRepo.findUsersList(code,status,name);
		   if(!users.isEmpty()) {
			   for(Object[] obj:users) {
				   int i=0;
				   JSONObject user= new JSONObject();
				   user.put("userId", obj[i++].toString());
				   user.put("userName", obj[i++].toString());
				   user.put("userCode", obj[i++].toString());
				   user.put("status", obj[i++].toString());
				   user.put("createdDate", obj[i]!=null?obj[i].toString():"");
				   i++;
				   user.put("lastLoginTime", obj[i]!=null?obj[i].toString():"");
				   i++;
				   results.add(user);
			   }
		   }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	@Override
	public JSONArray roleList(JSONObject filters) {
		JSONArray results = new JSONArray();
		try {

			Pageable page = PageRequest.of(Integer.parseInt(filters.get("first").toString()),
					Integer.parseInt(filters.get("size").toString()));
			List<Role> list = roleRepo.fetchRoleList(page);
			if (!list.isEmpty()) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				for (Role role : list) {
					JSONObject obj = new JSONObject();
					obj.put("roleId", role.getRoleId());
					obj.put("roleName", role.getRoleName());
					obj.put("description", role.getDescription());
					obj.put("createdDate", role.getCreatedDate() != null ? sdf.format(role.getCreatedDate()) : "");
					results.add(obj);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}
	@Override
	public JSONObject roleMenus(int roleId) {
		JSONObject node = new JSONObject();
		JSONArray menuGroups = new JSONArray();
		try {
			List<RoleMenuItem> roleMenus = fetchMenusByRole(roleId);
			Collections.sort(roleMenus, new Comparator<RoleMenuItem>() {

				@Override
				public int compare(RoleMenuItem o1, RoleMenuItem o2) {
					// TODO Auto-generated method stub
					return o1.getMenuItem().getMenuGroup().getMenuGroupOrder()
							- o2.getMenuItem().getMenuGroup().getMenuGroupOrder();
				}
			});

			Map<String, JSONObject> menuMap = new LinkedHashMap<String, JSONObject>();
			if (!roleMenus.isEmpty()) {
				for (RoleMenuItem roleMenu : roleMenus) {
					// Every has mapped menuitem
					MenuItem menuItem = roleMenu.getMenuItem();
					MenuGroup menuGroup = menuItem.getMenuGroup();
					String key = menuGroup.getGroupName();
					JSONArray menuItems = null;
					JSONObject grpNode = null;
					if (menuMap.containsKey(key)) {
						grpNode = menuMap.get(key);
						menuItems = (JSONArray) grpNode.get("menuLinks");
					} else {
						grpNode = new JSONObject();
						grpNode.put("groupName", menuGroup.getGroupName());
						menuItems = new JSONArray();
					}
					JSONObject itemNode = new JSONObject();
					itemNode.put("menuItemId", menuItem.getMenuItemId());
					itemNode.put("menuItem", menuItem.getMenuName());
					menuItems.add(itemNode);
					grpNode.put("menuLinks", menuItems);
					menuMap.put(key, grpNode);
				}
				for (Entry<String, JSONObject> grp : menuMap.entrySet()) {
					menuGroups.add(grp.getValue());
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		node.put("menus", menuGroups);
		return node;
	}
	public int findMasterLookupParentId(String type,int parentId){
		int count = 0;
		try{
		  count = masterLookupRepo.findMasterLookupParentId(type, parentId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}

}
