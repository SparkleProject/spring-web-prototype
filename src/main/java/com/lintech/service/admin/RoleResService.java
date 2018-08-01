package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintech.core.mybatis.Page;
import com.lintech.dao.RoleResDao;
import com.lintech.entity.RoleRes;


@Service
public class RoleResService {
    @Autowired
    RoleResDao roleResDao;
    
    public void save(RoleRes roleRes){
        roleResDao.save(roleRes);  
    }
    
    public void saveBatch(List<RoleRes> roleReses){
        for(RoleRes r:roleReses){
            save(r);
        }
    }
    
    public void delete(int id){
        roleResDao.delete(id);
    }
    
    public void deleteByClause(Map<String, Object> params){
        roleResDao.deleteByClause(params);
    }
    
    public void update(RoleRes roleRes){
        roleResDao.update(roleRes); 
    }
    
    public RoleRes findOne(String id){
        return roleResDao.findOne(id);   
    }

    public List<RoleRes> findAll(){
        return roleResDao.findAll();
    }
    
    public List<RoleRes> findAll(Map<String, Object> params){
        return roleResDao.findAll(params);
    }
    public List<RoleRes> findAll(Map<String, Object> params,Page page){
        return roleResDao.findAll(params,page);
    }
}
