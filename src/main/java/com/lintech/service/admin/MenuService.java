package com.lintech.service.admin;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintech.core.easyui.Menu;
import com.lintech.core.mybatis.Page;
import com.lintech.dao.MenuDao;


@Service
public class MenuService {
    @Autowired
    MenuDao menuDao;
    
    public void save(Menu menu){
        menuDao.save(menu);  
    }
    
    public void delete(int id){
        menuDao.delete(id);
    }
    
    public void update(Menu menu){
        menuDao.update(menu); 
    }
    
    public Menu findOne(String id){
        return menuDao.findOne(id);   
    }
    
    public List<Menu> findAll(){
        return menuDao.findAll();
    }
    
    public List<Menu> findAll(Map<String, Object> params){
        return menuDao.findAll(params);
    }
    public List<Menu> findAll(Map<String, Object> params,Page page){
        return menuDao.findAll(params,page);
    }
    
    public List<Menu>  findAllByStaffId(int staffId){
        return menuDao.findAllByStaffId(staffId);
    }
    
    public List<Menu>  findAllByRole(String roleCode){
        return menuDao.findAllByRole(roleCode);
    }
}
