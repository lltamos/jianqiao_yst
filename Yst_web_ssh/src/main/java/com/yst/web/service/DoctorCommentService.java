package com.yst.web.service;

import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorComment;

public interface DoctorCommentService {
	public AppResult getDoctorCommentList(DoctorComment doctorComment);
	public AppResult addDoctorComment(DoctorComment doctorComment);
}
