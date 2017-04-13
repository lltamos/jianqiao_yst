package com.alqsoft.controller.balance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.alqframework.webmvc.servlet.ServletUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.Balance;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.cashreceivestation.CashReceiveStation;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.userinfo.UserBankCard;
import com.alqsoft.model.Permission;
import com.alqsoft.rpc.pay.RpcPayService;
import com.alqsoft.rpc.pay.impl.RpcWeChatPayServiceImpl;
import com.alqsoft.service.balance.BalanceService;
import com.alqsoft.service.balance.BalanceTransService;
import com.alqsoft.service.balance.CashReceiveStationService;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.service.userinfo.UserBankCardService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.MobileCheck;
import com.alqsoft.utils.SystemRole;

@RequestMapping("balance")
@Controller
public class BalanceController {

	@Autowired
	private BalanceService balanceService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CashReceiveStationService cashReceiveStationService;
	@Autowired
	private MobileCheck mobileCheck;

	/**
	 * 得到当前用户的分润金额--总院分润金额等
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("to-balance-list")
	@Permission(SystemRole.HOSPITAL)
	public String getAllCustomer(Model model, HttpServletRequest request) {

		Integer type = (Integer) request.getSession().getAttribute("customertype");
		if (type == 2) {
			Merchant merchant = (Merchant) request.getSession().getAttribute("merchant");
			Long customerId = merchant.getCustomerId();
			Customer customer = customerService.get(customerId);
			if (customer != null) {
				Balance balance = balanceService.findByCustomerId(customer.getId(), type);
				if (balance != null) {
					model.addAttribute("ketiixan", balance.getDepositFee());// 可提现金额
					model.addAttribute("fenrun", balance.getFee());// 分润总金额金额；
					model.addAttribute("yitixian", balance.getHaveWithdrawalFee());// 已提现
				}
			}
		}
		return "balance/cashRecordListMe";
	}

	/**
	 * 得到当前用户的分润金额--医生分润金额等
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("to-doctorbalance-list")
	@Permission(SystemRole.DOCTOR)
	public String getAllHospital(Model model, HttpServletRequest request) {
		/* return "balance/cashRecordList"; */

