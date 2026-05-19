package com.lintech.controller.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;

import com.lintech.entity.Function;
import com.lintech.service.admin.FunctionService;

@RestController
@RequestMapping("/api/admin/functions")
public class FunctionController {

    private final FunctionService functionService;

    public FunctionController(FunctionService functionService) {
        this.functionService = functionService;
    }

    @GetMapping
    public DataGrid<Function> list(@RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "20") int rows) {
        Page<Function> result = functionService.findAll(PageRequest.of(page - 1, rows));
        return new DataGrid<>(result.getContent(), (int) result.getTotalElements());
    }

    @GetMapping("/all")
    public DataGrid<Function> all() {
        List<Function> list = functionService.findAll();
        return new DataGrid<>(list, list.size());
    }

    @PostMapping
    public Messager create(@RequestBody Function function) {
        functionService.save(function);
        return Messager.SUCCESS;
    }

    @PutMapping("/{id}")
    public Messager update(@PathVariable Integer id, @RequestBody Function function) {
        function.setId(id);
        functionService.update(function);
        return Messager.SUCCESS;
    }

    @DeleteMapping("/{id}")
    public Messager delete(@PathVariable Integer id) {
        functionService.delete(id);
        return Messager.SUCCESS;
    }
}
