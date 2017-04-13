package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.ServiceDesc;
import com.yst.web.model.ServiceDesc;
import com.yst.web.service.ServiceDescService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
@Controller("serviceDescAction")
@Scope("prototype")
public class ServiceDescAction extends BaseAction{

	private static final long serialVersionUID = -7366781121441417196L;
	@Resource(name = "serviceDescService")
	private ServiceDescService serviceDescService;
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	ServiceDesc serviceDesc = new ServiceDesc();
	
	


	/**
	 * 获取服务描述
	 */
	public void getServiceDescInfoByType(){
		appResult = this.serviceDescService.findServiceDescByType(serviceDesc.getType());
		JSONUtils.sendJSON(appResult);
	}
	
	
	public void listAjax(){
		List<ServiceDesc> list  = this.serviceDescService.queryList();
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
		BeanUtils.getBase64Image(serviceDesc);//转换image为base64字符串
		appResult = this.serviceDescService.addServiceDesc(serviceDesc);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String updatePage(){
		serviceDesc = this.serviceDescService.findServiceDescInfo(serviceDesc.getId());
		return "update_page";
	}
	
	public void updateAjax(){
		BeanUtils.getBase64Image(serviceDesc);//转换image为base64字符串
		appResult = this.serviceDescService.updateServiceDesc(serviceDesc);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String deleteAjax(){
		this.serviceDescService.deleteServiceDesc(serviceDesc.getId());
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
	public ServiceDesc getModel() {
		return serviceDesc;
	}
}
