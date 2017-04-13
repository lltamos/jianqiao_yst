package com.yst.web.model;

import java.io.Serializable;

/**
 * 会诊
 * @author
 *
 */
public class DoctorMeetingServiceInfo implements Serializable{

	private static final long serialVersionUID = -4320951501580836665L;

	private Integer info_id;
	private Integer order_id;//针对的医生订单号
	private String contact_name;//会诊的联系人
	private String address;//会诊地址
	private String phone;//联系电话
	private Integer during;//会诊时长(小时)
	private String pay_relative_id;//订单号
	private transient DoctorServiceOrder doctorServiceOrder;
	
	
	public DoctorServiceOrder getDoctorServiceOrder() {
		return doctorServiceOrder;
	}
	public void setDoctorServiceOrder(DoctorServiceOrder doctorServiceOrder) {
		this.doctorServiceOrder = doctorServiceOrder;
	}
	public Integer getInfo_id() {
		return info_id;
	}
	public void setInfo_id(Integer info_id) {
		this.info_id = info_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public String getContact_name() {
		return contact_name;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
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
	public Integer getDuring() {
		return during;
	}
	public void setDuring(Integer during) {
		this.during = during;
	}
	public String getPay_relative_id() {
		return pay_relative_id;
	}
	public void setPay_relative_id(String pay_relative_id) {
		this.pay_relative_id = pay_relative_id;
	}
	
}
