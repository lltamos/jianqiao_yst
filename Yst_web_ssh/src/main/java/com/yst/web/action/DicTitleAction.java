package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.DicTitle;
import com.yst.web.service.DicTitleService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;

@Controller("dicTitleAction")
@Scope("prototype")
public class DicTitleAction extends BaseAction{

	private static final long serialVersionUID = -7231067026492827791L;

	@Resource(name = "dicTitleService")
	private DicTitleService dicTitleService;
	
	private DicTitle dicTitle = new DicTitle();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	
	
	public void listAjax(){
		List<DicTitle> list  = this.dicTitleService.queryList();
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
		appResult = this.dicTitleService.addDicTitle(dicTitle);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String updatePage(){
		dicTitle = this.dicTitleService.findDicTitleInfo(dicTitle.getTitle_id());
		return "update_page";
	}
	
	public void updateAjax(){
		appResult = this.dicTitleService.updateDicTitle(dicTitle);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String deleteAjax(){
		this.dicTitleService.deleteDicTitle(dicTitle.getTitle_id());
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
	public DicTitle getModel() {
		return dicTitle;
	}
}
