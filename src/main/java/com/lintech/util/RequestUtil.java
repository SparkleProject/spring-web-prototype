package com.lintech.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.lintech.core.util.ConfigUtil;

public class RequestUtil {
	
	public static String getDomain(HttpServletRequest request){
		String context = ConfigUtil.get("ctx");
		if(StringUtils.isBlank(context)){
			return getDomain(request,false);
		}else{
			return getDomain(request,true);
		}
	}
	
	public static String getDomain(HttpServletRequest request,boolean context){
		if(context){
			StringBuffer url = request.getRequestURL();  
			String domain = url.delete(url.length() - request.getRequestURI().length(), url.length()).append(request.getServletContext().getContextPath()).append("/").toString();
			return domain;
		}else{
			StringBuffer url = request.getRequestURL();  
			String domain = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
			return domain;
		}
	}
	
	

}
