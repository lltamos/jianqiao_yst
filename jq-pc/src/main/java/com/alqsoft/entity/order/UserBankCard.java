/**
 * Project Name:xyt_ydg
 * File Name:CustomerBankCard.java
 * Package Name:com.ydg.web.entity.customerbankcard
 * Date:2016年5月10日下午4:07:18
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.alqsoft.entity.order;

import java.util.Date;


import org.alqframework.orm.hibernate.IdEntity;


/**
 * 银行卡信息实体
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月15日 下午3:20:58
 * 
 */

@SuppressWarnings("serial")
public class UserBankCard extends IdEntity{
	
	
	private Integer customerType;//提现类型  1:总院  2:医生   3: 推荐人
	
	private long userId;//不同角色对应的id
	
	private String cardNum;//银行卡号
	
	private String telPhone;//银行所绑定的手机号
	
	private String bankName;//开户银行全称
	
	private String ownName;//开户人姓名    
	
	private Date  creatTime;
	
    private String isDelete;

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getOwnName() {
		return ownName;
	}

	public void setOwnName(String ownName) {
		this.ownName = ownName;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
}

