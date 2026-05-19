package com.lintech.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lintech.entity.User;
import com.lintech.security.UserPrincipalDetails;


@Controller("userLoginController")
public class LoginController {

	@RequestMapping(value = "/login")
	public String index(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "error", required = false) String error) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
			if (auth.getPrincipal() instanceof UserPrincipalDetails details) {
				User user = details.getUser();
				request.setAttribute("user", user);
			}
			return "redirect:/user/index";
		}

		if (error != null) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				AuthenticationException ex = (AuthenticationException) session.getAttribute(
						WebAttributes.AUTHENTICATION_EXCEPTION);
				String msg;
				if (ex instanceof UsernameNotFoundException) {
					msg = "用户名不存在";
				} else if (ex instanceof DisabledException) {
					msg = "该用户已被禁用";
				} else if (ex instanceof LockedException) {
					msg = "该用户已被锁定";
				} else if (ex instanceof BadCredentialsException) {
					msg = "用户名/密码错误";
				} else {
					msg = "未知错误";
				}
				request.setAttribute("error", msg);
			}
		}

		return "login";
	}
}
