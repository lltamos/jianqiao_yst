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
import com.yst.web.service.BalanceService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;

@Controller("balanceAction")
@Scope("prototype")
public class BalanceAction extends BaseAction {
	@Resource(name="balanceService")
	private BalanceService balanceService;
	private Balance balance= new Balance();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	public String add(){
		this.balanceService.add(balance);
		return SUCCESS;
	}
	
	public String delete(){
		this.balanceService.deleteById(balance.getBalance_id());
		return SUCCESS;
	}
	
	public String updatePage(){
		balance=this.balanceService.findById(balance.getBalance_id());
		return "update_page";
	}
	public String update(){
		this.balanceService.update(balance);
		return SUCCESS;
	}
	
	public String error(){
		System.out.println("error");
		return "error";
	}
	
	
	@Override
	public String execute() {
		List<Balance> balances =this.balanceService.selectAll();
		ActionContext.getContext().put("balances", balances);
		return LIST;
	}
	@Override
	public Balance getModel() {
		// TODO Auto-generated method stub
		return balance;
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
