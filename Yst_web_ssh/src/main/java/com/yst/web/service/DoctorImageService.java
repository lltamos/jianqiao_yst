package com.yst.web.service;

import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorImage;

public interface DoctorImageService {
	public AppResult findDoctorImageList(Integer doctor_id);
	public AppResult addDoctorImage(DoctorImage doctorImage);
	public AppResult deleteDoctorImage(Integer image_id);
	public DoctorImage getDoctorImage(Integer image_id);
	public AppResult updateDoctorImage(DoctorImage doctorImage);
	
}
