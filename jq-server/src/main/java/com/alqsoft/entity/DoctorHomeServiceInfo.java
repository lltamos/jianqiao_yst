package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "doctor_home_service_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class DoctorHomeServiceInfo extends IdEntity implements Serializable  {

	private static final long serialVersionUID = -9201850967639865089L;

	private Integer info_id;
	private Integer order_id;// 针对的医生订单号
	private String contact_name;// 到家服务的联系人
	private String address;// 服务地址
	private Integer home_doctor_type;// 1 为单次 2 为按年
	private String phone;// 联系电话
	private Integer member_count;// 家庭成员数量
	private transient DoctorServiceOrder doctorServiceOrder;
	
	// 接收参数
	private String pay_relative_id;
	
	
	
	public Integer getInfo_id() {
		return info_id;
	}

	public void setInfo_id(Integer info_id) {
		this.info_id = info_id;
	}

	public DoctorServiceOrder getDoctorServiceOrder() {
		return doctorServiceOrder;
	}

	public void setDoctorServiceOrder(DoctorServiceOrder doctorServiceOrder) {
		this.doctorServiceOrder = doctorServiceOrder;
	}

	public String getPay_relative_id() {
		return pay_relative_id;
	}

	public void setPay_relative_id(String pay_relative_id) {
		this.pay_relative_id = pay_relative_id;
	}

	public Integer getHome_doctor_type() {
		return home_doctor_type;
	}

	public void setHome_doctor_type(Integer home_doctor_type) {
		this.home_doctor_type = home_doctor_type;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContact_name() {
		return contact_name;
	}

	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}

	public Integer getMember_count() {
		return member_count;
	}

	public void setMember_count(Integer member_count) {
		this.member_count = member_count;
	}

}
