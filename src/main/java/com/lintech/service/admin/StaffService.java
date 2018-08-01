package com.lintech.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintech.core.mybatis.Page;
import com.lintech.dao.StaffDao;
import com.lintech.entity.Staff;

@Service
public class StaffService {
    @Autowired
    private StaffDao staffDao;
    
    public void save(Staff staff){
        staffDao.save(staff);  
    }
    
    public void delete(int id){
        staffDao.delete(id);
    }
    
    public void update(Staff staff){
        staffDao.update(staff); 
    }
    
    public Staff findOne(Integer id){
        return staffDao.findOne(id);   
    }
    
    public List<Staff> findAll(){
        return staffDao.findAll();
    }
    
    public List<Staff> findAll(Map<String, Object> params){
        return staffDao.findAll(params);
    }
    public List<Staff> findAll(Map<String, Object> params,Page page){
        return staffDao.findAll(params,page);
    }
    
    public void changePassword(Integer id,String password){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("password", password);
        staffDao.changePassword(params);
    }
    
    public void changeEanbled(String id,Integer enabled){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("enabled", enabled);
        staffDao.changeEanbled(params);
    }
    
    public Staff findOneByLoginName(String loginName){
        return staffDao.findOneByLoginName(loginName);
    }
}
