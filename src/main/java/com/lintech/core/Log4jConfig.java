package com.lintech.core;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Log4jConfig  {  
    private boolean reload = true;  
    private int interval = 5000;  
    private static final Logger logger = LoggerFactory.getLogger(Log4jConfig.class);  
  
    /** 
     * log4j日志自动加载 
     * @param reload   是否开启自动加载 
     * @param interval 自动加载时间(ms) 
     */  
    public Log4jConfig(boolean reload, int interval) {  
        this.reload = reload;  
        this.interval = interval;  
        if(this.reload) {
        	String log4jPath = Log4jConfig.class.getClassLoader().getResource("log4j.properties").getPath();  
        	logger.info("log4j config file : " + log4jPath);  
        	PropertyConfigurator.configureAndWatch(log4jPath, this.interval);  
        }
    }
    
}  