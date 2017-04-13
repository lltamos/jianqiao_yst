package com.yst.web.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorMeetingServiceInfo;
import com.yst.web.service.DoctorMeetingServiceInfoService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
@Controller("doctorMeetingServiceInfoAction")
@Scope("prototype")
public class DoctorMeetingServiceInfoAction extends BaseAction{

	private static final long serialVersionUID = 1797408663143085708L;
	
	@Resource(name = "doctorMeetingServiceInfoService")
	private DoctorMeetingServiceInfoService doctorMeetingServiceInfoService;
	private DoctorMeetingServiceInfo doctorMeetingServiceInfo = new DoctorMeetingServiceInfo();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	
	
	
	
	
	//以下是业务接口
	/**
	 * 录入会诊信息接口
	 */
	public void addConsultationInfo(){
		appResult = this.doctorMeetingServiceInfoService.addConsultation(doctorMeetingServiceInfo);
		JSONUtils.sendJSON(appResult);
	}
	
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	@Override
	public DoctorMeetingServiceInfo getModel() {
		return doctorMeetingServiceInfo;
	}
}
