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
import com.yst.web.model.Balance;
import com.yst.web.model.ExpressOrderInfo;
import com.yst.web.service.BalanceService;
import com.yst.web.service.ProductOrderService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;

@Controller("expressAction")
@Scope("prototype")
public class ExpressOrderInfoAction extends BaseAction {
	@Resource(name="productOrderService")
	private ProductOrderService productOrderService;
	private ExpressOrderInfo expressOrderInfo= new ExpressOrderInfo();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	public String error(){
		System.out.println("error");
		return "error";
	}
	public void sendGoods(){
		appResult = this.productOrderService.updateSendGoods(expressOrderInfo);
		JSONUtils.sendJSON(appResult);
	}
	public void getExpressInfo(){
		appResult = this.productOrderService.getExpressInfo(expressOrderInfo);
		JSONUtils.sendJSON(appResult);
	}
	
	public void sendAjax(){
		appResult = this.productOrderService.updateSendGoods(expressOrderInfo);
		JSONUtils.sendJSON(appResult);
	}
	@Override
	public String execute() {
		return LIST;
	}
	@Override
	public ExpressOrderInfo getModel() {
		// TODO Auto-generated method stub
		return expressOrderInfo;
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
	
}
