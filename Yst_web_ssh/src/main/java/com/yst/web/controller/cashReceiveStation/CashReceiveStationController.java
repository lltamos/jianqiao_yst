package com.yst.web.controller.cashReceiveStation;

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

import com.yst.web.entity.cashReceiveStation.CashReceiveStation;
import com.yst.web.model.AppResult;
import com.yst.web.model.Balance;
import com.yst.web.model.Customer;
import com.yst.web.model.Doctor;
import com.yst.web.service.BalanceService;
import com.yst.web.service.CashReceiveStationService;
import com.yst.web.service.CustomerService;
import com.yst.web.utils.BootStrapResult;
import com.yst.web.utils.ServerParam;

/***
 * 提现记录controller
 * @author lgn
 *
 */
@RequestMapping("ydmvc/cashReceiveStation")
@Controller
public class CashReceiveStationController {
	@Autowired
	private CashReceiveStationService cashReceiveStationService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private BalanceService balanceService;
	private AppResult appResult = new AppResult();
	private ServerParam serverParam;
	public ServerParam getServerParam() {
		return serverParam;
	}
	public void setServerParam(ServerParam serverParam) {
		this.serverParam = serverParam;
	}
	
	/***
	 * 提现记录列表页面--管理员
	 * @return
	 */
	@RequestMapping("cashReceiveStationPage.do")
	public String getCashReceiverSationPage(Model model,HttpServletRequest request)
	{
		return "cashReceiveStation/cashRecordList";
	}
	
	/***
	 * 提现记录列表页面--商户
	 * @return
	 */
	@RequestMapping("merchantsCashReceiveStationPage.do")
	public String merchantsCashReceiveStationPage(Model model,HttpServletRequest request)
	{
		Customer customer= (Customer) request.getSession().getAttribute("dbCustomer");
		if(customer!=null)
		{
			Balance balance=balanceService.findByCustomerId(customer.getCustomer_id());
			if(balance!=null)
			{
				model.addAttribute("ketiixan",balance.getDeposit_fee());//可提现金额
				model.addAttribute("fenrun",balance.getFee());//分润总金额金额；
				model.addAttribute("yitixian",balance.getHave_withdrawal_fee());//已提现
			}
			model.addAttribute("customerId",customer.getCustomer_id());
		}
		return "cashReceiveStation/MerchantsCashRecordList";
	}
	
	/***
	 * admin 提现记录列表
	 * @param model
	 * @param length
	 * @param start
	 * @return
	 */
	@RequestMapping("cashReceiveStation.do")
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
	/**
	 * 跳转医生提现记录列表页
	 * @return
	 */
	@RequestMapping("toDoctorCashReceiverPage.do")
	public String toDoctorCashReceiverPage(){
		
		return "doctor/doctorcashreceiver/list";
		
	}
	@RequestMapping("toDoctorCashReceiverDetail.do")
	public String toDoctorCashReceiverDetail(@RequestParam("id") Long id,Model model){
		CashReceiveStation cash=cashReceiveStationService.get(id);
		model.addAttribute("cash",cash);
		return "doctor/doctorcashreceiver/show";
		
	}
	/**
	 * 跳转推荐人提现记录
	 * @return
	 */
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
	/**
	 * 查询推广人提现记录列表
	 * @param model
	 * @param start
	 * @param length
	 * @param request
	 * @return
	 */
	@RequestMapping("findPushCashReceivePage.do")
	@ResponseBody
	public BootStrapResult<List<CashReceiveStation>> findPushCashReceivePage(Model model,
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer length, 
			HttpServletRequest request)
			{
		Integer page=start/length;
		Map<String,Object> param = new HashMap<String,Object>();
		Customer customer = (Customer) request.getSession().getAttribute(ServerParam.DOCTOR_SESSION);
		param.put("EQ_userId", customer.getCustomer_id());
		BootStrapResult<List<CashReceiveStation>> bootStrapResult=cashReceiveStationService.findPushCashReceivePage(param,page, length);
		return bootStrapResult;
			}
	/**
	 * 查询医生提现记录列表
	 * @param model
	 * @param start
	 * @param length
	 * @param request
	 * @return
	 */
	@RequestMapping("findDoctorCashReceivePage.do")
	@ResponseBody
	public BootStrapResult<List<CashReceiveStation>> findDoctorCashReceivePage(Model model,
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer length, 
			HttpServletRequest request)
	{
		Integer page=start/length;
		Map<String,Object> param = new HashMap<String,Object>();
		Doctor doctor = (Doctor) request.getSession().getAttribute(ServerParam.DOCTOR_SESSION);
		param.put("EQ_userId", doctor.getCustomer().getCustomer_id());
		BootStrapResult<List<CashReceiveStation>> bootStrapResult=cashReceiveStationService.findDoctorCashReceivePage(param,page, length);
		return bootStrapResult;
	}
	/***
	 * 提现记录详情
	 * @param model
	 * @param id
	 * @return
	 */
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
	
	/***
	 * 去提现页面
	 * @return
	 */
	@RequestMapping("cashReceiveStationFormPage.do")
	public String getCashReceiverSationFormPage(Model model,HttpServletRequest request)
	{
		Customer customer= (Customer) request.getSession().getAttribute("dbCustomer");
		if(customer!=null)
		{
			Balance balance=balanceService.findByCustomerId(customer.getCustomer_id());
			if(balance!=null)
			{
				model.addAttribute("ketiixan",balance.getDeposit_fee());//可提现金额
				model.addAttribute("fee",balance.getFee());//结算金额；
				model.addAttribute("yitixian",balance.getHave_withdrawal_fee());
			}
			
		}
		
		return "cashReceiveStation/cashReceiveStationForm";
	}

