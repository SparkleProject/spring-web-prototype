package com.lintech.core.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

public class IPUtils {
	
	/**
	 * {code=0, data={"area":"华东","area_id":"300000","city":"福州市","city_id":"350100","country":"中国","country_id":"CN","county":"","county_id":"-1","ip":"117.29.96.249","isp":"电信","isp_id":"100017","region":"福建省","region_id":"350000"}}
	 */
//	public static Map<String,String> getIpInfo(String ip){
//		try {
//			String json = Https.get("http://ip.taobao.com/service/getIpInfo.php?ip=117.29.96.249");
//			JSONObject parseObject = JSON.parseObject(json);
//			Object code = parseObject.get("code");
//			if(code!=null){
//				if("0".equals(code.toString())){
//					String data = parseObject.get("data").toString();
//					return JSON.parseObject(data, Map.class);
//				}
//			}
//		} catch (Exception e) {
//			
//		}
//		return null;
//	}
	
//
//	public static Map<String,String> getIpInfo(){
//		try {
//			String url="http://1212.ip138.com/ic.asp";
//			String html = Https.get(url,null,new BasicResponseHandler());
//			String s=new String(html.getBytes("ISO8859-1"), "GBK");
//			String ip = StringUtils.substringBetween(s,"您的IP是：[", "]");
//			String from = StringUtils.substringBetween(s,"来自：", "</center>");
//			String[] split = from.split(" ");
//			String city = split[0];
//			String isp = split[1];
//			Map<String,String> info=Maps.newHashMap();
//			info.put("ip",ip);
//			info.put("city",city);
//			info.put("isp",isp);
//			return info;
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
//		return null;
//	}
	
	//{"ip":"61.235.82.163","pro":"广东省","proCode":"440000","city":"广州市","cityCode":"440100","region":"天河区","regionCode":"440106","addr":"广东省广州市天河区 蓝色心情网吧","regionNames":"","err":""}
	public static Map<String,String> getIpInfo(HttpServletRequest request){
		 String ip = getIP(request);
		 return getIpInfo(ip);
	}
	
	//{"ip":"61.235.82.163","pro":"广东省","proCode":"440000","city":"广州市","cityCode":"440100","region":"天河区","regionCode":"440106","addr":"广东省广州市天河区 蓝色心情网吧","regionNames":"","err":""}
	public static Map<String,String> getIpInfo(String ip){
		try {
			String url="http://whois.pconline.com.cn/ipJson.jsp?json=true&ip="+ip;
			String json = Https.get(url);
			Map<String,String> parseObject = JSON.parseObject(json, Map.class);
			String code = parseObject.get("err");
			if(StringUtils.isBlank(code.toString())){
				return parseObject;
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	
	//http://whois.pconline.com.cn/ipJson.jsp?json=true&level=3&ip=61.235.82.163
	public static String getIP(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if(index != -1){
                return ip.substring(0,index);
            }else{
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
            return ip;
        }
        return request.getRemoteAddr();
    }
}
