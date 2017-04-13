package com.alqsoft.entity;

import java.io.Serializable;

import org.alqframework.orm.hibernate.IdEntity;
public class ImLog extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//医生id
	private Long doctorId;
	//客户id
	private Long customerId;
	//医生名称
	private String doctorPhone;
	//客户名称
	private String customerPhone;
	
	
	
	
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getDoctorPhone() {
		return doctorPhone;
	}
	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	
	
}