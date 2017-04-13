package com.yst.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.doctor.DoctorMeetingServiceInfoDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorMeetingServiceInfo;
import com.yst.web.service.DoctorMeetingServiceInfoService;
@Service("doctorMeetingServiceInfoService")
@Transactional
public class DoctorMeetingServiceInfoServiceImpl implements DoctorMeetingServiceInfoService{

	@Resource(name="doctorMeetingServiceInfoDao")
	private DoctorMeetingServiceInfoDao doctorMeetingServiceInfoDao;
	
	@Override
	public AppResult addConsultation(DoctorMeetingServiceInfo doctorMeetingServiceInfo) {
		AppResult appResult = new AppResult();
		appResult.setVersion(1);
		appResult.setResult(appResult.FAILED);
		String pay_relative_id = doctorMeetingServiceInfo.getPay_relative_id();
		if(!"".equals(pay_relative_id)&&pay_relative_id!=null){
			this.doctorMeetingServiceInfoDao.save(doctorMeetingServiceInfo);
			appResult.setResult(AppResult.SUCCESS);
		}else{
			appResult.setError_info("订单号不能为空");
			appResult.setData("");
		}
		return appResult;
	}

}
