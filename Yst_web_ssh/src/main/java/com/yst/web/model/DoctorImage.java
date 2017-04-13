package com.yst.web.model;

import java.io.Serializable;

public class DoctorImage implements Serializable{
	
	private static final long serialVersionUID = -1149753782412512834L;


	private Integer doctor_image_id;
	private Integer doctor_id;//医生的ID
	private String des;//图片描述
	private String image;//图片的位置
	private transient Doctor doctor;
	
	
	private String str_doctor;
	
	
	
	public String getStr_doctor() {
		return str_doctor;
	}
	public void setStr_doctor(String str_doctor) {
		this.str_doctor = str_doctor;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public Integer getDoctor_image_id() {
		return doctor_image_id;
	}
	public void setDoctor_image_id(Integer doctor_image_id) {
		this.doctor_image_id = doctor_image_id;
	}
	public Integer getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}
	
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
