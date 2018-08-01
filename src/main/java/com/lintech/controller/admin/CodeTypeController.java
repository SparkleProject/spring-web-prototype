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
import com.lintech.entity.CodeType;
import com.lintech.service.admin.CodeTypeService;

@Controller
@RequestMapping("/admin/code/type")
public class CodeTypeController{
    @Autowired
    CodeTypeService codeTypeService;
    
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
        List<CodeType> codeTypeList=codeTypeService.findAll(params,page);
        DataGrid<CodeType> datagrid=new DataGrid<CodeType>(codeTypeList,page.getTotal());
        return datagrid;
    }
    
    @ResponseBody
    @RequestMapping("/all")
    public Object all(HttpServletRequest request,HttpServletResponse response){
        List<CodeType> codeTypeList=codeTypeService.findAll();
        DataGrid<CodeType> datagrid=new DataGrid<CodeType>(codeTypeList,new Page(1,100).getTotal());
        return datagrid;
    }
    
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request, HttpServletResponse response){
            String id=request.getParameter("id");
            codeTypeService.delete(Integer.valueOf(id));
            return Messager.SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(CodeType codeType){
        if(null==codeType.getId()){
            codeTypeService.save(codeType);
            return Messager.SUCCESS;
        }else{
            codeTypeService.update(codeType);
            return Messager.SUCCESS;
        }
    }

}
