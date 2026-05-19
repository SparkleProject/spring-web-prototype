package com.lintech.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.lintech.entity.User;
import com.lintech.service.admin.UserService;

@Component
public class UserUserDetailsService implements UserDetailsService {

	private final UserService userService;

	public UserUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findOneByLoginName(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserPrincipalDetails(user);
	}
}
