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

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.EasyUI;
import com.lintech.core.easyui.Messager;
import com.lintech.core.easyui.TreeNode;
import com.lintech.core.mybatis.Page;
import com.lintech.core.util.ConfigUtil;
import com.lintech.core.util.ControllerUtils;
import com.lintech.entity.Role;
import com.lintech.entity.RoleStaff;
import com.lintech.service.admin.RoleService;
import com.lintech.service.admin.RoleStaffService;

@Controller
@RequestMapping("/admin/role-staff")
public class RoleStaffController{
    @Autowired
    RoleService roleService;
    @Autowired
    RoleStaffService roleStaffService;
    
    @ResponseBody
    @RequestMapping("/init")
    public Object init(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> params = new HashMap<String, Object>();
        String name=ControllerUtils.getString(request, "name");
        int index=ControllerUtils.getInt(request, "page",1);
        int rows=ControllerUtils.getInt(request, "rows",ConfigUtil.getInt("pagesize"));
        Page page=new Page(index,rows);
        params.put("name", name);
        List<RoleStaff> roleStaffList=roleStaffService.findAll(params,page);
        DataGrid<RoleStaff> datagrid=new DataGrid<RoleStaff>(roleStaffList,page.getTotal());
        return datagrid;
    }
    
    @ResponseBody
    @RequestMapping("/all")
    public Object all(HttpServletRequest request,HttpServletResponse response){
        List<RoleStaff> roleStaffList=roleStaffService.findAll();
        DataGrid<RoleStaff> datagrid=new DataGrid<RoleStaff>(roleStaffList,new Page(1,100).getTotal());
        return datagrid;
    }
    
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request, HttpServletResponse response){
            String id=request.getParameter("id");
            roleStaffService.delete(Integer.valueOf(id));
            return Messager.SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(HttpServletRequest request, HttpServletResponse response){
        String staffId=request.getParameter("id");
        String[] roleIds=request.getParameterValues("roleId");
        
        //clear history authorization
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("staffId",staffId);
        if(staffId==null)
            return null;
        roleStaffService.deleteByClause(params);
    
        //add new authorization
        if(roleIds!=null){
            for(String roleId:roleIds){
                if(Integer.parseInt(roleId)==TreeNode.TREE_ROOT){
                    continue;
                }else{
                    RoleStaff vo=new RoleStaff();
                    vo.setRoleId(Integer.parseInt(roleId));
                    vo.setStaffId(Integer.parseInt(staffId));
                    roleStaffService.save(vo);
                }
            }   
        }
        return Messager.SUCCESS;
    }
    
    @ResponseBody
    @RequestMapping("/load-role")
    public Object load_role(HttpServletRequest request, HttpServletResponse response){
            String staffId=request.getParameter("id");
            //query
            List<Role> voList =roleService.findAll();
            Map<String,Object> params=new HashMap<String, Object>();
            params.put("staffId",staffId);
            List<RoleStaff> roleStaffList=roleStaffService.findAll(params);
            for(RoleStaff v:roleStaffList){
                for(Role role:voList){
                    if(role.getId()==v.getRoleId()){
                        role.setChecked(true);
                    }
                }
            }
            return EasyUI.getEasyUITree(voList);
    }

}
