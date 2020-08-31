package com.project.util;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements Job {
	public static final String SCHEDULE_KEY = "JOBNAME";

	public void execute(JobExecutionContext context) throws JobExecutionException {
		Scheduler scheduler = context.getScheduler();
		JobDataMap jobMap = context.getJobDetail().getJobDataMap();
		String jobName = jobMap.getString(JobRunner.SCHEDULE_KEY);
		if (jobName.equalsIgnoreCase("File Import")) {
			System.out.println("File Import Called...");
		}

	}

}
