package com.project.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.BusinessTerrorityRepository;
import com.project.dao.MasterLookUpRepository;
import com.project.dao.RoleMenuItemRepository;
import com.project.dao.ScheduleJobsRepository;
import com.project.dao.UserRepository;
import com.project.pojo.MasterLookUp;
import com.project.pojo.RoleMenuItem;
import com.project.pojo.ScheduleJobs;
import com.project.pojo.Users;
import com.project.service.intf.LoginServiceInf;

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
				obj.put("parent", ld.getParentMasterId());
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

}
