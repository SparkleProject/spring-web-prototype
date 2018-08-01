package com.lintech.core.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * 控制层工具类
 * @author jonathan
 *
 */
public class ControllerUtils {
    /**
     * 获取请求参数值
     * @param request
     * @param parameter
     * @return
     */
    public static String getString(HttpServletRequest request,String parameter){
        return request.getParameter(parameter);
    }
    
    
    /**
     * 获取请求参数值
     * @param request
     * @param parameter
     * @return
     */
    public static boolean getBoolean(HttpServletRequest request,String parameter,boolean defaultVal){
    	String string = getString(request,parameter,String.valueOf(defaultVal));
        return Boolean.parseBoolean(string);
        
    }
    
    /**
     * 获取请求参数值,可设默认值
     * @param request
     * @param parameter
     * @param defaultVal
     * @return
     */
    public static  String getString(HttpServletRequest request,String parameter,String defaultVal){
        String result = request.getParameter(parameter);
        if(StringUtils.isEmpty(result)){
            return defaultVal;
        }else{
            return result;
        }
    }
    
    
    /**
     * 获取请求参数值
     * @param request
     * @param parameter
     * @return
     */
    public static  Integer getInt(HttpServletRequest request,String parameter){
        String result = request.getParameter(parameter);
        if(StringUtils.isBlank(result)){
            return null;
        }else{
            return Integer.parseInt(result);
        }
    }
    
    
    
    /**
     * 获取请求参数值,可设默认值
     * @param request
     * @param parameter
     * @param defaultVal
     * @return
     */
    public static Integer getInt(HttpServletRequest request,String parameter,int defaultVal){
        String result = request.getParameter(parameter);
        if(StringUtils.isBlank(result)){
            return defaultVal;
        }else{
            return Integer.parseInt(result);
        }
    }
    
    
    /**
     * 获取请求参数值,可设默认值
     * @param request
     * @param parameter
     * @param defaultVal
     * @return
     */
    public static Long getLong(HttpServletRequest request,String parameter,long defaultVal){
        String result = request.getParameter(parameter);
        if(StringUtils.isBlank(result)){
            return defaultVal;
        }else{
            return Long.parseLong(result);
        }
    }
    
    /**
     * 获取请求参数值
     * @param request
     * @param parameter
     * @return
     */
    public static  Double getDouble(HttpServletRequest request,String parameter){
        String result = request.getParameter(parameter);
        BigDecimal b=new BigDecimal(result);
        return b.doubleValue();
    }
    
    /**
     * 获取String类型参数的值,如s.getBytes("ISO-8859-1"),"UTF-8")
     * @param request
     * @param paramName
     */
    public static String getStringDecode(HttpServletRequest request, String paramName){
        try {
            String s = request.getParameter(paramName);
            if(s==null){
                return null;
            }else{
                String sd=new String(s.getBytes("ISO-8859-1"),"UTF-8");
                return sd;
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getCause());
        }
    }
    
    /**
     * 获取请求者IP
     * 支持服务器反向代理过的IP
     * @param request
     * @return
     */
    public static String getRequestIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    /**
     *  禁用缓存
     * @param response
     */
    public static void preventCaching(HttpServletResponse response) {
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 1L);
        response.setHeader("Cache-Control", "no-cache");
        response.addHeader("Cache-Control", "no-store");
    }
    
    
    /**
     * 获取UUID
     * @param request
     * @param parameter
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
    
    /**
     * 获取UUID
     * 适用于小型系统
     * @param request
     * @param parameter
     * @return
     */
    public static synchronized String getUUID16() {
    	return UUID.randomUUID().toString().toUpperCase().substring(0, 18);
    }
}
