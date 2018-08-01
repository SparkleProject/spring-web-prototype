package com.lintech.controller.admin;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogContoller {
    Logger log=LogManager.getLogger(getClass());
    /**
     * 更改全局日志输出级别（慎用）
     * @param level
     */
    @RequestMapping("/admin/log/{level}")
    public void changeLogLevel(@PathVariable("level") String level){
        //TODO Open this comment if you will change Application log level.
        //LogManager.getRootLogger().setLevel(Level.toLevel(level));
        log.info("Not implements yet.");
    }
    
    /**
     * 更改指定包日志输出级别
     * @param packages
     * @param level
     */
    @RequestMapping("/admin/log/{package}/{level}")
    public void changeLogLevel(@PathVariable("package") String packages,@PathVariable("level") String level){
        LogManager.getLogger(packages).setLevel(Level.toLevel(level));
    }

}
