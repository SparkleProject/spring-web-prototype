package com.lintech.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin/dashboard")
public class DashboardController{
	
    @RequestMapping("/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response){
    	 response.setContentType("text/html;charset=UTF-8");
         return new ModelAndView("/admin/dashboard");
    }
}
