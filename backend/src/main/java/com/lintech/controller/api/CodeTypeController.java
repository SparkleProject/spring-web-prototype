package com.lintech.controller.api;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;

import com.lintech.entity.CodeType;
import com.lintech.service.admin.CodeTypeService;

@RestController
@RequestMapping("/api/admin/code-types")
public class CodeTypeController {

    private final CodeTypeService codeTypeService;

    public CodeTypeController(CodeTypeService codeTypeService) {
        this.codeTypeService = codeTypeService;
    }

    @GetMapping
    public DataGrid<CodeType> list(@RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "20") int rows) {
        Page<CodeType> result = codeTypeService.findAll(PageRequest.of(page - 1, rows));
        return new DataGrid<>(result.getContent(), (int) result.getTotalElements());
    }

    @GetMapping("/all")
    public List<CodeType> all() {
        return codeTypeService.findAll();
    }

    @PostMapping
    public Messager create(@RequestBody CodeType codeType) {
        codeTypeService.save(codeType);
        return Messager.SUCCESS;
    }

    @PutMapping("/{id}")
    public Messager update(@PathVariable Integer id, @RequestBody CodeType codeType) {
        codeType.setId(id);
        codeTypeService.update(codeType);
        return Messager.SUCCESS;
    }

    @DeleteMapping("/{id}")
    public Messager delete(@PathVariable Integer id) {
        codeTypeService.delete(id);
        return Messager.SUCCESS;
    }
}
