package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "imlog")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
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