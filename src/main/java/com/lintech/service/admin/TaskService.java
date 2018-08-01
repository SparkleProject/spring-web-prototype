package com.lintech.service.admin;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lintech.core.mybatis.Page;
import com.lintech.dao.TaskDao;
import com.lintech.entity.Task;


/**
 * Task服务类
 * @author 
 */
@Service
public class TaskService {
    @Autowired
    private TaskDao taskDao;
    
    /**
     * 新增
     * @param task
     */
    public void save(Task task){
        taskDao.save(task);  
    }
    
    /**
     * 根据ID删除
     * @param id
     */
    public void delete(Serializable id){
        taskDao.delete(id);
    }
    
    /**
     * 更新
     * @param task
     */
    public void update(Task task){
        taskDao.update(task); 
    }
    
    /**
     * 根据ID查询
     * @param id
     * @return
     */
    public Task findOne(Serializable id){
        return taskDao.findOne(id);   
    }
    
    /**
     * 根据条件查询全部
     * @param params
     * @param page
     * @return
     */
    public List<Task> findAll(Map<String, Object> params,Page page){
        if(params!=null){
        	return taskDao.findAll(params,page);
        }
        return Collections.emptyList();
    }
    
    /**
     * 查询全部
     * @param params
     * @param page
     * @return
     */
    public List<Task> findAll(){
            return taskDao.findAll();
    }
    
    public void changeState(Integer id,Integer state){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("state", state);
        taskDao.changeState(params);
    }
    
    public void changeAllState(Integer state){
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("state", state);
        taskDao.changeAllState(params);
    }

}
