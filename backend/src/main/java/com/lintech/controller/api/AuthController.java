package com.lintech.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lintech.core.easyui.EasyUI;
import com.lintech.core.easyui.Menu;
import com.lintech.core.easyui.Messager;
import com.lintech.core.easyui.TreeNode;
import com.lintech.core.util.SecurityUtil;
import com.lintech.entity.Staff;
import com.lintech.security.AdminUserDetails;
import com.lintech.service.admin.MenuService;
import com.lintech.service.admin.StaffService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final StaffService staffService;
    private final MenuService menuService;

    public AuthController(AuthenticationManager authenticationManager,
                          StaffService staffService,
                          MenuService menuService) {
        this.authenticationManager = authenticationManager;
        this.staffService = staffService;
        this.menuService = menuService;
    }

    @PostMapping("/login")
    public Object login(@RequestBody Map<String, String> request, HttpServletRequest httpRequest) {
        String username = request.get("username");
        String password = request.get("password");
        try {
            Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            HttpSession session = httpRequest.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());

            AdminUserDetails details = (AdminUserDetails) auth.getPrincipal();
            Staff staff = details.getStaff();

            List<TreeNode> menuTree = getMenuTree(staff.getId());

            Map<String, Object> result = new HashMap<>();
            result.put("success", true);
            result.put("staff", staff);
            result.put("menu", menuTree);
            return result;
        } catch (AuthenticationException e) {
            return new Messager(false, "Invalid username or password");
        }
    }

    @GetMapping("/me")
    public Object me() {
        Integer staffId = SecurityUtil.getCurrentStaffId();
        Staff staff = staffService.findOne(staffId);
        List<TreeNode> menuTree = getMenuTree(staffId);

        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("staff", staff);
        result.put("menu", menuTree);
        return result;
    }

    @PostMapping("/logout")
    public Messager logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        SecurityContextHolder.clearContext();
        return Messager.SUCCESS;
    }

    private List<TreeNode> getMenuTree(Integer staffId) {
        List<Menu> srcList = menuService.findAll();
        List<Menu> menuList = menuService.findAllByStaffId(staffId);
        // Wrap parent nodes
        EasyUI.getEasyUIMenu(srcList, menuList, "");
        return EasyUI.getEasyUITree(menuList);
    }
}
