package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 电话呼叫记录表doctor_call_info
 *
 */
public class DoctorCallInfo implements Serializable{

	private static final long serialVersionUID = 5017981351836269438L;
	
	private Integer id;
	private String order_id;
	private Integer during_time;//呼叫持续时间(秒)
	private Integer remain_time;//呼叫剩余时间(秒)
	private Integer buy_time;//购买的时间(秒)
	private Date update_date;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
