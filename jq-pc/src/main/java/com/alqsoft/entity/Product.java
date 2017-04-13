package com.alqsoft.entity;

import java.io.Serializable;

import org.alqframework.orm.hibernate.IdEntity;


public class Product extends IdEntity implements Serializable {
	/**
	 * 服务包实体
	 * 关联实体服务包主图ProductImage，服务包医生ProductDoctor
	 */
	private static final long serialVersionUID = 1L;
	
	private Long merchantId;//总院ID
	
	private String name;//名称
	
	private String des1;//内容介绍
	
	private Integer deleted;//删除标记 0正常 1禁用
	
	private Long marketPrice;//市场价
	
	private Long totalPrice;//实际总价格
	
	private Long depositePrice;//定金
	
	private Integer divide;//分期数
	
	private Long fee1;//1期价格
	
	private Long fee2;//2期价格
	
	private Long fee3;//3期价格
	
	private Long fee4;//4期价格
	
	private Long fee5;//5期价格
	
	private Integer off;//开关 0上架 1下架
	
	private String detailUrl;//图文详情地址
	
	private String detailHtml;//图文详情html
	
	private String serviceProcess;//服务流程
	
	private String refundProcess;//退款流程
	
	private String special;//特别提示
	
	private String appointment;//如何预约
	
	private String customerServiceId;//客服账号
	
	private Long saleCount;//销量
	
	private Long viewCount;//点击量
	private Long serviceTypeId;//服务类型ID
	private Long customerId;//推荐人ID
	private String customerPhone;//推荐人电话
	private String customerName;//推荐人姓名
	private String merchantName;//总院名称
	private String productTypeName;//服务包类型名称
	
	
	public Long getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(Long marketPrice) {
		this.marketPrice = marketPrice;
	}
	public Long getSaleCount() {
		return saleCount;
	}
	public void setSaleCount(Long saleCount) {
		this.saleCount = saleCount;
	}
	public Long getViewCount() {
		return viewCount;
	}
	public void setViewCount(Long viewCount) {
		this.viewCount = viewCount;
	}
	public Long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Long getFee1() {
		return fee1;
	}
	public void setFee1(Long fee1) {
		this.fee1 = fee1;
	}
	public Long getFee2() {
		return fee2;
	}
	public void setFee2(Long fee2) {
		this.fee2 = fee2;
	}
	public Long getFee3() {
		return fee3;
	}
	public void setFee3(Long fee3) {
		this.fee3 = fee3;
	}
	public Long getFee4() {
		return fee4;
	}
	public void setFee4(Long fee4) {
		this.fee4 = fee4;
	}
	public Long getFee5() {
		return fee5;
	}
	public void setFee5(Long fee5) {
		this.fee5 = fee5;
	}
	public String getAppointment() {
		return appointment;
	}
	public void setAppointment(String appointment) {
		this.appointment = appointment;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public Long getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDes1() {
		return des1;
	}
	public void setDes1(String des1) {
		this.des1 = des1;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Long getDepositePrice() {
		return depositePrice;
	}
	public void setDepositePrice(Long depositePrice) {
		this.depositePrice = depositePrice;
	}
	public Integer getDivide() {
		return divide;
	}
	public void setDivide(Integer divide) {
		this.divide = divide;
	}
	public Integer getOff() {
		return off;
	}
	public void setOff(Integer off) {
		this.off = off;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	public String getDetailHtml() {
		return detailHtml;
	}
	public void setDetailHtml(String detailHtml) {
		this.detailHtml = detailHtml;
	}
	public String getServiceProcess() {
		return serviceProcess;
	}
	public void setServiceProcess(String serviceProcess) {
		this.serviceProcess = serviceProcess;
	}
	public String getRefundProcess() {
		return refundProcess;
	}
	public void setRefundProcess(String refundProcess) {
		this.refundProcess = refundProcess;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public String getCustomerServiceId() {
		return customerServiceId;
	}
	public void setCustomerServiceId(String customerServiceId) {
		this.customerServiceId = customerServiceId;
	}
	public Long getServiceTypeId() {
		return serviceTypeId;
	}
	public void setServiceTypeId(Long serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
}