	/***
	 * 提现
	 * @param model
	 * @param cashReceiveStation
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("CashResult.do")
	public void getSupplierCashData(Model model,
			@ModelAttribute("cashForm") CashReceiveStation cashReceiveStation,
			HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		Map<Object, Object> map=new HashMap<Object, Object>();
		Customer customer= (Customer) request.getSession().getAttribute("dbCustomer");
		map.put(serverParam.CUSTOMER_SESSION, customer);
		//Customer c=customerService.findById(customer_id);
		
		cashReceiveStation.setMoney(cashReceiveStation.getMoney());
		appResult = cashReceiveStationService.luanch(cashReceiveStation,request, map);
		//用户信息余额
		Balance info=balanceService.findByCustomerId(customer.getCustomer_id());
		if(appResult.getResult().equals("SUCCESS"))
		{
			response.getWriter().print("ok");//提现成功
		}else if(appResult.getResult().equals("CashMoneyMore"))
		{
			response.getWriter().print("more");//提现金额多于账户余额
		}else if(appResult.getResult().equals("0104"))
		{
			//打款失败 把提现金额返回可提现和总金额中
			info.setDeposit_fee(info.getDeposit_fee()+cashReceiveStation.getMoney());
			info.setHave_withdrawal_fee(info.getHave_withdrawal_fee()-cashReceiveStation.getMoney());
			if(info.getDeposit_fee()<0)
			{
				info.setDeposit_fee(0);
			}if(info.getHave_withdrawal_fee()<0)
			{
				info.setHave_withdrawal_fee(0);
			}
			balanceService.update(info);
			response.getWriter().print("0104");//操作拒绝
		}else if(appResult.getResult().equals("0100"))
		{
			info.setDeposit_fee(info.getDeposit_fee()+cashReceiveStation.getMoney());
			info.setHave_withdrawal_fee(info.getHave_withdrawal_fee()-cashReceiveStation.getMoney());
			if(info.getDeposit_fee()<0)
			{
				info.setDeposit_fee(0);
			}if(info.getHave_withdrawal_fee()<0)
			{
				info.setHave_withdrawal_fee(0);
			}
			balanceService.update(info);
			response.getWriter().print("0100");//商户提交的字段长度、格式错误
		}else if(appResult.getResult().equals("0101"))
		{
			info.setDeposit_fee(info.getDeposit_fee()+cashReceiveStation.getMoney());
			info.setHave_withdrawal_fee(info.getHave_withdrawal_fee()-cashReceiveStation.getMoney());
			if(info.getDeposit_fee()<0)
			{
				info.setDeposit_fee(0);
			}if(info.getHave_withdrawal_fee()<0)
			{
				info.setHave_withdrawal_fee(0);
			}
			balanceService.update(info);
			response.getWriter().print("0101");//商户验签错误
		}else if(appResult.getResult().equals("0102"))
		{
			info.setDeposit_fee(info.getDeposit_fee()+cashReceiveStation.getMoney());
			info.setHave_withdrawal_fee(info.getHave_withdrawal_fee()-cashReceiveStation.getMoney());
			if(info.getDeposit_fee()<0)
			{
				info.setDeposit_fee(0);
			}if(info.getHave_withdrawal_fee()<0)
			{
				info.setHave_withdrawal_fee(0);
			}
			balanceService.update(info);
			response.getWriter().print("0102");//手续费计算出错
		}else if(appResult.getResult().equals("0103"))
		{
			info.setDeposit_fee(info.getDeposit_fee()+cashReceiveStation.getMoney());
			info.setHave_withdrawal_fee(info.getHave_withdrawal_fee()-cashReceiveStation.getMoney());
			if(info.getDeposit_fee()<0)
			{
				info.setDeposit_fee(0);
			}if(info.getHave_withdrawal_fee()<0)
			{
				info.setHave_withdrawal_fee(0);
			}
			balanceService.update(info);
			response.getWriter().print("0103");//商户备付金帐户金额不足
		}else if(appResult.getResult().equals("0105"))
		{
			info.setDeposit_fee(info.getDeposit_fee()+cashReceiveStation.getMoney());
			info.setHave_withdrawal_fee(info.getHave_withdrawal_fee()-cashReceiveStation.getMoney());
			if(info.getDeposit_fee()<0)
			{
				info.setDeposit_fee(0);
			}if(info.getHave_withdrawal_fee()<0)
			{
				info.setHave_withdrawal_fee(0);
			}
			balanceService.update(info);
			response.getWriter().print("0105");//重复交易
		}else if(appResult.getResult().equals("BankLengthError"))
		{
			info.setDeposit_fee(info.getDeposit_fee()+cashReceiveStation.getMoney());
			info.setHave_withdrawal_fee(info.getHave_withdrawal_fee()-cashReceiveStation.getMoney());
			if(info.getDeposit_fee()<0)
			{
				info.setDeposit_fee(0);
			}if(info.getHave_withdrawal_fee()<0)
			{
				info.setHave_withdrawal_fee(0);
			}
			balanceService.update(info);
			response.getWriter().print("BankLengthError");//银联返回信息长度不对
		}
		else if(appResult.getResult().equals("waiting"))
		{
			response.getWriter().print("waiting");//银联正在处理
		}
	  
	}
	
	
}