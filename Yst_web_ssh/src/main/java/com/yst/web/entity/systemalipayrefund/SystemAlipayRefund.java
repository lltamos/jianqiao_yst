package com.yst.web.entity.systemalipayrefund;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/***
 * 支付宝退款记录
 * @author Lgn
 *
 */
@Entity
@Table(name = "system_alipay_refund")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class SystemAlipayRefund extends IdEntity{

	private String service;//接口名称
	
	private String partner;//合作者身份ID 
	
	private String inputCharset;//参数编码字符集
	
	private String signType ;//签名方式
	
	private String sign;//签名
	
	private String notifyUrl;//服务器异步通知页面路径
	
	private String sellerEmail;//卖家支付宝账号

	private String refunDate;//退款请求时间
	
	private String batchNo ;//批次号
	
	private String batchNum ;//总笔数
	
	private String detailData ;// 单笔数据集 格式为：原付款支付宝交易号^退款总金额^退款理由

	private String successNum;//成功笔数
	
	private String resultDetails;//退款结果 返回格式：交易号^退款金额^处理结果
	
	private String status;//退款状态 0失败 1成功
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getRefunDate() {
		return refunDate;
	}

	public void setRefunDate(String refunDate) {
		this.refunDate = refunDate;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getBatchNum() {
		return batchNum;
	}

	public void setBatchNum(String batchNum) {
		this.batchNum = batchNum;
	}

	public String getDetailData() {
		return detailData;
	}

	public void setDetailData(String detailData) {
		this.detailData = detailData;
	}

	public String getSuccessNum() {
		return successNum;
	}

	public void setSuccessNum(String successNum) {
		this.successNum = successNum;
	}

	public String getResultDetails() {
		return resultDetails;
	}

	public void setResultDetails(String resultDetails) {
		this.resultDetails = resultDetails;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
