package com.yst.web.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.CustomerRelative;
import com.yst.web.service.CustomerIllnessRecordService;
import com.yst.web.service.CustomerRelativeService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
@Controller("customerRelativeAction")
@Scope("prototype")
public class CustomerRelativeAction extends BaseAction{

	private static final long serialVersionUID = 5057871577433160995L;
	@Resource(name = "customerRelativeService")
	private CustomerRelativeService customerRelativeService;
	@Resource(name = "customerIllnessRecordService")
	private CustomerIllnessRecordService customerIllnessRecordService;
	AppResult appResult = new AppResult();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	CustomerRelative customerRelative = new CustomerRelative();
	
	
	/**
	 * 添加相关人员接口
	 */
	public void addCustomerRelative(){
		appResult = this.customerRelativeService.addCustomerRelative(customerRelative);
		JSONUtils.sendJSON(appResult);
	}
	
	
	/**
	 * 获取相关人员列表
	 */
	public void getRelativeList(){
		appResult = this.customerRelativeService.selectByParame(customerRelative);
		JSONUtils.sendJSON(appResult);
	}
	
	/**
	 * 删除相关人员信息
	 */
	public void deleteRelative(){
		Integer customer_id =customerRelative.getCustomer_id();
		Integer relative_id = customerRelative.getRelative_id();
		if(customer_id!=null && !"".equals(customer_id) && relative_id!=null && !"".equals(relative_id)){
			//this.customerIllnessRecordService.deleteByParame(relative_id);
			appResult = this.customerRelativeService.deleteRelative(customer_id, relative_id);
		}else{
			appResult.setError_info("客户id或关系id不能为空");
			appResult.setData("");
		}
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
	public CustomerRelative getModel() {
		return customerRelative;
	}

}
