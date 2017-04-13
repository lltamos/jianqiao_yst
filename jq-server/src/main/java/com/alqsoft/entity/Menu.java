package com.alqsoft.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Menu implements Serializable {
	private Integer menu_id;
	private String name;
	private String url;
	private transient Menu parent;
	private Integer order_index;
	private String des;
	private String sn;
	private transient Set<Menu> children = new HashSet<Menu>();
	private Integer pid;
	private transient Set<ACL> roleMenus;
	
	
	public Set<ACL> getRoleMenus() {
		return roleMenus;
	}

	public void setRoleMenus(Set<ACL> roleMenus) {
		this.roleMenus = roleMenus;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		if (parent != null){
			this.pid = parent.getMenu_id();
		}
		this.parent = parent;
	}

	public Integer getOrder_index() {
		return order_index;
	}

	public void setOrder_index(Integer order_index) {
		this.order_index = order_index;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public Set<Menu> getChildren() {
		return children;
	}

	public void setChildren(Set<Menu> children) {
		this.children = children;
	}

}
