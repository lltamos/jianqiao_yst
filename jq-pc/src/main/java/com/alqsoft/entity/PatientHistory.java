package com.alqsoft.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.alqframework.orm.hibernate.IdEntity;
/**
 * 患者求医用于前台显示的实体
 * @Description: TODO
 * @author shenguang
 * @e-mail shenguang044539@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月15日 下午7:43:09
 * Copyright  2013 北京易商通公司 All rights reserved.
 *
 */
public class PatientHistory extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nickName;//求医昵称
	
	private Integer age;//求医年纪
	 
	private Integer sex;//求医性别
	  
	private String patientAddress;//求医详细地址
	  
	private String tell;//求医电话
	
	private String diseaseDesc;//病情描述
	
	private Long  customerId ;//用户Id
	
	private String createTime;
	
	private String headImage ;//用户头像
	
	private String[] imageUrl;//图片地址

	private Integer provId;
	
	private String provName ;
	
	public String getProvName() {
		return provName;
	}

	public void setProvName(String provName) {
		this.provName = provName;
	}

	public Integer getProvId() {
		return provId;
	}

	public void setProvId(Integer provId) {
		this.provId = provId;
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

	public String[] getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String[] imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
	
	
}
