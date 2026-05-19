package com.lintech.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;
import com.lintech.core.task.ScheduleService;

import com.lintech.entity.Task;
import com.lintech.service.admin.TaskService;

@RestController
@RequestMapping("/api/admin/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ScheduleService schedulerService;

    public TaskController(TaskService taskService, ScheduleService schedulerService) {
        this.taskService = taskService;
        this.schedulerService = schedulerService;
    }

    @GetMapping
    public DataGrid<Task> list(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "20") int rows) {
        Page<Task> result = taskService.findAll(PageRequest.of(page - 1, rows));
        return new DataGrid<>(result.getContent(), (int) result.getTotalElements());
    }

    @PostMapping
    public Messager create(@RequestBody Task task) {
        task.setState(Task.STATE_STOP);
        taskService.save(task);
        return Messager.SUCCESS;
    }

    @PutMapping("/{id}")
    public Messager update(@PathVariable Integer id, @RequestBody Task task) {
        task.setId(id);
        taskService.update(task);
        try {
            schedulerService.update(task);
            return Messager.SUCCESS;
        } catch (Exception e) {
            return new Messager(false, e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public Messager delete(@PathVariable Integer id) {
        taskService.delete(id);
        try {
            schedulerService.delete(id);
            return Messager.SUCCESS;
        } catch (Exception e) {
            return new Messager(false, e.getMessage());
        }
    }

    @PutMapping("/{id}/startup")
    public Messager startup(@PathVariable Integer id) {
        taskService.changeState(id, Task.STATE_RUN);
        Task task = taskService.findOne(id);
        try {
            schedulerService.startup(task);
            return Messager.SUCCESS;
        } catch (Exception e) {
            return new Messager(false, e.getMessage());
        }
    }

    @PutMapping("/{id}/shutdown")
    public Messager shutdown(@PathVariable Integer id) {
        taskService.changeState(id, Task.STATE_STOP);
        try {
            schedulerService.shutdown(id);
            return Messager.SUCCESS;
        } catch (Exception e) {
            return new Messager(false, e.getMessage());
        }
    }

    @PutMapping("/{id}/pause")
    public Messager pause(@PathVariable Integer id) {
        taskService.changeState(id, Task.STATE_PAUSE);
        try {
            schedulerService.pause(id);
            return Messager.SUCCESS;
        } catch (Exception e) {
            return new Messager(false, e.getMessage());
        }
    }

    @PutMapping("/{id}/resume")
    public Messager resume(@PathVariable Integer id) {
        taskService.changeState(id, Task.STATE_RUN);
        try {
            schedulerService.resume(id);
            return Messager.SUCCESS;
        } catch (Exception e) {
            return new Messager(false, e.getMessage());
        }
    }
}
