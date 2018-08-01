package com.lintech.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController{
	
	
	 @RequestMapping(value="/admin/login")
	 public String index(HttpServletRequest request, HttpServletResponse response) {
		 String code = request.getParameter("code");
		 if(StringUtils.isNotBlank(code)) {
			 if("-1".equals(code)) {
                request.setAttribute("error", "Session timeout");
			 }
			 return "admin/login";
		 }
		 
		 Subject currentUser = SecurityUtils.getSubject();  
		 if(currentUser.isAuthenticated()){
			 return "redirect:/admin/";
		 }
		 
		 String errorClassName = (String)request.getAttribute("shiroLoginFailure");  
		 String msg=null;
        if(UnsupportedTokenException.class.getName().equals(errorClassName)) {  
            msg = "UserName/Password cannot is required";
        } else if(UnknownAccountException.class.getName().equals(errorClassName)) {  
            msg = "UserName is not exist";
        } else if(DisabledAccountException.class.getName().equals(errorClassName)) {  
            msg = "This user is disabled";
        } else if(LockedAccountException.class.getName().equals(errorClassName)) {  
            msg = "This user is locked";
        }  else if(IncorrectCredentialsException.class.getName().equals(errorClassName)) {  
            msg = "UserName/Password is incorrect";
        }else if(errorClassName != null) {  
            msg = "Unknow Error";
        }  
	     request.setAttribute("error", msg);  
		 return "admin/login";
	 }
}
