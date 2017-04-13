package com.yst.web.controller.mobile.view.customer;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.dao.feedback.FeedBackDao;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.service.CustomerService;
import com.yst.web.service.feedback.FeedBackService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年6月19日 下午9:36:16
 * 
 */
@RequestMapping("/ydmvc/mobile/view/Customer")
@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private FeedBackService feedBackService;
	
	@RequestMapping("Logins.do")
	@ResponseBody
	public AppResult Login(@RequestParam(value="phone",required=false)String phone,@RequestParam(value="password",required=false)String password){
		return customerService.userLogin(phone,password);
	}
	
	@RequestMapping("register.do")
	@ResponseBody
	public Result Register(Customer customer){
		return customerService.register(customer);
	}
	
	@RequestMapping("getCustomerById.do")
	@ResponseBody
	public Result getCustomerById(@RequestParam(value="customerId",required=false)String customerId){
		return customerService.getCustomerById(customerId);
	}
	
	@RequestMapping("updateCustomerById.do")
	@ResponseBody
	public Result updateCustomerById(
			@RequestParam(value="customerId",required=false)String customerId,
			@RequestParam(value="name",required=false)String name,
			@RequestParam(value="image",required=false)String image){
		return customerService.updateCustomerById(customerId,name,image);
	}
	
	/**
	 * 修改支付密码
	 * @param customerId
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 */
	@RequestMapping("updateCustomerPayPwdById.do")
	@ResponseBody
	public Result updateCustomerPayPwdById(
			@RequestParam(value="customerId",required=false)Integer customerId,
			@RequestParam(value="oldpassword",required=false)String oldpassword,
			@RequestParam(value="newpassword",required=false)String newpassword
			){
		return this.customerService.updateCustomerPayPwdById(customerId,oldpassword,newpassword);
	}
	
	/**
	 * 修改登录密码
	 * @param customerId
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 */
	@RequestMapping("updateCustomerPwdById.do")
	@ResponseBody
	public Result updateCustomerPwdById(
			@RequestParam(value="customerId",required=false)Integer customerId,
			@RequestParam(value="oldpassword",required=false)String oldpassword,
			@RequestParam(value="newpassword",required=false)String newpassword
			){
		return this.customerService.updateCustomerPwdById(customerId,oldpassword,newpassword);
	}
	
	@RequestMapping("saveCustomerFeedback.do")
	@ResponseBody
	public Result saveCustomerFeedback(
			@RequestParam(value="customerId",required=false)Integer customerId,
			@RequestParam(value="content",required=false)String content
			){
		return this.feedBackService.saveCustomerFeedback(customerId,content);
	}
}
