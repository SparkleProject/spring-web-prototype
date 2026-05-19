package com.lintech.core.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lintech.entity.Staff;
import com.lintech.entity.User;
import com.lintech.security.AdminUserDetails;
import com.lintech.security.UserPrincipalDetails;

public class SecurityUtil {
	public static final String salt = "9d5e3ecdeb4cdb7acfd63075ae046672";

	public static Integer getCurrentStaffId() {
		return getCurrentStaff().getId();
	}

	public static Staff getCurrentStaff() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AdminUserDetails details = (AdminUserDetails) auth.getPrincipal();
		return details.getStaff();
	}

	public static Integer getCurrentUserId() {
		return getCurrentUser().getId();
	}

	public static User getCurrentUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipalDetails details = (UserPrincipalDetails) auth.getPrincipal();
		return details.getUser();
	}

	public static String md5(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);
			byte[] sourceBytes = s.getBytes(StandardCharsets.UTF_8);

			md.update(saltBytes);
			byte[] hashed = md.digest(sourceBytes);

			md.reset();
			hashed = md.digest(hashed);

			StringBuilder sb = new StringBuilder(hashed.length * 2);
			for (byte b : hashed) {
				sb.append(String.format("%02x", b & 0xff));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		System.out.println(md5("admin"));
	}
}
