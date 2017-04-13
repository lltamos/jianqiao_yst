package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorHomeServiceInfo;

public interface DoctorHomeServiceInfoService {
	public AppResult addDoctorHomeServiceInfo(DoctorHomeServiceInfo doctorHomeServiceInfo);
	
	public List<DoctorHomeServiceInfo> queryList();
	public DoctorHomeServiceInfo findDoctorHomeServiceInfoInfo(Integer order_id);
	public AppResult updateDoctorHomeServiceInfo(DoctorHomeServiceInfo doctorHomeServiceInfo);
	public void deleteDoctorHomeServiceInfo(Integer order_id);
}
