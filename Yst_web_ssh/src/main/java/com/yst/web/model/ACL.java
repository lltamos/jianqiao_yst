package com.yst.web.model;

import java.io.Serializable;

public class ACL implements Serializable{
	private Integer acl_id;
	private Role role;
	private Menu menu;
	public Integer getAcl_id() {
		return acl_id;
	}
	public void setAcl_id(Integer acl_id) {
		this.acl_id = acl_id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
