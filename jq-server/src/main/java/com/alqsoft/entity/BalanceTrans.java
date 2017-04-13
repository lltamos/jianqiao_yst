package com.alqsoft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "balance_trans")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class BalanceTrans extends IdEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long income;//收入
	private Long defray;//支出
	private String reason;//备注
	private Long balanceRemain;//剩余金额
	private Long customerId;//用户ID
	private Integer payStatus;//支付状态:0未支付 1已支付
	private Integer payType;//支付方式：0微信
	private String payRelativeId;//订单号
	private Date payTime;//支付时间
	private Integer type;//当前用户的角色
	public Long getIncome() {
		return income;
	}
	public void setIncome(Long income) {
		this.income = income;
	}
	public Long getDefray() {
		return defray;
	}
	public void setDefray(Long defray) {
		this.defray = defray;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Long getBalanceRemain() {
		return balanceRemain;
	}
	public void setBalanceRemain(Long balanceRemain) {
		this.balanceRemain = balanceRemain;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getPayRelativeId() {
		return payRelativeId;
	}
	public void setPayRelativeId(String payRelativeId) {
		this.payRelativeId = payRelativeId;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
	
}
