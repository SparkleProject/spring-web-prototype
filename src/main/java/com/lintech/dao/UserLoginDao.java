package com.lintech.dao;

import com.lintech.core.mybatis.Dao;
import com.lintech.core.mybatis.MyBatis;
import com.lintech.entity.UserLogin;


@MyBatis
public interface UserLoginDao extends Dao<UserLogin>{
	
	public void clearActive(String userId);
	
}
