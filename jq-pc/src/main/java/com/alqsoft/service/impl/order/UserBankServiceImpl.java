package com.alqsoft.service.impl.order;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.order.UserBankCard;
import com.alqsoft.entity.order.UserBankCardModel;
import com.alqsoft.rpc.RpcUserBankService;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.service.order.UserBankService;
import com.alqsoft.utils.CheckElement;
import com.alqsoft.utils.MobileCheck;
import com.alqsoft.utils.wechat.BeanUtils;
import com.alqsoft.utils.wechat.CopyBeanUtil;

/**
 * 银行卡信息业务层实现类
 * 
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月18日 下午4:35:03
 * 
 */
@Service
@Transactional
public class UserBankServiceImpl implements UserBankService {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private RpcUserBankService rpcBankService;
	// @Autowired
	// private DoctorService doctorService;

	@Override
	public Result saveBankInfo(Customer customer, UserBankCardModel userBank) {

		if (BeanUtils.checkFieldValueNull(customer) || BeanUtils.checkFieldValueNull(userBank)) {
			return ResultUtils.returnError("银行信息不能为空");
		}
		// TODO 手机 身份证 验证
		String idNumber = customer.getIDNumber();
		Long userid = customer.getId();
		String phone = customer.getPhone();

		if (idNumber == null || "".equals(idNumber)) {
			return ResultUtils.returnError("未实名认证不能绑定银行卡");
		}
		if (phone == null || "".equals(phone)) {
			return ResultUtils.returnError("未绑手机号不能修改银行卡");
		}
		if (userid == null || "".equals(userid)) {
			return ResultUtils.returnError("登陆用户id信息不存在");
		}
		String bankName = userBank.getBankName();
		String cardNum = userBank.getCardNum();
		String ownName = userBank.getOwnName();
		if ("".equals(bankName) || "".equals(cardNum) || "".equals(ownName)) {
			return ResultUtils.returnError("银行卡信息不完整");
		} else {
			if (!(customer.getName()).equals(ownName)) {
				return ResultUtils.returnError("非本人名下银行卡禁止绑定");
			}
		}
		MobileCheck duanxin = new MobileCheck();
		UserBankCard bankCard;
		// TODO 验证短信 验证码
		Result checkMsg = duanxin.checkMsg(userBank.getTelPhone(), userBank.getVerificationNO(),
				"JQBDYHK");
		if (checkMsg.getCode() != null) {
			if (checkMsg.getCode() != 1) {

				return ResultUtils.returnError("验证码错误");// 短信验证错误
			} else {
				CheckElement checkElement = new CheckElement();
				// TODO 三要素验证
				try {
					Result checkThreeElement = checkElement.checkThreeElement(ownName, idNumber,
							cardNum);

					if (checkThreeElement.getCode() == 1) {
						return ResultUtils.returnError(checkThreeElement.getMsg());
					}
				} catch (Exception e) {
					e.printStackTrace();
					return ResultUtils.returnError("服务器繁忙");
				}
				Customer customerById = customerService.getCustomerById(userid);
				if (!BeanUtils.checkFieldValueNull(customerById)) {
					Integer type = customerById.getType();
					Long customerId = customerById.getCustomerId();
					bankCard = new UserBankCard();
					bankCard = (UserBankCard) CopyBeanUtil.Copy(bankCard, userBank, false);
					if (customerId == null || customerId == 0) {
						bankCard.setCustomerType(3);
						bankCard.setUserId((customer.getId()));
					} else if (type == 1) {
						Long doctorId = customerById.getDoctorId();
						bankCard.setCustomerType(1);
						bankCard.setUserId(doctorId);
						// TODO 先跑通
					} else if (type == 2) {
						Long merchantId = customerById.getMerchantId();
						bankCard.setCustomerType(2);
						bankCard.setUserId(merchantId);
					}
					Result saveBankCard = rpcBankService.saveBankCard(customer, bankCard);
					if (BeanUtils.checkFieldValueNull(saveBankCard)) {
						return ResultUtils.returnError("保存银行信息失败");
					}
					return ResultUtils.returnSuccess("SUCCESS");
				}
			}
		}
		return ResultUtils.returnError("短信验证失败");
	}

	@Override
	public Result updateBankInfo(Customer customer, UserBankCardModel userBank) {
		Long userid = customer.getId();
		String phone = customer.getPhone();
		String username = customer.getName();
		String idNumber = customer.getIDNumber();
		if (idNumber == null || "".equals(idNumber)) {
			return ResultUtils.returnError("未实名认证不能绑定银行卡");
		}
		if (phone == null || "".equals(phone)) {
			return ResultUtils.returnError("未绑手机号号不能修改银行卡");
		}
		if ((userid == null || "".equals(userid)) || BeanUtils.checkFieldValueNull(userBank)) {
			return ResultUtils.returnError("银行信息不能为空");
		}
		UserBankCard content;
		Result findUserBankInfo = rpcBankService.findUserBankInfo(customer.getId());
		if (findUserBankInfo.getCode() == 0) {
			content = (UserBankCard) findUserBankInfo.getContent();
		} else {
			return ResultUtils.returnError("已有银行信息异常");
		}
		String bankName = userBank.getBankName();
		String cardNum = userBank.getCardNum();
		String telPhone = userBank.getTelPhone();
		if ("".equals(bankName) || "".equals(cardNum) || "".equals(telPhone)) {
			return ResultUtils.returnError("银行卡信息不完整");
		}
		MobileCheck duanxin = new MobileCheck();
		// (1)短信验证码
		Result checkMsg = duanxin.checkMsg(phone, userBank.getVerificationNO(), "JQ2017031603");
		// UserBankCard bankCard;
		if (checkMsg.getCode() != null) {
			if (checkMsg.getCode() != 1) {
				return ResultUtils.returnError("验证码错误");
			}

			CheckElement checkElement = new CheckElement();
			Result myresult = rpcBankService.findUserBankInfo(userid);
			if (myresult.getCode() == 1) {
				return ResultUtils.returnError("该用户没用绑定银行卡");
			}

			// TODO 三要素验证
			try {
				Result checkThreeElement = checkElement.checkThreeElement(username, idNumber,
						cardNum);

				if (checkThreeElement.getCode() == 1) {
					return ResultUtils.returnError(checkThreeElement.getMsg());
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ResultUtils.returnError("服务器繁忙");
			}
			Result saveBankCard;

			try {
				content.setBankName(bankName);
				content.setCardNum(cardNum);
				content.setTelPhone(telPhone);
				saveBankCard = rpcBankService.saveBankCard(customer, content);
				if (BeanUtils.checkFieldValueNull(saveBankCard)) {
					return ResultUtils.returnError("保存银行信息失败");
				}
				return ResultUtils.returnSuccess("SUCCESS");
			} catch (Exception e) {
				System.out.println("rpc保存银行卡接口异常");
				e.printStackTrace();
			}

		}
		return ResultUtils.returnError("验证码异常");
	}

	public Result findUserBankByUserId(String userid) {
		Result result = new Result();
		try {
			Result findUserBankInfo = rpcBankService.findUserBankInfo(Long.parseLong(userid));
			return findUserBankInfo;
		} catch (Exception e) {
			System.out.println("rpc查询银行卡信息解耦异常");
			e.printStackTrace();
		}
		result.setCode(1);
		result.setMsg("查询数据库异常");
		return result;
	}
}
