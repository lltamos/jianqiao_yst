package com.alqsoft.entity.diarybook;

import java.beans.Transient;
import java.util.Date;

import org.alqframework.orm.hibernate.IdEntity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月10日 上午10:49:59
 * 
 */
public class DiaryBook extends IdEntity{
	
	//@NotNull(message="用户id不能为空")
	//@Length(min=1,max=10,message="请输入1-10个的字符")
	private Long customerId;//用户
	
	//@NotNull(message="项目id不能为空")
	private Long productId;//项目id
	
	//@NotNull(message="机构id不能为空")
	private Long merchantId;//机构id
	
	private Date buyDate;//购买时间
	
	private String diaryBookName;//日记本名称
	
	private Integer  satisfaction;//满意程度；1-5星
	
	private Integer commenterNum;//评论数量
	
	private Integer dairyNum;//日记数量
	
	private Long doctorId;//医生id
	
	private Long productOrderId;//项目id
	
	private String fabulousval;//赞的id
	
	private String steponval;//踩的id
	
	/**
	 * 接口返回参数
	 * @return
	 */
	private String productTypeName;
	private String productName;
	private String content;
	
	public String getFabulousval() {
		return fabulousval;
	}

	public void setFabulousval(String fabulousval) {
		this.fabulousval = fabulousval;
	}

	public String getSteponval() {
		return steponval;
	}

	public void setSteponval(String steponval) {
		this.steponval = steponval;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(Long productOrderId) {
		this.productOrderId = productOrderId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	

	public Integer getDairyNum() {
		return dairyNum;
	}

	public void setDairyNum(Integer dairyNum) {
		this.dairyNum = dairyNum;
	}
	
	public String getDiaryBookName() {
		return diaryBookName;
	}

	public void setDiaryBookName(String diaryBookName) {
		this.diaryBookName = diaryBookName;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Integer getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(Integer satisfaction) {
		this.satisfaction = satisfaction;
	}

	public Integer getCommenterNum() {
		return commenterNum;
	}

	public void setCommenterNum(Integer commenterNum) {
		this.commenterNum = commenterNum;
	}
	@Transient
	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	@Transient
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
