package com.alqsoft.service.dic;

import java.util.List;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.ProductType;

public interface ProductTypeService extends BaseService<ProductType>{
	/**
	 * 查询所有服务包类型
	 * @return
	 */
	public List<ProductType> findAll();
}
