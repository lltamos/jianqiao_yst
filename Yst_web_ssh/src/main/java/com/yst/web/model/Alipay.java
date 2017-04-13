package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;

public class Alipay implements Serializable{
	//发送数据
	private Integer alipay_id;
	private Integer alipay_act;
	private String send_payment_type;
	private String send_out_trade_no;
	private String send_subject;
	private Integer send_total_fee;
	private String send_body;
	//private String show_url;
	private String exter_invoke_ip;
	private Date create_time;
	//返回数据
	//返回的body
	private String buyer_email;
	private String buyer_id;
	private Double discount;
	private Date gmt_create;
	private Date gmt_payment;
	private String is_total_fee_adjust;
	private String notify_id;
	private Date notify_time;
	private String notify_type;
	private Double price;
	private Integer quantity;
	private String seller_email;
	private String seller_id;
	private String sign;
	private String sign_type;
	private String use_coupon;
	//返回 out_trade_no;
	//返回 payment_type;
	private String out_trade_no;
	private String payment_type;
	private String body;
	private String subject;
	private Double total_fee;
	//返回 subject
	//返回 total_fee
	private String trade_no;
	private String trade_status;
	public Integer getAlipay_id() {
		return alipay_id;
	}
	public void setAlipay_id(Integer alipay_id) {
		this.alipay_id = alipay_id;
	}
	
	
	public Integer getAlipay_act() {
		return alipay_act;
	}
	public void setAlipay_act(Integer alipay_act) {
		this.alipay_act = alipay_act;
	}
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getExter_invoke_ip() {
		return exter_invoke_ip;
	}
	public void setExter_invoke_ip(String exter_invoke_ip) {
		this.exter_invoke_ip = exter_invoke_ip;
	}
	public String getBuyer_email() {
		return buyer_email;
	}
	public void setBuyer_email(String buyer_email) {
		this.buyer_email = buyer_email;
	}
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
	public String getNotify_id() {
		return notify_id;
	}
	public void setNotify_id(String notify_id) {
		this.notify_id = notify_id;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Date getNotify_time() {
		return notify_time;
	}
	public void setNotify_time(Date notify_time) {
		this.notify_time = notify_time;
	}
	public String getNotify_type() {
		return notify_type;
	}
	public void setNotify_type(String notify_type) {
		this.notify_type = notify_type;
	}
	public String getSeller_email() {
		return seller_email;
	}
	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getTrade_no() {
		return trade_no;
	}
	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}
	public String getTrade_status() {
		return trade_status;
	}
	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}
	public Double getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Double total_fee) {
		this.total_fee = total_fee;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Date getGmt_create() {
		return gmt_create;
	}
	public void setGmt_create(Date gmt_create) {
		this.gmt_create = gmt_create;
	}
	public Date getGmt_payment() {
		return gmt_payment;
	}
	public void setGmt_payment(Date gmt_payment) {
		this.gmt_payment = gmt_payment;
	}
	public String getIs_total_fee_adjust() {
		return is_total_fee_adjust;
	}
	public void setIs_total_fee_adjust(String is_total_fee_adjust) {
		this.is_total_fee_adjust = is_total_fee_adjust;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getUse_coupon() {
		return use_coupon;
	}
	public void setUse_coupon(String use_coupon) {
		this.use_coupon = use_coupon;
	}
	public String getSend_payment_type() {
		return send_payment_type;
	}
	public void setSend_payment_type(String send_payment_type) {
		this.send_payment_type = send_payment_type;
	}
	public String getSend_out_trade_no() {
		return send_out_trade_no;
	}
	public void setSend_out_trade_no(String send_out_trade_no) {
		this.send_out_trade_no = send_out_trade_no;
	}
	public String getSend_subject() {
		return send_subject;
	}
	public void setSend_subject(String send_subject) {
		this.send_subject = send_subject;
	}
	
	public Integer getSend_total_fee() {
		return send_total_fee;
	}
	public void setSend_total_fee(Integer send_total_fee) {
		this.send_total_fee = send_total_fee;
	}
	public String getSend_body() {
		return send_body;
	}
	public void setSend_body(String send_body) {
		this.send_body = send_body;
	}
	
}
