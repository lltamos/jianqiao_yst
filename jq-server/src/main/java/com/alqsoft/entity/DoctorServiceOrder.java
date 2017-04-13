package com.alqsoft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//在线咨询
@Entity
@Table(name = "doctor_service_order")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class DoctorServiceOrder extends IdEntity implements Serializable{

	private static final long serialVersionUID = -1379826928319038003L;
	private String orderId;//订单号
	private Integer customerId;//客户id
	private Integer doctorId;//医生id
	private Date orderDate;//订单日期
	private Integer service_type_id;//服务种类id，关联dic_service_type
	private Integer price;//价格
	private Integer executed;// '是否执行，默认为0，未执行，1为正在执行中，2为执行完毕,3为评价完毕',
	private Integer deleted;//是否删除，1为删除，0为未删除'
	private Integer payStatus;//支付状态 0 为未支付， 1 为已支付  3为等待确认，到家服务的订单提交之后改为3(针对到家和会诊服务)，后台确认后改为0
	private Integer pay_type;//支付类型 0 为系统内支付,1 为支付宝, 2 为银联
	private String pay_relative_id;//对于支付宝，保存支付宝的订单号，对于银联，保存银联的订单号
	private Date pay_time;//支付时间
	private Date pay_timeout;// 到期时间
	private Integer approved;//暂时只对到家服务有效，默认为1，表示已经同意，到家服务需要后台批准之后才可以，0为开始状态，1为同意，2为拒绝
	private String doctorPhone;
	private DicServiceType dicServiceType;
	private Integer buy_time;
	//接收参数
	private Integer serviceType;//订单类型：1表示在线咨询，2表示在线预约
	private String str_service_name;//返回服务名称
	private String servcie_timeYear;//0是单次  1 是按年
	private String str_customer_name;
	private String customerPhone;//用户联系电话
	private String str_doctor_name;
	private String str_service_type_name;
	
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
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
	public Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
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
	public Date getPay_timeout() {
		return pay_timeout;
	}
	public void setPay_timeout(Date pay_timeout) {
		this.pay_timeout = pay_timeout;
	}
	public Integer getApproved() {
		return approved;
	}
	public void setApproved(Integer approved) {
		this.approved = approved;
	}
	
	public String getDoctorPhone() {
		return doctorPhone;
	}
	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}
	public DicServiceType getDicServiceType() {
		return dicServiceType;
	}
	public void setDicServiceType(DicServiceType dicServiceType) {
		this.dicServiceType = dicServiceType;
	}
	public Integer getBuy_time() {
		return buy_time;
	}
	public void setBuy_time(Integer buy_time) {
		this.buy_time = buy_time;
	}
	public Integer getServiceType() {
		return serviceType;
	}
	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
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
	public String getStr_customer_name() {
		return str_customer_name;
	}
	public void setStr_customer_name(String str_customer_name) {
		this.str_customer_name = str_customer_name;
	}
	
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
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
	
	
	
}
