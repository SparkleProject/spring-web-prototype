package com.lintech.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lintech.core.util.SecurityUtil;
import com.lintech.entity.User;


@Controller("userLoginController")
public class LoginController{
	
	
	 @RequestMapping(value="/login")
	 public String index(HttpServletRequest request, HttpServletResponse response) {
		 User user = SecurityUtil.getCurrentUser();
		 request.setAttribute("user", user);
		 Subject currentUser = SecurityUtils.getSubject();  
		 if(currentUser.isAuthenticated()){
			 return "redirect:/user/index";
		 }
		 String errorClassName = (String)request.getAttribute("shiroLoginFailure");  
		 String msg=null;
        if(UnsupportedTokenException.class.getName().equals(errorClassName)) {  
        	msg="用户名/密码 不能为空";  
        } else if(UnknownAccountException.class.getName().equals(errorClassName)) {  
        	msg="用户名不存在";  
        } else if(DisabledAccountException.class.getName().equals(errorClassName)) {  
        	msg="该用户已被禁用";  
        } else if(LockedAccountException.class.getName().equals(errorClassName)) {  
        	msg="该用户已被锁定";  
        }  else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {  
        	msg="用户名/密码错误";  
        }else if(errorClassName != null) {  
        	msg="未知错误";  
        }  
	     request.setAttribute("error", msg);  
		 return "login";
	 }
	 
	 
//	 private UserLogin updateUserLogin(User user, HttpServletRequest request){
//			//更新已登录为失效
//			Map<String, Object> params1=Maps.newHashMap(); 
//			params1.put("userId", user.getUserId());
//			params1.put("state", UserLogin.STATE_LOGIN);
//			List<UserLogin> userLoginList = userLoginService.findAll(params1);
//			if(userLoginList!=null&&!userLoginList.isEmpty()){
//				for(UserLogin ul:userLoginList){
//					ul.setState(UserLogin.STATE_LOGOUT);
//					userLoginService.update(ul);
//				}
//			}
//			UserLogin userLogin = new UserLogin();
//			String ip = IPUtils.getIP(request);
//			HttpSession session = request.getSession();
//			String sessionId = session.getId();
//			userLogin.setSessionId(sessionId);
//			userLogin.setIp(ip);
//			Map<String, Object> params=Maps.newHashMap();
//			params.put("sessionId", sessionId);
//			params.put("ip", ip);
//			
////			/{regionCode=440106, regionNames=, proCode=440000, err=, city=广州市, cityCode=440100, ip=61.235.82.163, pro=广东省, region=天河区, addr=广东省广州市天河区 蓝色心情网吧}
//			Map<String, String> ipInfo = IPUtils.getIpInfo(ip);
//			if(ipInfo!=null){
//				userLogin.setIp(ipInfo.get("ip"));
//				userLogin.setCountry(ipInfo.get("country"));
//				userLogin.setArea(ipInfo.get("area"));
//				userLogin.setRegion(ipInfo.get("pro"));
//				userLogin.setCity(ipInfo.get("city"));
//				userLogin.setAddr(ipInfo.get("addr"));
//				params.put("ip", ipInfo.get("ip"));
//			}
//			userLogin.setState(UserLogin.STATE_LOGIN);
//			userLogin.setUserId(user.getUserId());
//			userLogin.setUserName(user.getUserName());
//			userLoginService.save(userLogin);
//			return userLogin;
//		}
	 
}
