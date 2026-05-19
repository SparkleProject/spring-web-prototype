package com.lintech.controller.admin;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogContoller {
    private static final Logger log = LoggerFactory.getLogger(LogContoller.class);

    @RequestMapping("/admin/log/{level}")
    public void changeLogLevel(@PathVariable("level") String level){
        log.info("Not implements yet.");
    }

    @RequestMapping("/admin/log/{package}/{level}")
    public void changeLogLevel(@PathVariable("package") String packages, @PathVariable("level") String level){
        LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
        ch.qos.logback.classic.Logger logger = loggerContext.getLogger(packages);
        logger.setLevel(Level.toLevel(level));
    }

}
