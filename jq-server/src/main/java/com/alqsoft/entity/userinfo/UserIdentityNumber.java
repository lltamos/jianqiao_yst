package com.alqsoft.entity.userinfo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 用户身份证信息实体
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月15日 下午3:20:58
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "jq_user_identity_card")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class UserIdentityNumber extends IdEntity{

	
	private Integer customerType;//用户类型 1 医生 2 推荐人
	
	private String userId; //用户id
	
	private String name; //姓名
	
	private String identityNB; //身份证号 
	
	private Date  creatTime;
	
	
	
	
	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	private Integer isDelete;

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentityNB() {
		return identityNB;
	}


	public void setIdentityNB(String identityNB) {
		this.identityNB = identityNB;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
