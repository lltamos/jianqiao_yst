package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.DicHospitalType;
import com.yst.web.service.DicHospitalTypeService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
@Controller("dicHospitalTypeAction")
@Scope("prototype")
public class DicHospitalTypeAction extends BaseAction{
	
	private static final long serialVersionUID = -1348174430822062554L;
	
	@Resource(name = "dicHospitalTypeService")
	private DicHospitalTypeService dicHospitalTypeService;
	
	private DicHospitalType dicHospitalType = new DicHospitalType();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	
	
	public void listAjax(){
		List<DicHospitalType> list  = this.dicHospitalTypeService.queryList();
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
		appResult = this.dicHospitalTypeService.addDicHospitalType(dicHospitalType);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String updatePage(){
		dicHospitalType = this.dicHospitalTypeService.findDicHospitalTypeInfo(dicHospitalType.getHospital_type_id());
		return "update_page";
	}
	
	public void updateAjax(){
		appResult = this.dicHospitalTypeService.updateDicHospitalType(dicHospitalType);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String deleteAjax(){
		this.dicHospitalTypeService.deleteDicHospitalType(dicHospitalType.getHospital_type_id());
		return SUCCESS;
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
	public DicHospitalType getModel() {
		return dicHospitalType;
	}

}
