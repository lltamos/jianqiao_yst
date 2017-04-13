package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.DicServiceType;
import com.yst.web.service.DicServiceTypeService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
@Controller("dicServiceTypeAction")
@Scope("prototype")
public class DicServiceTypeAction extends BaseAction{

	private static final long serialVersionUID = 7200378669998294522L;

	@Resource(name = "dicServiceTypeService")
	private DicServiceTypeService dicServiceTypeService;
	
	private DicServiceType dicServiceType = new DicServiceType();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	
	
	public void listAjax(){
		List<DicServiceType> list  = this.dicServiceTypeService.queryList();
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
		appResult = this.dicServiceTypeService.addDicServiceType(dicServiceType);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String updatePage(){
		dicServiceType = this.dicServiceTypeService.findDicServiceTypeInfo(dicServiceType.getService_type_id());
		return "update_page";
	}
	
	public void updateAjax(){
		appResult = this.dicServiceTypeService.updateDicServiceType(dicServiceType);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String deleteAjax(){
		this.dicServiceTypeService.deleteDicServiceType(dicServiceType.getService_type_id());
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
	public DicServiceType getModel() {
		return dicServiceType;
	}
}
