package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;

import com.yst.web.annotations.Field;

public class UserComment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field
	private Integer user_comment_id;
	@Field
	private Integer customer_id;
	@Field
	private String customer_name;//顾客名称
	@Field
	private String customer_phone;//顾客电话
	@Field
	private String customer_comment;//顾客的评论信息
	@Field
	private Integer deleted;//是否删除 1 删除 0 未删除
	@Field
	private Integer comment_for_type;//评论对象  1 活动 2 知识库 3. 。。。。
	@Field
	private Date comment_date;//评论时间
	@Field
	private Integer comment_for_id;//评论的对象id
	@Field
	private Integer agree;//同意的次数
	@Field
	private Integer disagree;//不同意的次数
	@Field
	private Integer hot;//推荐，数字越大推荐越高'
	
	//接收参数
	private Integer order_by;//1 时间倒叙 2 热度倒叙
	
	
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getOrder_by() {
		return order_by;
	}
	public void setOrder_by(Integer order_by) {
		this.order_by = order_by;
	}
	public Integer getUser_comment_id() {
		return user_comment_id;
	}
	public void setUser_comment_id(Integer user_comment_id) {
		this.user_comment_id = user_comment_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public String getCustomer_comment() {
		return customer_comment;
	}
	public void setCustomer_comment(String customer_comment) {
		this.customer_comment = customer_comment;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Integer getComment_for_type() {
		return comment_for_type;
	}
	public void setComment_for_type(Integer comment_for_type) {
		this.comment_for_type = comment_for_type;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}
	public Integer getComment_for_id() {
		return comment_for_id;
	}
	public void setComment_for_id(Integer comment_for_id) {
		this.comment_for_id = comment_for_id;
	}
	public Integer getAgree() {
		return agree;
	}
	public void setAgree(Integer agree) {
		this.agree = agree;
	}
	public Integer getDisagree() {
		return disagree;
	}
	public void setDisagree(Integer disagree) {
		this.disagree = disagree;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	
}
