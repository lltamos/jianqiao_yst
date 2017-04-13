package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.ServiceDesc;

public interface ServiceDescService {

	public AppResult findServiceDescByType(Integer type);
	
	public List<ServiceDesc> queryList();
	public ServiceDesc findServiceDescInfo(Integer id);
	public AppResult updateServiceDesc(ServiceDesc serviceDesc);
	public void deleteServiceDesc(Integer id);
	public AppResult addServiceDesc(ServiceDesc serviceDesc);

	List<ServiceDesc> selectAll();
}
