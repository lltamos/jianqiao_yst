package com.yst.web.model;

import java.io.Serializable;
//活动
public class Activity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer activity_id;
	private transient Merchant merchant;
	private String content_desc;//图文详情
	
	public Integer getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(Integer activity_id) {
		this.activity_id = activity_id;
	}
	public Merchant getMerchant() {
		return merchant;
	}
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}
	public String getContent_desc() {
		return content_desc;
	}
	public void setContent_desc(String content_desc) {
		this.content_desc = content_desc;
	}
	
}
