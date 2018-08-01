package com.lintech.service.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintech.core.mybatis.Page;
import com.lintech.dao.UserDao;
import com.lintech.entity.User;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    
    public void save(User user){
        userDao.save(user);  
    }
    
    public void delete(int id){
        userDao.delete(id);
    }
    
    public void update(User user){
        userDao.update(user); 
    }
    
    public User findOne(Integer id){
        return userDao.findOne(id);   
    }
    
    public List<User> findAll(){
        return userDao.findAll();
    }
    
    public List<User> findAll(Map<String, Object> params){
        return userDao.findAll(params);
    }
    public List<User> findAll(Map<String, Object> params,Page page){
        return userDao.findAll(params,page);
    }
    
    public void changePassword(Integer id,String password){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("password", password);
        userDao.changePassword(params);
    }
    
    public void changeEanbled(String id,Integer enabled){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("enabled", enabled);
        userDao.changeEanbled(params);
    }
    
    public User findOneByLoginName(String loginName){
        return userDao.findOneByLoginName(loginName);
    }
}
