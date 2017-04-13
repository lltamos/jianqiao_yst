package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "role")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class SystemRole extends IdEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private int role_id;
	private String role_name;
	private String des;
/*	private transient Set<UserRole> roleUsers;
	private transient Set<ACL> roleMenus;*/
	
	private String checkeds;
	
	
	public String getCheckeds() {
		return checkeds;
	}
	public void setCheckeds(String checkeds) {
		this.checkeds = checkeds;
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
	
}
