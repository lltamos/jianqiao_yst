package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 用户地址表，记录送货址信息
 * @author admin
 *
 */
@Entity
@Table(name = "customer_address")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class CustomerAddress implements Serializable{

	private static final long serialVersionUID = 5829650652028423658L;
	@Id
	@Column(name = "customer_id", unique = true, nullable = false)
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
