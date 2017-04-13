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
import com.yst.web.model.Customer;
import com.yst.web.model.MerchantActivity;
import com.yst.web.model.Product;
import com.yst.web.service.ActivityService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.CommUtils;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ServerParam;

@Controller("merchantActivityAction")
@Scope("prototype")
public class MerchantActivityAction extends BaseAction {
	@Resource(name = "activityService")
	private ActivityService activityService;
	private MerchantActivity merchantActivity = new MerchantActivity();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	public String addPage(){
		Integer merchant_id = CommUtils.getMerchant_id(session);
		List<Product> products = this.activityService.getProductList(merchant_id);
		ActionContext.getContext().put("products", products);
		return "add_page";
	}
	public String updatePage(){
		Integer merchant_id = CommUtils.getMerchant_id(session);
		List<Product> products = this.activityService.getProductList(merchant_id);
		ActionContext.getContext().put("products", products);
		merchantActivity =this.activityService.findById(merchantActivity.getMerchant_activity_id());
		return "update_page";
	}
	public String delete(){
		this.activityService.deleteById(merchantActivity.getMerchant_activity_id());
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
	public MerchantActivity getModel() {
		// TODO Auto-generated method stub
		return merchantActivity;
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

	// 业务接口
	public void getActivityList() {
		appResult = this.activityService
				.getMerchantActivityList(merchantActivity);
		JSONUtils.sendJSON(appResult);
	}

	public void getActivityInfo() {
		appResult = this.activityService.getActivityInfo(merchantActivity);
		JSONUtils.sendJSON(appResult);
	}

	// 页面ajax
	public void listAjax() {
		Integer merchant_id = CommUtils.getMerchant_id(session);
		List<MerchantActivity> merchants = this.activityService.selectAllByPage(
			merchant_id, session);
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(merchants);
		JSONUtils.sendJSON(appResult);
	}
	
	public void addAjax() {
		Integer merchant_id = CommUtils.getMerchant_id(session);
		BeanUtils.getBase64Image(merchantActivity);// 转换image为base64字符串
		appResult = this.activityService.add(merchantActivity,merchant_id);
		JSONUtils.sendJSON(appResult);
	}

	public void updateAjax() {
		BeanUtils.getBase64Image(merchantActivity);// 转换image为base64字符串
		appResult = this.activityService.updateInfo(merchantActivity);
		JSONUtils.sendJSON(appResult);
	}
}
