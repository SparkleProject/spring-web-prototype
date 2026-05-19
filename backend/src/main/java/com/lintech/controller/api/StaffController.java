package com.lintech.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;

import com.lintech.core.util.SecurityUtil;
import com.lintech.entity.Staff;
import com.lintech.service.admin.StaffService;

@RestController
@RequestMapping("/api/admin/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public DataGrid<Staff> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int rows) {
        Page<Staff> result = staffService.findAll(PageRequest.of(page - 1, rows));
        return new DataGrid<>(result.getContent(), (int) result.getTotalElements());
    }

    @GetMapping("/{id}")
    public Staff getById(@PathVariable Integer id) {
        return staffService.findOne(id);
    }

    @PostMapping
    public Messager create(@RequestBody Staff staff) {
        staffService.save(staff);
        return Messager.SUCCESS;
    }

    @PutMapping("/{id}")
    public Messager update(@PathVariable Integer id, @RequestBody Staff staff) {
        staff.setId(id);
        staffService.update(staff);
        return Messager.SUCCESS;
    }

    @DeleteMapping("/{id}")
    public Messager delete(@PathVariable Integer id) {
        staffService.delete(id);
        return Messager.SUCCESS;
    }

    @PutMapping("/{id}/password")
    public Messager changePassword(@PathVariable Integer id, @RequestBody java.util.Map<String, String> body) {
        String password = body.get("password_new");
        String encrypted = SecurityUtil.md5(password);
        staffService.changePassword(id, encrypted);
        return Messager.SUCCESS;
    }

    @PutMapping("/{id}/enabled")
    public Messager toggleEnabled(@PathVariable Integer id, @RequestBody java.util.Map<String, Integer> body) {
        Integer enabled = body.get("enabled");
        staffService.changeEanbled(String.valueOf(id), enabled);
        return Messager.SUCCESS;
    }
}
