package com.lintech.controller.admin;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;
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
        int index = ControllerUtils.getInt(request, "page", 1);
        int rows = ConfigUtil.getInt("pagesize");
        Pageable pageable = PageRequest.of(index - 1, rows);
        Page<Function> result = functionService.findAll(pageable);
        DataGrid<Function> datagrid = new DataGrid<Function>(result.getContent(), (int) result.getTotalElements());
        return datagrid;
    }

    @ResponseBody
    @RequestMapping("/all")
    public Object all(HttpServletRequest request,HttpServletResponse response){
        List<Function> functionList=functionService.findAll();
        DataGrid<Function> datagrid=new DataGrid<Function>(functionList, functionList.size());
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
