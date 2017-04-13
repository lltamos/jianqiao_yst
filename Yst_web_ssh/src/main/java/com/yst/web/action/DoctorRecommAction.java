package com.yst.web.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorRecomm;
import com.yst.web.service.DoctorRecommService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
@Controller("doctorRecommAction")
@Scope("prototype")
public class DoctorRecommAction extends BaseAction{

	private static final long serialVersionUID = 5698224571794340053L;
	@Resource(name="doctorRecommService")
	private DoctorRecommService doctorRecommService;
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	private DoctorRecomm doctorRecomm = new DoctorRecomm();
	
	
	
	/**
	 * 获取首页家庭医生列表接口
	 */
	public void getHomeDoctorList(){
		appResult = this.doctorRecommService.getHomeDoctorList();
		JSONUtils.sendJSON(appResult);
	}
	
	
	/**
	 * 获取首页疑难杂症医生列表接口
	 */
	public void  getSpecDoctorList(){
		appResult = this.doctorRecommService.getSpecDoctorList();
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
	public DoctorRecomm getModel() {
		return doctorRecomm;
	}
	

}
