package com.alqsoft.entity;

import org.alqframework.orm.hibernate.IdEntity;
/**
 * 用户地址表，记录送货址信息
 * @author admin
 *
 */

public class CustomerAddress extends IdEntity{


	private Integer customerId;
	private String address;
	private String phone;
	private String addressee;
	private String zipCode;
	
	private Integer is_default;//0为不是，1为是默认

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Integer getIs_default() {
		return is_default;
	}

	public void setIs_default(Integer is_default) {
		this.is_default = is_default;
	}
	
	
	
	
}
