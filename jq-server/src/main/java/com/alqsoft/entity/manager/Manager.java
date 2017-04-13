
package com.alqsoft.entity.manager;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Date:     2016年10月11日 下午3:43:46 <br/>
 * @author   zhangcan
 * @version  普惠商城管理员表
 * @since    JDK 1.8
 * @see 	 
 */
@Entity
@Table(name = "alq_manager", indexes = {
		@Index(columnList = "account", name = "index_manager_account")
		})
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler", "fieldHandler" })
public class Manager extends IdEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String account;//登录用户名
	
	private String password;//登录密码

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

