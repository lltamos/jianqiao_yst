package com.alqsoft.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 
 * @Description: TODO
 * @author Shen.joe
 * @e-mail sudiluo_java@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年7月8日 下午5:11:45
 * 
 */
public class OrderModel implements Serializable{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

private Long id;//id
	
	private String createUser;
	
	private String createdIp;
	
	private Date createdTime;
	
	private Date updateTime;
	@NotNull
	private Long memberId;//会员
	@NotNull
	private Long merchantId;//商户
	@NotNull
	private Long addressId;//收货地址
	@NotBlank
	private String items;//json 格式 {"prodId":"1","count":"2"}
	@NotNull
	private Integer isNeedInvoice;//是否需要发票
	
	private String note;//备注
	
	private Double sum_integral;//订单总积分

	private Double dfjf;//	订单总金额
	
	public Integer getIsNeedInvoice() {
		return isNeedInvoice;
	}

	public void setIsNeedInvoice(Integer isNeedInvoice) {
		this.isNeedInvoice = isNeedInvoice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreatedIp() {
		return createdIp;
	}

	public void setCreatedIp(String createdIp) {
		this.createdIp = createdIp;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getSum_integral() {
		return sum_integral;
	}

	public void setSum_integral(Double sum_integral) {
		this.sum_integral = sum_integral;
	}

	public Double getDfjf() {
		return dfjf;
	}

	public void setDfjf(Double dfjf) {
		this.dfjf = dfjf;
	}
	
}
