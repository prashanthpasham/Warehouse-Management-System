package com.project.util;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.dao.ScheduleJobsRepository;
import com.project.pojo.ScheduleJobs;
import com.project.service.intf.FileImportServiceIntf;
import com.project.service.intf.LoginServiceInf;

@Component
@Transactional
public class CommandRunner implements CommandLineRunner {
	@Autowired
	private LoginServiceInf loginServiceIntf;
	@Autowired
	private FileImportServiceIntf fileImportServiceIntf;
	/*
	 * @Autowired private RoleRepository roleRepo;
	 * 
	 * @Autowired private MenuGroupRepository menuGrpRepo;
	 * 
	 * @Autowired private MenuItemRepository menuItemRepo;
	 * 
	 * @Autowired private RoleMenuItemRepository roleMenuItemRepo;
	 * 
	 * @Autowired private UserRepository userRepository;
	 */
	/* @Autowired private ScheduleJobsRepository scheduleRepo;*/
	public void run(String... args) throws Exception {
		// executeScriptsOnEmptySchema();
		scheduleJobs();
		
	}

	public void scheduleJobs() {
		try {
			
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler = factory.getScheduler();
			if (scheduler != null) {
				scheduler.clear();
			}
			List<ScheduleJobs> jobsList = loginServiceIntf.scheduleJobs("active");
			for (ScheduleJobs job : jobsList) {
				JobDetail detail = JobBuilder.newJob(JobRunner.class).withIdentity(job.getJobName(), "group").build();
				TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger()
						.withIdentity(job.getJobName(), "Trigger").startAt(job.getCreatedDate());
				Trigger trigger = null;
				if (job.getCronExpression() == null) {
					trigger = triggerBuilder.withSchedule(SimpleScheduleBuilder.simpleSchedule()
							.withIntervalInSeconds(job.getIntervalInSec()).withRepeatCount(-1)).build();
				} else {
					trigger = triggerBuilder.withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
							.build();
				}
				JobDataMap jobMap = detail.getJobDataMap();
				jobMap.put(JobRunner.SCHEDULE_KEY, job.getJobName());
				jobMap.put(JobRunner.IMPORT_INTF,fileImportServiceIntf);
				scheduler.scheduleJob(detail, trigger);
			}
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public void executeScriptsOnEmptySchema() { try { int usersCount =
	 * loginServiceIntf.findUsersCount(); if (usersCount == 0) { Date
	 * currentDate = new Date(); MenuGroup m = new MenuGroup();
	 * m.setCreatedDate(currentDate); m.setGroupName("Company Managment");
	 * MenuGroup m1 = menuGrpRepo.save(m); MenuItem mi = new MenuItem();
	 * mi.setCreatedDate(currentDate); mi.setMenuGroup(m1);
	 * mi.setMenuName("Company Info"); MenuItem mi1 = menuItemRepo.save(mi);
	 * Role r = new Role(); r.setCreatedDate(currentDate);
	 * r.setRoleName("Admin"); r.setDescription("Admin Role"); Role r1 =
	 * roleRepo.save(r); RoleMenuItem rm = new RoleMenuItem();
	 * rm.setMenuItem(mi1); rm.setRole(r1); roleMenuItemRepo.save(rm);
	 * AESEncryption.init("t7GcYbbdbKxZtV2ge6qpeQ=="); Users users = new
	 * Users(); users.setUserCode("100");
	 * users.setPassword(AESEncryption.getInstance().encode("sysadmin#welcome"))
	 * ; users.setUserName("sysadmin"); users.setRole(r);
	 * users.setStatus("active"); userRepository.save(users);
	 * ScheduleJobs jobs = new ScheduleJobs();
		jobs.setCreatedDate(new Date());
		jobs.setIntervalInSec(60);
		jobs.setJobName("File Import");
		jobs.setStatus("active");
		scheduleRepo.save(jobs);
	 * } } catch (Exception e) { e.printStackTrace(); } }
	 */

}
