package com.yst.web.model;

import java.util.Date;

import com.yst.web.annotations.Field;

public class ProductOrder {
	@Field
	private Integer product_order_id;
	private  Customer customer;
	private  Product product;
	private transient Merchant merchant;
	private transient Doctor doctor;
	@Field
	private Integer price;//价格
	@Field
	private Long deposite_price;//定金
	@Field
	private Date create_date;//创建时间
	@Field
	private Integer deleted;//是否禁用
	@Field
	private Integer count;//数量
	@Field
	private Integer pay_status; // 0 待支付定金 1已支付定金 2已支付全款 3确认一期款 4确认二期款 5确认三期款 6确认四期款 7确认五期款 8退一期款 9退二期款 10退三期款 11退四期款 12退五期款 13订单完成            
	@Field                      
	private Integer pay_type;//支付类型 0支付宝 1银联 2京东
	@Field
	private String pay_relative_id;//订单号
	@Field
	private Date pay_time;
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
	private Integer fee1_pay_status;//0未支付 1已支付 2已确认 3分润完成 4退款中 5退款成功 6退款失败
	@Field
	private Integer fee2_pay_status;
	@Field
	private Integer fee3_pay_status;
	@Field
	private Integer fee4_pay_status;
	@Field
	private Integer fee5_pay_status;
	private Date fee1_update_time;//x期状态更新时间
	private Date fee2_update_time;
	private Date fee3_update_time;
	private Date fee4_update_time;
	private Date fee5_update_time;
	@Field
	private Integer is_real;//
	@Field
	private Integer product_for;
	@Field
	private Integer send_status;//配送状态
	@Field
	private String buyer_des; //买家留言
	@Field
	private Integer delivery_time;//送货时间
	@Field
	private Integer is_invoice;//是否开发票
	@Field
	private String invoice_name;//发票抬头
	@Field
	private Integer freight;//运费
	@Field
	private String address;//地址
	@Field
	private String addressee;//收件人
	@Field
	private String addressee_phone;//收件人手机
	@Field
	private String zip_code;//邮编
	private String patient_name;//患者姓名快照
	private String patient_phone;//患者电话快照
	private String patient_address;//患者地址
	private String recomm_phone;//推荐人手机
	private String recomm_address;//推荐人地址
	private String merchant_address;//总院地址
	private String merchant_phone;//总院电话
	
	private Integer customer_id;
	private Integer merchant_id;
	private Integer product_id;
	private Integer address_id;
	//view参数
	@Field(name="customer.name")
	private String customer_name;
	@Field(name="customer.phone")
	private String customer_phone;
	@Field(name="product.name")
	private String product_name;
	@Field(name="merchant.name")
	private String merchant_name;
	@Field(name="product.recomm_customer.phone")
	private String product_recomm_phone;
	@Field
	private Integer divide;
	
	private Integer shareMoney;
	private Date shareTime;
	private String phone;
	private String name;
	private Integer period;
	private String pay_password;
	
	private Integer fenrun_status;//分润状态  0 未分润 1已分润
	
	private Integer type;// 0 待支付定金 1已支付定金 2已支付全款 3确认一期款 4确认二期款 5确认三期款 6确认四期款 7确认五期款 8退一期款 9退二期款 10退三期款 11退四期款 12退五期款 13订单完成
	
