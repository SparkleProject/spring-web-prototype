package com.lintech.controller.admin;

import java.text.ParseException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lintech.core.easyui.DataGrid;
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
        int index = ControllerUtils.getInt(request, "page", 1);
        int rows = ConfigUtil.getInt("pagesize");
        Pageable pageable = PageRequest.of(index - 1, rows);
        Page<StaffLogin> result = staffLoginService.findAll(pageable);
        DataGrid<StaffLogin> datagrid = new DataGrid<StaffLogin>(result.getContent(), (int) result.getTotalElements());
        return datagrid;
    }

}
