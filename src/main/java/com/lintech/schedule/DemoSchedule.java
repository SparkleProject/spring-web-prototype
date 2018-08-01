package com.lintech.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DemoSchedule {
	
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	public void doJob() {
		logger.debug("schedule test.");
	}

}
