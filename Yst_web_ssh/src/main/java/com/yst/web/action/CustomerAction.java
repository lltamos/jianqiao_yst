package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.mysql.jdbc.StringUtils;
import com.opensymphony.xwork2.ActionContext;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.service.CustomerService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.HttpClientObject;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.MD5;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ServerParam;

@Controller("customerAction")
@Scope("prototype")
public class CustomerAction extends BaseAction {
	@Resource(name = "customerService")
	private CustomerService customerService;
	private Customer customer = new Customer();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();

	public String add() {
		this.customerService.add(customer);
		return SUCCESS;
	}
	
	public String addPage(){
		return "add_page";
	}
	public String delete() {
		this.customerService.deleteById(customer.getCustomer_id(),customer.getDeleted());
		return SUCCESS;
	}

	public String updatePage() {
		customer = this.customerService.findById(customer.getCustomer_id());
		return "update_page";
	}

	public String update() {
		appResult=this.customerService.updateInfo(customer);
		return SUCCESS;
	}

	public String error() {
		System.out.println("error");
		return "error";
	}

	@Override
	public String execute() {
		return LIST;
	}

	/**
	 * 发送短信 http Get请求 请求Url:"http://sms.1xinxi.cn/asmx/smsservice.aspx?” 参数说明
	 * name = "wbhsqfwzx"; pwd = "9FA6EB4D075A98F84F896CF0D451"; type = "pt";
	 * 以上三个参数是固定的 Content：发送内容+ "【社区服务中心】” 注意必须加上【社区服务中心】 否则是发送不了的 Mobile：手机号
	 * 例子：http://sms.1xinxi.cn/asmx/smsservice.aspx? name = wbhsqfwzx& pwd =
	 * 9FA6EB4D075A98F84F896CF0D451& type =
	 * pt&Mobile=12345678909&Content=验证码XXX【社区服务中心】
	 * 
	 * 返回结果是json对象，解析后 Code 此参数=0代表发送成功 Msg 失败后的消息 其他几个参数不是很重要
	 * 
	 * @return
	 */
	public int sendMessage(String phone) {
		System.out.println("开始发送短信。。。。。。");
		int telcode = getRandom();
		String mobile = phone;
		String url = "http://sms.1xinxi.cn/asmx/smsservice.aspx?";
		String param = "";
//		String name = "wbhsqfwzx";
//		String pwd = "9FA6EB4D075A98F84F896CF0D451";
		String name="yishangtong";
		String pwd ="9FA6EB4D075A98F84F896CF0D451";
		String type = "pt";
		String content = "验证码为：" + telcode + "【社区服务中心】";
		param = "name=" + name + "&pwd=" + pwd + "&type=" + type + "&Mobile="
				+ mobile + "&Content=" + content;
		System.out.println(url + param);
		HttpClientObject hco = new HttpClientObject();
		hco.setGet(url + param);
		long start = System.currentTimeMillis();
		hco.submit();
		long end = System.currentTimeMillis();
		System.out.println("____________用时：" + (end - start));
		String str = hco.getResponseString();
		System.out.println(str + "   结束发送短信。。。。。。");
		if (StringUtils.isEmptyOrWhitespaceOnly(str)) {
			return 1;
		}
		String[] results = str.split(",");
		int result = Integer.parseInt(results[0]);
		if (result > 0) {
			appResult.setError_info(results[1]);
		} else {
			this.customerService.saveXML(telcode, mobile);
		}
		return result;
	}

	public void sendMessage() {
		JSONUtils.sendJSON(this.customerService.sendMessage(customer));
	}

	public int getRandom() {
		int num = (int) (Math.random() * 9000 + 1000);
		return num;
	}

	public void getCode() {
		Integer version = this.customer.getVersion();
		String client_type = this.customer.getClient_type();
		String phone = this.customer.getPhone();
		int i = 1;
		if (StringUtils.isEmptyOrWhitespaceOnly(phone)) {
			appResult.setError_info("手机号为空");
		} else {
			i = sendMessage(phone);
		}
		if (i == 0) {
			appResult.setResult(AppResult.SUCCESS);
		} else {
			appResult.setResult(AppResult.FAILED);
		}
		appResult.setVersion(1);
		JSONUtils.sendJSON(appResult);
	}

