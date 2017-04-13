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
import com.yst.web.model.BalanceTrans;
import com.yst.web.service.BalanceTransService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;

@Controller("balanceTransAction")
@Scope("prototype")
public class BalanceTransAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name="balanceTransService")
	private BalanceTransService balanceTransService;
	private BalanceTrans balanceTrans= new BalanceTrans();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	public String add(){
		this.balanceTransService.add(balanceTrans);
		return SUCCESS;
	}
	
	public String delete(){
		this.balanceTransService.deleteById(balanceTrans.getCustomer_id());
		return SUCCESS;
	}
	
	public String updatePage(){
		balanceTrans=this.balanceTransService.findById(balanceTrans.getCustomer_id());
		return "update_page";
	}
	public String update(){
		this.balanceTransService.update(balanceTrans);
		return SUCCESS;
	}
	
	public String error(){
		System.out.println("error");
		return "error";
	}
	
	
	@Override
	public String execute() {
		List<BalanceTrans> balanceTranss =this.balanceTransService.selectAll();
		ActionContext.getContext().put("balanceTrans", balanceTrans);
		return LIST;
	}
	@Override
	public BalanceTrans getModel() {
		// TODO Auto-generated method stub
		return balanceTrans;
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
	
	
	//以下为接口业务
	public void updateBalance(){
		appResult = this.balanceTransService.updateBalance(balanceTrans,response,request);
		JSONUtils.sendJSON(appResult);
	}
	
	public void getBalanceList(){
		appResult = this.balanceTransService.getBalanceList(balanceTrans);
		JSONUtils.sendJSON(appResult);
		
	}
	public void getBalance(){
		appResult = this.balanceTransService.getBalance(balanceTrans);
		JSONUtils.sendJSON(appResult);
	}
	public void submitBalancePay(){
		appResult = this.balanceTransService.updateBalancePay(balanceTrans);
		JSONUtils.sendJSON(appResult);
	}
}
