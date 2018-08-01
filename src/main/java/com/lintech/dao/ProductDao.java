package com.lintech.dao;

import java.util.List;

import com.lintech.core.mybatis.Dao;
import com.lintech.core.mybatis.MyBatis;
import com.lintech.entity.Product;


@MyBatis
public interface ProductDao extends Dao<Product>{
	
	public List<Product> findOneByCode(String code);
}
