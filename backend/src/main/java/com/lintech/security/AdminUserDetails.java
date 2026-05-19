package com.lintech.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lintech.entity.Staff;

public class AdminUserDetails implements UserDetails {

	private final Staff staff;

	public AdminUserDetails(Staff staff) {
		this.staff = staff;
	}

	public Staff getStaff() {
		return staff;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return staff.getPassword();
	}

	@Override
	public String getUsername() {
		return staff.getLoginName();
	}

	@Override
	public boolean isEnabled() {
		return staff.getEnabled() != null && staff.getEnabled() == 1;
	}

	@Override
	public boolean isAccountNonLocked() {
		return staff.getLocked() == null || staff.getLocked() != 1;
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
