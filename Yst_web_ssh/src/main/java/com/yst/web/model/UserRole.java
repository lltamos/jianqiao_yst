package com.yst.web.model;

import java.io.Serializable;

public class UserRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int user_role_id;
	private User user;
	private Role role;

	public int getUser_role_id() {
		return user_role_id;
	}
	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
}
