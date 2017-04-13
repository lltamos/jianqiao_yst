package com.yst.web.service;

import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorCallInfo;

public interface DoctorCallInfoService {
	
//	public AppResult addDoctorCallInfoInfo(DoctorCallInfo doctorCallInfo);
//	public AppResult getDoctorCallInfoList(Integer order_id);
	public AppResult saveOrUpdateRemianCallTime(DoctorCallInfo doctorCallInfo);
}
