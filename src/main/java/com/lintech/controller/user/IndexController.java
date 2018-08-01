package com.lintech.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lintech.core.easyui.Menu;
import com.lintech.core.util.SecurityUtil;
import com.lintech.entity.User;
import com.lintech.service.admin.MenuService;
import com.lintech.service.admin.RoleResService;

@Controller("userIndexController")
public class IndexController{
	
    @Autowired
    MenuService menuService;
    
    @Autowired
    RoleResService roleResService;
    

    @RequestMapping("/user/index")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("user/index",getModel(request));
    }

	private Map<String, Object> getModel(HttpServletRequest request) {
		User user = SecurityUtil.getCurrentUser();
        List<Menu> menuList=menuService.findAllByRole("ROLE_USER");
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("menuList",menuList);
        model.put("user",user);
		return model;
	}
	
	
	
}
