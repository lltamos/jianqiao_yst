package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class UserTable extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer user_id;
	
	private String loginName;
	private String password;
	
	private Integer deleted;
	
	private String createBy;

	/*private List roles;*/
	
	/*private transient Set<UserRole> roleUsers;*/

	private String imageCode;

	private String checkeds;
	
	private String url;
	
	/**
	 * 后台管理员用户登录权限
	 * 1.后台超管权限
	 * 2.后台总院权限
	 * 3.后台医生权限
	 * 4.后台推荐人权限
	 */
	private String authority;
	
	
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

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


	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

}
