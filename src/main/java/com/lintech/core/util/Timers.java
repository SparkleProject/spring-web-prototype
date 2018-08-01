package com.lintech.core.util;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class Timers {
	
	private static long startTime;
	
	public  static long start(){
		startTime=System.currentTimeMillis();
		return startTime;
	}
	
	public static long stop(){
		return System.currentTimeMillis()-startTime;
	}
	
	
	public static String stopFormat(){
		long time=System.currentTimeMillis()-startTime;
		Date date = DateUtils.addHours(new Date(time), -8);
		return DateFormatUtils.format(date, "HH:mm:ss.SSS");
	}
	
	public static void main(String[] args) throws InterruptedException {
		start();
		Thread.sleep(3000);
		System.out.println(stopFormat());
	}

}
