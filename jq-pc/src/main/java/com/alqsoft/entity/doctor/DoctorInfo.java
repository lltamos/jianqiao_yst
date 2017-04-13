package com.alqsoft.entity.doctor;

import org.alqframework.orm.hibernate.IdEntity;
/**
 * 
 * @Description: 医生用于前台显示的实体
 * @author shenguang
 * @e-mail shenguang044539@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月21日 下午5:48:30
 * Copyright  2013 北京易商通公司 All rights reserved.
 *
 */
public class DoctorInfo extends IdEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String imageHeader;//医生图像
	private String name;//医生姓名
	private Long titleId;//抬头id
	private String titleName;//抬头名称
	private Long hospitalypeId;//医院id
	private String hospitalypeName;//医院名称
	private String hospital;//医院名称
	private Long productId;//服务包id
	private String productName;//服务包id
	private String productTypeName;//服务包id
	private String merchantName;//总院名称
	private Long specId;//擅长id
	private String specName;//医生擅长
	private Integer price1;//在线doctor_service_order的价格（在线咨询）
	private Long price2;//在线 product deposite_price价格（在线预约）
	private Integer onlineAsk;//在线咨询数量
	private Integer onlineDate;//现在预约数量；
	public Long getHospitalypeId() {
		return hospitalypeId;
	}
	public void setHospitalypeId(Long hospitalypeId) {
		this.hospitalypeId = hospitalypeId;
	}
	public String getHospitalypeName() {
		return hospitalypeName;
	}
	public void setHospitalypeName(String hospitalypeName) {
		this.hospitalypeName = hospitalypeName;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getImageHeader() {
		return imageHeader;
	}
	public void setImageHeader(String imageHeader) {
		this.imageHeader = imageHeader;
	}
	public String getName() {
		return name;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTitleId() {
		return titleId;
	}
	public void setTitleId(Long titleId) {
		this.titleId = titleId;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public Integer getOnlineAsk() {
		return onlineAsk;
	}
	public void setOnlineAsk(Integer onlineAsk) {
		this.onlineAsk = onlineAsk;
	}
	public Integer getOnlineDate() {
		return onlineDate;
	}
	public void setOnlineDate(Integer onlineDate) {
		this.onlineDate = onlineDate;
	}
	public Long getSpecId() {
		return specId;
	}
	public void setSpecId(Long specId) {
		this.specId = specId;
	}
	public Integer getPrice1() {
		return price1;
	}
	public void setPrice1(Integer price1) {
		this.price1 = price1;
	}
	public Long getPrice2() {
		return price2;
	}
	public void setPrice2(Long price2) {
		this.price2 = price2;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public String getHospital() {
		return hospital;
	}
	public void setHospital(String hospital) {
		this.hospital = hospital;
	}
	
}
