package com.yst.web.model;

import java.io.Serializable;
import java.util.Date;

public class MerchantActivity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer merchant_activity_id;
	private transient Merchant merchant;
	private Integer activity_type; //活动类型1 商品促销 2 活动召集
	private Integer prod_acti_id;//所对应的商品id，如果活动是商品促销的话。或者是活动activity的id，如果是活动的话
	private Date begin_time;//活动开始显示时间
	private Date end_time;//活动结束显示时间
	private Integer deleted;//是否删除1 删除 0 未删除 默认为0
	private Integer approved; //1为允许显示 2 为拒绝 0 初始化 现在默认为1
	private String name;//列表显示的名称
	private String short_desc;//列表显示的简单描述信息
	private String image;//列表的图片地址
	
	private String content_desc;
	
	
	public String getContent_desc() {
		return content_desc;
	}
	public void setContent_desc(String content_desc) {
		this.content_desc = content_desc;
	}
	public Integer getMerchant_activity_id() {
		return merchant_activity_id;
	}
	public void setMerchant_activity_id(Integer merchant_activity_id) {
		this.merchant_activity_id = merchant_activity_id;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public Integer getActivity_type() {
		return activity_type;
	}
	public void setActivity_type(Integer activity_type) {
		this.activity_type = activity_type;
	}
	public Integer getProd_acti_id() {
		return prod_acti_id;
	}
	public void setProd_acti_id(Integer prod_acti_id) {
		this.prod_acti_id = prod_acti_id;
	}
	public Date getBegin_time() {
		return begin_time;
	}
	public void setBegin_time(Date begin_time) {
		this.begin_time = begin_time;
	}
	public Date getEnd_time() {
		return end_time;
	}
	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}
	public Integer getDeleted() {
		return deleted;
	}
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
	public Integer getApproved() {
		return approved;
	}
	public void setApproved(Integer approved) {
		this.approved = approved;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShort_desc() {
		return short_desc;
	}
	public void setShort_desc(String short_desc) {
		this.short_desc = short_desc;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
}
