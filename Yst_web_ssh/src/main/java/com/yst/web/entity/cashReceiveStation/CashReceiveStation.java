/**
 * Project Name:ydg
 * File Name:CashReceiveStation.java
 * Package Name:com.ydg.web.entity.cashreceivestation
 * Date:2015年11月24日下午12:06:23
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.yst.web.entity.cashReceiveStation;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**提现记录表
 * ClassName:CashReceiveStation <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年11月24日 下午12:06:23 <br/>
 * @author   lenovo
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Entity
@Table(name = "cash_receive_station")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class CashReceiveStation extends IdEntity{
	
	private Integer money;//金额，以分为单位；
	
	private Integer userId;//用户id
	
	private Integer cssType;//类型1总院后台 2 医生后台 3推荐人
	
	//private Date approveDate;//批准时间
	
	private Integer status;//1已申请 2打款成功 3打款失败
	
	private String bankName;//银行名称；
	
	private String cardNo;//银行卡号
	
	private String comments;//备注信息
	
	private String personName;//收款人名称
	
	private String personNo;//收款人卡号

	//以下是新加字段
	private String tel;//电话
	
	private String name;//名称
	
	private String bankPrv;//省
	
	private String bankCity;//市
	
	private String bankSub;//开户支行
	
	//private String oraStatus;//银行返回状态  0：正在处理中  1：提现成功  2：提现失败
	
	//private String oraName;
	
	private Date createTime;
	//private Date updateTime;
	
	private String merSeqId;//流水号
	private String  merDate;//商户日期
	private String chkValue;//签名值
	private Integer feeIncome;//手续费；
	
	private String signDate;//京东代付  签名数据
	
	private String bankAbbr;//京东代付 银行缩写
	

	public Integer getCssType() {
		return cssType;
	}

	public void setCssType(Integer cssType) {
		this.cssType = cssType;
	}

	public Integer getFeeIncome() {
		return feeIncome;
	}

	public void setFeeIncome(Integer feeIncome) {
		this.feeIncome = feeIncome;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getBankAbbr() {
		return bankAbbr;
	}

	public void setBankAbbr(String bankAbbr) {
		this.bankAbbr = bankAbbr;
	}

	public String getMerSeqId() {
		return merSeqId;
	}

	public void setMerSeqId(String merSeqId) {
		this.merSeqId = merSeqId;
	}

	public String getMerDate() {
		return merDate;
	}

	public void setMerDate(String merDate) {
		this.merDate = merDate;
	}

	public String getChkValue() {
		return chkValue;
	}

	public void setChkValue(String chkValue) {
		this.chkValue = chkValue;
	}

	public Integer getMoney() {
		return money;
	}

	public void setMoney(Integer money) {
		this.money = money;
	}



	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonNo() {
		return personNo;
	}

	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankPrv() {
		return bankPrv;
	}

	public void setBankPrv(String bankPrv) {
		this.bankPrv = bankPrv;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}

	public String getBankSub() {
		return bankSub;
	}

	public void setBankSub(String bankSub) {
		this.bankSub = bankSub;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	
}