		Integer type = (Integer) request.getSession().getAttribute("customertype");
		if (type == 1) {
			Doctors doctors = (Doctors) request.getSession().getAttribute("doctors");
			Long customerId = doctors.getCustomerId();
			Customer customer = customerService.get(customerId);
			if (customer != null) {
				Balance balance = balanceService.findByCustomerId(customer.getId(), type);
				if (balance != null) {
					model.addAttribute("ketiixan", balance.getDepositFee());// 可提现金额
					model.addAttribute("fenrun", balance.getFee());// 分润总金额金额；
					model.addAttribute("yitixian", balance.getHaveWithdrawalFee());// 已提现
				}
			}
		}
		return "balance/cashRecordList";
	}

	/**
	 * 获取推荐人的分润金额等
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("to-balancetjr-list")
	@Permission(SystemRole.RECOMMENDER)
	public String getAllBalance(Model model, HttpServletRequest request) {

		Integer type = (Integer) request.getSession().getAttribute("customertype");
		if (type == 3) {
			Customer cusotmer = (Customer) request.getSession().getAttribute("recommender");

			if (cusotmer != null) {
				Long customerId = cusotmer.getId();
				Balance balance = balanceService.findByCustomerId(customerId, type);
				if (balance != null) {
					model.addAttribute("ketiixan", balance.getDepositFee());// 可提现金额
					model.addAttribute("fenrun", balance.getFee());// 分润总金额金额；
					model.addAttribute("yitixian", balance.getHaveWithdrawalFee());// 已提现
				}
			}
		}
		return "balance/cashRecordListTjr";
	}

	/**
	 * 提现记录 ----总院提现记录分页
	 * 
	 * @param start
	 * @param length
	 * @param customername
	 * @param status
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("balance-list")
	@ResponseBody
	@Permission(SystemRole.HOSPITAL)
	public BootStrapResult findBalanceByPage(@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "status", required = false) String status, HttpServletRequest request,
			HttpSession session) {
		if (session.getAttribute(SystemRole.HOSPITAL.getName()) == null) {
			return null;
		}
		Integer page = start / length;
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		// Map<String, Object> searchParams = new HashMap<String,Object>();
		Integer customerType = (Integer) request.getSession().getAttribute("customertype");

		/*
		 * if(customerType != null && customerType ==1){ Doctors doctors =
		 * (Doctors) request.getSession().getAttribute("doctors"); Long
		 * customerId = doctors.getCustomerId();
		 * searchParams.put("EQ_customerId", customerId); }
		 */
		if (customerType != null && customerType == 2) {
			Merchant merchant = (Merchant) request.getSession().getAttribute("merchant");
			Long customerId = merchant.getCustomerId();
			/*
			 * searchParams.put("EQ_customerId", customerId);
			 * searchParams.put("EQ_type", customerType);
			 */
			searchParams.put("EQ_cssId", customerId);
			searchParams.put("EQ_cssType", customerType);
		}
		/*
		 * if(customerType != null && customerType ==3){ Customer customer =
		 * (Customer) request.getSession().getAttribute("recommender"); Long
		 * customerId = customer.getId(); searchParams.put("EQ_customerId",
		 * customerId); }
		 */
		BootStrapResult<List<CashReceiveStation>> bootStrapResult = cashReceiveStationService
				.getCashReceiveStationPage(searchParams, page, length, request);
		return bootStrapResult;
	}

	/**
	 * 医生提现记录分页
	 * 
	 * @param start
	 * @param length
	 * @param name
	 * @param status
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("balancedoctor-list")
	@ResponseBody
	@Permission(SystemRole.DOCTOR)
	public BootStrapResult findDoctorBalanceByPage(@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length, HttpServletRequest request,
			HttpSession session) {
		if (session.getAttribute(SystemRole.DOCTOR.getName()) == null) {
			return null;
		}
		Integer page = start / length;
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		// Map<String, Object> searchParams = new HashMap<String,Object>();
		Integer customerType = (Integer) request.getSession().getAttribute("customertype");

		if (customerType != null && customerType == 1) {
			Doctors doctors = (Doctors) request.getSession().getAttribute("doctors");
			Long customerId = doctors.getCustomerId();
			searchParams.put("EQ_cssId", customerId);
			searchParams.put("EQ_cssType", customerType);
		}
		/*
		 * if(customerType != null && customerType ==2){ Merchant merchant =
		 * (Merchant) request.getSession().getAttribute("merchant"); Long
		 * customerId = merchant.getCustomerId();
		 * searchParams.put("EQ_customerId", customerId); } if(customerType !=
		 * null && customerType ==3){ Customer customer = (Customer)
		 * request.getSession().getAttribute("recommender"); Long customerId =
		 * customer.getId(); searchParams.put("EQ_customerId", customerId); }
		 */
		BootStrapResult<List<CashReceiveStation>> bootStrapResult = cashReceiveStationService
				.getCashReceiveStationPage(searchParams, page, length, request);
		return bootStrapResult;
	}

	/**
	 * 推荐人提现列表
	 * 
	 * @param start
	 * @param length
	 * @param name
	 * @param status
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("balancetjr-list")
	@ResponseBody
	@Permission(SystemRole.RECOMMENDER)
	public BootStrapResult findtjrBalanceByPage(@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "status", required = false) String status, HttpServletRequest request,
			HttpSession session) {
		if (session.getAttribute(SystemRole.RECOMMENDER.getName()) == null) {
			return null;
		}
		Integer page = start / length;
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		// Map<String, Object> searchParams = new HashMap<String,Object>();
		Integer customerType = (Integer) request.getSession().getAttribute("customertype");

		/*
		 * if(customerType != null && customerType ==1){ Doctors doctors =
		 * (Doctors) request.getSession().getAttribute("doctors"); Long
		 * customerId = doctors.getCustomerId();
		 * searchParams.put("EQ_customerId", customerId); } if(customerType !=
		 * null && customerType ==2){ Merchant merchant = (Merchant)
		 * request.getSession().getAttribute("merchant"); Long customerId =
		 * merchant.getCustomerId(); searchParams.put("EQ_customerId",
		 * customerId); }
		 */
		if (customerType != null && customerType == 3) {
			Customer customer = (Customer) request.getSession().getAttribute("recommender");
			Long customerId = customer.getId();
			searchParams.put("EQ_cssId", customerId);
			searchParams.put("EQ_cssType", customerType);
		}
		BootStrapResult<List<CashReceiveStation>> bootStrapResult = cashReceiveStationService
				.getCashReceiveStationPage(searchParams, page, length, request);
		return bootStrapResult;
	}

	/**
	 * 推荐人提现详情
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("tjrbalance-view")
	@Permission(SystemRole.RECOMMENDER)
	public String getTjrBalnaceView(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam(value = "id", required = false) Long id) {
		CashReceiveStation cash = cashReceiveStationService.get(id);
		model.addAttribute("cash", cash);
		return "balance/show";
	}

	/**
	 * 总院提现详情
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("balance-view")
	@Permission(SystemRole.HOSPITAL)
	public String getBalnaceView(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam(value = "id", required = false) Long id) {
		CashReceiveStation cash = cashReceiveStationService.get(id);
		model.addAttribute("cash", cash);
		return "balance/show";
	}

	/**
	 * 医生提现详情
	 * 
	 * @param model
	 * @param request
	 * @param response
	 * @param session
	 * @param id
	 * @return
	 */
	@RequestMapping("dotorbalance-view")
	@Permission(SystemRole.DOCTOR)
	public String getDoctorBalnaceView(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam(value = "id", required = false) Long id) {
		CashReceiveStation cash = cashReceiveStationService.get(id);
		model.addAttribute("cash", cash);
		return "balance/show";
	}

	@RequestMapping("balance-sendCode")
	@ResponseBody
	@Permission(SystemRole.DOCTOR)
	public Result sendCode(Model model, @RequestParam(required = false) String phone, HttpServletRequest request) {
		Result sendMsg = mobileCheck.sendMsg(phone, "JQ2017031605");
		return sendMsg;
	}
	@RequestMapping("balance-sendHospitalCode")
	@ResponseBody
	@Permission(SystemRole.HOSPITAL)
	public Result sendCodeHospital(Model model, @RequestParam(required = false) String phone, HttpServletRequest request) {
		Result sendMsg = mobileCheck.sendMsg(phone, "JQ2017031605");
		return sendMsg;
	}
	@RequestMapping("balance-sendTjrCode")
	@ResponseBody
	@Permission(SystemRole.RECOMMENDER)
	public Result sendCodeTjr(Model model, @RequestParam(required = false) String phone, HttpServletRequest request) {
		Result sendMsg = mobileCheck.sendMsg(phone, "JQ2017031605");
		return sendMsg;
	}

	/**
	 * 跳转到医生提现页面
	 * 
	 * @param model
	 * @param request
	 * @param ressponse
	 * @return
	 */
	@RequestMapping("goto-doctorcashreceive")
	@Permission(SystemRole.DOCTOR)
	public String goToCashReceive(Model model, HttpServletRequest request, HttpServletResponse ressponse) {
		Doctors doctors = (Doctors) request.getSession().getAttribute("doctors");
		
		Long customerId = doctors.getCustomerId();
		Customer customer = customerService.getCustomerById(customerId);
		Integer bankStatus = customer.getBankStatus();
		if(bankStatus ==null || bankStatus !=1){
			model.addAttribute("type",1);
			model.addAttribute("phone", customer.getPhone());
			return "nameauthentication/save-brank";
		}
		return "balance/cashReceiveStationForm";
	}

	// 跳转到总院提现
	@RequestMapping("goto-cashreceive")
	@Permission(SystemRole.HOSPITAL)
	public String goToMeCashReceive(Model model, HttpServletRequest request, HttpServletResponse ressponse) {
		Merchant merchant = (Merchant) request.getSession().getAttribute("merchant");
		Long customerId = merchant.getCustomerId();
		Customer customer = customerService.getCustomerById(customerId);
		Integer bankStatus = customer.getBankStatus();
		if(null == bankStatus || bankStatus !=1){
			model.addAttribute("type",2);
			model.addAttribute("phone", customer.getPhone());
			return "nameauthentication/save-brank";
		}
		return "balance/cashReceiveStationFormMe";
	}

	// 跳转到推荐人提现
	@RequestMapping("goto-tjrcashreceive")
	@Permission(SystemRole.RECOMMENDER)
	public String goToTjrCashReceive(Model model, HttpServletRequest request, HttpServletResponse ressponse) {
		Customer customer = (Customer) request.getSession().getAttribute("recommender");
		Integer bankStatus = customer.getBankStatus();
		if(null == bankStatus || bankStatus !=1){
			model.addAttribute("type",3);
			model.addAttribute("phone", customer.getPhone());
			return "nameauthentication/save-brank";
		}
		return "balance/cashReceiveStationFormTjr";
	}

	@RequestMapping("doctorcashreceive")
	@Permission(SystemRole.DOCTOR)
	@ResponseBody
	public Result doctorCashReceive(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam Long money1, // 钱
			@RequestParam String cardNo, // 卡号
			@RequestParam String personName, // 开户人姓名
			@RequestParam String bankName, // 银行名称
			@RequestParam String code, @RequestParam String phone) {

		if (session.getAttribute(SystemRole.DOCTOR.getName()) == null) {
			return null;
		}
		Integer customerType = (Integer) request.getSession().getAttribute("customertype");

		if (customerType == null || customerType.equals("")) {
			return null;
		}

		if (money1 == null || "".equals(money1) || money1 < 1) {
			return ResultUtils.returnError("提现金额有误!");
		}
		if (cardNo == null || "".equals(cardNo) || cardNo.length() > 19 || cardNo.length() < 16) {
			return ResultUtils.returnError("银行卡号有误!");
		}
		if (personName == null || "".equals(personName)) {
			return ResultUtils.returnError("开户人姓名有误!");
		}
		if (bankName == null || "".equals(bankName)) {
			return ResultUtils.returnError("银行名称有误!");
		}
		if (code == null || "".equals(code)) {
			return ResultUtils.returnError("验证码有误!");
		}
		if (phone == null || "".equals(phone)) {
			return ResultUtils.returnError("手机号码有误!");
		}

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("money", money1);
		map.put("cardNo", cardNo);
		map.put("personName", personName);
		map.put("bankName", bankName);
		map.put("phone", phone);
		map.put("code", code);
		map.put("customerType", customerType);

		Result result = balanceService.confirmMoney(map, request);

		return result;

	}
	@RequestMapping("hospitalcashreceive")
	@Permission(SystemRole.HOSPITAL)
	@ResponseBody
	public Result hospitalCashReceive(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam Long money1, // 钱
			@RequestParam String cardNo, // 卡号
			@RequestParam String personName, // 开户人姓名
			@RequestParam String bankName, // 银行名称
			@RequestParam String code, @RequestParam String phone) {

		if (session.getAttribute(SystemRole.DOCTOR.getName()) == null) {
			return null;
		}
		Integer customerType = (Integer) request.getSession().getAttribute("customertype");

		if (customerType == null || customerType.equals("")) {
			return null;
		}

		if (money1 == null || "".equals(money1) || money1 < 1) {
			return ResultUtils.returnError("提现金额有误!");
		}
		if (cardNo == null || "".equals(cardNo) || cardNo.length() > 19 || cardNo.length() < 16) {
			return ResultUtils.returnError("银行卡号有误!");
		}
		if (personName == null || "".equals(personName)) {
			return ResultUtils.returnError("开户人姓名有误!");
		}
		if (bankName == null || "".equals(bankName)) {
			return ResultUtils.returnError("银行名称有误!");
		}
		if (code == null || "".equals(code)) {
			return ResultUtils.returnError("验证码有误!");
		}
		if (phone == null || "".equals(phone)) {
			return ResultUtils.returnError("手机号码有误!");
		}

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("money", money1);
		map.put("cardNo", cardNo);
		map.put("personName", personName);
		map.put("bankName", bankName);
		map.put("phone", phone);
		map.put("code", code);
		map.put("customerType", customerType);

		Result result = balanceService.confirmMoney(map, request);

		return result;

	}
	@RequestMapping("tjrcashreceive")
	@Permission(SystemRole.DOCTOR)
	@ResponseBody
	public Result tjrCashReceive(Model model, HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestParam Long money1, // 钱
			@RequestParam String cardNo, // 卡号
			@RequestParam String personName, // 开户人姓名
			@RequestParam String bankName, // 银行名称
			@RequestParam String code, @RequestParam String phone) {

		if (session.getAttribute(SystemRole.DOCTOR.getName()) == null) {
			return null;
		}
		Integer customerType = (Integer) request.getSession().getAttribute("customertype");

		if (customerType == null || customerType.equals("")) {
			return null;
		}

		if (money1 == null || "".equals(money1) || money1 < 1) {
			return ResultUtils.returnError("提现金额有误!");
		}
		if (cardNo == null || "".equals(cardNo) || cardNo.length() > 19 || cardNo.length() < 16) {
			return ResultUtils.returnError("银行卡号有误!");
		}
		if (personName == null || "".equals(personName)) {
			return ResultUtils.returnError("开户人姓名有误!");
		}
		if (bankName == null || "".equals(bankName)) {
			return ResultUtils.returnError("银行名称有误!");
		}
		if (code == null || "".equals(code)) {
			return ResultUtils.returnError("验证码有误!");
		}
		if (phone == null || "".equals(phone)) {
			return ResultUtils.returnError("手机号码有误!");
		}

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("money", money1);
		map.put("cardNo", cardNo);
		map.put("personName", personName);
		map.put("bankName", bankName);
		map.put("phone", phone);
		map.put("code", code);
		map.put("customerType", customerType);

		Result result = balanceService.confirmMoney(map, request);

		return result;

	}
}
