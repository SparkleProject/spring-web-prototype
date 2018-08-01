package com.lintech.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller("IndexController")
public class IndexController{
    
    @RequestMapping("/index")
    public String index(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		return "index";
    }
    
}
