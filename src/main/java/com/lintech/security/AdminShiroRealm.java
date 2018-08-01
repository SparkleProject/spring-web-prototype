package com.lintech.security;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lintech.core.util.SecurityUtil;
import com.lintech.entity.Role;
import com.lintech.entity.RoleRes;
import com.lintech.entity.Staff;
import com.lintech.service.admin.RoleResService;
import com.lintech.service.admin.RoleService;
import com.lintech.service.admin.StaffService;

public class AdminShiroRealm extends AuthorizingRealm{ 
   private Logger logger=LoggerFactory.getLogger(this.getClass());
   private static final int PERMITION=2;
   
   StaffService staffService;
   
   RoleResService roleResService;
	
   RoleService roleService;
    
   // 获取授权信息
   protected AuthorizationInfo doGetAuthorizationInfo( 
      PrincipalCollection principals) { 
      String username = (String) principals.fromRealm(getName()).iterator().next(); 
      if( username != null ){ 
         Set<String> pers=getPermission(username); 
         if( pers != null && !pers.isEmpty() ){ 
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(); 
            for(String each:pers )
            info.addStringPermission(each);
            return info; 
         } 
      } 
      return null; 
   } 
   
   // 获取认证信息
   protected AuthenticationInfo doGetAuthenticationInfo(
      AuthenticationToken authcToken ) throws AuthenticationException { 
      UsernamePasswordToken token = (UsernamePasswordToken) authcToken; 
      String username = token.getUsername(); 
      String password = new String(token.getPassword());
      if(StringUtils.isEmpty(username)|| StringUtils.isEmpty(password)){ 
    	  throw new UnsupportedTokenException();
      }else{
    	  Staff staff = staffService.findOneByLoginName(username);
          if(staff==null)
         	 throw new UnknownAccountException(username);
          if(staff.getEnabled()!=1)
         	 throw new DisabledAccountException(username);
          if(staff.getLocked()==1)
         	 throw new LockedAccountException(username);
          if(!staff.getPassword().equals(SecurityUtil.md5(password))){
         	 throw new IncorrectCredentialsException(username);
          }
          
          logger.debug("########### Staff "+username+" logined. ###########");
          SimpleAuthenticationInfo sai = new SimpleAuthenticationInfo(
        		  staff,staff.getPassword(),getName()); 
          sai.setCredentialsSalt(ByteSource.Util.bytes(SecurityUtil.salt));
          return sai;
      }
   }
   
   private Set<String> getPermission(String username){
	   Staff staff = staffService.findOneByLoginName(username);
	   Set<String> perm=new HashSet<String>();
	   Map<String,Object> params=new HashMap<String,Object>();
       params.put("staffId", staff.getId());
       List<Role> roleList = roleService.findAll(new HashMap<String,Object>());
       for(Role role:roleList){
       	Map<String,Object> _params=new HashMap<String,Object>();
       	_params.put("roleId", role.getId());
       	_params.put("resType", PERMITION);
       	List<RoleRes> rrList = roleResService.findAll(_params);
       	for(RoleRes rr:rrList){
       		perm.add(rr.getResCode());
       	}
       }
       logger.debug(username +" permission"+perm.toString());
       return perm;
   }
   

	public void setStaffService(StaffService staffService) {
		this.staffService = staffService;
	}
	
	public void setRoleResService(RoleResService roleResService) {
		this.roleResService = roleResService;
	}
	
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	   
	   

}