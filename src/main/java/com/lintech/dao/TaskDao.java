package com.lintech.dao;

import java.util.Map;

import com.lintech.core.mybatis.Dao;
import com.lintech.core.mybatis.MyBatis;
import com.lintech.entity.Task;


@MyBatis
public interface TaskDao extends Dao<Task>{
    /**
     * 开启、禁用任务
     * @param params
     */
    void changeState(Map<String,Object> params);
    
    /**
     * 全部开启、禁用任务
     * @param params
     */
    void changeAllState(Map<String,Object> params);
    
}
