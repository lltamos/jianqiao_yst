/**
 * Project Name:Yst_web_ssh
 * File Name:DiaryBook.java
 * Package Name:com.yst.web.controller.diarybook
 * Date:2016年1月5日下午11:47:40
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.alqsoft.entity.diarybook;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**日记本
 * ClassName:DiaryBook <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年1月5日 下午11:47:40 <br/>
 * @author   张灿
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Entity
@Table(name = "diary_book")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class DiaryBook extends IdEntity{
	
	//@NotNull(message="用户id不能为空")
	//@Length(min=1,max=10,message="请输入1-10个的字符")
	private Long customerId;//用户
	
	//@NotNull(message="项目id不能为空")
	private Long productId;//项目id
	
	//@NotNull(message="机构id不能为空")
	private Long merchantId;//机构id
	
	private Date buyDate;//购买时间
	
	private String diaryBookName;//日记本名称
	
	private Integer  satisfaction;//满意程度；1-5星
	
	private Integer commenterNum;//评论数量
	
	private Integer dairyNum;//日记数量
	
	private Long doctorId;//医生id
	
	private Long productOrderId;//项目id
	
	private String fabulousval;//赞的id
	
	private String steponval;//踩的id
	
	/**
	 * 接口返回参数
	 * @return
	 */
	private String productTypeName;
	private String productName;
	private String content;
	
	public String getFabulousval() {
		return fabulousval;
	}

	public void setFabulousval(String fabulousval) {
		this.fabulousval = fabulousval;
	}

	public String getSteponval() {
		return steponval;
	}

	public void setSteponval(String steponval) {
		this.steponval = steponval;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(Long productOrderId) {
		this.productOrderId = productOrderId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getDairyNum() {
		return dairyNum;
	}

	public void setDairyNum(Integer dairyNum) {
		this.dairyNum = dairyNum;
	}
	
	public String getDiaryBookName() {
		return diaryBookName;
	}

	public void setDiaryBookName(String diaryBookName) {
		this.diaryBookName = diaryBookName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Integer getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}

	public Integer getCommenterNum() {
		return commenterNum;
	}

	public void setCommenterNum(Integer commenterNum) {
		this.commenterNum = commenterNum;
	}
	@Transient
	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	@Transient
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}

