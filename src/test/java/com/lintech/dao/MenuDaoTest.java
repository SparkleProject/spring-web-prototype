package com.lintech.dao;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lintech.core.easyui.Menu;
import com.lintech.core.mybatis.Page;
import com.lintech.core.test.AbstractDaoTest;
import com.lintech.dao.MenuDao;

public class MenuDaoTest extends AbstractDaoTest {
	
	@Autowired
	private MenuDao menuDao;
	
	@Test
	public void findByPage(){
		Page page=new Page(2, 10);
		List<Menu> list = menuDao.findAll(page);
		System.out.println(list.size());
	}
	
	
	@Test
	public void findAllMap(){
		List<Map<String, String>> list = menuDao.findAllMap();
		for(Map<String,String> map:list){
			System.out.println(map);
		}
	}
}
