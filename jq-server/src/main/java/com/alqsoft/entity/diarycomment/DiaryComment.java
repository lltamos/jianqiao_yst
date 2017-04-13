/**
 * Project Name:Yst_web_ssh
 * File Name:DiaryComment.java
 * Package Name:com.yst.web.entity.dairycomment
 * Date:2016年1月6日上午12:33:43
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.alqsoft.entity.diarycomment;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ClassName:DiaryComment <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年1月6日 上午12:33:43 <br/>
 * @author   张灿
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Entity
@Table(name = "diary_comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class DiaryComment extends IdEntity{
	
	private String content;//评论内容；
	
	private Long customerId;//用户
	
	private String customerName;//用户姓名
	
	private String customerHandImg;//用户头像
	
	private Long diaryId;//日记
	
	private Integer anonymous;//

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerHandImg() {
		return customerHandImg;
	}

	public void setCustomerHandImg(String customerHandImg) {
		this.customerHandImg = customerHandImg;
	}

	public Long getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(Long diaryId) {
		this.diaryId = diaryId;
	}

	public Integer getAnonymous() {
		return anonymous;
	}

	public void setAnonymous(Integer anonymous) {
		this.anonymous = anonymous;
	}
	
	
	
}

