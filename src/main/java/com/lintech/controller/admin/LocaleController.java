package com.lintech.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;


public class LocaleController implements Controller{
    
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String contextPath = request.getContextPath();
        String from=request.getParameter("from");
        if(StringUtils.isEmpty(from)){
            from=contextPath+"/login.jsp";
        }
        response.sendRedirect(from);
        return null;
    }
}
