package com.alqsoft.entity.diary;

import java.beans.Transient;

import org.alqframework.orm.hibernate.IdEntity;

import com.alqsoft.entity.diarybook.DiaryBook;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 上午10:33:22
 * 
 */
public class Diary extends IdEntity{
	
	private String content;//日记内容
	
	private Integer customerId;//用户
	
	private Integer commenterNum;//评论数量
	
	private Integer browNum;//浏览数量
	
	private Long diaryBookId;//日记本
	
	private String productName;//项目名称
	
	private Integer productId;//项目id
	
	private String productTypeName;//项目类型名称；
	
	private Long productTypeId;//项目类型id；
	
	private Long productOrderId;//项目id

	private String merchantName;//医院名称
	
	private String doctorName;//医生名称
	
	private Long doctorId;//医生id
	
	public Long getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(Long productOrderId) {
		this.productOrderId = productOrderId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public Long getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Long productTypeId) {
		this.productTypeId = productTypeId;
	}



	/**
	 * 接口返回参数
	 * @return
	 */
	private String[] attachmentAddress;	//附件地址数组
	private String customerName;	//用户名
	private String customerAddress;		//用户地址
	private String customerlogoimg;   //用户头像
	private String diaryBookName;		//日记本名称
	private Integer satisfaction;		//满意度
	
	public Integer getBrowNum() {
		return browNum;
	}

	public void setBrowNum(Integer browNum) {
		this.browNum = browNum;
	}

	public Integer getCommenterNum() {
		return commenterNum;
	}

	public void setCommenterNum(Integer commenterNum) {
		this.commenterNum = commenterNum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	@Transient
	public String[] getAttachmentAddress() {
		return attachmentAddress;
	}

	public void setAttachmentAddress(String[] attachmentAddress) {
		this.attachmentAddress = attachmentAddress;
	}
	
	@Transient
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@Transient
	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	@Transient
	public String getCustomerlogoimg() {
		return customerlogoimg;
	}
	
	public void setCustomerlogoimg(String customerlogoimg) {
		this.customerlogoimg = customerlogoimg;
	}
	@Transient
	public String getDiaryBookName() {
		return diaryBookName;
	}

	public void setDiaryBookName(String diaryBookName) {
		this.diaryBookName = diaryBookName;
	}
	@Transient
	public Integer getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}

	public Long getDiaryBookId() {
		return diaryBookId;
	}

	public void setDiaryBookId(Long diaryBookId) {
		this.diaryBookId = diaryBookId;
	}
	
	
	

}
