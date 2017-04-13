package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "activity")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Activity  extends IdEntity implements Serializable {

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
