package com.yst.web.model;

import java.io.Serializable;
import java.util.Set;

public class Role implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int role_id;
	private String role_name;
	private String des;
	private transient Set<UserRole> roleUsers;
	private transient Set<ACL> roleMenus;
	
	private String checkeds;
	
	
	public String getCheckeds() {
		return checkeds;
	}
	public void setCheckeds(String checkeds) {
		this.checkeds = checkeds;
	}
	public Set<ACL> getRoleMenus() {
		return roleMenus;
	}
	public void setRoleMenus(Set<ACL> roleMenus) {
		this.roleMenus = roleMenus;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public Set<UserRole> getRoleUsers() {
		return roleUsers;
	}
	public void setRoleUsers(Set<UserRole> roleUsers) {
		this.roleUsers = roleUsers;
	}
	
	
}
