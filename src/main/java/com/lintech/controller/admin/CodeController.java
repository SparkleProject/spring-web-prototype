package com.lintech.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
        int index = ControllerUtils.getInt(request, "page", 1);
        int rows = ConfigUtil.getInt("pagesize");
        Pageable pageable = PageRequest.of(index - 1, rows);
        Page<Code> result = codeService.findAll(pageable);
        DataGrid<Code> datagrid = new DataGrid<Code>(result.getContent(), (int) result.getTotalElements());
        return datagrid;
    }

    @ResponseBody
    @RequestMapping("/all")
    public Object all(HttpServletRequest request,HttpServletResponse response){
        List<Code> codeList=codeService.findAll();
        DataGrid<Code> datagrid=new DataGrid<Code>(codeList, codeList.size());
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
            params.put("msg", "Duplicate data: "+code.toString());
            return params;
        } catch (Exception e) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("isError", true);
            params.put("msg", "Other error: "+e.getMessage());
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
