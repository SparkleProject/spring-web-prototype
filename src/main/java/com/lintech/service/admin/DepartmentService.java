package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintech.core.mybatis.Page;
import com.lintech.dao.DepartmentDao;
import com.lintech.entity.Department;


@Service
public class DepartmentService {
    @Autowired
    DepartmentDao departmentDao;
    
    public void save(Department department){
        departmentDao.save(department);  
    }
    
    public void delete(int id){
        departmentDao.delete(id);
    }
    
    public void update(Department department){
        departmentDao.update(department); 
    }
    
    public Department findOne(String id){
        return departmentDao.findOne(id);   
    }
    
    public List<Department> findAll(){
        return departmentDao.findAll();
    }
    
    public List<Department> findAll(Map<String, Object> params){
        return departmentDao.findAll(params);
    }
    public List<Department> findAll(Map<String, Object> params,Page page){
        return departmentDao.findAll(params,page);
    }
}
