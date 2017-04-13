package com.alqsoft.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "advertise")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class Advertise extends IdEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer adv_id;
	private Integer order_index;
	private String name;
	private String image;
	private Date start_date;
	private Date end_date;
	private String link_content;
	
	private String detail_html;
	
	
	public String getDetail_html() {
		return detail_html;
	}
	public void setDetail_html(String detail_html) {
		this.detail_html = detail_html;
	}
	public Integer getAdv_id() {
		return adv_id;
	}
	public void setAdv_id(Integer adv_id) {
		this.adv_id = adv_id;
	}
	public Integer getOrder_index() {
		return order_index;
	}
	public void setOrder_index(Integer order_index) {
		this.order_index = order_index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getLink_content() {
		return link_content;
	}
	public void setLink_content(String link_content) {
		this.link_content = link_content;
	}
	
}
