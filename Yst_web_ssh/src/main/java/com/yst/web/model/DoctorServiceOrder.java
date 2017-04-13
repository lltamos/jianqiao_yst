package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;

import com.yst.web.annotations.Field;

public class DoctorServiceOrder implements Serializable{

	private static final long serialVersionUID = -1379826928319038003L;

	@Field
	private Integer order_id;//订单号
	private Integer customer_id;//客户id
	private Integer doctor_id;//医生id
	private Date order_date;//订单日期
	private Integer service_type_id;//服务种类id，关联dic_service_type;1是在线咨询2是电话咨询
	@Field
	private Integer price;//价格
	private Integer executed;// '是否执行，默认为0，未执行，1为正在执行中，2为执行完毕,3为评价完毕',
	private Integer deleted;//是否删除，1为删除，0为未删除'
	private Integer pay_status;//支付状态 0 为未支付， 1 为已支付  3为等待确认，到家服务的订单提交之后改为3(针对到家和会诊服务)，后台确认后改为0
	private Integer pay_type;//支付类型 0 为系统内支付,1 为支付宝, 2 为银联
	private String pay_relative_id;//对于支付宝，保存支付宝的订单号，对于银联，保存银联的订单号
	private Date pay_time;//支付时间
	private Integer approved;//暂时只对到家服务有效，默认为1，表示已经同意，到家服务需要后台批准之后才可以，0为开始状态，1为同意，2为拒绝
	private transient Customer customer;
	private Doctor doctor;
	private DicServiceType dicServiceType;
	//购买时长/可通话时长
	private Integer buy_time;
	//在线有效咨询开始时间
	private Date online_consult_start_time;
	//在线有效咨询结束时间
	private Date online_consult_end_time;
	//已通话时间
	private Integer already_call_duration;
	//剩余通话时间
	private Integer residue_call_duration;
	
	
	
	
	//接收参数
	private Integer service_type;
	
	
	private String str_service_name;//返回服务名称
	private String servcie_timeYear;//0是单次  1 是按年
	@Field(name="customer.name")
	private String str_customer_name;
	private String str_customer_phone;//用户联系电话
	@Field(name="doctor.name")
	private String str_doctor_name;
	@Field(name="dicServiceType.name")
	private String str_service_type_name;
	
	
	
	
	public Integer getBuy_time() {
		return buy_time;
	}
	public void setBuy_time(Integer buy_time) {
		this.buy_time = buy_time;
	}
	public Integer getService_type() {
		return service_type;
	}
	public void setService_type(Integer service_type) {
		this.service_type = service_type;
	}
	public String getStr_customer_phone() {
		return str_customer_phone;
	}
	public void setStr_customer_phone(String str_customer_phone) {
		this.str_customer_phone = str_customer_phone;
	}
	public String getStr_customer_name() {
		return str_customer_name;
	}
	public void setStr_customer_name(String str_customer_name) {
		this.str_customer_name = str_customer_name;
	}
	public String getStr_doctor_name() {
		return str_doctor_name;
	}
	public void setStr_doctor_name(String str_doctor_name) {
		this.str_doctor_name = str_doctor_name;
	}
	public String getStr_service_type_name() {
		return str_service_type_name;
	}
	public void setStr_service_type_name(String str_service_type_name) {
		this.str_service_type_name = str_service_type_name;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getDoctor_id() {
		return doctor_id;
	}
	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public Integer getService_type_id() {
		return service_type_id;
	}
	public void setService_type_id(Integer service_type_id) {
		this.service_type_id = service_type_id;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getExecuted() {
		return executed;
	}
	public void setExecuted(Integer executed) {
		this.executed = executed;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Integer getPay_status() {
		return pay_status;
	}
	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
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
	public Integer getApproved() {
		return approved;
	}
	public void setApproved(Integer approved) {
		this.approved = approved;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public DicServiceType getDicServiceType() {
		return dicServiceType;
	}
	public void setDicServiceType(DicServiceType dicServiceType) {
		this.dicServiceType = dicServiceType;
	}
	public String getStr_service_name() {
		return str_service_name;
	}
	public void setStr_service_name(String str_service_name) {
		this.str_service_name = str_service_name;
	}
	public String getServcie_timeYear() {
		return servcie_timeYear;
	}
	public void setServcie_timeYear(String servcie_timeYear) {
		this.servcie_timeYear = servcie_timeYear;
	}
	public Date getOnline_consult_start_time() {
		return online_consult_start_time;
	}
	public void setOnline_consult_start_time(Date online_consult_start_time) {
		this.online_consult_start_time = online_consult_start_time;
	}
	public Date getOnline_consult_end_time() {
		return online_consult_end_time;
	}
	public void setOnline_consult_end_time(Date online_consult_end_time) {
		this.online_consult_end_time = online_consult_end_time;
	}
	public Integer getAlready_call_duration() {
		return already_call_duration;
	}
	public void setAlready_call_duration(Integer already_call_duration) {
		this.already_call_duration = already_call_duration;
	}
	public Integer getResidue_call_duration() {
		return residue_call_duration;
	}
	public void setResidue_call_duration(Integer residue_call_duration) {
		this.residue_call_duration = residue_call_duration;
	}
	
	
}
