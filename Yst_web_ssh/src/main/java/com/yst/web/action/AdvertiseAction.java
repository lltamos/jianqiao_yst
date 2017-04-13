package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yst.web.model.Advertise;
import com.yst.web.model.AppResult;
import com.yst.web.model.Store;
import com.yst.web.service.AdvertiseService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.MD5;
import com.yst.web.utils.PageModelContext;

@Controller("advertiseAction")
@Scope("prototype")
public class AdvertiseAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = "advertiseService")
	private AdvertiseService advertiseService;
	private Advertise advertise = new Advertise();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();

	public String add() {
		this.advertiseService.add(advertise);
		return SUCCESS;
	}

	public String delete() {
		this.advertiseService.deleteById(advertise.getAdv_id());
		return SUCCESS;
	}

	public String updatePage() {
		advertise = this.advertiseService.findById(advertise.getAdv_id());
		return "update_page";
	}
	public String addPage(){
		return "add_page";
	}
	public String update() {
		this.advertiseService.update(advertise);
		return SUCCESS;
	}

	public String error() {
		System.out.println("error");
		return "error";
	}

	@Override
	public String execute() {
		return LIST;
	}

	@Override
	public Advertise getModel() {
		// TODO Auto-generated method stub
		return advertise;
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

	// 以下方法接口业务
	public void getAdvList() {
		appResult = this.advertiseService.getAdvList(advertise);
		JSONUtils.sendJSON(appResult);
	}

	public void getInfo() {
		appResult = this.advertiseService.getInfo(advertise);
		JSONUtils.sendJSON(appResult);
	}

	// 页面ajax
	public void listAjax() {
		List<Advertise> stores = this.advertiseService.selectAllByPage();
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(stores);
		JSONUtils.sendJSON(appResult);
	}
	public void addAjax() {
		BeanUtils.getBase64Image(advertise);// 转换image为base64字符串
		appResult = this.advertiseService.add(advertise);
		JSONUtils.sendJSON(appResult);
	}
	
	public void updateAjax() {
		BeanUtils.getBase64Image(advertise);// 转换image为base64字符串
		appResult = this.advertiseService.updateInfo(advertise);
		JSONUtils.sendJSON(appResult);
	}
}
