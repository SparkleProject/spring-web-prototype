package com.lintech.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lintech.core.easyui.EasyUI;
import com.lintech.core.easyui.Messager;
import com.lintech.core.easyui.TreeNode;
import com.lintech.entity.Department;
import com.lintech.service.admin.DepartmentService;


@Controller
@RequestMapping("/admin/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    
    @ResponseBody
    @RequestMapping("/init")
    public Object init(HttpServletRequest request,HttpServletResponse response){
        List<Department> departmentList=departmentService.findAll();
        List<TreeNode> tree=EasyUI.getEasyUITree(departmentList); 
        return tree;
    }
    
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request, HttpServletResponse response){
            String id=request.getParameter("id");
            departmentService.delete(Integer.valueOf(id));
            return Messager.SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(Department department){
        if(null==department.getId()){
            departmentService.save(department);
            return Messager.SUCCESS;
        }else{
            departmentService.update(department);
            return Messager.SUCCESS;
        }
    }

}
