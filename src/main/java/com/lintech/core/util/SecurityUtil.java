package com.lintech.core.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;

import com.lintech.entity.Staff;
import com.lintech.entity.User;

public class SecurityUtil{
	public static final String salt="9d5e3ecdeb4cdb7acfd63075ae046672";
	
	public static Integer getCurrentStaffId(){
		return getCurrentStaff().getId();
	}
	
	public static Staff getCurrentStaff(){
		return (Staff)SecurityUtils.getSubject().getPrincipal();
	}
	
	
	public static Integer getCurrentUserId(){
		return getCurrentUser().getId();
	}
	
	public static User getCurrentUser(){
		return (User)SecurityUtils.getSubject().getPrincipal();
	}
	
	
	public static String md5(String s){
		return new Md5Hash(s,salt,2).toString();
	}
	
	public static void main(String[] args) {
		System.out.println(md5("admin"));
	}
	
}
