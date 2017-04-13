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
import com.yst.web.model.Merchant;
import com.yst.web.model.Store;
import com.yst.web.service.MerchantService;
import com.yst.web.service.StoreService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.CommUtils;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;

@Controller("storeAction")
@Scope("prototype")
public class StoreAction extends BaseAction {
	@Resource(name="storeService")
	private StoreService storeService;
	@Resource(name="merchantService")
	private MerchantService merchantService;
	private Store store= new Store();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	public String add(){
		this.storeService.add(store);
		return SUCCESS;
	}
	
	public String delete(){
		this.storeService.deleteById(store.getStore_id());
		return SUCCESS;
	}
	
	public String updatePage(){
		store=this.storeService.findById(store.getStore_id());
		List<Merchant> merchants =this.merchantService.selectAll();
		ActionContext.getContext().put("merchants", merchants);
		return "update_page";
	}
	public String addPage(){
		List<Merchant> merchants =this.merchantService.selectAll();
		ActionContext.getContext().put("merchants", merchants);
		return "add_page";
	}
	public String update(){
		this.storeService.update(store);
		return SUCCESS;
	}
	
	public String error(){
		System.out.println("error");
		return "error";
	}
	
	
	@Override
	public String execute() {
		return LIST;
	}
	@Override
	public Store getModel() {
		// TODO Auto-generated method stub
		return store;
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
	

	//以下方法接口业务
	public void reg(){
		CommUtils.printRequest(request);
		appResult = this.storeService.reg(store);
		JSONUtils.sendJSON(appResult);
		
	}
	
	public void getStoreList(){
		appResult = this.storeService.getStoreList(store);
		JSONUtils.sendJSON(appResult);
	}
	
	public void getInfo(){
		appResult = this.storeService.getInfo(store);
		JSONUtils.sendJSON(appResult);
	}
	
	//页面ajax
	public void listAjax(){
		List<Store> stores = this.storeService.selectAllByPage();
		setData(stores);
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(stores);
		JSONUtils.sendJSON(appResult);
	}
	
	public void addAjax() {
		appResult = this.storeService.reg(store);
		JSONUtils.sendJSON(appResult);
	}
	
	public void updateAjax() {
		appResult = this.storeService.updateInfo(store);
		JSONUtils.sendJSON(appResult);
	}

	public void updateInfo() {
		appResult = this.storeService.updateInfo(store);
		JSONUtils.sendJSON(appResult);
	}
	public void setData(List<Store> stores) {
		for (Store store : stores) {// 手动添加data...
			Merchant merchant = store.getMerchant();
			store.setMerchant_name(merchant.getName());
		}
	}
	
}
