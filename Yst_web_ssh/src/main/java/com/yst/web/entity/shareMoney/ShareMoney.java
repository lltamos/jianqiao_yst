package com.yst.web.entity.shareMoney;

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

	
	
	private String orderNumber;//订单号
	
	private String projectName;//项目名称
	
	private String patientName;//患者姓名
	
	private String patientTel;//患者电话
	
	private Integer orderMoney;//订单金额
	
	private Integer orderStatues;//订单状态
	
	private String  shareType;//分润类型 0:专家机构 1:患者推荐人2：平台3：项目推荐人
	
	private Integer shareStatues;//分润状态 1:已分润 0:待分润
	
	private Integer shareMoney;//分润金额
	
	private Integer patientId;//患者id

	private Integer customerId;
	
	private Integer merchantId;//总院id
	
	private Integer doctorId;//医生id
	
	private Integer tjrType;// 0:介绍患者 1:介绍专家2是患者推荐人 同时 是专家推荐人;此字段废弃
	
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public Integer getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(Integer merchantId) {
		this.merchantId = merchantId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientTel() {
		return patientTel;
	}

	public void setPatientTel(String patientTel) {
		this.patientTel = patientTel;
	}

	public Integer getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Integer orderMoney) {
		this.orderMoney = orderMoney;
	}

	public Integer getOrderStatues() {
		return orderStatues;
	}

	public void setOrderStatues(Integer orderStatues) {
		this.orderStatues = orderStatues;
	}

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	public Integer getShareStatues() {
		return shareStatues;
	}

	public void setShareStatues(Integer shareStatues) {
		this.shareStatues = shareStatues;
	}

	public Integer getShareMoney() {
		return shareMoney;
	}

	public void setShareMoney(Integer shareMoney) {
		this.shareMoney = shareMoney;
	}

	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getTjrType() {
		return tjrType;
	}
	public void setTjrType(Integer tjrType) {
		this.tjrType = tjrType;
	}
	
	
}
