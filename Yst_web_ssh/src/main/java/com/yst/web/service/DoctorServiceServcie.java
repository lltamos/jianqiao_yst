package com.yst.web.service;

import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorService;

public interface DoctorServiceServcie {
	public AppResult addServiceInfo(DoctorService doctorService);
	public AppResult getDoctorService(Integer doctor_id);
}
