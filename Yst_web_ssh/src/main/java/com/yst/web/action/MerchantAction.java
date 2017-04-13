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
import com.yst.web.model.Merchant;
import com.yst.web.service.MerchantService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ServerParam;

@Controller("merchantAction")
@Scope("prototype")
public class MerchantAction extends BaseAction {
	@Resource(name = "merchantService")
	private MerchantService merchantService;
	private Merchant merchant = new Merchant();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();

	@Override
	public String execute() {
		return LIST;
	}

	public String delete() {
		this.merchantService.deleteById(merchant.getMerchant_id(),
				merchant.getDeleted());
		return SUCCESS;
	}

	public String updatePage() {
		merchant = this.merchantService.findById(merchant.getMerchant_id());
		return "update_page";
	}

	public String verifyPage() {
		merchant = this.merchantService.findById(merchant.getMerchant_id());
		return "verify_page";
	}

	public String update() {
		this.merchantService.update(merchant);
		return SUCCESS;
	}

	public String search() {
		List<Merchant> merchants = this.merchantService.selectByName(merchant);
		ActionContext.getContext().put("merchants", merchants);
		return LIST;
	}

	@Override
	public Merchant getModel() {
		// TODO Auto-generated method stub
		return merchant;
	}

	public String showPage() {
		merchant = this.merchantService.findById(merchant.getMerchant_id());
		return "show_page";
	}

	public String add() {
		return SUCCESS;
	}

	public String addPage() {
		return "add_page";
	}

	/**
	 * 商铺注册接口
	 * 
	 * client_type String A 为安卓 I 为IOS version int 接口版本号 默认为1 phone String
	 * 用户的手机号 password String 旧的密码 new_password String 新的密码 密码(MD5传输) code
	 * String 短信验证码 name String 名字 sex int 1为男 0 为女 birth_date String 出生日期
	 * 格式为1991-1-1
	 */

	public void reg() {
		appResult = this.merchantService.reg(merchant);
		JSONUtils.sendJSON(appResult);
	}

	public void getVerify() {
		appResult = this.merchantService.getVerify(merchant);
		JSONUtils.sendJSON(appResult);
	}

	public void getInfo() {
		appResult = this.merchantService.getInfo(merchant);
		JSONUtils.sendJSON(appResult);
	}

	public void updateInfo() {
		appResult = this.merchantService.updateInfo(merchant);
		JSONUtils.sendJSON(appResult);
	}

	public void getAllMerchant() {
		appResult = this.merchantService.getAllMerchant();
		JSONUtils.sendJSON(appResult);
	}

	public void testBean() {
		appResult = BeanUtils.uploadImage(merchant, "merchant");
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

	// 页面ajax
	public void listAjax() {
		Customer dbCustomer = (Customer) session
				.get(ServerParam.CUSTOMER_SESSION);
		if (dbCustomer != null) {
			if (dbCustomer.getMerchant() != null) {
				merchant = dbCustomer.getMerchant();
			}
		}
		List<Merchant> merchants = this.merchantService
				.selectAllByPage(merchant,session);
		setData(merchants);
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(merchants);
		JSONUtils.sendJSON(appResult);
	}

	public void addAjax() {
		BeanUtils.getBase64Image(merchant);// 转换image为base64字符串
		appResult = this.merchantService.add(merchant);
		JSONUtils.sendJSON(appResult);
	}

	public void updateAjax() {
		BeanUtils.getBase64Image(merchant);// 转换image为base64字符串
		appResult = this.merchantService.updateInfo(merchant);
		JSONUtils.sendJSON(appResult);
	}

	public void setData(List<Merchant> merchants) {
		for (Merchant merchant : merchants) {// 手动添加data...
			Customer customer = merchant.getCustomer();
			merchant.setCustomer_name(customer.getName());
			merchant.setCustomer_phone(customer.getPhone());
		}
	}
}
