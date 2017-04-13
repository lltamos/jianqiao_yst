package com.alqsoft.entity;

import java.io.Serializable;
import java.util.Date;

import org.alqframework.orm.hibernate.IdEntity;


public class ProductOrder extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long marketPrice;//市场价1
	
	private Long totalPrice;//总价格1
	
	private Long depositePrice;//定金1
	
	private Integer count;//数量1
	
	private Integer payStatus; //1 0 待支付定金 1已支付定金 2已支付全款 3确认一期款 4确认二期款 5确认三期款 6确认四期款 7确认五期款 8退一期款 9退二期款 10退三期款 11退四期款 12退五期款 13订单完成            
	                      
	private Integer payType;//支付类型 0支付宝 1银联 2京东 3微信1
	
	private String payRelativeId;//订单号1
	
	private Date payTime;

	private Integer divide;//分期数1
	
	private Long fee1;//x期价格1
	
	private Long fee2;
	
	private Long fee3;
	
	private Long fee4;
	
	private Long fee5;
	
	private Integer feePayStatus1;//1   0未支付 1已支付 2已确认 3分润完成 4退款中 5退款成功 6退款失败
	
	private Integer feePayStatus2;
	
	private Integer feePayStatus3;
	
	private Integer feePayStatus4;
	
	private Integer feePayStatus5;
	private Date feeUpdateTime1;//x期状态更新时间
	private Date feeUpdateTime2;
	private Date feeUpdateTime3;
	private Date feeUpdateTime4;
	private Date feeUpdateTime5;
	
	private String zipCode;//邮编1
	private String patientName;//患者姓名快照1
	private String patientPhone;//患者电话快照1
	private String patientAddress;//患者地址1
	private String recommPhone;//患者推荐人手机1
	private String recommName;//患者推荐人姓名1
	private String recommAddress;//患者推荐人地址1
	private String merchantAddress;//总院地址1
	private String merchantPhone;//总院电话1
	private String merchantName;//总院名称1
	private String productName;//服务包名称1
	private String productRecommName;//项目推荐人名称1
	private String productRecommPhone;//项目推荐人手机号1
	private String customerName;//用户名称1
	private String customerPhone;//用户手机号1
	private String doctorPhone;//医生手机号1

	private Long recommId;//患者项目推荐人ID1
	private Long productRecommId;//项目推荐人ID1
	private Long doctorId;//医生ID1
	private Long customerId;//用户ID1
	private Long merchantId;//总院ID1
	private Long productId;//项目ID1
	
	private String priductOrderTypeName;//服务包类型名称
	
	private Integer type;// 0 待支付定金 1已支付定金 2已支付全款 3确认一期款 4确认二期款 5确认三期款 6确认四期款 7确认五期款 8退一期款 9退二期款 10退三期款 11退四期款 12退五期款 13订单完成

	/**
	 * 是用字段
	 * @return
	 */
	private String productImage;
	
	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public String getPriductOrderTypeName() {
		return priductOrderTypeName;
	}

	public void setPriductOrderTypeName(String priductOrderTypeName) {
		this.priductOrderTypeName = priductOrderTypeName;
	}

	public String getDoctorPhone() {
		return doctorPhone;
	}

	public void setDoctorPhone(String doctorPhone) {
		this.doctorPhone = doctorPhone;
	}

	public Long getRecommId() {
		return recommId;
	}

	public void setRecommId(Long recommId) {
		this.recommId = recommId;
	}

	public Long getProductRecommId() {
		return productRecommId;
	}

	public void setProductRecommId(Long productRecommId) {
		this.productRecommId = productRecommId;
	}

	public Integer getFeePayStatus1() {
		return feePayStatus1;
	}

	public void setFeePayStatus1(Integer feePayStatus1) {
		this.feePayStatus1 = feePayStatus1;
	}

	public Integer getFeePayStatus2() {
		return feePayStatus2;
	}

	public void setFeePayStatus2(Integer feePayStatus2) {
		this.feePayStatus2 = feePayStatus2;
	}

	public Integer getFeePayStatus3() {
		return feePayStatus3;
	}

	public void setFeePayStatus3(Integer feePayStatus3) {
		this.feePayStatus3 = feePayStatus3;
	}

	public Integer getFeePayStatus4() {
		return feePayStatus4;
	}

	public void setFeePayStatus4(Integer feePayStatus4) {
		this.feePayStatus4 = feePayStatus4;
	}

	public Integer getFeePayStatus5() {
		return feePayStatus5;
	}

	public void setFeePayStatus5(Integer feePayStatus5) {
		this.feePayStatus5 = feePayStatus5;
	}

	public Date getFeeUpdateTime1() {
		return feeUpdateTime1;
	}

	public void setFeeUpdateTime1(Date feeUpdateTime1) {
		this.feeUpdateTime1 = feeUpdateTime1;
	}

	public Date getFeeUpdateTime2() {
		return feeUpdateTime2;
	}

	public void setFeeUpdateTime2(Date feeUpdateTime2) {
		this.feeUpdateTime2 = feeUpdateTime2;
	}

	public Date getFeeUpdateTime3() {
		return feeUpdateTime3;
	}

	public void setFeeUpdateTime3(Date feeUpdateTime3) {
		this.feeUpdateTime3 = feeUpdateTime3;
	}

	public Date getFeeUpdateTime4() {
		return feeUpdateTime4;
	}

	public void setFeeUpdateTime4(Date feeUpdateTime4) {
		this.feeUpdateTime4 = feeUpdateTime4;
	}

	public Date getFeeUpdateTime5() {
		return feeUpdateTime5;
	}

	public void setFeeUpdateTime5(Date feeUpdateTime5) {
		this.feeUpdateTime5 = feeUpdateTime5;
	}

	public Long getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Long marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Long getDepositePrice() {
		return depositePrice;
	}

	public void setDepositePrice(Long depositePrice) {
		this.depositePrice = depositePrice;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public String getPayRelativeId() {
		return payRelativeId;
	}

	public void setPayRelativeId(String payRelativeId) {
		this.payRelativeId = payRelativeId;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Long getFee1() {
		return fee1;
	}

	public void setFee1(Long fee1) {
		this.fee1 = fee1;
	}

	public Long getFee2() {
		return fee2;
	}

	public void setFee2(Long fee2) {
		this.fee2 = fee2;
	}

	public Long getFee3() {
		return fee3;
	}

	public void setFee3(Long fee3) {
		this.fee3 = fee3;
	}

	public Long getFee4() {
		return fee4;
	}

	public void setFee4(Long fee4) {
		this.fee4 = fee4;
	}

	public Long getFee5() {
		return fee5;
	}

	public void setFee5(Long fee5) {
		this.fee5 = fee5;
	}


	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientPhone() {
		return patientPhone;
	}

	public void setPatientPhone(String patientPhone) {
		this.patientPhone = patientPhone;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}

	public String getRecommPhone() {
		return recommPhone;
	}

	public void setRecommPhone(String recommPhone) {
		this.recommPhone = recommPhone;
	}

	public String getRecommName() {
		return recommName;
	}

	public void setRecommName(String recommName) {
		this.recommName = recommName;
	}

	public String getRecommAddress() {
		return recommAddress;
	}

	public void setRecommAddress(String recommAddress) {
		this.recommAddress = recommAddress;
	}

	public String getMerchantAddress() {
		return merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}

	public String getMerchantPhone() {
		return merchantPhone;
	}

	public void setMerchantPhone(String merchantPhone) {
		this.merchantPhone = merchantPhone;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductRecommName() {
		return productRecommName;
	}

	public void setProductRecommName(String productRecommName) {
		this.productRecommName = productRecommName;
	}

	public String getProductRecommPhone() {
		return productRecommPhone;
	}

	public void setProductRecommPhone(String productRecommPhone) {
		this.productRecommPhone = productRecommPhone;
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

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}


	public Integer getDivide() {
		return divide;
	}

	public void setDivide(Integer divide) {
		this.divide = divide;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
