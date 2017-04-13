/*package com.alqsoft.controller.balance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.webmvc.servlet.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.Balance;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.cashreceivestation.CashReceiveStation;
import com.alqsoft.service.balance.BalanceService;
import com.alqsoft.service.balance.CashReceiveStationService;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.utils.BootStrapResult;



*//***
 * 提现记录controller
 * @author 
 *
 *//*
@RequestMapping("balance")
@Controller
public class CashReceiveStationController {
	@Autowired
	private CashReceiveStationService cashReceiveStationService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BalanceService balanceService;

	
	*//***
	 * 提现记录列表页面--商户
	 * @return
	 *//*
	@RequestMapping("to-balance-list")
	public String merchantsCashReceiveStationPage(Model model,HttpServletRequest request)
	{
		Customer customer= (Customer) request.getSession().getAttribute("dbCustomer");
		if(customer!=null)
		{
			Balance balance=balanceService.findByCustomerId(customer.getId());
			if(balance!=null)
			{
				model.addAttribute("ketiixan",balance.getDeposit_fee());//可提现金额
				model.addAttribute("fenrun",balance.getFee());//分润总金额金额；
				model.addAttribute("yitixian",balance.getHave_withdrawal_fee());//已提现
			}
			model.addAttribute("customerId",customer.getId());
		}
		return "balance/cashRecordList";
	}
	
	*//***
	 * 提现记录列表--商户
	 * @param model
	 * @param length
	 * @param start
	 * @return
	 *//*
	@RequestMapping("balance-list")
	@ResponseBody
	public BootStrapResult getCashReceiverStationRecord(Model model,
			@RequestParam(value="start",defaultValue="1") Integer start,@RequestParam(value="length",defaultValue="5") Integer length,
			@RequestParam(value="search") String search,HttpServletRequest request)
	{
		Integer page=start/length;
		//request.setAttribute("search_LIKE_name", search);
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		BootStrapResult<List<CashReceiveStation>> bootStrapResult=cashReceiveStationService.getCashReceiveStationPage(searchParams, page, length);
		return bootStrapResult;
	}
	
	*//**
	 * 跳转推荐人提现记录
	 * @return
	 *//*
	@RequestMapping("toPushCashReceiverPage.do")
	public String toPushCashReceiverPage(){
		return "push/pushCashReceiver/list";
	}
	@RequestMapping("toPushCashReceiverDetail.do")
	public String toPushCashReceiverDetail(@RequestParam("id") Long id,Model model){
		CashReceiveStation cash=cashReceiveStationService.get(id);
		model.addAttribute("cash",cash);
		return "push/pushCashReceiver/show";
		
	}
	*//**
	 * 查询推广人提现记录列表
	 * @param model
	 * @param start
	 * @param length
	 * @param request
	 * @return
	 *//*
	@RequestMapping("findPushCashReceivePage.do")
	@ResponseBody
	public BootStrapResult<List<CashReceiveStation>> findPushCashReceivePage(Model model,
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer length, 
			HttpServletRequest request)
			{
		Integer page=start/length;
		Map<String,Object> param = new HashMap<String,Object>();
		Customer customer = (Customer) request.getSession().getAttribute("user");
		param.put("EQ_userId", customer.getId());
		BootStrapResult<List<CashReceiveStation>> bootStrapResult=cashReceiveStationService.findPushCashReceivePage(param,page, length);
		return bootStrapResult;
			}
	
	*//***
	 * 提现记录详情
	 * @param model
	 * @param id
	 * @return
	 *//*
	@RequestMapping("cashReceiveStationShow.do")
	public String getCashReceiverSatationShow(Model model,@RequestParam("id") Long id,@RequestParam("type") Integer type)
	{
		if(id!=null)
		{
			if(type==1)//管理员详情
			{
				CashReceiveStation cash=cashReceiveStationService.get(id);
				model.addAttribute("cash",cash);
				return "cashReceiveStation/show";
			}else{//商户详情 
				CashReceiveStation cash=cashReceiveStationService.get(id);
				model.addAttribute("cash",cash);
				return "cashReceiveStation/MerchantsCashRecordShow";
			}
			
		}
		return null;
	}
	
	

	
	
}*/