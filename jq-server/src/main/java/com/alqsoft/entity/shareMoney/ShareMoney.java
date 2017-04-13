package com.alqsoft.entity.shareMoney;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/***
 * 分润记录表
 * @author Lgn
 *
 */
@Entity
@Table(name = "share_money")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class ShareMoney extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String orderNumber;//订单号
	
	private String productName;//项目名称
	
	private String customerPhone;//订单用户帐号
	
	private Long totalPrice;//订单总价
	
	private Integer orderStatues;//1确认一期款 2确认二期款 3确认三期款 4确认四期款 5确认五期款
	
	private Integer divide;//分期数
	
	private Long fee;//当期金额
	
	private Integer  shareType;//分润类型 0:总院 1:医生2：患者推荐人3：项目推荐人4：平台
	
	private Long shareMoney;//分润金额
	
	private Long customerId;//分润用户ID
	
	private String reason;//分润原因
	
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public Long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Integer getOrderStatues() {
		return orderStatues;
	}
	public void setOrderStatues(Integer orderStatues) {
		this.orderStatues = orderStatues;
	}
	public Integer getDivide() {
		return divide;
	}
	public void setDivide(Integer divide) {
		this.divide = divide;
	}
	public Long getFee() {
		return fee;
	}
	public void setFee(Long fee) {
		this.fee = fee;
	}
	public Integer getShareType() {
		return shareType;
	}
	public void setShareType(Integer shareType) {
		this.shareType = shareType;
	}
	public Long getShareMoney() {
		return shareMoney;
	}
	public void setShareMoney(Long shareMoney) {
		this.shareMoney = shareMoney;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

}
