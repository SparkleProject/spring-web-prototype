package com.lintech.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.EasyUI;
import com.lintech.core.easyui.Messager;
import com.lintech.core.easyui.TreeNode;

import com.lintech.entity.Role;
import com.lintech.entity.RoleStaff;
import com.lintech.service.admin.RoleService;
import com.lintech.service.admin.RoleStaffService;

@RestController
@RequestMapping("/api/admin/role-staff")
public class RoleStaffController {

    private final RoleService roleService;
    private final RoleStaffService roleStaffService;

    public RoleStaffController(RoleService roleService, RoleStaffService roleStaffService) {
        this.roleService = roleService;
        this.roleStaffService = roleStaffService;
    }

    @GetMapping
    public DataGrid<RoleStaff> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int rows) {
        Page<RoleStaff> result = roleStaffService.findAll(PageRequest.of(page - 1, rows));
        return new DataGrid<>(result.getContent(), (int) result.getTotalElements());
    }

    @DeleteMapping("/{id}")
    public Messager delete(@PathVariable Integer id) {
        roleStaffService.delete(id);
        return Messager.SUCCESS;
    }

    @GetMapping("/staff/{staffId}/roles")
    public List<TreeNode> loadRoles(@PathVariable Integer staffId) {
        List<Role> voList = roleService.findAll();
        Map<String, Object> params = new HashMap<>();
        params.put("staffId", staffId);
        List<RoleStaff> roleStaffList = roleStaffService.findAll(params);
        for (RoleStaff v : roleStaffList) {
            for (Role role : voList) {
                if (role.getId().equals(v.getRoleId())) {
                    role.setChecked(true);
                }
            }
        }
        return EasyUI.getEasyUITree(voList);
    }

    @PutMapping("/staff/{staffId}/roles")
    public Messager saveRoles(@PathVariable Integer staffId, @RequestBody Map<String, List<Integer>> body) {
        List<Integer> roleIds = body.get("roleIds");
        Map<String, Object> params = new HashMap<>();
        params.put("staffId", staffId);
        roleStaffService.deleteByClause(params);

        if (roleIds != null) {
            for (Integer roleId : roleIds) {
                if (roleId == TreeNode.TREE_ROOT) continue;
                RoleStaff vo = new RoleStaff();
                vo.setRoleId(roleId);
                vo.setStaffId(staffId);
                roleStaffService.save(vo);
            }
        }
        return Messager.SUCCESS;
    }
}
