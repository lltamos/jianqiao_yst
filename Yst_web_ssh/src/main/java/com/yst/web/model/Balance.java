package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;

public class Balance implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer balance_id;
	private transient Customer Customer;
	private Integer balance_remain;//余额
	private Date last_update_time;
	private Integer last_update_user;
	
	//新加字段
    private Integer fee;//结算金额；
	private Integer deposit_fee;//可提现金额
	private Integer have_withdrawal_fee;//已提现金额    
	
	
	
	public Customer getCustomer() {
		return Customer;
	}
	public void setCustomer(Customer customer) {
		Customer = customer;
	}
	public Integer getBalance_id() {
		return balance_id;
	}
	public void setBalance_id(Integer balance_id) {
		this.balance_id = balance_id;
	}
	
	public Integer getBalance_remain() {
		return balance_remain;
	}
	public void setBalance_remain(Integer balance_remain) {
		this.balance_remain = balance_remain;
	}
	public Date getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(Date last_update_time) {
		this.last_update_time = last_update_time;
	}
	public Integer getLast_update_user() {
		return last_update_user;
	}
	public void setLast_update_user(Integer last_update_user) {
		this.last_update_user = last_update_user;
	}
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	public Integer getDeposit_fee() {
		return deposit_fee;
	}
	public void setDeposit_fee(Integer deposit_fee) {
		this.deposit_fee = deposit_fee;
	}
	public Integer getHave_withdrawal_fee() {
		return have_withdrawal_fee;
	}
	public void setHave_withdrawal_fee(Integer have_withdrawal_fee) {
		this.have_withdrawal_fee = have_withdrawal_fee;
	}
	
}
