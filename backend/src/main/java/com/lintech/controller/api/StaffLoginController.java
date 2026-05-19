package com.lintech.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.lintech.core.easyui.DataGrid;

import com.lintech.entity.StaffLogin;
import com.lintech.service.admin.StaffLoginService;

@RestController
@RequestMapping("/api/admin/staff-logins")
public class StaffLoginController {

    private final StaffLoginService staffLoginService;

    public StaffLoginController(StaffLoginService staffLoginService) {
        this.staffLoginService = staffLoginService;
    }

    @GetMapping
    public DataGrid<StaffLogin> list(@RequestParam(defaultValue = "1") int page,
                                     @RequestParam(defaultValue = "20") int rows) {
        Page<StaffLogin> result = staffLoginService.findAll(PageRequest.of(page - 1, rows));
        return new DataGrid<>(result.getContent(), (int) result.getTotalElements());
    }
}
