package com.lintech.core.task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

//@WebServlet(
//urlPatterns="/plugin/schedule",
//name = "TaskInitializer",
//loadOnStartup = 20
//)
public class ScheduleInitializer extends  HttpServlet{
    private static final long serialVersionUID = -1147163548732029758L;
    Logger logger=LoggerFactory.getLogger(getClass());
    
    @Override
    public void init() throws ServletException {
        super.init();
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        ScheduleService schedulerService=(ScheduleService)webApplicationContext.getBean("scheduleService");
        try {
            logger.debug("Schedule starting....");
            schedulerService.startup();
            logger.debug("Schedule ready.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
