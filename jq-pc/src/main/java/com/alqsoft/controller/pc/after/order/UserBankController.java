package com.alqsoft.controller.pc.after.order;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.order.UserBankCardModel;
import com.alqsoft.service.order.UserBankService;
import com.alqsoft.utils.CheckElement;
import com.alqsoft.utils.MobileCheck;

/**
 * 用户银行卡控制层
 * 
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月18日 下午3:51:40
 * 
 */
@Controller
@RequestMapping("order/userbank")
public class UserBankController {

	@Autowired
	private UserBankService bankService;

	@RequestMapping("saveBank")
	public String toSaveBank(UserBankCardModel bankCardModel, HttpServletRequest request) {
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		try {
			Result saveBankInfo = bankService.saveBankInfo(customer, bankCardModel);
			Integer code = saveBankInfo.getCode();
			if (code == 1) {
				return "FAIL";
			}
			return "SECCESS";
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "FAIL";

	}

	@RequestMapping("updateBank")
	public String updateBank(UserBankCardModel bankCardModel, HttpServletRequest request) {
		Customer customer = (Customer) request.getSession().getAttribute("customer");
		try {
			Result updateBankInfo = bankService.updateBankInfo(customer, bankCardModel);
			Integer code = updateBankInfo.getCode();
			if (code == 1) {
				return "FAIL";
			}
			return "SECCESS";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "FAIL";

	}

	/**
	 * 前往更新页面
	 * 
	 * @author Yaowei
	 * @param
	 * @return String
	 * @Time 2017年3月20日
	 */
	@RequestMapping("toUpdateBank")
	public String toUpdateBank() {
		System.out.println("toUpdateBank");
		return "after/usercenter/updateCustomerBindBankInfo";
	}

	/**
	 * 前往绑定页面
	 * 
	 * @author Yaowei
	 * @param
	 * @return String
	 * @Time 2017年3月20日
	 */
	@RequestMapping("toSaveBank")
	public String toSaveBank() {
		System.out.println("toSaveBank");
		return "after/usercenter/customerBindBankInfo";
	}

	/**
	 * 
	 * @author Yaowei
	 * @param
	 * @return String
	 * @Time 2017年3月20日
	 */
	@RequestMapping("sendMsg")
	public String sendMsg(HttpServletRequest request) {
		Customer attribute = (Customer) request.getSession().getAttribute("customer");
		String phone2 = attribute.getPhone();
		MobileCheck sendMsg = new MobileCheck();
		Result sendMsg2 = sendMsg.sendMsg(phone2, "JQ2017031603");
		if (sendMsg2.getCode() == 1) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}
}
