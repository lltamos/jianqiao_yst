package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.ProductType;
import com.yst.web.service.ProductTypeService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
@Controller("productTypeAction")
@Scope("prototype")
public class ProductTypeAction extends BaseAction{
	
	private static final long serialVersionUID = -1348174430822062554L;
	
	@Resource(name = "productTypeService")
	private ProductTypeService productTypeService;
	
	private ProductType productType = new ProductType();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	
	
	public void listAjax(){
		List<ProductType> list  = this.productTypeService.queryList();
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
		appResult = this.productTypeService.addProductType(productType);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String updatePage(){
		productType = this.productTypeService.findProductTypeInfo(productType.getProduct_type_id());
		return "update_page";
	}
	
	public void updateAjax(){
		appResult = this.productTypeService.updateProductType(productType);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String deleteAjax(){
		this.productTypeService.deleteProductType(productType.getProduct_type_id());
		return SUCCESS;
	}
	
	//以下为接口业务
	public void getProductTypeList(){
		appResult = this.productTypeService.getProductTypeList();
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
	public ProductType getModel() {
		return productType;
	}

}
