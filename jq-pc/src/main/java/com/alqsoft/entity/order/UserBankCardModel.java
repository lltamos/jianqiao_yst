package com.alqsoft.entity.order;


/**
 * 银行pojo
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月15日 下午5:16:01
 * 
 */
public class UserBankCardModel {

	
	
	private String cardNum;//银行卡号必填
	
	private String telPhone;//银行所绑定的手机号必填
	
	private String bankName;//开户银行全称必填
	
	private String ownName;//开户人姓名    必填
	
	private String VerificationNO;//验证码
	
	private String idCard;//身份证
	
	
	
	
	
	public String getCardNum() {
		return cardNum;
	}

	public String getVerificationNO() {
		return VerificationNO;
	}

	public void setVerificationNO(String verificationNO) {
		VerificationNO = verificationNO;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getOwnName() {
		return ownName;
	}

	public void setOwnName(String ownName) {
		this.ownName = ownName;
	}

	
}
