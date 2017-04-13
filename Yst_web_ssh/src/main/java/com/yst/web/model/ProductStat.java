package com.yst.web.model;

import java.io.Serializable;

import com.yst.web.annotations.Field;


public class ProductStat implements Serializable {
	@Field
	private Integer product_id;
	private transient Product product;
	@Field
	private Integer sale_count;
	@Field
	private Integer view_count;
	
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getSale_count() {
		return sale_count;
	}
	public void setSale_count(Integer sale_count) {
		this.sale_count = sale_count;
	}
	public Integer getView_count() {
		return view_count;
	}
	public void setView_count(Integer view_count) {
		this.view_count = view_count;
	}
	
}
