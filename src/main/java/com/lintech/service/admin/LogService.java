package com.lintech.service.admin;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintech.core.mybatis.Page;
import com.lintech.dao.LogDao;
import com.lintech.entity.Log;

@Service
public class LogService {

	@Autowired
	private LogDao dao;

	public void save(Log tester) {
		dao.save(tester);
	}

	public void delete(Serializable id) {
		dao.delete(id);
	}
	
	public void truncate() {
		dao.truncate();
	}

	public void update(Log tester) {
		dao.update(tester);
	}

	public Log findOne(Serializable id) {
		return dao.findOne(id);
	}

	public List<Log> findAll() {
		return dao.findAll();
	}
	
	public List<Log> findAll(Map<String, Object> params) {
		return dao.findAll(params);
	}

	public List<Log> findAll(Map<String, Object> params, Page page) {
		if (params != null) {
			return dao.findAll(params, page);
		}
		return Collections.emptyList();
	}

}
