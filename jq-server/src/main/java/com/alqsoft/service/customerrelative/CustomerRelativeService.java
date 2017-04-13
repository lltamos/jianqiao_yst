package com.alqsoft.service.customerrelative;

import com.alqsoft.entity.customerrelative.CustomerRelative;

import java.util.List;

import org.alqframework.orm.BaseService;

public interface CustomerRelativeService extends BaseService<CustomerRelative>{

	
	public List<CustomerRelative> findAll();

}
