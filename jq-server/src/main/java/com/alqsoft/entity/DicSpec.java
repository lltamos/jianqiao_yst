package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 专长字典
 * @author admin
 *
 */
@Entity
@Table(name = "dic_spec")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class DicSpec extends IdEntity implements Serializable{

	private static final long serialVersionUID = 6561606244810098395L;
	
	
	private Integer parentSpecId;
	private String name;
	
	
	public Integer getParentSpecId() {
		return parentSpecId;
	}
	public void setParentSpecId(Integer parentSpecId) {
		this.parentSpecId = parentSpecId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
