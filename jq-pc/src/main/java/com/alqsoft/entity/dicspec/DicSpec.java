package com.alqsoft.entity.dicspec;

import java.io.Serializable;

import org.alqframework.orm.hibernate.IdEntity;
/**
 * 专长字典
 * @author zj
 *
 */
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
