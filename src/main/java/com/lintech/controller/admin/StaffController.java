package com.lintech.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;
import com.lintech.core.util.ConfigUtil;
import com.lintech.core.util.ControllerUtils;
import com.lintech.core.util.SecurityUtil;
import com.lintech.entity.Staff;
import com.lintech.service.admin.StaffService;

@Controller
@RequestMapping("/admin/staff")
public class StaffController{
    @Autowired
    StaffService staffService;

    @ResponseBody
    @RequestMapping("/init")
    public Object init(HttpServletRequest request,HttpServletResponse response){
        int index = ControllerUtils.getInt(request, "page", 1);
        int rows = ControllerUtils.getInt(request, "rows", ConfigUtil.getInt("pagesize"));
        Pageable pageable = PageRequest.of(index - 1, rows);
        Page<Staff> result = staffService.findAll(pageable);
        DataGrid<Staff> datagrid = new DataGrid<Staff>(result.getContent(), (int) result.getTotalElements());
        return datagrid;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request, HttpServletResponse response){
            String id=request.getParameter("id");
            staffService.delete(Integer.valueOf(id));
            return Messager.SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(Staff staff){
        if(null==staff.getId()){
            staffService.save(staff);
            return Messager.SUCCESS;
        }else{
            staffService.update(staff);
            return Messager.SUCCESS;
        }
    }

    @ResponseBody
    @RequestMapping("/load")
    public Object load(HttpServletRequest request, HttpServletResponse response){
         String id = ControllerUtils.getString(request, "id");
         if(id!=null){
             Staff staff=staffService.findOne(Integer.valueOf(id));
             return staff;
         }

          String d=ControllerUtils.getString(request,"d");
          Map<String, Object> params = new HashMap<String, Object>();
          if(!StringUtils.isEmpty(d)){
              params.put("deptId", d);
          }
          List<Staff> staffList=new ArrayList<Staff>();
          List<Staff> staffList2=staffService.findAll(params);
          Staff first=new Staff();
          first.setName("-- Please select --");
          staffList.add(first);
          if(staffList2!=null){
              staffList.addAll(staffList2);
          }
          return staffList;
      }


    @ResponseBody
    @RequestMapping("/suggest")
    public Object suggest(HttpServletRequest request, HttpServletResponse response){
        // pagination
        int pageSize = ControllerUtils.getInt(request, "limit", ConfigUtil.getInt("pagesize"));
        int pageIndex = ControllerUtils.getInt(request, "page", 1);
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);

        Page<Staff> result = staffService.findAll(pageable);

        // render view
        return result.getContent();
    }

    @ResponseBody
    @RequestMapping("/password")
    public Object password(HttpServletRequest request, HttpServletResponse response){
         Integer id=ControllerUtils.getInt(request,"id");
         String password=ControllerUtils.getString(request,"password_new");
         String password_new_ec=SecurityUtil.md5(password);
         staffService.changePassword(id,password_new_ec);
         return Messager.SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/enabled/{id}/{enabled}")
    public Object enabled(HttpServletRequest request, HttpServletResponse response,@PathVariable String id,@PathVariable Integer enabled){
        staffService.changeEanbled(id,enabled);
        return Messager.SUCCESS;
    }
}
