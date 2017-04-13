/**
 * Project Name:Yst_web_ssh
 * File Name:Order.java
 * Package Name:com.yst.web.entity.order
 * Date:2016年3月16日下午11:26:01
 * Copyright (c) 2016, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.alqsoft.entity.order;

import org.alqframework.orm.hibernate.IdEntity;

/**
 * ClassName:Order <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2016年3月16日 下午11:26:01 <br/>
 * @author   张灿
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */

public class PatientOrder extends IdEntity{
	
	
	private static final long serialVersionUID = 1L;

	private String orderNum;//订单号
	
	private Integer productId;//项目编号
	
	private String productName;//项目名称
	
	private Integer merchantId;//机构专家
	
	private String merchantName;//机构专家名称 例如 **医院
	
	private Integer patientId;//患者
	
	private String patientName;//患者名称
	
	private Long money;//订单的金额
	
	private String phone;//患者手机号
	
	private String address;//患者地址
	
	private Integer type;//记录订单最新状态  0未支付 1已签约 2部分支付 3全额支付 4阶段治疗中5最终治疗中 6完成；
	
	private Integer fenrunStatu;//订单分润状态  0未分润 1已分润

	private Long amountPaid;//已付金额
	
	private Integer dividePaid;//已付期数
	
	private String contractAddress;//合同附件地址
	
	
	public String getContractAddress() {
		return contractAddress;
	}

	public void setContractAddress(String contractAddress) {
		this.contractAddress = contractAddress;
	}

	public Integer getDividePaid() {
		return dividePaid;
	}

	public void setDividePaid(Integer dividePaid) {
		this.dividePaid = dividePaid;
	}

	public Long getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(Long amountPaid) {
		this.amountPaid = amountPaid;
	}

	public Integer getFenrunStatu() {
		return fenrunStatu;
	}

	public void setFenrunStatu(Integer fenrunStatu) {
		this.fenrunStatu = fenrunStatu;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Long getMoney() {
		return money;
	}

	public void setMoney(Long money) {
		this.money = money;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}

