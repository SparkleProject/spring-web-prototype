package com.lintech.core.task;

import com.lintech.entity.Task;


/**
 * 任务调度服务
 */
public interface ScheduleService {

    /**
     * 启动所有任务
     * @throws Exception
     */
    public abstract void startup() throws Exception;

    /**
     * 停止所有任务
     * @throws Exception
     */
    public abstract void shutdown() throws Exception;
    
    /**
     * 启动任务
     * @throws Exception
     */
    public abstract void startup(Task task) throws Exception;
    
    /**
     * 停止任务
     * @throws Exception
     */
    public abstract void shutdown(Integer id) throws Exception;


    /**
     * 添加任务
     * @param id
     * @throws Exception
     */
    public abstract void add(Task task) throws Exception;
    

    /**
     * 删除任务
     * @throws Exception
     */
    public abstract void delete(Integer id) throws Exception;
    
    /**
     * 更新任务
     * @param id
     * @throws Exception
     */
    public abstract void update(Task task) throws Exception;
    
    /**
     * 暂停任务
     * @param id
     * @throws Exception
     */
    public abstract void pause(Integer id) throws Exception;
    
    /**
     * 唤醒任务
     * @param id
     * @throws Exception
     */
    public abstract void resume(Integer id) throws Exception;
    /**
     * 暂停所有任务
     * @param id
     * @throws Exception
     */
    public abstract void pauseAll() throws Exception;
    
    /**
     * 唤醒所有任务
     * @param id
     * @throws Exception
     */
    public abstract void resumeAll() throws Exception;

}