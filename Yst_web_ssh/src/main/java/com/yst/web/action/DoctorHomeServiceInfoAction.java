package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorHomeServiceInfo;
import com.yst.web.service.DoctorHomeServiceInfoService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
@Controller("doctorHomeServiceInfoAction")
@Scope("prototype")
public class DoctorHomeServiceInfoAction extends BaseAction{

	private static final long serialVersionUID = 8829878473114379135L;
	@Resource(name = "doctorHomeServiceInfoService")
	private DoctorHomeServiceInfoService doctorHomeServiceInfoService;
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	DoctorHomeServiceInfo doctorHomeServiceInfo = new DoctorHomeServiceInfo();

	
	public void listAjax(){
		List<DoctorHomeServiceInfo> list  = this.doctorHomeServiceInfoService.queryList();
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(list);
		JSONUtils.sendJSON(appResult);
	}
	
	public String addPage(){
		return "add_page";
	}
	
	
	public void addAjax(){
		appResult = this.doctorHomeServiceInfoService.addDoctorHomeServiceInfo(doctorHomeServiceInfo);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String updatePage(){
		doctorHomeServiceInfo = this.doctorHomeServiceInfoService.findDoctorHomeServiceInfoInfo(doctorHomeServiceInfo.getOrder_id());
		return "update_page";
	}
	
	public void updateAjax(){
		appResult = this.doctorHomeServiceInfoService.updateDoctorHomeServiceInfo(doctorHomeServiceInfo);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String deleteAjax(){
		this.doctorHomeServiceInfoService.deleteDoctorHomeServiceInfo(doctorHomeServiceInfo.getOrder_id());
		return SUCCESS;
	}
	
	
	
	
	//以下是业务接口
	
	/**
	 * 到家服务信息录入接口
	 */
	public void entryDoctorHomeServiceInfo(){
		appResult = this.doctorHomeServiceInfoService.addDoctorHomeServiceInfo(doctorHomeServiceInfo);
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
	public DoctorHomeServiceInfo getModel() {
		return doctorHomeServiceInfo;
	}

}