	public String getProduct_recomm_phone() {
		return product_recomm_phone;
	}
	public void setProduct_recomm_phone(String product_recomm_phone) {
		this.product_recomm_phone = product_recomm_phone;
	}
	public Date getFee1_update_time() {
		return fee1_update_time;
	}
	public void setFee1_update_time(Date fee1_update_time) {
		this.fee1_update_time = fee1_update_time;
	}
	public Date getFee2_update_time() {
		return fee2_update_time;
	}
	public void setFee2_update_time(Date fee2_update_time) {
		this.fee2_update_time = fee2_update_time;
	}
	public Date getFee3_update_time() {
		return fee3_update_time;
	}
	public void setFee3_update_time(Date fee3_update_time) {
		this.fee3_update_time = fee3_update_time;
	}
	public Date getFee4_update_time() {
		return fee4_update_time;
	}
	public void setFee4_update_time(Date fee4_update_time) {
		this.fee4_update_time = fee4_update_time;
	}
	public Date getFee5_update_time() {
		return fee5_update_time;
	}
	public void setFee5_update_time(Date fee5_update_time) {
		this.fee5_update_time = fee5_update_time;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getPatient_phone() {
		return patient_phone;
	}
	public void setPatient_phone(String patient_phone) {
		this.patient_phone = patient_phone;
	}
	public String getPatient_address() {
		return patient_address;
	}
	public void setPatient_address(String patient_address) {
		this.patient_address = patient_address;
	}
	public String getRecomm_phone() {
		return recomm_phone;
	}
	public void setRecomm_phone(String recomm_phone) {
		this.recomm_phone = recomm_phone;
	}
	public String getRecomm_address() {
		return recomm_address;
	}
	public void setRecomm_address(String recomm_address) {
		this.recomm_address = recomm_address;
	}
	public String getMerchant_address() {
		return merchant_address;
	}
	public void setMerchant_address(String merchant_address) {
		this.merchant_address = merchant_address;
	}
	public String getMerchant_phone() {
		return merchant_phone;
	}
	public void setMerchant_phone(String merchant_phone) {
		this.merchant_phone = merchant_phone;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public Integer getFenrun_status() {
		return fenrun_status;
	}
	public void setFenrun_status(Integer fenrun_status) {
		this.fenrun_status = fenrun_status;
	}
	public String getAddressee_phone() {
		return addressee_phone;
	}
	public void setAddressee_phone(String addressee_phone) {
		this.addressee_phone = addressee_phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressee() {
		return addressee;
	}
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public Integer getDelivery_time() {
		return delivery_time;
	}
	public void setDelivery_time(Integer delivery_time) {
		this.delivery_time = delivery_time;
	}
	public Integer getIs_invoice() {
		return is_invoice;
	}
	public void setIs_invoice(Integer is_invoice) {
		this.is_invoice = is_invoice;
	}
	public String getInvoice_name() {
		return invoice_name;
	}
	public void setInvoice_name(String invoice_name) {
		this.invoice_name = invoice_name;
	}
	public Integer getFreight() {
		return freight;
	}
	public void setFreight(Integer freight) {
		this.freight = freight;
	}
	public String getBuyer_des() {
		return buyer_des;
	}
	public void setBuyer_des(String buyer_des) {
		this.buyer_des = buyer_des;
	}
	public Integer getAddress_id() {
		return address_id;
	}
	public void setAddress_id(Integer address_id) {
		this.address_id = address_id;
	}
	public Integer getProduct_for() {
		return product_for;
	}
	public void setProduct_for(Integer product_for) {
		this.product_for = product_for;
	}
	public Integer getIs_real() {
		return is_real;
	}
	public void setIs_real(Integer is_real) {
		this.is_real = is_real;
	}
	
	public Integer getSend_status() {
		return send_status;
	}
	public void setSend_status(Integer send_status) {
		this.send_status = send_status;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getMerchant_name() {
		return merchant_name;
	}
	public void setMerchant_name(String merchant_name) {
		this.merchant_name = merchant_name;
	}
	public String getPay_password() {
		return pay_password;
	}
	public void setPay_password(String pay_password) {
		this.pay_password = pay_password;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public Integer getFee1_pay_status() {
		return fee1_pay_status;
	}
	public void setFee1_pay_status(Integer fee1_pay_status) {
		this.fee1_pay_status = fee1_pay_status;
	}
	public Integer getFee2_pay_status() {
		return fee2_pay_status;
	}
	public void setFee2_pay_status(Integer fee2_pay_status) {
		this.fee2_pay_status = fee2_pay_status;
	}
	public Integer getFee3_pay_status() {
		return fee3_pay_status;
	}
	public void setFee3_pay_status(Integer fee3_pay_status) {
		this.fee3_pay_status = fee3_pay_status;
	}
	public Integer getFee4_pay_status() {
		return fee4_pay_status;
	}
	public void setFee4_pay_status(Integer fee4_pay_status) {
		this.fee4_pay_status = fee4_pay_status;
	}
	public Integer getFee5_pay_status() {
		return fee5_pay_status;
	}
	public void setFee5_pay_status(Integer fee5_pay_status) {
		this.fee5_pay_status = fee5_pay_status;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getDivide() {
		return divide;
	}
	public void setDivide(Integer divide) {
		this.divide = divide;
	}
	public Integer getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}
	public Integer getMerchant_id() {
		return merchant_id;
	}
	public void setMerchant_id(Integer merchant_id) {
		this.merchant_id = merchant_id;
	}
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Integer getProduct_order_id() {
		return product_order_id;
	}
	public void setProduct_order_id(Integer product_order_id) {
		this.product_order_id = product_order_id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
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
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getPay_status() {
		return pay_status;
	}
	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}
	public Integer getPay_type() {
		return pay_type;
	}
	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}
	public String getPay_relative_id() {
		return pay_relative_id;
	}
	public void setPay_relative_id(String pay_relative_id) {
		this.pay_relative_id = pay_relative_id;
	}
	public Date getPay_time() {
		return pay_time;
	}
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}
	public Integer getShareMoney() {
		return shareMoney;
	}
	public void setShareMoney(Integer shareMoney) {
		this.shareMoney = shareMoney;
	}
	public Date getShareTime() {
		return shareTime;
	}
	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
	}
	
	
}
