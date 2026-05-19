package com.lintech.controller.api;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.lintech.core.easyui.Combobox;
import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;

import com.lintech.entity.Code;
import com.lintech.service.admin.CodeService;

import java.util.List;

@RestController
@RequestMapping("/api/admin/codes")
public class CodeController {

    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping
    public DataGrid<Code> list(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "20") int rows) {
        Page<Code> result = codeService.findAll(PageRequest.of(page - 1, rows));
        return new DataGrid<>(result.getContent(), (int) result.getTotalElements());
    }

    @GetMapping("/all")
    public DataGrid<Code> all() {
        List<Code> codeList = codeService.findAll();
        return new DataGrid<>(codeList, codeList.size());
    }

    @PostMapping
    public Object create(@RequestBody Code code) {
        try {
            codeService.save(code);
            return Messager.SUCCESS;
        } catch (DuplicateKeyException e) {
            return new Messager(false, "Duplicate data: " + code.toString());
        }
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id, @RequestBody Code code) {
        try {
            code.setId(id);
            codeService.update(code);
            return Messager.SUCCESS;
        } catch (DuplicateKeyException e) {
            return new Messager(false, "Duplicate data: " + code.toString());
        }
    }

    @DeleteMapping("/{id}")
    public Messager delete(@PathVariable Integer id) {
        codeService.delete(id);
        return Messager.SUCCESS;
    }

    @GetMapping("/combobox/{type}")
    public List<Combobox> combobox(@PathVariable String type,
                                   @RequestParam(required = false) String select) {
        if (StringUtils.isNotBlank(select)) {
            return codeService.findCombobox(type, false, select);
        }
        return codeService.findCombobox(type, false);
    }
}
