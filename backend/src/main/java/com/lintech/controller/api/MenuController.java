package com.lintech.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.lintech.core.easyui.EasyUI;
import com.lintech.core.easyui.Menu;
import com.lintech.core.easyui.Messager;
import com.lintech.core.easyui.TreeNode;
import com.lintech.service.admin.MenuService;

@RestController
@RequestMapping("/api/admin/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<TreeNode> list() {
        List<Menu> menuList = menuService.findAll();
        return EasyUI.getEasyUITree(menuList);
    }

    @PostMapping
    public Messager create(@RequestBody Menu menu) {
        menuService.save(menu);
        return Messager.SUCCESS;
    }

    @PutMapping("/{id}")
    public Messager update(@PathVariable Integer id, @RequestBody Menu menu) {
        menu.setId(id);
        menuService.update(menu);
        return Messager.SUCCESS;
    }

    @DeleteMapping("/{id}")
    public Messager delete(@PathVariable Integer id) {
        menuService.delete(id);
        return Messager.SUCCESS;
    }
}
