package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "express_order_info")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class ExpressOrderInfo extends IdEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer express_id;
	private String order_id;
	private String express_name;
	private String express_no;
	
	
	public Integer getExpress_id() {
		return express_id;
	}
	public void setExpress_id(Integer express_id) {
		this.express_id = express_id;
	}
	public String getExpress_name() {
		return express_name;
	}
	public void setExpress_name(String express_name) {
		this.express_name = express_name;
	}
	public String getExpress_no() {
		return express_no;
	}
	public void setExpress_no(String express_no) {
		this.express_no = express_no;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
}
