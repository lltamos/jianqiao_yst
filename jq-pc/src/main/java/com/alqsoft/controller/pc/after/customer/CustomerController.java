package com.alqsoft.controller.pc.after.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.Customer;
import com.alqsoft.rpc.customer.RpcCustomerService;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.utils.MobileCheck;

/**
 * 用户控制层
 * @author zj
 *
 */
@Controller
@RequestMapping("/pc/view/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private RpcCustomerService rpcCustomerService;
	@Autowired
	private MobileCheck mobileCheck;

	/**
	 * 跳转首页
	 * @return
	 */
	@RequestMapping("toIndex")
	public String indexPage(Model model){
		model.addAttribute("check", "index");
		return "view/customer/homepage";
	}
	
	/**
	 * 到用户登录页面 --销毁全部有可能存在的session
	 * @return
	 */
	@RequestMapping("to-login")
	public String toLogin(HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,Model model){
		session.removeAttribute("dbUser");
		session.removeAttribute("customer");
		session.removeAttribute("customertype");
		session.removeAttribute("doctors");
		session.removeAttribute("merchant");
		session.removeAttribute("recommender");
		session.removeAttribute("nameAuthentication");
		return "view/customer/login";
	}
	
	/**
	 * 到用户注册页面
	 * @return
	 */
	@RequestMapping("to-register")
	public String toRegister(){
		
		return "view/customer/register";
	}
	/**
	 * 注册后去首页
	 * @return
	 */
	@RequestMapping("to-index")
	public String registerToIndex(HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,@RequestParam(value="phone")String phone){
		session.removeAttribute("dbUser");
		session.removeAttribute("customer");
		session.removeAttribute("customertype");
		session.removeAttribute("doctors");
		session.removeAttribute("merchant");
		session.removeAttribute("recommender");
		session.removeAttribute("nameAuthentication");
		if(phone!=null){
			Customer customer = customerService.getCustomerByPhone(phone, request,  session);
			if(customer != null ){
				return "view/customer/homepage";
			}
		}
		return "view/customer/register";
	}
	/**
	 * 忘记密码
	 * @return
	 */
	@RequestMapping("to-forgetpassword")
	public String toForgetPassword(){
		
		return "view/customer/forgetpassword";
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("login-out")
	public String loginOut(HttpSession session){
		session.removeAttribute("customer");
		return "redirect:/pc/view/customer/toIndex";
	}
	
	/**
	 * 用户登录       登陆前将所有有可能存在的session销毁掉-w
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping("login")
	@ResponseBody
	public Result login(@RequestParam(value="phone")String phone,
			@RequestParam(value="password")String password,
			@RequestParam(value="imageCode")String imageCode,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session){
		session.removeAttribute("dbUser");
		session.removeAttribute("customer");
		session.removeAttribute("customertype");
		session.removeAttribute("doctors");
		session.removeAttribute("merchant");
		session.removeAttribute("recommender");
		session.removeAttribute("nameAuthentication");
		Result result = customerService.userLogin(phone,password,request,imageCode,session);
		return result;
	}
	
	/**
	 * 用户注册
	 * @param customer
	 * @return
	 */
	@RequestMapping("register")
	@ResponseBody
	public Result register(Customer customer,@RequestParam(value="checkmsg")String checkmsg){
		Result result = rpcCustomerService.userRegister(customer,checkmsg);
		return result;
	}
	
	/**
	 * 注册发送短信验证码
	 * @param phone
	 * @return
	 */
	@RequestMapping("register-sendmsg")
	@ResponseBody
	public Result sendMsg(@RequestParam(value="phone")String phone){
		Result sendMsg = mobileCheck.sendMsg(phone, "JQ2017031602");
		return sendMsg;
	}
	
	/**
	 * 修改密码发送短信验证码
	 * @param phone
	 * @return
	 */
	@RequestMapping("forget-send")
	@ResponseBody
	public Result forgetSend(@RequestParam(value="phone")String phone){
		Result sendMsg = mobileCheck.sendMsg(phone, "JQ2017031603");
		return sendMsg;
	}
	
	
	@RequestMapping("forgetpassword")
	@ResponseBody
	public Result forgetpassword(Customer customer,@RequestParam(value="checkmsg")String checkmsg){
		Result result = rpcCustomerService.forgetpassword(customer,checkmsg);
		return result;
	}
	
	/**
	 * 获取验证码
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("user-code")
	public void userCode(Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		this.customerService.getVerifyCode(request,response,session);
	}
	
	/**
	 * 
	* @Title: getCustomer 
	* @Description: 忘记密码发送验证码之前先验证账号是否存在
	* @return Result    返回类型 
	* @author 腾卉 
	* @throws
	 */
	@RequestMapping("get-customer")
	@ResponseBody
	public Result getCustomer(Model model,
			@RequestParam(value="phone")String phone,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session){
		Customer customer = customerService.getCustomer(phone);
		if( customer != null ){
			return ResultUtils.returnSuccess("账户存在！");
		}
		return ResultUtils.returnError("账户不存在！");
	}
	
}
