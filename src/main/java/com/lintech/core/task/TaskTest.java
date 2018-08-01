package com.lintech.core.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskTest implements org.quartz.Job{
	
	Logger log=LoggerFactory.getLogger(getClass());

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		log.info("task test");
	}

}
