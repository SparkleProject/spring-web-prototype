package com.lintech.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 日期工具<br>
 * 关于格式化字符串为日期的方法，请使用org.apache.commons.lang3.time.DateUtils
 */
public class Dates {
    public static final String MONTH_FORMAT = "yyyy-MM";
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String MONTH_FORMAT_CN = "yyyy年MM月";
    public static final String DATE_FORMAT_CN = "yyyy年MM月dd日";
    public static final String DATETIME_FORMAT_CN ="yyyy年MM月dd日 HH时mm分";
    public static final String FORMAT_EEE_MMM_dd="EEE MMM dd HH:mm:ss Z yyyy";//"Wed Nov 26 00:00:00 +0800 2014" new Locale("English")
    
    /**
     * 格式化日期
     * @param date 日期
     * @param format 格式
     * @return
     */
    public static String formatDate(Date date,String format) {
        return new SimpleDateFormat(format).format(date);
    }
    

    
    /**
     * 获取每个月最后一天
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) { 
		Calendar cal = Calendar.getInstance(); 
		cal.setTime(date); 
		cal.set(Calendar.DAY_OF_MONTH, 1); 
		cal.roll(Calendar.DAY_OF_MONTH, -1); 
		return cal.getTime(); 
   } 
    
  
    /**
     * 获取前几天的日期
     * @param days 天数
     * @return
     */
    public static Date getPrevious(int days) {
        return new Date(System.currentTimeMillis() - days * 3600000L * 24L);
    }
    
    /**
     * 获取后几天的日期
     * @param days 天数
     * @return
     */
    public static Date next(int days,Date date) {
        return new Date(date.getTime() + days * 3600000L * 24L);
    }
    

    /**
     * 获取本月第一天的日期
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) { 
    	Calendar cal = Calendar.getInstance(); 
    	cal.set(Calendar.DAY_OF_MONTH, 1);
         Date beginTime = cal.getTime();
        return beginTime;
    } 
    
    /**
     * 获取本周的第一天的日期
     * @return
     */
    public static Date getFirstDateOfWeek(){
    	   Calendar calendar = Calendar.getInstance();
    	   int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK); //获取周开始基准
    	   int current = calendar.get(Calendar.DAY_OF_WEEK); //获取当天周内天数
    	   calendar.add(Calendar.DAY_OF_WEEK, min-current); //当天-基准，获取周开始日期
    	   Date start = calendar.getTime();
    	   return start;
    }
    
    /**
     * 获取本月最后一天的日期
     * @return
     */
    public static Date getLastDateOfWeek(){
    	Calendar calendar = Calendar.getInstance();
    	int min = calendar.getActualMinimum(Calendar.DAY_OF_WEEK); //获取周开始基准
    	int current = calendar.get(Calendar.DAY_OF_WEEK); //获取当天周内天数
    	calendar.add(Calendar.DAY_OF_WEEK, min-current); //当天-基准，获取周开始日期
    	calendar.add(Calendar.DAY_OF_WEEK, 6); //开始+6，获取周结束日期
    	Date end = calendar.getTime();
    	return end;
    }
    
    public static Date parseDate(String date){
    	return parse(date,DATE_FORMAT,java.util.Locale.CHINA);
	}
    
    
    public static Date parseDateTime(String date){
    	return parse(date,DATETIME_FORMAT,java.util.Locale.CHINA);
    }
    
    
    public static String format(long date,String format,Locale locale){
    	SimpleDateFormat sdf = new SimpleDateFormat(format,locale);
    	return sdf.format(new Date(date));
    }
    
    public static Date parse(String date,String format,Locale locale){
		SimpleDateFormat sdf = new SimpleDateFormat(format,locale);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
