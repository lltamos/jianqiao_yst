package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;

public class BalanceTrans implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer trans_id;
	private transient Customer customer;
	private Integer balance;
	private Date create_time;
	private Integer create_by;
	private String reason;
	private Integer balance_remain;
	private Integer customer_id;
	private Integer pay_status;
	private Integer pay_type;
	private String pay_relative_id;
	private Date pay_time;
	private String pay_password;
	
	
	public String getPay_password() {
		return pay_password;
	}
	public void setPay_password(String pay_password) {
		this.pay_password = pay_password;
	}
	public Integer getPay_type() {
		return pay_type;
	}
	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}
	public String getPay_relative_id() {
		return pay_relative_id;
	}
	public void setPay_relative_id(String pay_relative_id) {
		this.pay_relative_id = pay_relative_id;
	}
	public Date getPay_time() {
		return pay_time;
	}
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	public Integer getPay_status() {
		return pay_status;
	}
	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(Integer trans_id) {
		this.trans_id = trans_id;
	}
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getCreate_by() {
		return create_by;
	}
	public void setCreate_by(Integer create_by) {
		this.create_by = create_by;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public Integer getBalance_remain() {
		return balance_remain;
	}
	public void setBalance_remain(Integer balance_remain) {
		this.balance_remain = balance_remain;
	}
	
	
}
