package com.lintech.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lintech.core.easyui.Menu;
import com.lintech.core.easyui.Messager;
import com.lintech.core.util.ControllerUtils;
import com.lintech.core.util.SecurityUtil;
import com.lintech.entity.User;
import com.lintech.service.admin.MenuService;
import com.lintech.service.admin.RoleResService;
import com.lintech.service.admin.UserService;


@Controller("userPasswordController")
public class PasswordController{
	
	@Autowired
    MenuService menuService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    RoleResService roleResService;
    
	 @RequestMapping(value="/user/password")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("user/password",getModel(request));
    }

	private Map<String, Object> getModel(HttpServletRequest request) {
		User user = SecurityUtil.getCurrentUser();
        List<Menu> menuList=menuService.findAllByStaffId(user.getId());
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("menuList",menuList);
        model.put("user",user);
		return model;
	}
	
	 
	 @ResponseBody
	 @RequestMapping(value="/user/password/submit")
	 public Object submit(HttpServletRequest request, HttpServletResponse response) {
		 User user = SecurityUtil.getCurrentUser();
		 String passwordOld=ControllerUtils.getString(request,"passwordOld");
		 String passwordNew=ControllerUtils.getString(request,"passwordNew");
		 String passwordOldMD5=SecurityUtil.md5(passwordOld);
	     String passwordNewMD5=SecurityUtil.md5(passwordNew);
	     if(user.getPassword().equals(passwordOldMD5)) {
	    	 userService.changePassword(user.getId(),passwordNewMD5);
	    	 return new Messager(true);
	     }else {
	    	 return new Messager(false,"旧密码不正确");
	     }
	 }
	 

	 
}
