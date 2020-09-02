package com.project.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.RoleMenuItemRepository;
import com.project.dao.ScheduleJobsRepository;
import com.project.dao.UploadFileHistoryRepository;
import com.project.dao.UserRepository;
import com.project.pojo.RoleMenuItem;
import com.project.pojo.ScheduleJobs;
import com.project.pojo.UploadFileHistory;
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

}
