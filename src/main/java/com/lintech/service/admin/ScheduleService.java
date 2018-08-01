package com.lintech.service.admin;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScheduleService implements Job{
    
    Log log=LogFactory.getLog(getClass());
    
    @Override
    public void execute(JobExecutionContext jobexecutioncontext) throws JobExecutionException {
        log.debug("quartz scheduling.");
        System.out.println("scheduling @ "+new Date().getTime());
    }

}
