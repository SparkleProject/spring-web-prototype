package com.lintech.dao;

import java.util.List;
import java.util.Map;

import com.lintech.core.easyui.Menu;
import com.lintech.core.mybatis.Dao;
import com.lintech.core.mybatis.MyBatis;


@MyBatis
public interface MenuDao extends Dao<Menu>{
    
    List<Menu> findAllByStaffId(int staffId);
    
    List<Map<String,String>> findAllMap();

	List<Menu> findAllByRole(String roleCode);
    
}
