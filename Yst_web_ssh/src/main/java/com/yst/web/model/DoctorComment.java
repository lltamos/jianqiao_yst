package com.yst.web.model;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;

import org.alqframework.json.jackson.convertor.JacksonConvertorDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 医生评价表
 * @author admin
 *
 */
public class DoctorComment implements Serializable{

	private static final long serialVersionUID = -5311401511659611184L;

	private Integer comment_id;//评价id
	private Integer doctorId;//医生的id
	private Integer customer_id;//顾客的id
	private Integer order_id;//订单id
	private Integer customer_sex;//顾客性别，便于快速查找
	private String customer_phone;//顾客电话，便于快速组成列表
	private Integer customer_age;//顾客年龄，便于快速组成列表
	private Integer service_star;//服务星级，最大5星，最小1星
	@JsonSerialize(using = JacksonConvertorDate.class)
	private Date comment_date;//评价时间
	private String customer_comment;//评价内容
	
	
	private String phone;
	private Integer sex;
	private Integer age;
	private String cImage;
	
	@Transient
	public String getcImage() {
		return cImage;
	}
	public void setcImage(String cImage) {
		this.cImage = cImage;
	}
	@Transient
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Transient
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	@Transient
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getCustomer_sex() {
		return customer_sex;
	}
	public void setCustomer_sex(Integer customer_sex) {
		this.customer_sex = customer_sex;
	}
	public Integer getCustomer_age() {
		return customer_age;
	}
	public void setCustomer_age(Integer customer_age) {
		this.customer_age = customer_age;
	}
	public Integer getService_star() {
		return service_star;
	}
	public void setService_star(Integer service_star) {
		this.service_star = service_star;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	public String getCustomer_comment() {
		return customer_comment;
	}
	public void setCustomer_comment(String customer_comment) {
		this.customer_comment = customer_comment;
	}
	
}
