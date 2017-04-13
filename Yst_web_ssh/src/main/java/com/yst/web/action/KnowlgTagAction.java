package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.KnowlgTag;
import com.yst.web.service.KnowlgTagService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
@Controller("knowlgTagAction")
@Scope("prototype")
public class KnowlgTagAction extends BaseAction{

	private static final long serialVersionUID = 6508118954799892906L;


	@Resource(name="knowlgTagService")
	private KnowlgTagService knowlgTagService;
	
	
	private KnowlgTag knowlgTag = new KnowlgTag();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	
	
	public void listAjax(){
		List<KnowlgTag> list  = this.knowlgTagService.queryList();
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(list);
		JSONUtils.sendJSON(appResult);
	}
	
	//以下为业务接口
	/**
	 * 获取标签列表接口
	 */
	public void getKnowlgTagList(){
		appResult = this.knowlgTagService.getKnowlgTagList();
		JSONUtils.sendJSON(appResult);
	}
	
	
	
	public String addPage(){
		return "add_page";
	}
	
	
	public void addAjax(){
		appResult = this.knowlgTagService.addKnowlgTag(knowlgTag);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String updatePage(){
		knowlgTag = this.knowlgTagService.findKnowlgTagInfo(knowlgTag.getId());
		return "update_page";
	}
	
	public void updateAjax(){
		appResult = this.knowlgTagService.updateKnowlgTag(knowlgTag);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String deleteAjax(){
		this.knowlgTagService.deleteKnowlgTag(knowlgTag.getId());
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
	public KnowlgTag getModel() {
		return knowlgTag;
	}
}
