package com.aleixo.lbd.security;

public class UserAuthenticate {
	private String username;
	private String password;
	private String token;
	private String role;

	
	
	public UserAuthenticate(String username, String token, String role) {
		super();
		this.username = username;
		this.token = token;
		this.role = role;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
