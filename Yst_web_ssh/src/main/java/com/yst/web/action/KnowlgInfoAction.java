package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yst.web.model.AppResult;
import com.yst.web.model.KnowlgInfo;
import com.yst.web.model.KnowlgTag;
import com.yst.web.service.KnowlgInfoService;
import com.yst.web.service.KnowlgTagService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
@Controller("knowlgInfoAction")
@Scope("prototype")
public class KnowlgInfoAction extends BaseAction{

	private static final long serialVersionUID = -2736658452335000410L;

	@Resource(name="knowlgInfoService")
	private KnowlgInfoService knowlgInfoService;
	@Resource(name="knowlgTagService")
	private KnowlgTagService knowlgTagService;
	
	private KnowlgInfo knowlgInfo = new KnowlgInfo();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	
	
	public void listAjax(){
		List<KnowlgInfo> list  = this.knowlgInfoService.queryList();
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(list);
		JSONUtils.sendJSON(appResult);
	}
	
	public String addPage(){
		List<KnowlgTag> knowlgTagList = this.knowlgTagService.queryList();
		ActionContext.getContext().put("knowlgTagList", knowlgTagList);
		return "add_page";
	}
	
	
	public void addAjax(){
		appResult = this.knowlgInfoService.addKnowlgInfo(knowlgInfo);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String updatePage(){
		List<KnowlgTag> knowlgTagList = this.knowlgTagService.queryList();
		ActionContext.getContext().put("knowlgTagList", knowlgTagList);
		knowlgInfo = this.knowlgInfoService.findKnowlgInfoInfo(knowlgInfo.getId());
		return "update_page";
	}
	
	public void updateAjax(){
		appResult = this.knowlgInfoService.updateKnowlgInfo(knowlgInfo);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String deleteAjax(){
		this.knowlgInfoService.deleteKnowlgInfo(knowlgInfo.getId());
		return SUCCESS;
	}
	
	//以下为业务接口
	/**
	 * 获取知识库列表接口
	 */
	public void getKnowlgInfoList(){
		appResult = this.knowlgInfoService.getKnowlgInfoList(knowlgInfo);
		JSONUtils.sendJSON(appResult);
	}
	/**
	 * 获取知识库详情接口
	 */
	public void getKnowlgDetails (){
		appResult = this.knowlgInfoService.reKnowlgInfo(knowlgInfo.getId());
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
	public KnowlgInfo getModel() {
		return knowlgInfo;
	}
}
