package com.lintech.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;
import com.lintech.core.mybatis.Page;
import com.lintech.core.task.ScheduleService;
import com.lintech.core.util.ConfigUtil;
import com.lintech.core.util.ControllerUtils;
import com.lintech.entity.Task;
import com.lintech.service.admin.TaskService;

@Controller
@RequestMapping("/admin/task")
public class TaskController{
    @Autowired
    TaskService taskService;
    
    @Autowired
    ScheduleService schedulerService;
    
    @ResponseBody
    @RequestMapping("/init")
    public Object init(HttpServletRequest request,HttpServletResponse response){
        Map<String, Object> params = new HashMap<String, Object>();
        String name=ControllerUtils.getString(request, "name");
        int index=ControllerUtils.getInt(request, "page",1);
        Page page=new Page(index,ConfigUtil.getInt("pagesize"));
        params.put("name", name);
        List<Task> taskList=taskService.findAll(params,page);
        DataGrid<Task> datagrid=new DataGrid<Task>(taskList,page.getTotal());
        return datagrid;
    }
    
    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(HttpServletRequest request, HttpServletResponse response) throws NumberFormatException{
            String id=request.getParameter("id");
            taskService.delete(Integer.valueOf(id));
            try {
				schedulerService.delete(Integer.valueOf(id));
				return Messager.SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				return new Messager(false, e.getMessage());
			}
    }

    @ResponseBody
    @RequestMapping("/save")
    public Object save(Task task){
        if(null==task.getId()){
            task.setState(Task.STATE_STOP);
            taskService.save(task);
            return Messager.SUCCESS;
        }else{
            taskService.update(task);
            try {
				schedulerService.update(task);
				return Messager.SUCCESS;
			} catch (Exception e) {
				e.printStackTrace();
				return new Messager(false, e.getMessage());
			}
            
        }
    }
    
    
    @ResponseBody
    @RequestMapping("/pause/{id}")
    public Object pause(HttpServletRequest request, HttpServletResponse response,
            @PathVariable Integer id
            ) {
        taskService.changeState(id,Task.STATE_PAUSE);
        try {
			schedulerService.pause(id);
			return Messager.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return new Messager(false, e.getMessage());
		}
        
    }
    
    @ResponseBody
    @RequestMapping("/resume/{id}")
    public Object resume(HttpServletRequest request, HttpServletResponse response,
            @PathVariable Integer id
            ){
        taskService.changeState(id,Task.STATE_RUN);
        try {
			schedulerService.resume(id);
			return Messager.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return new Messager(false, e.getMessage());
		}
        
    }
    
    @ResponseBody
    @RequestMapping("/startup/{id}")
    public Object startup(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id) {
        taskService.changeState(id, Task.STATE_RUN);
        Task task = taskService.findOne(id);
        try {
			schedulerService.startup(task);
			return Messager.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return new Messager(false, e.getMessage());
		}
    }
    
    @ResponseBody
    @RequestMapping("/shutdown/{id}")
    public Object shutdown(HttpServletRequest request, HttpServletResponse response,@PathVariable Integer id){
        taskService.changeState(id, Task.STATE_STOP);
        try {
			schedulerService.shutdown(id);
			return Messager.SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return new Messager(false, e.getMessage());
		}
    }

}
