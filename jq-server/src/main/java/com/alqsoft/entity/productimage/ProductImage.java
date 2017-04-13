package com.alqsoft.entity.productimage;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 服务包主图表
 * @author Administrator
 *
 */
@Entity
@Table(name = "product_image")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class ProductImage extends IdEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long productId;//服务包ID
	
	private String address;////附件地址

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