	public void checkCode() {
		appResult = this.customerService.checkXML(customer);
		JSONUtils.sendJSON(appResult);
	}

	public void login() {
		appResult = this.customerService.login(customer);
		JSONUtils.sendJSON(appResult);
	}

	public void reg() {
		appResult = this.customerService.reg(customer);
		JSONUtils.sendJSON(appResult);
	}

	public void getPassword() {
		appResult = this.customerService.checkXML(customer);
		String result = appResult.getResult();
		if (result.equals(AppResult.SUCCESS)) {
			appResult = this.customerService.updatePhonePassword(customer);
		}
		JSONUtils.sendJSON(appResult);
	}
	public void getPayPassword() {
		appResult = this.customerService.checkXML(customer);
		String result = appResult.getResult();
		if (result.equals(AppResult.SUCCESS)) {
			appResult = this.customerService.updatePhonePayPassword(customer);
		}
		JSONUtils.sendJSON(appResult);
	}
	/**
	 * 修改用户密码
	 */
	public void updatePassword() {
		appResult = this.customerService.updatePassword(customer);
		JSONUtils.sendJSON(appResult);
	}
	public void updatePayPassword(){
		appResult = this.customerService.updatePayPassword(customer);
		JSONUtils.sendJSON(appResult);
	}
	public void updateInfo() {
		appResult = this.customerService.updateInfo(customer);
		JSONUtils.sendJSON(appResult);
	}
	
	public void getInfo(){
		appResult = this.customerService.getInfo(customer);
		JSONUtils.sendJSON(appResult);
	}
	public void getProductOrderList(){
		appResult = this.customerService.getProductOrderList(customer);
		JSONUtils.sendJSON(appResult);
	}
	public void testHuanXin(){
		appResult = this.customerService.setHuanXin(customer);
		JSONUtils.sendJSON(appResult);
	}
	
	public void deleteHuanXin(){
		appResult = this.customerService.deleteHuanXin(customer);
		JSONUtils.sendJSON(appResult);
	}
	@Override
	public Customer getModel() {
		return customer;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}
	//页面ajax
	public void listAjax(){
		List<Customer> customers = this.customerService.selectAllByPage();
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(customers);
		JSONUtils.sendJSON(appResult);
	}
	public void updateAjax(){
		BeanUtils.getBase64Image(customer);//转换image为base64字符串
		customer.setPassword(MD5.getMD5ofStr(customer.getPassword()));//md5加密
		appResult=this.customerService.updateInfo(customer);
		JSONUtils.sendJSON(appResult);
	}
	public void addAjax(){
		appResult=this.customerService.checkXML(customer);
		if(appResult.getResult().equals(AppResult.SUCCESS)){
			BeanUtils.getBase64Image(customer);//转换image为base64字符串
			customer.setPassword(MD5.getMD5ofStr(customer.getPassword()));//md5加密
			appResult=this.customerService.reg(customer);
		}
		JSONUtils.sendJSON(appResult);
	}
	public void patientAjax(){
		List<Customer> customers = this.customerService.selectPatientPage();
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(customers);
		JSONUtils.sendJSON(appResult);
	}
	public void addPatientAjax(){
		appResult=this.customerService.addPatient(customer);
		JSONUtils.sendJSON(appResult);
	}
	public void webLogin() {
		String role=request.getParameter("loginRole");
		appResult = this.customerService.loginWeb(customer,session,Long.parseLong(role));
		JSONUtils.sendJSON(appResult);
	}
	public String merchantPage(){
		return "merchant_list";
	}
	public String patientPage(){
		return "patient_page";
	}
	public String addPatientPage(){
		List<Customer> customers = this.customerService.selectAll();
		ActionContext.getContext().put("customers", customers);
		return "add_patient_page";
	}
}
