package com.lintech.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lintech.entity.RoleRes;
import com.lintech.entity.RoleStaff;
import com.lintech.entity.Staff;
import com.lintech.service.admin.RoleResService;
import com.lintech.service.admin.RoleStaffService;

public class AdminAuthorizationFilter extends AuthorizationFilter{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	
	private final String KEY_PREFIX="com.lintech.security.AdminAuthorizationFilter:";
	
	private final String CACHE_KEY="adminShiroCache";
	
	RoleResService roleResService;
	
	RoleStaffService roleStaffService;
	
	protected AntPathMatcher matcher=new AntPathMatcher();
	
	List<String> exclusions;
	
	CacheManager cacheManager;
	
	
    public boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse response, Object mappedValue) throws IOException {
    	HttpServletRequest request = (HttpServletRequest) servletRequest;  
    	String servletPath = request.getServletPath();
    	if(isLoginRequest(servletRequest, response)){
    		return true;
    	}
    	if(isExclusion(servletPath)){
    		return true;
    	}
    	Integer staffId=null;
    	try {
    		staffId = getStaffId(servletRequest, response);
		} catch (NullPointerException e) {
			//session timeout
			return false;
		}
        
    	String key = getKey(staffId, servletPath);
    	
    	if(cacheManager!=null){
    		Cache<Object, Object> cache = cacheManager.getCache(CACHE_KEY);
    		if(cache!=null){
        		Object object = cache.get(key);
        		if(object==null)
        			cache.put(key, isPermission(servletPath, staffId));
        		Boolean result=(Boolean)cache.get(key);
        		logger.debug("adminAuth from cache [ key:"+key+",value:"+result+" ]");
        		return result;
        	}
    	}
    	return isPermission(servletPath, staffId);
    }

	private Integer getStaffId(ServletRequest servletRequest,
			ServletResponse response) {
		Subject subject = getSubject(servletRequest, response);
		Staff staff=(Staff)subject.getPrincipal();
		return staff.getId();
	}

	private boolean isPermission(String servletPath, Integer staffId) {
		Map<String,Object> params=new HashMap<String,Object>();
        params.put("staffId", staffId);
        List<RoleStaff> roleList = roleStaffService.findAll(params);
        for(RoleStaff roleStaff:roleList){
        	Map<String,Object> _params=new HashMap<String,Object>();
        	_params.put("roleId", roleStaff.getRoleId());
        	List<RoleRes> rrList = roleResService.findAll(_params);
        	for(RoleRes rr:rrList){
        		logger.debug("partner:{},servlet path:{}",rr.getResCode(), servletPath);
        		if(match(rr.getResCode(), servletPath)){
        			return true;
        		}
        		
        	}
        }
        return false;
	}

	public void setRoleResService(RoleResService roleResService) {
		this.roleResService = roleResService;
	}

	
	public void setRoleStaffService(RoleStaffService roleStaffService) {
		this.roleStaffService = roleStaffService;
	}

	public void setExclusions(List<String> exclusions) {
		this.exclusions = exclusions;
	}
	

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	private boolean match(String partner,String url){
		if(partner.indexOf(".")>-1||partner.indexOf("?")>-1){
			return matcher.match(partner, url);
		}else{
			return matcher.match(partner, url)||matcher.match(getPartner(partner), url);
		}
	}
	
	private String getPartner(String partner){
		if(partner.endsWith("/")){
			return partner+="**";
		}else{
			return partner+"/**";
		}
	}
	
	private boolean isExclusion(String url){
		if(exclusions==null){
			return true;
		}else{
			for(String partner:exclusions){
				if(matcher.match(partner,url)){
					return true;
				}
			}
		}
		return false;
	}
	

	
	public String getKey(Integer staffId,String servletPath){
		return KEY_PREFIX+":"+staffId+":"+servletPath;
	}
}
