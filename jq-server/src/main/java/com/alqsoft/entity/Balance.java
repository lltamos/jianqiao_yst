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
@Table(name = "balance")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class Balance extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long balanceRemain;//余额                
	private Date lastUpdateTime;//上次更新时间
	private Integer lastUpdateUser;//上次更新用户
	private Long customerId;//用户ID
    private Long fee;//结算金额；
	private Long depositFee;//可提现金额    自增不见
	private Long haveWithdrawalFee;//已提现金额    只增不减
	private Integer type;//角色类型 1-医生   2-总院  3-推荐人
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getBalanceRemain() {
		return balanceRemain;
	}
	public void setBalanceRemain(Long balanceRemain) {
		this.balanceRemain = balanceRemain;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Integer getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(Integer lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public Long getDepositFee() {
		return depositFee;
	}
	public void setDepositFee(Long depositFee) {
		this.depositFee = depositFee;
	}
	public Long getHaveWithdrawalFee() {
		return haveWithdrawalFee;
	}
	public void setHaveWithdrawalFee(Long haveWithdrawalFee) {
		this.haveWithdrawalFee = haveWithdrawalFee;
	}

	
}
