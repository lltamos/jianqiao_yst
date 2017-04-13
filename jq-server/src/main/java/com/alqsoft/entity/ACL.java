package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.alqsoft.entity.role.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "acl")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class ACL extends IdEntity implements Serializable{
	private static final long serialVersionUID = 1L;
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
