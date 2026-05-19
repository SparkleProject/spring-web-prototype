package com.lintech.controller.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.EasyUI;
import com.lintech.core.easyui.Menu;
import com.lintech.core.easyui.Messager;
import com.lintech.core.easyui.TreeNode;

import com.lintech.entity.Role;
import com.lintech.entity.RoleRes;
import com.lintech.service.admin.MenuService;
import com.lintech.service.admin.RoleResService;
import com.lintech.service.admin.RoleService;

@RestController
@RequestMapping("/api/admin/roles")
public class RoleController {

    private final RoleService roleService;
    private final RoleResService roleResService;
    private final MenuService menuService;

    public RoleController(RoleService roleService, RoleResService roleResService, MenuService menuService) {
        this.roleService = roleService;
        this.roleResService = roleResService;
        this.menuService = menuService;
    }

    @GetMapping
    public DataGrid<Role> list(@RequestParam(defaultValue = "1") int page,
                               @RequestParam(defaultValue = "20") int rows) {
        Page<Role> result = roleService.findAll(PageRequest.of(page - 1, rows));
        return new DataGrid<>(result.getContent(), (int) result.getTotalElements());
    }

    @PostMapping
    public Messager create(@RequestBody Role role) {
        roleService.save(role);
        return Messager.SUCCESS;
    }

    @PutMapping("/{id}")
    public Messager update(@PathVariable Integer id, @RequestBody Role role) {
        role.setId(id);
        roleService.update(role);
        return Messager.SUCCESS;
    }

    @DeleteMapping("/{id}")
    public Messager delete(@PathVariable Integer id) {
        roleService.delete(id);
        return Messager.SUCCESS;
    }

    @GetMapping("/{roleId}/menus")
    public List<TreeNode> loadMenus(@PathVariable Integer roleId) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleId", roleId);
        params.put("resType", RoleRes.RES_TYPE_MENU);
        List<RoleRes> roleResList = roleResService.findAll(params);
        List<Menu> menuList = menuService.findAll();
        for (RoleRes vo : roleResList) {
            for (Menu mvo : menuList) {
                if (vo.getResId().equals(mvo.getId())) {
                    mvo.setChecked(true);
                }
            }
        }
        return EasyUI.getEasyUITree(menuList);
    }

    @PutMapping("/{roleId}/menus")
    public Messager saveMenus(@PathVariable Integer roleId, @RequestBody Map<String, List<Integer>> body) {
        List<Integer> menuIds = body.get("menuIds");
        Map<String, Object> params = new HashMap<>();
        params.put("roleId", roleId);
        params.put("resType", RoleRes.RES_TYPE_MENU);
        roleResService.deleteByClause(params);

        List<RoleRes> roleResList = new ArrayList<>();
        if (menuIds != null) {
            for (Integer menuId : menuIds) {
                if (menuId == TreeNode.TREE_ROOT) continue;
                RoleRes vo = new RoleRes();
                vo.setResId(menuId);
                vo.setResType(RoleRes.RES_TYPE_MENU);
                vo.setRoleId(roleId);
                roleResList.add(vo);
            }
        }
        roleResService.saveBatch(roleResList);
        return Messager.SUCCESS;
    }

    @GetMapping("/{roleId}/functions")
    public List<RoleRes> loadFunctions(@PathVariable Integer roleId) {
        Map<String, Object> params = new HashMap<>();
        params.put("roleId", roleId);
        params.put("resType", RoleRes.RES_TYPE_FUNCTION);
        return roleResService.findAll(params);
    }

    @PutMapping("/{roleId}/functions")
    public Messager saveFunctions(@PathVariable Integer roleId, @RequestBody Map<String, List<Integer>> body) {
        List<Integer> funcIds = body.get("funcIds");
        Map<String, Object> params = new HashMap<>();
        params.put("roleId", roleId);
        params.put("resType", RoleRes.RES_TYPE_FUNCTION);
        roleResService.deleteByClause(params);

        List<RoleRes> roleResList = new ArrayList<>();
        if (funcIds != null) {
            for (Integer funcId : funcIds) {
                if (funcId == TreeNode.TREE_ROOT) continue;
                RoleRes vo = new RoleRes();
                vo.setResId(funcId);
                vo.setResType(RoleRes.RES_TYPE_FUNCTION);
                vo.setRoleId(roleId);
                roleResList.add(vo);
            }
        }
        roleResService.saveBatch(roleResList);
        return Messager.SUCCESS;
    }
}
