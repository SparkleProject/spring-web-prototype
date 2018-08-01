package com.lintech.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;
import com.lintech.core.mybatis.Page;
import com.lintech.core.util.ConfigUtil;
import com.lintech.core.util.ControllerUtils;
import com.lintech.entity.Code;
import com.lintech.service.admin.CodeService;

@Controller
@RequestMapping("/admin/code")
public class CodeController{
    @Autowired
    CodeService codeService;
    
    @ResponseBody
    @RequestMapping("/init")
    public Object init(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> params = new HashMap<String, Object>();
        String type=ControllerUtils.getString(request, "type");
        int index=ControllerUtils.getInt(request, "page",1);
        Page page=new Page(index,ConfigUtil.getInt("pagesize"));
        params.put("type", type);
        List<Code> codeList=codeService.findAll(params,page);
        DataGrid<Code> datagrid=new DataGrid<Code>(codeList,page.getTotal());
        return datagrid;
    }
    
    @ResponseBody
    @RequestMapping("/all")
    public Object all(HttpServletRequest request,HttpServletResponse response){
        List<Code> codeList=codeService.findAll();
        DataGrid<Code> datagrid=new DataGrid<Code>(codeList,new Page(1,100).getTotal());
        return datagrid;
    }
    
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request, HttpServletResponse response){
            String id=request.getParameter("id");
            codeService.delete(Integer.valueOf(id));
            return Messager.SUCCESS;
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(Code code){
    	try {
	        if(null==code.getId()){
	        	codeService.save(code);
	        }else{
	            codeService.update(code);
	        }
	        return Messager.SUCCESS;
    	} catch (DuplicateKeyException e) {
    		Map<String, Object> params = new HashMap<String, Object>();
    		params.put("isError", true);
    		params.put("msg", "重复数据："+code.toString());
    		return params;
    	} catch (Exception e) {
    		Map<String, Object> params = new HashMap<String, Object>();
    		params.put("isError", true);
    		params.put("msg", "其他错误："+e.getMessage());
    		return params;
    	}
    }
    
    @ResponseBody
    @RequestMapping("/combobox/{type}")
    public Object combobox(HttpServletRequest request, HttpServletResponse response,@PathVariable String type){
    	String select = request.getParameter("select");
    	if(StringUtils.isNotBlank(select)) {
    		return codeService.findCombobox(type,false,select);
    	}
    	return codeService.findCombobox(type,false);
    }
    

}
