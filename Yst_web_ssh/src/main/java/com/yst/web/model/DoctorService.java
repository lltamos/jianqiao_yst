package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 
 * DoctorService.java
 * @Description: 健桥- 医生服务设置开关
 * @author 朱军
 * @copyright 2015-2016
 * @create-time 2016年11月24日 下午4:16:44
 *
 */
public class DoctorService implements Serializable{
	
	private static final long serialVersionUID = -5956979802199217097L;
	
	private Integer doctor_service_id;
	private Integer doctor_id;
	private transient Doctor doctor;
	private String phone;
	//在线咨询开关：1开0关
	private Integer online_consult_switch;
	//在线咨询价格
	private Long online_consult_price;
	//在线咨询描述
	private String online_consult_describe;
	//电话咨询开关：1开0关
	private Integer phone_consult_switch;
	//电话咨询价格
	private Long phone_consult_price;
	//电话咨询描述
	private String phone_consult_describe;
	

	public Integer getDoctor_service_id() {
		return doctor_service_id;
	}

	public void setDoctor_service_id(Integer doctor_service_id) {
		this.doctor_service_id = doctor_service_id;
	}

	public Integer getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getOnline_consult_switch() {
		return online_consult_switch;
	}

	public void setOnline_consult_switch(Integer online_consult_switch) {
		this.online_consult_switch = online_consult_switch;
	}

	public Integer getPhone_consult_switch() {
		return phone_consult_switch;
	}

	public void setPhone_consult_switch(Integer phone_consult_switch) {
		this.phone_consult_switch = phone_consult_switch;
	}

	public Long getOnline_consult_price() {
		return online_consult_price;
	}

	public void setOnline_consult_price(Long online_consult_price) {
		this.online_consult_price = online_consult_price;
	}

	public String getOnline_consult_describe() {
		return online_consult_describe;
	}

	public void setOnline_consult_describe(String online_consult_describe) {
		this.online_consult_describe = online_consult_describe;
	}

	public Long getPhone_consult_price() {
		return phone_consult_price;
	}

	public void setPhone_consult_price(Long phone_consult_price) {
		this.phone_consult_price = phone_consult_price;
	}

	public String getPhone_consult_describe() {
		return phone_consult_describe;
	}

	public void setPhone_consult_describe(String phone_consult_describe) {
		this.phone_consult_describe = phone_consult_describe;
	}

	
}
