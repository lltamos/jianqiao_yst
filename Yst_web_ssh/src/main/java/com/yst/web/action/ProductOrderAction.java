package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.Merchant;
import com.yst.web.model.Product;
import com.yst.web.model.ProductOrder;
import com.yst.web.service.ProductOrderService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.BootStrapResult;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;

@Controller("productOrderAction")
@Scope("prototype")
public class ProductOrderAction extends BaseAction {
	@Resource(name="productOrderService")
	private ProductOrderService productOrderService;
	private ProductOrder productOrder= new ProductOrder();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	
	public String add(){
		this.productOrderService.add(productOrder);
		return SUCCESS;
	}
	
	public String delete(){
		this.productOrderService.deleteById(productOrder.getProduct_order_id());
		return SUCCESS;
	}
	public String disable(){
		this.productOrderService.update(productOrder);
		return LIST;
	}
	public String sendPage(){
		productOrder=this.productOrderService.findById(productOrder.getProduct_order_id());
		return "send_page";
	}
	public String update(){
		this.productOrderService.update(productOrder);
		return SUCCESS;
	}
	
	public String error(){
		System.out.println("error");
		return "error";
	}
	/**
	 * 查询医生疑难杂症订单分润列表
	 */
	public void findProductOrderList(){
		BootStrapResult<List<Map<String, Object>>> br = this.productOrderService.findProductOrderList(request);
		JSONUtils.sendJSON(br);
	}
	
	
	@Override
	public String execute() {
		return LIST;
	}
	@Override
	public ProductOrder getModel() {
		return productOrder;
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
	//以下为业务接口
	
	public void addOrder(){
		appResult = this.productOrderService.addOrder(productOrder,request);
		JSONUtils.sendJSON(appResult);
	}
	
	public void getMerchantList(){
		appResult= this.productOrderService.getMerchantList(productOrder);
		JSONUtils.sendJSON(appResult);
	}
	
	public void getInfo(){
		appResult = this.productOrderService.getInfo(productOrder);
		JSONUtils.sendJSON(appResult);
	}
	public void submitPay(){
		appResult = this.productOrderService.updatePay(productOrder);
		JSONUtils.sendJSON(appResult);
	}
	//ajax
	public void listAjax() {
		List<ProductOrder> products = this.productOrderService.selectAllByPage();
		setData(products);
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(products);
		JSONUtils.sendJSON(appResult);
	}
	
	public void setData(List<ProductOrder> products) {
		for (ProductOrder productOrder : products) {// 手动添加data...
			Merchant merchant = productOrder.getMerchant();
			Customer customer = productOrder.getCustomer();
			Product product = productOrder.getProduct();
			Customer recomm=product.getRecomm_customer();
			if(recomm!=null){
				productOrder.setProduct_recomm_phone(recomm.getName());
			}
			productOrder.setCustomer_name(customer.getName());
			productOrder.setCustomer_phone(customer.getPhone());
			productOrder.setMerchant_name(merchant.getName());
			productOrder.setProduct_name(product.getName());
		}
	}

	public void setData(ProductOrder productOrder) {
		Merchant merchant = productOrder.getMerchant();
		Customer customer = productOrder.getCustomer();
		Product product = productOrder.getProduct();
		Customer recomm=product.getRecomm_customer();
		if(recomm!=null){
			productOrder.setProduct_recomm_phone(recomm.getName());
		}
		productOrder.setCustomer_name(customer.getName());
		productOrder.setCustomer_phone(customer.getPhone());
		productOrder.setMerchant_name(merchant.getName());
		productOrder.setProduct_name(product.getName());
	}
}
