package com.yst.web.model;

import java.io.Serializable;

import com.yst.web.annotations.Field;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field
	private Integer product_id;
	private Integer merchant_id;
	@Field
	private String name;
	@Field
	private String des1;
	@Field
	private String des2;
	@Field
	private String des3;
	@Field
	private String des4;
	@Field
	private Integer deleted;
	@Field
	private String image1;
	@Field
	private String image2;
	@Field
	private String image3;
	@Field
	private String image4;
	@Field
	private Integer price;//项目总价
	@Field
	private Long deposite_price;//定金
	@Field
	private Integer divide;
	@Field
	private Integer fee1;
	@Field
	private Integer fee2;
	@Field
	private Integer fee3;
	@Field
	private Integer fee4;
	@Field
	private Integer fee5;
	@Field
	private Integer off;
	@Field
	private String detail_url;
	@Field
	private String detail_html;
	@Field
	private String service_process;
	@Field
	private String refund_process;
	@Field
	private String special;
	@Field
	private Integer is_real;
	@Field
	private Integer product_for;
	@Field
	private Integer freight;
	@Field
	private String customer_service_id;
	private transient Merchant merchant;
	private transient ProductType productType;
	private ProductStat productStat;
	private transient Doctor doctor;
	private Customer recomm_customer;//推荐人
	// 接收的参数
	private String CKEditor;
	private String langCode;
	private String CKEditorFuncNum;
	private String upload;
	private Integer order_type;
	private Double latitude;
	private Double longitude;
	private Integer product_type_id;
	private Integer doctor_id;
	// view参数
	@Field(name="merchant.name")
	private String merchant_name;
	@Field(name="recomm_customer.phone")
	private String recomm_phone;
	private String product_type_name;
	

	public String getRecomm_phone() {
		return recomm_phone;
	}

	public void setRecomm_phone(String recomm_phone) {
		this.recomm_phone = recomm_phone;
	}

	public Customer getRecomm_customer() {
		return recomm_customer;
	}

	public void setRecomm_customer(Customer recomm_customer) {
		this.recomm_customer = recomm_customer;
	}

	public Integer getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Integer doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Long getDeposite_price() {
		return deposite_price;
	}

	public void setDeposite_price(Long deposite_price) {
		this.deposite_price = deposite_price;
	}

	public String getCustomer_service_id() {
		return customer_service_id;
	}

	public void setCustomer_service_id(String customer_service_id) {
		this.customer_service_id = customer_service_id;
	}

	public String getProduct_type_name() {
		return product_type_name;
	}

	public void setProduct_type_name(String product_type_name) {
		this.product_type_name = product_type_name;
	}

	public Integer getFreight() {
		return freight;
	}

	public void setFreight(Integer freight) {
		this.freight = freight;
	}

	public Integer getProduct_for() {
		return product_for;
	}

	public void setProduct_for(Integer product_for) {
		this.product_for = product_for;
	}

	public ProductStat getProductStat() {
		return productStat;
	}

	public void setProductStat(ProductStat productStat) {
		this.productStat = productStat;
	}

	public Integer getProduct_type_id() {
		return product_type_id;
	}

	public void setProduct_type_id(Integer product_type_id) {
		this.product_type_id = product_type_id;
	}

	public Integer getIs_real() {
		return is_real;
	}

	public void setIs_real(Integer is_real) {
		this.is_real = is_real;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getService_process() {
		return service_process;
	}

	public void setService_process(String service_process) {
		this.service_process = service_process;
	}

	public String getRefund_process() {
		return refund_process;
	}

	public void setRefund_process(String refund_process) {
		this.refund_process = refund_process;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public String getDetail_html() {
		return detail_html;
	}

	public void setDetail_html(String detail_html) {
		this.detail_html = detail_html;
	}

	public String getCKEditor() {
		return CKEditor;
	}

	public void setCKEditor(String cKEditor) {
		CKEditor = cKEditor;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getCKEditorFuncNum() {
		return CKEditorFuncNum;
	}

	public void setCKEditorFuncNum(String cKEditorFuncNum) {
		CKEditorFuncNum = cKEditorFuncNum;
	}

	public String getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = upload;
	}

	public String getDetail_url() {
		return detail_url;
	}

	public void setDetail_url(String detail_url) {
		this.detail_url = detail_url;
	}

	public Integer getOff() {
		return off;
	}

	public void setOff(Integer off) {
		this.off = off;
	}
	public String getMerchant_name() {
		return merchant_name;
	}

	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Integer getOrder_type() {
		return order_type;
	}

	public void setOrder_type(Integer order_type) {
		this.order_type = order_type;
	}

	public Integer getFee1() {
		return fee1;
	}

	public void setFee1(Integer fee1) {
		this.fee1 = fee1;
	}

	public Integer getFee2() {
		return fee2;
	}

	public void setFee2(Integer fee2) {
		this.fee2 = fee2;
	}

	public Integer getFee3() {
		return fee3;
	}

	public void setFee3(Integer fee3) {
		this.fee3 = fee3;
	}

	public Integer getFee4() {
		return fee4;
	}

	public void setFee4(Integer fee4) {
		this.fee4 = fee4;
	}

	public Integer getFee5() {
		return fee5;
	}

	public void setFee5(Integer fee5) {
		this.fee5 = fee5;
	}

	/* 以字段id作为文档id */
	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Integer getMerchant_id() {
		return merchant_id;
	}

	public void setMerchant_id(Integer merchant_id) {
		this.merchant_id = merchant_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDes1() {
		return des1;
	}

	public void setDes1(String des1) {
		this.des1 = des1;
	}

	public String getDes2() {
		return des2;
	}

	public void setDes2(String des2) {
		this.des2 = des2;
	}

	public String getDes3() {
		return des3;
	}

	public void setDes3(String des3) {
		this.des3 = des3;
	}

	public String getDes4() {
		return des4;
	}

	public void setDes4(String des4) {
		this.des4 = des4;
	}

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getImage3() {
		return image3;
	}

	public void setImage3(String image3) {
		this.image3 = image3;
	}

	public String getImage4() {
		return image4;
	}

	public void setImage4(String image4) {
		this.image4 = image4;
	}

	public Integer getDivide() {
		return divide;
	}

	public void setDivide(Integer divide) {
		this.divide = divide;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
