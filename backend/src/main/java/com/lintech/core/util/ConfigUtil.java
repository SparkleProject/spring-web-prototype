package com.lintech.core.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;


public class ConfigUtil {
	private static final String CONFIG_FILE_NAME="config.properties";
	private static Properties prop=null;
	static{
		prop=new Properties();
		try {
			prop.load(ConfigUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String get(String propertyName){
		return prop.getProperty(propertyName);
	}
	
	public static int getInt(String propertyName){
		return new BigDecimal(get(propertyName)).intValue();
	}
}
