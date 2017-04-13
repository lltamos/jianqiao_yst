package com.yst.web.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorService;
import com.yst.web.service.DoctorServiceServcie;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
@Controller("doctorServiceAction")
@Scope("prototype")
public class DoctorServiceAction extends BaseAction{
	
	private static final long serialVersionUID = 1959850661285986919L;
	
	@Resource(name = "doctorServiceService")
	DoctorServiceServcie doctorServiceService;
	private DoctorService doctorService = new DoctorService();
	AppResult appResult = new AppResult();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	/**
	 * 提交服务
	 */
	public void submitService(){
		appResult = this.doctorServiceService.addServiceInfo(doctorService);
		JSONUtils.sendJSON(appResult);
	}
	
	/**
	 * 获取医生服务设置信息
	 */
	public void getDoctorService(){
		appResult = this.doctorServiceService.getDoctorService(doctorService.getDoctor_id());
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
	public Object getModel() {
		return doctorService;
	}

}
