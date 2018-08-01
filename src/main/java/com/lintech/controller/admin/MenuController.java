package com.lintech.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lintech.core.easyui.EasyUI;
import com.lintech.core.easyui.Menu;
import com.lintech.core.easyui.Messager;
import com.lintech.core.easyui.TreeNode;
import com.lintech.service.admin.MenuService;


@Controller
@RequestMapping("/admin/menu")
public class MenuController{
    @Autowired
    MenuService menuService;
    
    @ResponseBody
    @RequestMapping("/init")
    public Object init(HttpServletRequest request,HttpServletResponse response){
        List<Menu> menuList=menuService.findAll();
        List<TreeNode> tree=EasyUI.getEasyUITree(menuList); 
        return tree;
    }
    
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request, HttpServletResponse response){
            String id=request.getParameter("id");
            menuService.delete(Integer.valueOf(id));
            return Messager.SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(Menu menu){
        if(null==menu.getId()){
            menuService.save(menu);
            return Messager.SUCCESS;
        }else{
            menuService.update(menu);
            return Messager.SUCCESS;
        }
    }

}
