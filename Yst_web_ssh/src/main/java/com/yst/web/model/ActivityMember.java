package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;

public class ActivityMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer activity_member_id;
	private transient MerchantActivity activity;
	private transient Customer customer;
	private Integer approved;//是否同意，同意为1 不同意为2 初始化为0
	private Date apply_time; //申请时间
	public Integer getActivity_member_id() {
		return activity_member_id;
	}
	public void setActivity_member_id(Integer activity_member_id) {
		this.activity_member_id = activity_member_id;
	}
	
	public MerchantActivity getActivity() {
		return activity;
	}
	public void setActivity(MerchantActivity activity) {
		this.activity = activity;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Integer getApproved() {
		return approved;
	}
	public void setApproved(Integer approved) {
		this.approved = approved;
	}
	public Date getApply_time() {
		return apply_time;
	}
	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}
	
	
}
