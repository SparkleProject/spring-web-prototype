package com.lintech.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lintech.core.easyui.Messager;
import com.lintech.core.util.ControllerUtils;
import com.lintech.core.util.SecurityUtil;
import com.lintech.entity.Staff;
import com.lintech.service.admin.StaffService;

@Controller
@RequestMapping("/admin/profile")
public class ProfileController{
    
    @Autowired
    StaffService staffService;
    
    @ResponseBody
    @RequestMapping("/password")
    public Object password(HttpServletRequest request,HttpServletResponse response){
        Integer staffId=SecurityUtil.getCurrentStaffId();
        Staff staff = staffService.findOne(staffId);
        String password_cur=staff.getPassword();
        String password_old=ControllerUtils.getString(request,"password_old");
        String password_old_ec=SecurityUtil.md5(password_old);
        if(!password_cur.equals(password_old_ec)){
            return new Messager(false,"旧密码输入错误!");
        }
        String password_new=ControllerUtils.getString(request,"password_new");
        String password_new_ec=SecurityUtil.md5(password_new);
        staffService.changePassword(staffId,password_new_ec);
        return Messager.SUCCESS;
    }
}
