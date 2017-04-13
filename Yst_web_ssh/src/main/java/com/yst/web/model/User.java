package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.yst.web.annotations.Field;

public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field
	private Integer user_id;
	@Field
	private String login_name;
	private String password;
	@Field
	private Date create_time;
	@Field
	private Date update_time;
	@Field
	private Date delete_time;
	@Field
	private Integer deleted;
	@Field
	private String create_by;

	private List roles;
	private transient Set<UserRole> roleUsers;

	private String imageCode;

	private String checkeds;
	
	private String url;
	
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCheckeds() {
		return checkeds;
	}

	public void setCheckeds(String checkeds) {
		this.checkeds = checkeds;
	}

	public List getRoles() {
		return roles;
	}

	public void setRoles(List roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Set<UserRole> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(Set<UserRole> roleUsers) {
		this.roleUsers = roleUsers;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public Date getDelete_time() {
		return delete_time;
	}

	public void setDelete_time(Date delete_time) {
		this.delete_time = delete_time;
	}

	public String getCreate_by() {
		return create_by;
	}

	public void setCreate_by(String create_by) {
		this.create_by = create_by;
	}

}
