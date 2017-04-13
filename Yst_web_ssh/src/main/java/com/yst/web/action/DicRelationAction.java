package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.DicRelation;
import com.yst.web.service.DicRelationService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
@Controller("dicRelationAction")
@Scope("prototype")
public class DicRelationAction extends BaseAction{


	private static final long serialVersionUID = 7141986203978220977L;

	@Resource(name = "dicRelationService")
	private DicRelationService dicRelationService;
	
	private DicRelation dicRelation = new DicRelation();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	
	
	public void listAjax(){
		List<DicRelation> list  = this.dicRelationService.queryList();
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
		appResult = this.dicRelationService.addDicRelation(dicRelation);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String updatePage(){
		dicRelation = this.dicRelationService.findDicRelationInfo(dicRelation.getRelation_id());
		return "update_page";
	}
	
	public void updateAjax(){
		appResult = this.dicRelationService.updateDicRelation(dicRelation);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String deleteAjax(){
		this.dicRelationService.deleteDicRelation(dicRelation.getRelation_id());
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
	public DicRelation getModel() {
		return dicRelation;
	}
}
