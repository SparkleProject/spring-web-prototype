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
import com.lintech.entity.User;
import com.lintech.service.admin.UserService;

@Controller
@RequestMapping("/admin/user")
public class UserController{
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/init")
    public Object init(HttpServletRequest request,HttpServletResponse response){
        int index = ControllerUtils.getInt(request, "page", 1);
        int rows = ControllerUtils.getInt(request, "rows", ConfigUtil.getInt("pagesize"));
        Pageable pageable = PageRequest.of(index - 1, rows);
        Page<User> result = userService.findAll(pageable);
        DataGrid<User> datagrid = new DataGrid<User>(result.getContent(), (int) result.getTotalElements());
        return datagrid;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request, HttpServletResponse response){
            String id=request.getParameter("id");
            userService.delete(Integer.valueOf(id));
            return Messager.SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(User user){
        if(null==user.getId()){
            user.setEnabled(1);
            user.setLocked(0);
            userService.save(user);
            return Messager.SUCCESS;
        }else{
            userService.update(user);
            return Messager.SUCCESS;
        }
    }

    @ResponseBody
    @RequestMapping("/load")
    public Object load(HttpServletRequest request, HttpServletResponse response){
         String id = ControllerUtils.getString(request, "id");
         if(id!=null){
             User user=userService.findOne(Integer.valueOf(id));
             return user;
         }

          String d=ControllerUtils.getString(request,"d");
          Map<String, Object> params = new HashMap<String, Object>();
          if(!StringUtils.isEmpty(d)){
              params.put("deptId", d);
          }
          List<User> userList=new ArrayList<User>();
          List<User> userList2=userService.findAll(params);
          User first=new User();
          first.setName("-- Please select --");
          userList.add(first);
          if(userList2!=null){
              userList.addAll(userList2);
          }
          return userList;
      }


    @ResponseBody
    @RequestMapping("/suggest")
    public Object suggest(HttpServletRequest request, HttpServletResponse response){
        // pagination
        int pageSize = ControllerUtils.getInt(request, "limit", ConfigUtil.getInt("pagesize"));
        int pageIndex = ControllerUtils.getInt(request, "page", 1);
        Pageable pageable = PageRequest.of(pageIndex - 1, pageSize);

        Page<User> result = userService.findAll(pageable);

        // render view
        return result.getContent();
    }

    @ResponseBody
    @RequestMapping("/password")
    public Object password(HttpServletRequest request, HttpServletResponse response){
         Integer id=ControllerUtils.getInt(request,"id");
         String password=ControllerUtils.getString(request,"password_new");
         String password_new_ec=SecurityUtil.md5(password);
         userService.changePassword(id,password_new_ec);
         return Messager.SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/enabled/{id}/{enabled}")
    public Object enabled(HttpServletRequest request, HttpServletResponse response,@PathVariable String id,@PathVariable Integer enabled){
        userService.changeEanbled(id,enabled);
        return Messager.SUCCESS;
    }
}
