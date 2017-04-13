package com.yst.web.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.CustomerAddress;
import com.yst.web.service.CustomerAddressService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
@Controller("customerAddressAction")
@Scope("prototype")
public class CustomerAddressAction extends BaseAction{

	private static final long serialVersionUID = -73430620720086763L;
	
	@Resource(name="customerAddressService")
	private CustomerAddressService customerAddressService;
	
	AppResult appResult = new AppResult();
	CustomerAddress customerAddress = new CustomerAddress();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	//以下为业务接口
	public void getCustomerAddressList(){
		appResult = this.customerAddressService.findCustomerAddressList(customerAddress.getCustomer_id());
		JSONUtils.sendJSON(appResult);
	}
	
	public void addCustomerAddress(){
		appResult = this.customerAddressService.addCustomerAddress(customerAddress);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public void updateCustomerAddress(){
		appResult = this.customerAddressService.updateCustomerAddress(customerAddress);
		JSONUtils.sendJSON(appResult);
	}
	public void deleteCustomerAddress(){
		appResult = this.customerAddressService.deleteCustomerAddress(customerAddress);
		JSONUtils.sendJSON(appResult);
	}
	public void saveCustomerAddress(){
		appResult = this.customerAddressService.saveCustomerAddress(customerAddress);
		JSONUtils.sendJSON(appResult);
	}
	public void getAddressInfo(){
		appResult = this.customerAddressService.getAddressInfo(customerAddress);
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
	public CustomerAddress getModel() {
		return customerAddress;
	}

}
