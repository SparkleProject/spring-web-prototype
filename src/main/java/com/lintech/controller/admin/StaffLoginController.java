package com.lintech.controller.admin;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.mybatis.Page;
import com.lintech.core.util.ConfigUtil;
import com.lintech.core.util.ControllerUtils;
import com.lintech.entity.StaffLogin;
import com.lintech.service.admin.StaffLoginService;

@Controller
@RequestMapping("/admin/staff-login")
public class StaffLoginController{
    @Autowired
    StaffLoginService staffLoginService;
    
    @ResponseBody
    @RequestMapping("/init")
    public Object init(HttpServletRequest request,HttpServletResponse response) throws ParseException{
        Map<String, Object> params = new HashMap<String, Object>();
        String staffId=ControllerUtils.getString(request, "staffId");
        String loginDateFrom=ControllerUtils.getString(request, "loginDateFrom");
        String loginDateTo=ControllerUtils.getString(request, "loginDateTo");
        int index=ControllerUtils.getInt(request, "page",1);
        Page page=new Page(index,ConfigUtil.getInt("pagesize"));
        params.put("staffId",staffId);
        params.put("loginDateFrom",loginDateFrom);
        params.put("loginDateTo",loginDateTo);
        List<StaffLogin> staffList=staffLoginService.findAll(params,page);
        DataGrid<StaffLogin> datagrid=new DataGrid<StaffLogin>(staffList,page.getTotal());
        return datagrid;
    }

}
