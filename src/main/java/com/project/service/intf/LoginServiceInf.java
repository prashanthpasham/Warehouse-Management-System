package com.project.service.intf;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.project.pojo.BusinessTerritory;
import com.project.pojo.MasterLookUp;
import com.project.pojo.RoleMenuItem;
import com.project.pojo.ScheduleJobs;
import com.project.pojo.Users;

public interface LoginServiceInf {
	public Users loginValidation(String userName, String password);

	public int findUsersCount();

	public List<ScheduleJobs> scheduleJobs(String status);
   
	public List<RoleMenuItem> fetchMenusByRole(int roleId);
	
	public MasterLookUp saveMasterLookUp(MasterLookUp lookUp);

	public JSONArray fetchBusinessHierarchy(String type);

	public Users getUserByName(String username);
	
	public MasterLookUp findByMasterLookupId(Integer id);
	
	public BusinessTerritory findByBusinessTerritoryId(Integer id);
	
	public int findBsNameinTerritory(String bsName,int parentId);

	public String saveBusinessTerritory(BusinessTerritory territory);

	public JSONArray fetchBusinessTerritory();
	
	public JSONObject getBusinessTerritoryByParentId(int parentId);

}
