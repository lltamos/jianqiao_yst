package com.yst.web.model;

import java.io.Serializable;

import javax.persistence.Transient;

public class ProductType implements Serializable{
	
	private static final long serialVersionUID = -6168978784675492517L;
	private Integer product_type_id;
	private String name;
	private String des;
	private Integer deleted;
	
	//接口返回数据
	private String img;
	private String hospital_name;
	private String huanXinId;
	private String productId;
	
	@Transient
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	@Transient
	public String getHuanXinId() {
		return huanXinId;
	}
	public void setHuanXinId(String huanXinId) {
		this.huanXinId = huanXinId;
	}
	@Transient
	public String getHospital_name() {
		return hospital_name;
	}
	public void setHospital_name(String hospital_name) {
		this.hospital_name = hospital_name;
	}
	@Transient
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public Integer getProduct_type_id() {
		return product_type_id;
	}
	public void setProduct_type_id(Integer product_type_id) {
		this.product_type_id = product_type_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	
	
}
