package com.alqsoft.entity;

import java.io.Serializable;
/**
 * 商家表
 */
import org.alqframework.orm.hibernate.IdEntity;
public class Merchant extends IdEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String imageCred1;
	private String imageCred2;
	private Integer verify;
	//是否绑定银行卡: 0 未绑定 1 已绑定
	private Integer bankVerify;
	private Integer deleted;
	
	private String des;
	
	private String rejectReason;
	private Integer version;
	private String clientType;
	private Integer customerId;

	private String customerName;
	private String customerPhone;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImageCred1() {
		return imageCred1;
	}
	public void setImageCred1(String imageCred1) {
		this.imageCred1 = imageCred1;
	}
	public String getImageCred2() {
		return imageCred2;
	}
	public void setImageCred2(String imageCred2) {
		this.imageCred2 = imageCred2;
	}
	public Integer getVerify() {
		return verify;
	}
	public void setVerify(Integer verify) {
		this.verify = verify;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public String getRejectReason() {
		return rejectReason;
	}
	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
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
	public Integer getBankVerify() {
		return bankVerify;
	}
	public void setBankVerify(Integer bankVerify) {
		this.bankVerify = bankVerify;
	}
	
}
