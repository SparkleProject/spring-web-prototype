package com.lintech.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.lintech.core.easyui.EasyUI;
import com.lintech.core.easyui.Messager;
import com.lintech.core.easyui.TreeNode;
import com.lintech.entity.Department;
import com.lintech.service.admin.DepartmentService;

@RestController
@RequestMapping("/api/admin/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<TreeNode> list() {
        List<Department> departmentList = departmentService.findAll();
        return EasyUI.getEasyUITree(departmentList);
    }

    @PostMapping
    public Messager create(@RequestBody Department department) {
        departmentService.save(department);
        return Messager.SUCCESS;
    }

    @PutMapping("/{id}")
    public Messager update(@PathVariable Integer id, @RequestBody Department department) {
        department.setId(id);
        departmentService.update(department);
        return Messager.SUCCESS;
    }

    @DeleteMapping("/{id}")
    public Messager delete(@PathVariable Integer id) {
        departmentService.delete(id);
        return Messager.SUCCESS;
    }
}
