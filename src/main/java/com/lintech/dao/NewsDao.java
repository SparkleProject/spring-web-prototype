package com.lintech.dao;

import java.util.List;
import java.util.Map;

import com.lintech.core.mybatis.Dao;
import com.lintech.core.mybatis.MyBatis;
import com.lintech.core.mybatis.Page;
import com.lintech.entity.News;


@MyBatis
public interface NewsDao extends Dao<News>{
	
	public News findOneWithBLOBs(Integer id);

	public List<News> findAllWithBLOBs(Map<String, Object> params,Page page);

}
