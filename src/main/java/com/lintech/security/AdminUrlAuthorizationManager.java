package com.lintech.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.lintech.entity.RoleRes;
import com.lintech.entity.RoleStaff;
import com.lintech.service.admin.RoleResService;
import com.lintech.service.admin.RoleStaffService;

@Component
public class AdminUrlAuthorizationManager implements AuthorizationManager<RequestAuthorizationContext> {

	private final RoleResService roleResService;
	private final RoleStaffService roleStaffService;
	private final AntPathMatcher matcher = new AntPathMatcher();

	public AdminUrlAuthorizationManager(RoleResService roleResService, RoleStaffService roleStaffService) {
		this.roleResService = roleResService;
		this.roleStaffService = roleStaffService;
	}

	@Override
	public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext context) {
		Authentication auth = authentication.get();
		if (auth == null || !auth.isAuthenticated()) {
			return new AuthorizationDecision(false);
		}
		if (!(auth.getPrincipal() instanceof AdminUserDetails adminDetails)) {
			return new AuthorizationDecision(false);
		}

		String servletPath = context.getRequest().getServletPath();
		Integer staffId = adminDetails.getStaff().getId();

		return new AuthorizationDecision(isPermission(servletPath, staffId));
	}

	private boolean isPermission(String servletPath, Integer staffId) {
		Map<String, Object> params = new HashMap<>();
		params.put("staffId", staffId);
		List<RoleStaff> roleList = roleStaffService.findAll(params);
		for (RoleStaff roleStaff : roleList) {
			Map<String, Object> roleParams = new HashMap<>();
			roleParams.put("roleId", roleStaff.getRoleId());
			List<RoleRes> rrList = roleResService.findAll(roleParams);
			for (RoleRes rr : rrList) {
				if (match(rr.getResCode(), servletPath)) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean match(String pattern, String url) {
		if (pattern.indexOf('.') > -1 || pattern.indexOf('?') > -1) {
			return matcher.match(pattern, url);
		} else {
			String wildcardPattern = pattern.endsWith("/") ? pattern + "**" : pattern + "/**";
			return matcher.match(pattern, url) || matcher.match(wildcardPattern, url);
		}
	}
}
