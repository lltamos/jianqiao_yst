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
import com.yst.web.model.CustomerRelative;
import com.yst.web.model.RelativeMedicineRecord;
import com.yst.web.service.CustomerRelativeService;
import com.yst.web.service.RelativeMedicineRecordService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.CommUtils;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
@Controller("relativeMedicineRecordAction")
@Scope("prototype")
public class RelativeMedicineRecordAction extends BaseAction{
	
	private static final long serialVersionUID = 998972372128198977L;
	@Resource(name = "relativeMedicineRecordService")
	private RelativeMedicineRecordService relativeMedicineRecordService;
	@Resource(name = "customerRelativeService")
	private CustomerRelativeService customerRelativeService;
	AppResult appResult = new AppResult();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	RelativeMedicineRecord relativeMedicineRecord = new RelativeMedicineRecord();
	
	
	
	
	public void listAjax(){
		List<RelativeMedicineRecord> relativeMedicineRecordList = this.relativeMedicineRecordService.findRelativeMedicineRecordList();
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(relativeMedicineRecordList);
		JSONUtils.sendJSON(appResult);
	}
	
	public String showPage(){
		relativeMedicineRecord = this.relativeMedicineRecordService.findRelativeMedicineRecordInfo(relativeMedicineRecord.getRecord_id());
		relativeMedicineRecord.setStr_relative(relativeMedicineRecord.getCustomerRelative().getName());
		return "show_page";
	}
	
	public String addPage(){
		List<CustomerRelative> customerRelativeList = this.customerRelativeService.selectAll();
		ActionContext.getContext().put("customerRelativeList", customerRelativeList);
		return "add_page";
	}
	
	
	public void addAjax(){
		appResult = this.relativeMedicineRecordService.addRelativeMedicineRecord(relativeMedicineRecord);
		JSONUtils.sendJSON(appResult);
	}
	
	
	
	public String updatePage(){
		List<CustomerRelative> customerRelativeList = this.customerRelativeService.selectAll();
		ActionContext.getContext().put("customerRelativeList", customerRelativeList);
		relativeMedicineRecord = this.relativeMedicineRecordService.findRelativeMedicineRecordInfo(relativeMedicineRecord.getRecord_id());
		return "update_page";
	}
	
	public void updateAjax(){
		appResult = this.relativeMedicineRecordService.updateRelativeMedicineRecord(relativeMedicineRecord);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String deleteAjax(){
		appResult = this.relativeMedicineRecordService.deleteRelativeMedicineRecord(relativeMedicineRecord.getRecord_id());
		JSONUtils.sendJSON(appResult);
		return SUCCESS;
	}
	
	
	
	//下面为接口
	/**
	 * 添加相关人员的用药记录主信息
	 */
	public void addRelativeMedicineRecord(){

		CommUtils.printRequest(request);
		String relative_id =request.getParameter("relative_id");
		System.out.println(relative_id);
		appResult = this.relativeMedicineRecordService.addRelativeMedicineRecord(relativeMedicineRecord);
		JSONUtils.sendJSON(appResult);
	}
	
	/**
	 * 获取相关人员的用药记录列表
	 */
	public void getRelativeMedicineRecordList(){
		appResult = this.relativeMedicineRecordService.getRelativeMedicineRecord(relativeMedicineRecord.getRelative_id());
		JSONUtils.sendJSON(appResult);
	}
	
	/**
	 * 删除相关人员用药记录
	 */
	public void deleteRelativeMedicineRecord(){
		appResult = this.relativeMedicineRecordService.deleteRelativeMedicineRecord(relativeMedicineRecord.getRecord_id());
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
	public RelativeMedicineRecord getModel() {
		return relativeMedicineRecord;
	}

}
