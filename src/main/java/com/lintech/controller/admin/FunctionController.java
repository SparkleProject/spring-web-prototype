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
import com.lintech.core.easyui.Messager;
import com.lintech.core.mybatis.Page;
import com.lintech.core.util.ConfigUtil;
import com.lintech.core.util.ControllerUtils;
import com.lintech.entity.Function;
import com.lintech.service.admin.FunctionService;

@Controller
@RequestMapping("/admin/function")
public class FunctionController{
    @Autowired
    FunctionService functionService;
    
    @ResponseBody
    @RequestMapping("init")
    public Object init(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> params = new HashMap<String, Object>();
        String name=ControllerUtils.getString(request, "name");
        int index=ControllerUtils.getInt(request, "page",1);
        Page page=new Page(index,ConfigUtil.getInt("pagesize"));
        params.put("name", name);
        List<Function> functionList=functionService.findAll(params,page);
        DataGrid<Function> datagrid=new DataGrid<Function>(functionList,page.getTotal());
        return datagrid;
    }
    
    @ResponseBody
    @RequestMapping("/all")
    public Object all(HttpServletRequest request,HttpServletResponse response){
        List<Function> functionList=functionService.findAll();
        DataGrid<Function> datagrid=new DataGrid<Function>(functionList,new Page(1,100).getTotal());
        return datagrid;
    }
    
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request, HttpServletResponse response){
            String id=request.getParameter("id");
            functionService.delete(Integer.valueOf(id));
            return Messager.SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(Function function){
        if(null==function.getId()){
            functionService.save(function);
            return Messager.SUCCESS;
        }else{
            functionService.update(function);
            return Messager.SUCCESS;
        }
    }

}
