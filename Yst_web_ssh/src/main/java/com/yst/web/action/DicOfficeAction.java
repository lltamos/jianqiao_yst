package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.DicOffice;
import com.yst.web.service.DicOfficeService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
@Controller("dicOfficeAction")
@Scope("prototype")
public class DicOfficeAction extends BaseAction{
	
	private static final long serialVersionUID = -1348174430822062554L;
	
	@Resource(name = "dicOfficeService")
	private DicOfficeService dicOfficeService;
	
	private DicOffice dicOffice = new DicOffice();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	
	
	public void listAjax(){
		List<DicOffice> list  = this.dicOfficeService.queryList();
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
		appResult = this.dicOfficeService.addDicOffice(dicOffice);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String updatePage(){
		dicOffice = this.dicOfficeService.findDicOfficeInfo(dicOffice.getOffice_id());
		return "update_page";
	}
	
	public void updateAjax(){
		appResult = this.dicOfficeService.updateDicOffice(dicOffice);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String deleteAjax(){
		this.dicOfficeService.deleteDicOffice(dicOffice.getOffice_id());
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
	public DicOffice getModel() {
		return dicOffice;
	}

}
