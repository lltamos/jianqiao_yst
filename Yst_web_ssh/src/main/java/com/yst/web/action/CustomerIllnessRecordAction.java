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
import com.yst.web.model.CustomerIllnessRecord;
import com.yst.web.model.CustomerRelative;
import com.yst.web.service.CustomerIllnessRecordService;
import com.yst.web.service.CustomerRelativeService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
@Controller("customerIllnessRecordAction")
@Scope("prototype")
public class CustomerIllnessRecordAction extends BaseAction{

	private static final long serialVersionUID = -7624221138823971686L;
	@Resource(name = "customerIllnessRecordService")
	private CustomerIllnessRecordService customerIllnessRecordService;
	@Resource(name = "customerRelativeService")
	private CustomerRelativeService customerRelativeService;
	AppResult appResult = new AppResult();
	CustomerIllnessRecord customerIllnessRecord = new CustomerIllnessRecord();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	
	public String showPage(){
		customerIllnessRecord = this.customerIllnessRecordService.findCustomerIllnessRecordInfo(customerIllnessRecord.getRecord_id());
		customerIllnessRecord.setStr_relative(customerIllnessRecord.getCustomerRelative().getName());
		return "show_page";
	}
	
	public String addPage(){
		List<CustomerRelative> customerRelativeList = this.customerRelativeService.selectAll();
		ActionContext.getContext().put("customerRelativeList", customerRelativeList);
		return "add_page";
	}
	
	public String updatePage(){
		List<CustomerRelative> customerRelativeList = this.customerRelativeService.selectAll();
		ActionContext.getContext().put("customerRelativeList", customerRelativeList);
		customerIllnessRecord = this.customerIllnessRecordService.findCustomerIllnessRecordInfo(customerIllnessRecord.getRecord_id());
		return "update_page";
	}
	
	public void updateAjax(){
		appResult = this.customerIllnessRecordService.updateCustomerIllnessRecord(customerIllnessRecord);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public void addAjax(){
		BeanUtils.getBase64Image(customerIllnessRecord);// 转换image为base64字符串
		appResult = this.customerIllnessRecordService.addIllnessRecord(customerIllnessRecord);
		JSONUtils.sendJSON(appResult);
		
	}
	
	
	public void listAjax(){
		List<CustomerIllnessRecord> customerIllnessRecord = this.customerIllnessRecordService.findCustomerIllnessRecordList();
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(customerIllnessRecord);
		JSONUtils.sendJSON(appResult);
	}
	
	/**
	 * 删除病例记录
	 */
	public String deleteAjax(){
		appResult = this.customerIllnessRecordService.deleteIllnessRecord(customerIllnessRecord.getRecord_id());
		JSONUtils.sendJSON(appResult);
		return SUCCESS;
	}
	
	
	//下面为业务模块接口
	/**
	 * 获取简单列表接口
	 */
	public void getRecordList(){
		appResult = this.customerIllnessRecordService.queryRecordList(customerIllnessRecord);
		JSONUtils.sendJSON(appResult);
	}
	
	/**
	 * 详情接口
	 */
	public void details(){
		appResult = this.customerIllnessRecordService.findDetails(customerIllnessRecord.getRecord_id());
		JSONUtils.sendJSON(appResult);
	}
	
	/**
	 * 添加病例接口
	 */
	public void addIllnessRecord(){
		appResult = this.customerIllnessRecordService.addIllnessRecord(customerIllnessRecord);
		JSONUtils.sendJSON(appResult);
	}
	/**
	 * 删除病例的接口
	 */
	public void deleteIllnessRecord(){
		appResult = this.customerIllnessRecordService.deleteIllnessRecord(customerIllnessRecord.getRecord_id());
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
		this.response =response; 
	}

	@Override
	public CustomerIllnessRecord getModel() {
		return customerIllnessRecord;
	}

}
