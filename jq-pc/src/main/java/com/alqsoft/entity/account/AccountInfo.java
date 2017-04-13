	package com.alqsoft.entity.account;

import org.alqframework.orm.hibernate.IdEntity;
import org.apache.ibatis.type.Alias;

/**
 * @Title: Account.java
 * @Description: 前台用户实体类
 * @author 陈振兵
 * @e-mail chenzhenbing@139.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年5月16日 下午3:30:31
 * Copyright © 2013 厦门卓讯信息技术有限公司 All rights reserved.
 * 
 */

@Alias("accountinfo")
public class AccountInfo extends IdEntity{
	

	private static final long serialVersionUID = 1L;
	
	private String accountName = "";// 用户名
	
	private String accountPassword = "";// 用户密码
	
	private String accountRealName = "";// 真实名称
	private String mobile = "";// 电话号码
	
	private String tel = "";// 手机号码
	
	private String email = "";// 电子邮件
	
	private String address = "";// 地址
	private Integer isLocked = 0;// 是否被锁定，0代表没有被锁定，1代表没锁定
	private Long times = 0L;// 登录的次数
	private Integer gender = 0;// 0未知，1为男，2为女
	
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getAccountPassword() {
		return accountPassword;
	}
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}
	public String getAccountRealName() {
		return accountRealName;
	}
	public void setAccountRealName(String accountRealName) {
		this.accountRealName = accountRealName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getIsLocked() {
		return isLocked;
	}
	public void setIsLocked(Integer isLocked) {
		this.isLocked = isLocked;
	}
	public Long getTimes() {
		return times;
	}
	public void setTimes(Long times) {
		this.times = times;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}

}
