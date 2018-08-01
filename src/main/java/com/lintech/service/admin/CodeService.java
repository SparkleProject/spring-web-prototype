package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintech.core.easyui.Combobox;
import com.lintech.core.easyui.EasyUI;
import com.lintech.core.easyui.Formatter;
import com.lintech.core.mybatis.Page;
import com.lintech.dao.CodeDao;
import com.lintech.entity.Code;


@Service
public class CodeService {
    @Autowired
    CodeDao dao;
    
    public void save(Code code){
        dao.save(code);  
    }
    
    public void delete(int id){
        dao.delete(id);
    }
    
    public void update(Code code){
        dao.update(code);
    }
    
    public Code findOne(String id){
        return dao.findOne(id);   
    }
    
    public List<Code> findAll(){
        return dao.findAll();
    }
    
    public List<Code> findAll(Map<String, Object> params){
        return dao.findAll(params);
    }
    public List<Code> findAll(Map<String, Object> params,Page page){
        return dao.findAll(params,page);
    }

	public List<Combobox> findCombobox(String type,boolean appendFirst) {
		List<Combobox> list = dao.findCombobox(type);
		return EasyUI.formatCombobox(list,null,appendFirst,null);
	}
	
	public List<Combobox> findCombobox(String type,boolean appendFirst,String select) {
		List<Combobox> list = dao.findCombobox(type);
		return EasyUI.formatCombobox(list,null,appendFirst,select);
	}
}
