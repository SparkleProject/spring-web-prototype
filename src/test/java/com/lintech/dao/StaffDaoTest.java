package com.lintech.dao;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.lintech.core.mybatis.Page;
import com.lintech.core.test.AbstractDaoTest;
import com.lintech.dao.StaffDao;
import com.lintech.entity.Staff;

public class StaffDaoTest extends AbstractDaoTest {
	
	@Autowired
	private StaffDao staffDao;
	
	@Test
	public void testAdd(){
		Page page=new Page(2, 10);
		List<Staff> list = staffDao.findAll(page);
		System.out.println(list.size());
	}
	
	
	@Test
	public void findAllMap(){
		List<Map<String, String>> list = staffDao.findAllMap();
		for(Map<String,String> map:list){
			System.out.println(map);
		}
	}
}
