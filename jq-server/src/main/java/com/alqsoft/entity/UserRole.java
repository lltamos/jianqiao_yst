package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "user_role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class UserRole  extends IdEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private int user_role_id;
	private UserTable user_table;
	private SystemRole system_role;

	public int getUser_role_id() {
		return user_role_id;
	}
	public void setUser_role_id(int user_role_id) {
		this.user_role_id = user_role_id;
	}
	public UserTable getUser_table() {
		return user_table;
	}
	public void setUser_table(UserTable user_table) {
		this.user_table = user_table;
	}
	public SystemRole getSystem_role() {
		return system_role;
	}
	public void setSystem_role(SystemRole system_role) {
		this.system_role = system_role;
	}
}
