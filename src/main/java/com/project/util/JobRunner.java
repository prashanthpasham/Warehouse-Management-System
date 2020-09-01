package com.project.util;

import javax.annotation.PostConstruct;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.project.service.intf.FileImportServiceIntf;
import com.project.service.intf.LoginServiceInf;

@Component
public class JobRunner implements Job {
	public static final String SCHEDULE_KEY = "JOBNAME";
	@Autowired
	private FileImportServiceIntf fileServiceIntf;

	@PostConstruct
	public void init() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		System.out.println("springbean...");
	}

	public void execute(JobExecutionContext context) throws JobExecutionException {
		// Scheduler scheduler = context.getScheduler();

		JobDataMap jobMap = context.getJobDetail().getJobDataMap();
		String jobName = jobMap.getString(JobRunner.SCHEDULE_KEY);
		if (jobName.equalsIgnoreCase("File Import")) {
			pickFileFromFTPFolder();
		}

	}

	public void pickFileFromFTPFolder() {
		try {
			System.out.println("fileServiceIntf>>" + fileServiceIntf);
			fileServiceIntf.pickFileFromFTPFolder();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
