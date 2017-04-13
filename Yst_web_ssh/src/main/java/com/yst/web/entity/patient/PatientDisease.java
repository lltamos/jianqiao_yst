package com.yst.web.entity.patient;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 患者求医
 * @Description: TODO
 * @author 黄鑫
 * @e-mail zhangzhaocan@yeah.net
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年4月22日 下午4:58:50
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
@Entity
@Table(name = "patient_disease")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class PatientDisease extends IdEntity{

	private String nickName;//求医昵称
	  
	private Integer age;//求医年纪
	 
	private Integer sex;//求医性别
	  
	private String patientAddress;//求医详细地址
	  
	private String tell;//求医电话
	  
	private String diseaseDesc;//病情描述
	  
	private Integer productTypeId;//项目类型id
	  
	private Integer customerId;//求医人
	
	private Integer provId;//所在省id
	
	private Integer cityId;//所在市id
	
	//接口
	private String provName;//省
	private String cityName;//市
	private String[] imgAddress;//求医信息图片列表
	private String logoImg;//患者头像
	
	
	@Transient
	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	@Transient
	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Transient
	public String getLogoImg() {
		return logoImg;
	}

	public void setLogoImg(String logoImg) {
		this.logoImg = logoImg;
	}

	@Transient
	public String[] getImgAddress() {
		return imgAddress;
	}

	public void setImgAddress(String[] imgAddress) {
		this.imgAddress = imgAddress;
	}
	
	public Integer getProvId() {
		return provId;
	}

	public void setProvId(Integer provId) {
		this.provId = provId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public String getDiseaseDesc() {
		return diseaseDesc;
	}

	public void setDiseaseDesc(String diseaseDesc) {
		this.diseaseDesc = diseaseDesc;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

}
