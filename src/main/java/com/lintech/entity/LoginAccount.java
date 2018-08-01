package com.lintech.entity;

public class LoginAccount {
	
	String loginName;
	String password;
	
	public LoginAccount() {
		super();
	}
	public LoginAccount(String loginName, String password) {
		super();
		this.loginName = loginName;
		this.password = password;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	


}
