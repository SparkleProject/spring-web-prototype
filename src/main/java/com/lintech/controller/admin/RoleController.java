package com.lintech.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lintech.core.BaseController;
import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;
import com.lintech.core.util.ConfigUtil;
import com.lintech.core.util.ControllerUtils;
import com.lintech.entity.Role;
import com.lintech.service.admin.RoleService;

@Controller
@RequestMapping("/admin/role")
public class RoleController extends BaseController {
    @Autowired
    RoleService roleService;

    @ResponseBody
    @RequestMapping("/init")
    public Object init(HttpServletRequest request,HttpServletResponse response){
        int index = ControllerUtils.getInt(request, "page", 1);
        int rows = ConfigUtil.getInt("pagesize");
        Pageable pageable = PageRequest.of(index - 1, rows);
        Page<Role> result = roleService.findAll(pageable);
        DataGrid<Role> datagrid = new DataGrid<Role>(result.getContent(), (int) result.getTotalElements());
        return datagrid;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request, HttpServletResponse response){
            String id=request.getParameter("id");
            roleService.delete(Integer.valueOf(id));
            return Messager.SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(Role role){
        if(null==role.getId()){
            roleService.save(role);
            return Messager.SUCCESS;
        }else{
            roleService.update(role);
            return Messager.SUCCESS;
        }
    }
}
