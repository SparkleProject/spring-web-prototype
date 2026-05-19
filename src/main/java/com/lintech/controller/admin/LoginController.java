package com.lintech.controller.admin;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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


@Controller
public class LoginController {

	@RequestMapping(value = "/admin/login")
	public String index(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "code", required = false) String code) {

		if (StringUtils.isNotBlank(code) && "-1".equals(code)) {
			request.setAttribute("error", "Session timeout");
			return "admin/login";
		}

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
			return "redirect:/admin/";
		}

		if (error != null) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				AuthenticationException ex = (AuthenticationException) session.getAttribute(
						WebAttributes.AUTHENTICATION_EXCEPTION);
				String msg;
				if (ex instanceof UsernameNotFoundException) {
					msg = "UserName is not exist";
				} else if (ex instanceof DisabledException) {
					msg = "This user is disabled";
				} else if (ex instanceof LockedException) {
					msg = "This user is locked";
				} else if (ex instanceof BadCredentialsException) {
					msg = "UserName/Password is incorrect";
				} else {
					msg = "Unknown Error";
				}
				request.setAttribute("error", msg);
			}
		}

		return "admin/login";
	}
}
