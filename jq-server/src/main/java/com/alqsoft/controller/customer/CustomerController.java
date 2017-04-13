package com.alqsoft.controller.customer;

import java.util.List;

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
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.model.Permission;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.MobileCheck;
import com.alqsoft.utils.SystemRole;

@RequestMapping("customer")
@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private MobileCheck mobileCheck;
	
	@RequestMapping("to-customer-list")
	@Permission(SystemRole.ADMIN)
	public String getAllCustomer(){
		return "customer/customer-list";
	}
	
	/**
	 * 后台用户登录---登陆前销毁所有有可能存在的session
	 * @param model
	 * @param cusotmer
	 * @param imageCode
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("cusotmer-login")
	@ResponseBody
	public Result login(Model model,Customer cusotmer,@RequestParam("imageCode")String imageCode,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		session.removeAttribute("dbUser");
		session.removeAttribute("customer");
		session.removeAttribute("customertype");
		session.removeAttribute("doctors");
		session.removeAttribute("merchant");
		session.removeAttribute("recommender");
		session.removeAttribute(SystemRole.SESSIONROLE.getName());
		session.removeAttribute(SystemRole.HOSPITAL.getName());
		session.removeAttribute(SystemRole.RECOMMENDER.getName());
		session.removeAttribute(SystemRole.DOCTOR.getName());
		session.removeAttribute("nameAuthentication");
		return this.customerService.login(cusotmer,imageCode,session,request);
	}
	
	/**
	 * 会员列表
	 * @param customername
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping("customer-list")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<Customer>> findCustomerByPage(@RequestParam(value="phone", required=false)String phone,
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer length){
		Integer page=start/length;
		BootStrapResult<List<Customer>> result = customerService.findCustomerByPage(phone,page,length);
		return result;
	}
	
	/**
	 * 跳转到添加用户页面
	 * @return
	 */
	@RequestMapping("add-customer")
	@Permission(SystemRole.ADMIN)
	public String addCustomer(){
		return "customer/customer-add";
	}
	
	/**
	 * 跳转到用户修改页面
	 * @return
	 */
	@RequestMapping("update-customer")
	@Permission(SystemRole.ADMIN)
	public String updateCustomer(Model model,
			@RequestParam(value="id",required=false)Long id,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session){
		/*if(customer.getId()+"" !=null){*/
		if(id !=null){
			Customer customer = this.customerService.get(id);
			String name = customer.getName();
			System.out.println(name);
			model.addAttribute("customer", this.customerService.get(id));
		}
		return "customer/customer-update";
	}
	
	
	/**
	 * 后台添加用户发送短信验证码
	 * @param model
	 * @param customerPhone
	 * @param request
	 * @return
	 */
	@RequestMapping("customer-add-sendCode")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result sendCode(Model model,@RequestParam(required=false) String customerPhone,HttpServletRequest request){
		Result sendMsg = mobileCheck.sendMsg(customerPhone, "JQ2017031602");
		return sendMsg;
	}
	
	/**
	 * 新增用户
	 * @param model
	 * @param merchant
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("customer-save")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result customerSave(Model model,
			Customer customer,
			//@RequestParam(required=false) MultipartFile[] imageCred,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		/*if(session.getAttribute(SystemRole.SUPERADMIN.getName())==null){
			return ResultUtils.returnError("登录超时，请重新登录！");
		}*/
		return this.customerService.saveCustomer(customer);
	}
	
	/**
	 * 修改用户信息
	 * @param model
	 * @param customer
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("customer-update")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result customerUpdate(Model model,
			Customer customer,
			//@RequestParam(required=false) MultipartFile[] imageCred,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) {
		if(session.getAttribute(SystemRole.ADMIN.getName())==null){
			return ResultUtils.returnError("登录超时，请重新登录！");
		}
		return this.customerService.updateCustomer(customer);
	}
	@RequestMapping("customer-delete")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result deleteCustomer(Model model,
			@RequestParam Long id,
			@RequestParam Integer deleted,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session){
		if(session.getAttribute(SystemRole.ADMIN.getName())==null){
			return ResultUtils.returnError("登录超时，请重新登录！");
		}
		return this.customerService.deleted(id,deleted);
	}
	
	@RequestMapping("to-nameauthentication-doctor")
	@Permission(SystemRole.DOCTOR)
	public String toNameAuthenticationDoctor(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session
			){
		Doctors doctors = (Doctors) session.getAttribute(SystemRole.DOCTOR.getName());
		Customer customer = customerService.get(doctors.getCustomerId());
		model.addAttribute("type", customer.getType());
		model.addAttribute("phone", customer.getPhone());
		return "nameauthentication/nameauthentication-list";
	}
	
	@RequestMapping("to-nameauthentication-merchant")
	@Permission(SystemRole.HOSPITAL)
	public String toNameAuthenticationMerchant(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session
			){
		Merchant merchant = (Merchant) session.getAttribute(SystemRole.HOSPITAL.getName());
		Customer customer = customerService.get(merchant.getCustomerId());
		/*model.addAttribute("type", customer.getType());*/
		model.addAttribute("phone", customer.getPhone());
		Integer type = (Integer) request.getSession().getAttribute("customertype");
		model.addAttribute("type", type);
		return "nameauthentication/nameauthentication-list";
	}
	
	@RequestMapping("to-nameauthentication-tuijianren")
	@Permission(SystemRole.RECOMMENDER)
	public String toNameAuthenticationTuijianren(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session
			){
		Customer customer1 = (Customer) session.getAttribute(SystemRole.RECOMMENDER.getName());
		Customer customer = customerService.get(customer1.getId());
		Integer type = (Integer) request.getSession().getAttribute("customertype");
		model.addAttribute("type", type);
		model.addAttribute("phone", customer.getPhone());
		/*model.addAttribute("type", customer.getType());*/
		return "nameauthentication/nameauthentication-list";
	}
}


