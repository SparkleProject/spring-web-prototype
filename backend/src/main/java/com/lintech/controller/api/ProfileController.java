package com.lintech.controller.api;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.lintech.core.easyui.Messager;
import com.lintech.core.util.SecurityUtil;
import com.lintech.entity.Staff;
import com.lintech.service.admin.StaffService;

@RestController
@RequestMapping("/api/admin/profile")
public class ProfileController {

    private final StaffService staffService;

    public ProfileController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PutMapping("/password")
    public Messager changePassword(@RequestBody Map<String, String> body) {
        Integer staffId = SecurityUtil.getCurrentStaffId();
        Staff staff = staffService.findOne(staffId);
        String passwordOld = body.get("password_old");
        String passwordOldEncrypted = SecurityUtil.md5(passwordOld);
        if (!staff.getPassword().equals(passwordOldEncrypted)) {
            return new Messager(false, "Old password is incorrect");
        }
        String passwordNew = body.get("password_new");
        String passwordNewEncrypted = SecurityUtil.md5(passwordNew);
        staffService.changePassword(staffId, passwordNewEncrypted);
        return Messager.SUCCESS;
    }
}
