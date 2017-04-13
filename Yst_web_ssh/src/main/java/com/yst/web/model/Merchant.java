package com.yst.web.model;

import java.io.Serializable;
import java.util.Set;

import com.yst.web.annotations.Field;

public class Merchant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer merchant_id;
	@Field
	private String name;
	private String image_cred_1;
	private String image_cred_2;
	private transient Customer customer;
	@Field
	private Integer verify;
	@Field
	private Integer deleted;
	@Field
	private String des;
	@Field
	private String reject_reason;
	private Integer version;
	private String client_type;
	private Integer customer_id;

	@Field(name = "customer.name")
	private String customer_name;
	@Field(name = "customer.phone")
	private String customer_phone;

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	public Integer getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(Integer merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getVerify() {
		return verify;
	}

	public void setVerify(Integer verify) {
		this.verify = verify;
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

	public String getReject_reason() {
		return reject_reason;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getClient_type() {
		return client_type;
	}

	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}

	public String getImage_cred_1() {
		return image_cred_1;
	}

	public void setImage_cred_1(String image_cred_1) {
		this.image_cred_1 = image_cred_1;
	}

	public String getImage_cred_2() {
		return image_cred_2;
	}

	public void setImage_cred_2(String image_cred_2) {
		this.image_cred_2 = image_cred_2;
	}

}
