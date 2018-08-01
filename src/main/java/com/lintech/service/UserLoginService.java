package com.lintech.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintech.core.mybatis.Page;
import com.lintech.dao.UserLoginDao;
import com.lintech.entity.UserLogin;

@Service
public class UserLoginService {
	
	@Autowired
	UserLoginDao dao;
	
	public void save(UserLogin userLogin){
		dao.save(userLogin);
	}
	
	public void update(UserLogin userLogin){
		dao.update(userLogin);
	}
	
	public void clearActive(String userId){
		dao.clearActive(userId);
	}
	
	public Long countAll(Map<String,Object> params){
		return dao.countAll(params);
	}
	
	public UserLogin findOne(Integer id){
		return dao.findOne(id);
	}
	
	public List<UserLogin> findAll(Map<String,Object> params){
		return dao.findAll(params);
	}
	
	public List<UserLogin> findAll(Map<String,Object> params, Page page){
		return dao.findAll(params, page);
	}

}
