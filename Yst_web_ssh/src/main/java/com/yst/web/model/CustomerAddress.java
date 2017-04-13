package com.yst.web.model;

import java.io.Serializable;
/**
 * 用户地址表，记录送货址信息
 * @author admin
 *
 */
public class CustomerAddress implements Serializable{

	private static final long serialVersionUID = 5829650652028423658L;
	
	private Integer id;
	private Integer customer_id;
	private String address;
	private String phone;
	private String addressee;
	private String zip_code;
	
	private Integer is_default;//0为不是，1为是默认
	
	
	public Integer getIs_default() {
		return is_default;
	}
	public void setIs_default(Integer is_default) {
		this.is_default = is_default;
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
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
