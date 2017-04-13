package com.alqsoft.service.dic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.DicHospitalType;
import com.alqsoft.utils.BootStrapResult;

public interface DicHospitalTypeService extends BaseService<DicHospitalType>{

	/*
	 * 查询所有医院类型
	 */
	public List<DicHospitalType> findAll();
	
	public BootStrapResult<List<DicHospitalType>> getDicHospitalTypePage(Integer page, Integer length, HttpServletRequest request);
	
	public DicHospitalType get(Long arg0);
}
