package com.lintech.dao;

import java.util.List;

import com.lintech.core.easyui.Combobox;
import com.lintech.core.mybatis.Dao;
import com.lintech.core.mybatis.MyBatis;
import com.lintech.entity.Code;


@MyBatis
public interface CodeDao extends Dao<Code>{

	List<Combobox> findCombobox(String field);
}
