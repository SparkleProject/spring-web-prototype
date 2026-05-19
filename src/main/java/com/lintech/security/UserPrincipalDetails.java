package com.lintech.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lintech.entity.User;

public class UserPrincipalDetails implements UserDetails {

	private final User user;

	public UserPrincipalDetails(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getLoginName();
	}

	@Override
	public boolean isEnabled() {
		return user.getEnabled() != null && user.getEnabled() == 1;
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getLocked() == null || user.getLocked() != 1;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
}
