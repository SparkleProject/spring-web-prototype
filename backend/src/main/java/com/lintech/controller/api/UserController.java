package com.lintech.controller.api;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import com.lintech.core.easyui.DataGrid;
import com.lintech.core.easyui.Messager;

import com.lintech.core.util.SecurityUtil;
import com.lintech.entity.User;
import com.lintech.service.admin.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/admin/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public DataGrid<User> list(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "20") int rows) {
        Page<User> result = userService.findAll(PageRequest.of(page - 1, rows));
        return new DataGrid<>(result.getContent(), (int) result.getTotalElements());
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return userService.findOne(id);
    }

    @PostMapping
    public Messager create(@RequestBody User user) {
        user.setEnabled(1);
        user.setLocked(0);
        userService.save(user);
        return Messager.SUCCESS;
    }

    @PutMapping("/{id}")
    public Messager update(@PathVariable Integer id, @RequestBody User user) {
        user.setId(id);
        userService.update(user);
        return Messager.SUCCESS;
    }

    @DeleteMapping("/{id}")
    public Messager delete(@PathVariable Integer id) {
        userService.delete(id);
        return Messager.SUCCESS;
    }

    @PutMapping("/{id}/password")
    public Messager changePassword(@PathVariable Integer id, @RequestBody Map<String, String> body) {
        String password = body.get("password_new");
        String encrypted = SecurityUtil.md5(password);
        userService.changePassword(id, encrypted);
        return Messager.SUCCESS;
    }

    @PutMapping("/{id}/enabled")
    public Messager toggleEnabled(@PathVariable Integer id, @RequestBody Map<String, Integer> body) {
        Integer enabled = body.get("enabled");
        userService.changeEanbled(String.valueOf(id), enabled);
        return Messager.SUCCESS;
    }
}
