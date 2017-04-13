package com.alqsoft.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.alqframework.orm.hibernate.IdEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 关系字典
 * @author admin
 *
 */
@Entity
@Table(name = "dic_relation")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class DicRelation extends IdEntity implements Serializable{

	private static final long serialVersionUID = 6260006397327080597L;

	private Integer relation_id;
	private String name;

	public Integer getRelation_id() {
		return relation_id;
	}
	public void setRelation_id(Integer relation_id) {
		this.relation_id = relation_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
