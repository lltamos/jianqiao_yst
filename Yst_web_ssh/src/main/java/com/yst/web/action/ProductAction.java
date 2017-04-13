package com.yst.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.LocProv;
import com.yst.web.model.Merchant;
import com.yst.web.model.Product;
import com.yst.web.model.ProductType;
import com.yst.web.model.ServiceDesc;
import com.yst.web.service.ILocCityService;
import com.yst.web.service.ILocProvService;
import com.yst.web.service.MerchantService;
import com.yst.web.service.ProductService;
import com.yst.web.service.ProductTypeService;
import com.yst.web.service.ServiceDescService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ServerParam;

@Controller("productAction")
@Scope("prototype")
public class ProductAction extends BaseAction {
	private static Log logger = LogFactory.getLog(ProductAction.class);
	@Resource(name = "productService")
	private ProductService productService;
	@Resource(name = "merchantService")
	private MerchantService merchantService;
	@Resource(name = "productTypeService")
	private ProductTypeService productTypeService;
	@Resource(name = "serviceDescService")
	private ServiceDescService serviceDescService;
	@Resource(name = ILocProvService.DINAME)
	private ILocProvService locProvService;
	// 市
	@Resource(name = ILocCityService.DINAME)
	private ILocCityService locCityService;
	private Product product = new Product();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();

	public String add() {
		this.productService.add(product);
		return SUCCESS;
	}
	public String indexPage() {
		return "index_page";
	}
	public String wapPage() {
		List<LocProv> provList = this.locProvService.selectAll();
		ActionContext.getContext().put("provList", provList);
		List<ProductType> productTypeList =this.productTypeService.selectAll();
		ActionContext.getContext().put("productTypeList", productTypeList);
		appResult=this.productService.getAllList(product);
		ActionContext.getContext().put("appResult", appResult);
		return "wap_page";
	}
	public String delete() {
		this.productService.deleteById(product.getProduct_id());
		return SUCCESS;
	}

	public String disable() {
		this.productService.updateInfo(product);
		return SUCCESS;
	}
	public String productPage() {
		List<ProductType> productTypeList =this.productTypeService.selectAll();
		ActionContext.getContext().put("productTypeList", productTypeList);
		product =this.productService.findById(product.getProduct_id());
		ActionContext.getContext().put("product", product);
		List<ServiceDesc> serviceDescList =this.serviceDescService.queryList();
		ActionContext.getContext().put("serviceDescList", serviceDescList);
		return "product_page";
	}
	public String productListPage() {
		List<LocProv> provList = this.locProvService.selectAll();
		ActionContext.getContext().put("provList", provList);
		List<ProductType> productTypeList =this.productTypeService.selectAll();
		ActionContext.getContext().put("productTypeList", productTypeList);
		appResult =this.productService.selectProductList(product,request);
		ActionContext.getContext().put("appResult", appResult);
		List<ServiceDesc> serviceDescList =this.serviceDescService.selectAll();
		ActionContext.getContext().put("serviceDescList", serviceDescList);
		return "product_list_page";
	}
	public String updatePage() {
		product = this.productService.findById(product.getProduct_id());
		List<Merchant> merchants = this.merchantService.selectAll();
		ActionContext.getContext().put("merchants", merchants);
		List<ProductType> productTypes = this.productTypeService.selectAll();
		ActionContext.getContext().put("productTypes", productTypes);
		return "update_page";
	}

	public String showPage() {
		product = this.productService.findById(product.getProduct_id());
		setData(product);
		return "show_page";
	}
	public String infoPage(){
		appResult = this.productService.updateGetInfo(product);
		ActionContext.getContext().put("appResult", appResult);
		return "info_page";
	}
	public String addPage() {
		List<Merchant> merchants = this.merchantService.selectAll();
		ActionContext.getContext().put("merchants", merchants);
		List<ProductType> productTypes = this.productTypeService.selectAll();
		ActionContext.getContext().put("productTypes", productTypes);
		return "add_page";
	}

	public String appPage() {
		product = this.productService.findById(product.getProduct_id());
		setData(product);
		return "app_page";
	}

	public String update() {
		this.productService.update(product);
		return SUCCESS;
	}

	public String error() {
		return "error";
	}

	@Override
	public String execute() {
		return LIST;
	}

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
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

	// 以下方法接口业务
	public void reg() {
		appResult = this.productService.reg(product);
		JSONUtils.sendJSON(appResult);

	}

	public void getProductList() {
		appResult = this.productService.getProductList(product);
		JSONUtils.sendJSON(appResult);
	}

	public void getInfo() {
		appResult = this.productService.updateGetInfo(product);
		JSONUtils.sendJSON(appResult);
	}

	public void deleteProduct() {
		appResult = this.productService.deleteProduct(product);
		JSONUtils.sendJSON(appResult);
	}

	public void getAllList() {
		appResult = this.productService.getAllList(product);
		JSONUtils.sendJSON(appResult);
	}

	// 页面ajax
	public void listAjax() {
		List<Product> products = this.productService.selectAllByPage();
		setData(products);
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(products);
		JSONUtils.sendJSON(appResult);
	}

	public void addAjax() {
		BeanUtils.getBase64Image(product);// 转换image为base64字符串
		appResult = this.productService.reg(product);
		JSONUtils.sendJSON(appResult);
	}

	public void updateAjax() {
		BeanUtils.getBase64Image(product);// 转换image为base64字符串
		appResult = this.productService.updateInfo(product);
		JSONUtils.sendJSON(appResult);
	}

	public void updateInfo() {
		appResult = this.productService.updateInfo(product);
		JSONUtils.sendJSON(appResult);
	}

	public void uploadFile() throws IOException {
		appResult = this.productService.uploadFile(product);
		PrintWriter out = response.getWriter();
		if (appResult.getResult().equals(AppResult.SUCCESS)) {
			String callback = product.getCKEditorFuncNum();
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback
					+ ",'" + appResult.getImg_urls().get("upload") + "','')");
			out.println("</script>");
		} else {
			out.print("<font color=\"red\" size=\"2\">"
					+ appResult.getError_info() + "</font>");
		}
	}

	public void getDetailPage() throws Exception {
		appResult = this.productService.getInfo(product);
		if (appResult.getResult().equals(AppResult.SUCCESS)) {
			String url = ((Product) appResult.getData()).getDetail_url();
			response.sendRedirect(ServerParam.DOMAIN + ServerParam.PROJECT_NAME
					+ url);
		} else {
			JSONUtils.sendJSON(appResult);
		}
	}

	// 初始化索引
	public void initIndex() {
		this.productService.initIndex();
	}

	public void testSearch() {
		this.productService.testSearch(product);
	}

	public void setData(List<Product> products) {
		for (Product product : products) {// 手动添加data...
			Customer recomm=product.getRecomm_customer();
			if(recomm!=null){
				product.setRecomm_phone(recomm.getPhone());
			}
			Merchant merchant = product.getMerchant();
			product.setMerchant_name(merchant.getName());
		}
	}

	public void setData(Product product) {
		Customer recomm=product.getRecomm_customer();
		if(recomm!=null){
			product.setRecomm_phone(recomm.getPhone());
		}
		Merchant merchant = product.getMerchant();
		product.setMerchant_name(merchant.getName());
	}
}
