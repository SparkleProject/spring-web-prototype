package com.lintech.dao;

import com.lintech.core.mybatis.Dao;
import com.lintech.core.mybatis.MyBatis;
import com.lintech.entity.Company;


@MyBatis
public interface CompanyDao extends Dao<Company>{
	
	public Company findOneByCode(String code);
}
