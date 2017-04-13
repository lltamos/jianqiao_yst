package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.DicServiceType;

public interface DicServiceTypeService {
	public List<DicServiceType> selectAll();
	
	public List<DicServiceType> queryList();
	public DicServiceType findDicServiceTypeInfo(Integer service_type_id);
	public AppResult updateDicServiceType(DicServiceType dicServiceType);
	public void deleteDicServiceType(Integer service_type_id);
	public AppResult addDicServiceType(DicServiceType dicServiceType);
}
