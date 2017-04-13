package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.alqsoft.entity.doctor.Doctors;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "doctor_service")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class DoctorService extends IdEntity implements Serializable{
	
	private static final long serialVersionUID = -5956979802199217097L;
	
	private Integer doctor_service_id;
	private Integer doctor_id;
	private Integer service_online_time_onoff;//在线按小时服务开关,0表示开启,1表示关闭
	private Integer service_online_time_price;//在线按小时服务价格
	private String service_online_time_desc;//在线按小时服务特殊说明
	private Integer customer_online_time_onoff;
	private Integer service_online_year_onoff;//在线按年服务开关
	private Integer service_online_year_price;//在线按年服务价格
	private String service_online_year_desc;//在线按年服务特殊说明
	private Integer customer_online_year_onoff;
	private Integer service_home_year_onoff;//按年到家服务开关
	private Integer service_home_year_price;//按年到家服务价格
	private String service_home_year_desc;//按年到家服务特殊说明
	private Integer customer_home_year_onoff;
	private Integer service_home_time_onoff;//按小时到家服务开关
	private Integer service_home_time_price;//按小时到家服务收费
	private String service_home_time_desc;//按小时到家服务描述
	private Integer customer_home_time_onoff;
	private Integer service_meet_onoff;//会诊服务开关
	private Integer service_meet_price;//会诊服务价格
	private String service_meet_desc;//会诊服务描述
	private Integer customer_meet_onoff;
	private transient Doctors doctor;
	
	private String phone;
	

	public Integer getDoctor_service_id() {
		return doctor_service_id;
	}

	public void setDoctor_service_id(Integer doctor_service_id) {
		this.doctor_service_id = doctor_service_id;
	}

	public Integer getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Integer getService_online_time_onoff() {
		return service_online_time_onoff;
	}

	public void setService_online_time_onoff(Integer service_online_time_onoff) {
		this.service_online_time_onoff = service_online_time_onoff;
	}

	public Integer getService_online_time_price() {
		return service_online_time_price;
	}

	public void setService_online_time_price(Integer service_online_time_price) {
		this.service_online_time_price = service_online_time_price;
	}

	public String getService_online_time_desc() {
		return service_online_time_desc;
	}

	public void setService_online_time_desc(String service_online_time_desc) {
		this.service_online_time_desc = service_online_time_desc;
	}

	public Integer getCustomer_online_time_onoff() {
		return customer_online_time_onoff;
	}

	public void setCustomer_online_time_onoff(Integer customer_online_time_onoff) {
		this.customer_online_time_onoff = customer_online_time_onoff;
	}

	public Integer getService_online_year_onoff() {
		return service_online_year_onoff;
	}

	public void setService_online_year_onoff(Integer service_online_year_onoff) {
		this.service_online_year_onoff = service_online_year_onoff;
	}

	public Integer getService_online_year_price() {
		return service_online_year_price;
	}

	public void setService_online_year_price(Integer service_online_year_price) {
		this.service_online_year_price = service_online_year_price;
	}

	public String getService_online_year_desc() {
		return service_online_year_desc;
	}

	public void setService_online_year_desc(String service_online_year_desc) {
		this.service_online_year_desc = service_online_year_desc;
	}

	public Integer getCustomer_online_year_onoff() {
		return customer_online_year_onoff;
	}

	public void setCustomer_online_year_onoff(Integer customer_online_year_onoff) {
		this.customer_online_year_onoff = customer_online_year_onoff;
	}

	public Integer getService_home_year_onoff() {
		return service_home_year_onoff;
	}

	public void setService_home_year_onoff(Integer service_home_year_onoff) {
		this.service_home_year_onoff = service_home_year_onoff;
	}

	public Integer getService_home_year_price() {
		return service_home_year_price;
	}

	public void setService_home_year_price(Integer service_home_year_price) {
		this.service_home_year_price = service_home_year_price;
	}

	public String getService_home_year_desc() {
		return service_home_year_desc;
	}

	public void setService_home_year_desc(String service_home_year_desc) {
		this.service_home_year_desc = service_home_year_desc;
	}

	public Integer getCustomer_home_year_onoff() {
		return customer_home_year_onoff;
	}

	public void setCustomer_home_year_onoff(Integer customer_home_year_onoff) {
		this.customer_home_year_onoff = customer_home_year_onoff;
	}

	public Integer getService_home_time_onoff() {
		return service_home_time_onoff;
	}

	public void setService_home_time_onoff(Integer service_home_time_onoff) {
		this.service_home_time_onoff = service_home_time_onoff;
	}

	public Integer getService_home_time_price() {
		return service_home_time_price;
	}

	public void setService_home_time_price(Integer service_home_time_price) {
		this.service_home_time_price = service_home_time_price;
	}

	public String getService_home_time_desc() {
		return service_home_time_desc;
	}

	public void setService_home_time_desc(String service_home_time_desc) {
		this.service_home_time_desc = service_home_time_desc;
	}

	public Integer getCustomer_home_time_onoff() {
		return customer_home_time_onoff;
	}

	public void setCustomer_home_time_onoff(Integer customer_home_time_onoff) {
		this.customer_home_time_onoff = customer_home_time_onoff;
	}

	public Integer getService_meet_onoff() {
		return service_meet_onoff;
	}

	public void setService_meet_onoff(Integer service_meet_onoff) {
		this.service_meet_onoff = service_meet_onoff;
	}

	public Integer getService_meet_price() {
		return service_meet_price;
	}

	public void setService_meet_price(Integer service_meet_price) {
		this.service_meet_price = service_meet_price;
	}

	public String getService_meet_desc() {
		return service_meet_desc;
	}

	public void setService_meet_desc(String service_meet_desc) {
		this.service_meet_desc = service_meet_desc;
	}

	public Integer getCustomer_meet_onoff() {
		return customer_meet_onoff;
	}

	public void setCustomer_meet_onoff(Integer customer_meet_onoff) {
		this.customer_meet_onoff = customer_meet_onoff;
	}

	
	

	public Doctors getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctors doctor) {
		this.doctor = doctor;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
