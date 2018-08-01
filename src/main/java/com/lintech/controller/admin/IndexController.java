package com.lintech.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lintech.core.easyui.EasyUI;
import com.lintech.core.easyui.Menu;
import com.lintech.core.util.SecurityUtil;
import com.lintech.entity.Staff;
import com.lintech.service.admin.MenuService;
import com.lintech.service.admin.RoleResService;
import com.lintech.service.admin.StaffService;

@Controller
@RequestMapping("/admin")
public class IndexController{
	
	private final String CACHE_KEY_PREFIX="com.lintech.controller.admin.IndexController:";

    @Autowired
    MenuService menuService;
    
    @Autowired
    StaffService staffService;
    
    @Autowired
    RoleResService roleResService;
    
    @Autowired
    CacheManager adminShiroCacheManager;

    @RequestMapping("/")
    public String index(HttpServletRequest request, HttpServletResponse response,Model model) {
    	Integer staffId=null;
    	try {
    		staffId=SecurityUtil.getCurrentStaffId();
		} catch (Exception e) {
    		return "redirect:/admin/login?code=-1";
		}
    	Map<String, Object> meta =getModel(request);
    	model.addAllAttributes(meta);
//        Cache<String, Map<String, Object>> cache = adminShiroCacheManager.getCache("adminShiroCache");
//        String key=CACHE_KEY_PREFIX+staffId;
//        if(model==null){
//        	 model=getModel(request);
//        	 cache.put(key, model);
//        }
        return "admin/index";
    }

	private Map<String, Object> getModel(HttpServletRequest request) {
		Integer staffId=SecurityUtil.getCurrentStaffId();
        String contentPath=request.getContextPath();
        Staff staff=staffService.findOne(staffId);
        List<Menu> srcList=menuService.findAll();
        List<Menu> menuList=menuService.findAllByStaffId(Integer.valueOf(staffId));
        String menu=EasyUI.getEasyUIMenu(srcList,menuList,contentPath);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("menu",menu);
        model.put("staff",staff);
		return model;
	}
}
