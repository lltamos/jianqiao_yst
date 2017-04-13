package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.DicHospitalType;

public interface DicHospitalTypeService {
	public List<DicHospitalType> selectAll();
	
	public List<DicHospitalType> queryList();
	public DicHospitalType findDicHospitalTypeInfo(Integer hospital_type_id);
	public AppResult updateDicHospitalType(DicHospitalType dicHospitalType);
	public AppResult deleteDicHospitalType(Integer hospital_type_id);
	public AppResult addDicHospitalType(DicHospitalType dicHospitalType);
}
