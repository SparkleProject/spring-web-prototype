package com.lintech.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lintech.core.BaseController;
import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;
import com.lintech.core.mybatis.Page;
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
        Map<String, Object> params = new HashMap<String, Object>();
        String name=ControllerUtils.getString(request, "name");
        String code=ControllerUtils.getString(request, "code");
        int index=ControllerUtils.getInt(request, "page",1);
        Page page=new Page(index,ConfigUtil.getInt("pagesize"));
        params.put("code", code);
        params.put("name", name);
        List<Role> roleList=roleService.findAll(params,page);
        DataGrid<Role> datagrid=new DataGrid<Role>(roleList,page.getTotal());
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
