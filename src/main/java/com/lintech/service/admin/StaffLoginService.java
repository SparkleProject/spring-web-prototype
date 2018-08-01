package com.lintech.service.admin;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintech.core.mybatis.Page;
import com.lintech.dao.StaffLoginDao;
import com.lintech.entity.StaffLogin;

@Service
public class StaffLoginService {
    @Autowired
    StaffLoginDao staffLoginDao;
    
    public void save(StaffLogin staffLogin){
        staffLoginDao.save(staffLogin);  
    }
    
    public void update(StaffLogin staffLogin){
        staffLoginDao.update(staffLogin); 
    }
    
    public StaffLogin findOne(String id){
        return staffLoginDao.findOne(id);   
    }
    
    public List<StaffLogin> findAll(Map<String, Object> params,Page page){
        if(params!=null){
            return staffLoginDao.findAll(params,page);
        }
        return Collections.emptyList();
    }
}
