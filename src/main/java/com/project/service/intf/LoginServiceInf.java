package com.project.service.intf;

import java.util.List;

import com.project.pojo.RoleMenuItem;
import com.project.pojo.ScheduleJobs;
import com.project.pojo.Users;

public interface LoginServiceInf {
	public Users loginValidation(String userName, String password);

	public int findUsersCount();

	public List<ScheduleJobs> scheduleJobs(String status);
   
	public List<RoleMenuItem> fetchMenusByRole(int roleId);
}
