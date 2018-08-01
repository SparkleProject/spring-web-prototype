package com.lintech.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lintech.core.easyui.EasyUI;
import com.lintech.core.easyui.Menu;
import com.lintech.core.easyui.Messager;
import com.lintech.core.easyui.TreeNode;
import com.lintech.entity.RoleRes;
import com.lintech.service.admin.MenuService;
import com.lintech.service.admin.RoleResService;
import com.lintech.service.admin.RoleService;


@Controller
@RequestMapping("/admin/role-res")
public class RoleResController{
    @Autowired
    RoleService roleService;
    
    @Autowired
    RoleResService roleResService;
    
    @Autowired
    MenuService menuService;
    
    @ResponseBody
    @RequestMapping("/save_menu")
    public Object save_menu(HttpServletRequest request, HttpServletResponse response){
           String roleId=request.getParameter("role_id");
           String[] menuIds=request.getParameterValues("menuId");
           
           //clear history authorization
           Map<String,Object> params=new HashMap<String, Object>();
           params.put("roleId",roleId);
           params.put("resType",RoleRes.RES_TYPE_MENU);
           if(StringUtils.isEmpty(roleId))
               return  null;
           roleResService.deleteByClause(params);
       
           //add new authorization
           List<RoleRes> roleResVOList=new ArrayList<RoleRes>();
           if(menuIds!=null){
               for(String menuId:menuIds){
                   if(TreeNode.TREE_ROOT==Integer.parseInt(menuId)){
                       continue;
                   }else{
                       RoleRes vo=new RoleRes();
                       vo.setResId(Integer.parseInt(menuId));
                       vo.setResType(RoleRes.RES_TYPE_MENU);
                       vo.setRoleId(Integer.parseInt(roleId));
                       roleResVOList.add(vo);
                   }
               }   
           }
           roleResService.saveBatch(roleResVOList);
           return Messager.SUCCESS;
       }
       
    @ResponseBody
    @RequestMapping("/save_func")
       public Object save_func(HttpServletRequest request, HttpServletResponse response){
               String roleId=request.getParameter("role_id");
               String[] funcIds=request.getParameterValues("funcId");
               if(StringUtils.isEmpty(roleId))
                   return null;
               //clear history authorization
               Map<String,Object> params=new HashMap<String, Object>();
               params.put("roleId",roleId);
               params.put("resType",RoleRes.RES_TYPE_FUNCTION);
               roleResService.deleteByClause(params);
               
               //add new authorization
               List<RoleRes> roleResVOList=new ArrayList<RoleRes>();
               if(funcIds!=null){
                   for(String funcId:funcIds){
                       if(Integer.parseInt(funcId)==TreeNode.TREE_ROOT){
                           continue;
                       }else{
                           RoleRes vo=new RoleRes();
                           vo.setResId(Integer.parseInt(funcId));
                           vo.setResType(RoleRes.RES_TYPE_FUNCTION);
                           vo.setRoleId(Integer.parseInt(roleId));
                           roleResVOList.add(vo);
                       }
                   }   
               }
               roleResService.saveBatch(roleResVOList);
               return Messager.SUCCESS;
       }
       
       @ResponseBody
       @RequestMapping("/load-menu")
       public Object load_menu(HttpServletRequest request, HttpServletResponse response){
           String roleId=request.getParameter("role_id");
           if(StringUtils.isEmpty(roleId))
               return null;
           Map<String,Object> params=new HashMap<String, Object>();
           params.put("roleId",roleId);
           params.put("resType",RoleRes.RES_TYPE_MENU);
           List<RoleRes> roleResList=roleResService.findAll(params);
           List<Menu> menuList =menuService.findAll();
           for(RoleRes vo:roleResList){
               for(Menu mvo:menuList){
                   if(vo.getResId()==mvo.getId()){
                       mvo.setChecked(true);
                   }
               }
           }
           List<TreeNode> tree=EasyUI.getEasyUITree(menuList); 
           return tree;
       }
       

       @ResponseBody
       @RequestMapping("/load-func")
       public Object load_func(HttpServletRequest request, HttpServletResponse response){
           String roleId=request.getParameter("role_id");
           if(StringUtils.isEmpty(roleId))
               return null;
           Map<String,Object> params=new HashMap<String, Object>();
           params.put("roleId",roleId);
           params.put("resType",RoleRes.RES_TYPE_FUNCTION);
           List<RoleRes> roleResList=roleResService.findAll(params);
           return roleResList;
       }
}
