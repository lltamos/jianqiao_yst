package com.yst.web.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorCallInfo;
import com.yst.web.service.DoctorCallInfoService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
@Controller("doctorCallInfoAction")
@Scope("prototype")
public class DoctorCallInfoAction extends BaseAction{

	
	private static final long serialVersionUID = 2579992670980935227L;
	
	@Resource(name = "doctorCallInfoService")
	private DoctorCallInfoService doctorCallInfoService;
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	DoctorCallInfo doctorCallInfo = new DoctorCallInfo();
	
	
	//以下为业务接口
	/**
	 * 添加电话呼叫记录
	 */
//	public void addDoctorCallInfo(){
//		appResult = this.doctorCallInfoService.addDoctorCallInfoInfo(doctorCallInfo);
//		JSONUtils.sendJSON(appResult);
//	}
	/**
	 * 获取电话呼叫记录列表接口
	 */
//	public void getDoctorCallInfoList(){
//		appResult = this.doctorCallInfoService.getDoctorCallInfoList(doctorCallInfo.getOrder_id());
//		JSONUtils.sendJSON(appResult);
//	}
	
	/**
	 * 修改剩余通话时长接口
	 */
	public void getRemainCallTime(){
		appResult = this.doctorCallInfoService.saveOrUpdateRemianCallTime(doctorCallInfo);
		JSONUtils.sendJSON(appResult);
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request= request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public DoctorCallInfo getModel() {
		return doctorCallInfo;
	}
}
