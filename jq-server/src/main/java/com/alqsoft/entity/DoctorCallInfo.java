package com.alqsoft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 电话呼叫记录表doctor_call_info
 *
 */
@Entity
@Table(name = "doctor_call_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class DoctorCallInfo extends IdEntity implements Serializable{

	private static final long serialVersionUID = 5017981351836269438L;
	
	private String order_id;
	private Integer during_time;//呼叫持续时间(秒)
	private Integer remain_time;//呼叫剩余时间(秒)
	private Integer buy_time;//购买的时间(秒)
	private Date update_date;
	public Integer getDuring_time() {
		return during_time;
	}
	public void setDuring_time(Integer during_time) {
		this.during_time = during_time;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public Integer getBuy_time() {
		return buy_time;
	}
	public void setBuy_time(Integer buy_time) {
		this.buy_time = buy_time;
	}
	public Date getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}
	public Integer getRemain_time() {
		return remain_time;
	}
	public void setRemain_time(Integer remain_time) {
		this.remain_time = remain_time;
	}
	
}
