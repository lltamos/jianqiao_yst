package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.Version;
import com.yst.web.service.VersionService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;

@Controller("versionAction")
@Scope("prototype")
public class VersionAction extends BaseAction {
	@Resource(name = "versionService")
	private VersionService versionService;
	private Version version = new Version();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();

	public String addPage() {
		return "add_page";
	}

	public String delete() {
		this.versionService.deleteById(version.getVersion_id());
		return SUCCESS;
	}

	// 页面ajax
	public void listAjax() {
		List<Version> versions = this.versionService.selectAllByPage();
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(versions);
		JSONUtils.sendJSON(appResult);
	}

	public void addAjax() {
		BeanUtils.getBase64Image(version);// 转换image为base64字符串
		appResult = this.versionService.updateInfo(version);
		JSONUtils.sendJSON(appResult);
	}

	public String error() {
		return "error";
	}
	public void getNewApp(){
		appResult = this.versionService.getNewApp(version);
		JSONUtils.sendJSON(appResult);
	}
	@Override
	public String execute() {
		return LIST;
	}

	@Override
	public Version getModel() {
		// TODO Auto-generated method stub
		return version;
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

}
