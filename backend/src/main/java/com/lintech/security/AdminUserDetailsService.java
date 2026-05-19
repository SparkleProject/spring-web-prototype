package com.lintech.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.lintech.entity.Staff;
import com.lintech.service.admin.StaffService;

@Component
public class AdminUserDetailsService implements UserDetailsService {

	private final StaffService staffService;

	public AdminUserDetailsService(StaffService staffService) {
		this.staffService = staffService;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Staff staff = staffService.findOneByLoginName(username);
		if (staff == null) {
			throw new UsernameNotFoundException(username);
		}
		return new AdminUserDetails(staff);
	}
}
