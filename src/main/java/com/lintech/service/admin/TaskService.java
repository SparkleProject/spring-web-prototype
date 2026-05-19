package com.lintech.service.admin;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lintech.dao.TaskRepository;
import com.lintech.entity.Task;


/**
 * Task service class
 */
@Service
@Transactional(readOnly = true)
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    /**
     * Save new task
     * @param task the task to save
     */
    @Transactional
    public void save(Task task){
        taskRepository.save(task);
    }

    /**
     * Delete by ID
     * @param id the task id
     */
    @Transactional
    public void delete(Serializable id){
        taskRepository.deleteById((Integer) id);
    }

    /**
     * Update task
     * @param task the task to update
     */
    @Transactional
    public void update(Task task){
        taskRepository.save(task);
    }

    /**
     * Find by ID
     * @param id the task id
     * @return the task or null
     */
    public Task findOne(Serializable id){
        return taskRepository.findById((Integer) id).orElse(null);
    }

    /**
     * Find all with pagination
     * @param pageable pagination info
     * @return page of tasks
     */
    public Page<Task> findAll(Pageable pageable){
        return taskRepository.findAll(pageable);
    }

    /**
     * Find all tasks
     * @return list of all tasks
     */
    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    @Transactional
    public void changeState(Integer id, Integer state){
        taskRepository.changeState(id, state);
    }

    @Transactional
    public void changeAllState(Integer state){
        taskRepository.changeAllState(state);
    }

}
