package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintech.core.mybatis.Page;
import com.lintech.dao.FunctionDao;
import com.lintech.entity.Function;


@Service
public class FunctionService {
    @Autowired
    FunctionDao functionDao;
    
    public void save(Function function){
        functionDao.save(function);  
    }
    
    public void delete(int id){
        functionDao.delete(id);
    }
    
    public void update(Function function){
        functionDao.update(function); 
    }
    
    public Function findOne(String id){
        return functionDao.findOne(id);   
    }
    
    public List<Function> findAll(){
        return functionDao.findAll();
    }
    
    public List<Function> findAll(Map<String, Object> params){
        return functionDao.findAll(params);
    }
    public List<Function> findAll(Map<String, Object> params,Page page){
        return functionDao.findAll(params,page);
    }
}
