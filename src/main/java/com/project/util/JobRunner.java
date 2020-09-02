package com.project.util;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
import com.project.service.intf.FileImportServiceIntf;

@Component
public class JobRunner implements Job {
	public static final String SCHEDULE_KEY = "JOBNAME";
	public static final String IMPORT_INTF = "FILE";

	public void execute(JobExecutionContext context) throws JobExecutionException {
		// Scheduler scheduler = context.getScheduler();

		JobDataMap jobMap = context.getJobDetail().getJobDataMap();
		String jobName = jobMap.getString(JobRunner.SCHEDULE_KEY);
		if (jobName.equalsIgnoreCase("File Import")) {
			pickFileFromFTPFolder((FileImportServiceIntf) jobMap.get(JobRunner.IMPORT_INTF));
		}

	}

	public void pickFileFromFTPFolder(FileImportServiceIntf fileServiceIntf) {
		try {
			fileServiceIntf.pickFileFromFTPFolder();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
