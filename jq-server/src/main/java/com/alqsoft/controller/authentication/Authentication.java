package com.alqsoft.controller.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.userinfo.UserBankCard;
import com.alqsoft.model.Permission;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.service.doctor.DoctorService;
import com.alqsoft.service.merchant.MerchantService;
import com.alqsoft.service.userinfo.UserBankCardService;
import com.alqsoft.utils.MobileCheck;
import com.alqsoft.utils.SystemRole;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月31日 上午10:09:04
 * 
 */
@RequestMapping("authentication")
@Controller
public class Authentication {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private UserBankCardService userBankCardService;
	
	@Autowired
	private MobileCheck mobileCheck;
	
	/**
	 * 医生实名认证和绑定银行卡
	 * @param phone
	 * @param name
	 * @param idcard
	 * @param bankNo
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("realNameByDoctor")
	@ResponseBody
	@Permission(SystemRole.DOCTOR)
	public Result realnamebydoctor(
			@RequestParam(value="phone", required=false)String phone,
			@RequestParam(value="name", required=false)String name,
			@RequestParam(value="idcard", required=false)String idcard,
			@RequestParam(value="bankNo", required=false)String bankNo,
			@RequestParam(value="code", required=false)String code,
			@RequestParam(value="update", required=false)Integer update,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session
			) throws Exception{
		Result result = new Result();
		Doctors doctors1 = (Doctors) session.getAttribute(SystemRole.DOCTOR.getName());
		if(doctors1 == null){
			result.setCode(2);
			result.setMsg("登陆超时，请重新登陆");
			return result;
		}
		Doctors doctors = doctorService.get(doctors1.getId());
		Long customerId = doctors.getCustomerId();
		String customerPhone = doctors.getCustomerPhone();
		if("".equals(bankNo) || null == bankNo){
			result = customerService.realName(phone, name, idcard, customerId, customerPhone);
		} else {
			result = customerService.realbrank(customerId, bankNo, code, update);
		}
		//result = customerService.realName(phone, name, idcard, customerId, customerPhone);
		return result;
	}
	
	/**
	 * 医院实名认证和绑定银行卡
	 * @param phone
	 * @param name
	 * @param idcard
	 * @param bankNo
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("realNameByMerchant")
	@ResponseBody
	@Permission(SystemRole.HOSPITAL)
	public Result realnamebymerchant(
			@RequestParam(value="phone", required=false)String phone,
			@RequestParam(value="name", required=false)String name,
			@RequestParam(value="idcard", required=false)String idcard,
			@RequestParam(value="bankNo", required=false)String bankNo,
			@RequestParam(value="code", required=false)String code,
			@RequestParam(value="update", required=false)Integer update,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session
			) throws Exception{
		Result result = new Result();
		Merchant merchant1 = (Merchant) session.getAttribute(SystemRole.HOSPITAL.getName());
		if(merchant1 == null){
			result.setCode(2);
			result.setMsg("登陆超时，请重新登陆");
			return result;
		}
		Merchant merchant = merchantService.get(merchant1.getId());
		Long customerId = merchant.getCustomerId();
		String customerPhone = merchant.getCustomerPhone();
		if("".equals(bankNo) || null == bankNo){
			result = customerService.realName(phone, name, idcard, customerId, customerPhone);
		} else {
			result = customerService.realbrank(customerId, bankNo, code, update);
		}
		//result = customerService.realName(phone, name, idcard, customerId, customerPhone);
		return result;
	}
	
	/**
	 * 推荐人实名认证和修改绑定银行卡
	 * @param phone
	 * @param name
	 * @param idcard
	 * @param bankNo
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("realNameByTjcustomer")
	@ResponseBody
	@Permission(SystemRole.RECOMMENDER)
	public Result realnamebytjcustomer(
			@RequestParam(value="phone", required=false)String phone,
			@RequestParam(value="name", required=false)String name,
			@RequestParam(value="idcard", required=false)String idcard,
			@RequestParam(value="bankNo", required=false)String bankNo,
			@RequestParam(value="code", required=false)String code,
			@RequestParam(value="update", required=false)Integer update,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session
			) throws Exception{
		Result result = new Result();
		Customer customer =  (Customer) session.getAttribute(SystemRole.RECOMMENDER.getName());
		if(customer == null){
			result.setCode(2);
			result.setMsg("登陆超时，请重新登陆");
			return result;
		}
		Long customerId = customer.getId();
		String customerPhone = customer.getPhone();
		if("".equals(bankNo) || null == bankNo){
			result = customerService.realName(phone, name, idcard, customerId, customerPhone);
		} else {
			result = customerService.realbrank(customerId, bankNo, code, update);
		}
		//result = customerService.realName(phone, name, idcard, customerId, customerPhone);
		return result;
	}
	
	/**
	 * 去医生绑定修改银行卡页面
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("to-savebrank-doctor")
	@Permission(SystemRole.DOCTOR)
	public String toNameAuthenticationDoctor(
			Model model,
			@RequestParam(value="code", required=false)Integer code,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session
			){
		Doctors doctors = (Doctors) session.getAttribute(SystemRole.DOCTOR.getName());
		Customer customer = customerService.get(doctors.getCustomerId());
		Integer bankStatus = customer.getBankStatus();
		if(code == null){
			code = 0;
		}
		if(code == 1){
			model.addAttribute("type", customer.getType());
			model.addAttribute("phone", customer.getPhone());
			model.addAttribute("update",1);
			return "nameauthentication/save-brank";
		}
		if(bankStatus == null){
			bankStatus = 0;
		}
		if(bankStatus == 1){
			Long userId = customer.getId();
			UserBankCard userBankCard = userBankCardService.findUserBankCardByUserId(userId);
			model.addAttribute("bankcard", userBankCard.getCardNum());
			model.addAttribute("type", customer.getType());
			return "nameauthentication/update-brank";
		}
		model.addAttribute("type", customer.getType());
		model.addAttribute("phone", customer.getPhone());
		return "nameauthentication/save-brank";
	}
	
	/**
	 * 去医院绑定修改银行卡页面
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("to-savebrank-merchant")
	@Permission(SystemRole.HOSPITAL)
	public String toNameAuthenticationMerchant(
			Model model,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session
			){
		Merchant merchant = (Merchant) session.getAttribute(SystemRole.HOSPITAL.getName());
		Customer customer = customerService.get(merchant.getCustomerId());
		Integer type = (Integer) request.getSession().getAttribute("customertype");
		model.addAttribute("type", type);
		model.addAttribute("phone", customer.getPhone());
		return "nameauthentication/save-brank";
	}
	
	/**
	 * 去推荐人绑定修改银行卡页面
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("to-savebrank-tuijianren")
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
		return "nameauthentication/save-brank";
	}
	
	/**
	 * 医生绑定银行卡发送手机验证码
	 * @param model
	 * @param phone
	 * @param request
	 * @return
	 */
	@RequestMapping("doctor-brank-sendCode")
	@ResponseBody
	@Permission(SystemRole.DOCTOR)
	public Result doctorBrankSendCode(
			Model model,
			@RequestParam(required=false) String phone,
			HttpServletRequest request
			){
		Result sendMsg = mobileCheck.sendMsg(phone, "JQ2017030331");
		return sendMsg;
	}
	
	/**
	 * 医院绑定银行卡发送验证码
	 * @param model
	 * @param phone
	 * @param request
	 * @return
	 */
	@RequestMapping("merchant-brank-sendCode")
	@ResponseBody
	@Permission(SystemRole.DOCTOR)
	public Result merchantBrankSendCode(
			Model model,
			@RequestParam(required=false) String phone,
			HttpServletRequest request
			){
		Result sendMsg = mobileCheck.sendMsg(phone, "JQ2017030331");
		return sendMsg;
	}
	
	/**
	 * 推荐人绑定银行卡发送验证码
	 * @param model
	 * @param phone
	 * @param request
	 * @return
	 */
	@RequestMapping("tjcustomer-brank-sendCode")
	@ResponseBody
	@Permission(SystemRole.DOCTOR)
	public Result tjcustomerBrankSendCode(
			Model model,
			@RequestParam(required=false) String phone,
			HttpServletRequest request
			){
		Result sendMsg = mobileCheck.sendMsg(phone, "JQ2017030331");
		return sendMsg;
	}
	
}
