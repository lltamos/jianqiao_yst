package com.yst.web.model;

import java.io.Serializable;

/**
 * 医生服务说明
 * @author admin
 *
 */
public class ServiceDesc implements Serializable{
	private static final long serialVersionUID = 4279273099881673058L;
	private Integer id;
	private Integer type;//类型 1 为疑难杂症 2 为家庭服务 3 为可购买的商品
	private String image_url;//介绍的图文详情
	private String type_desc;//类别描述
	
	
	
	//返回数据
	private Integer doctor_count;
	
	
	
	public Integer getDoctor_count() {
		return doctor_count;
	}
	public void setDoctor_count(Integer doctor_count) {
		this.doctor_count = doctor_count;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getType_desc() {
		return type_desc;
	}
	public void setType_desc(String type_desc) {
		this.type_desc = type_desc;
	}
	

}
