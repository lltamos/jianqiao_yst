package com.alqsoft.controller.pc.after.customer;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.rpc.RpcUpdateCustormerService;
import com.alqsoft.service.attachment.AttachmentService;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.utils.CustomerUtils;
import com.alqsoft.utils.MobileCheck;

/**
 * 
 * @Description: TODO
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月17日 上午11:56:21 Copyright © 2013 北京易商通公司 All rights
 *              reserved.
 * 
 */

@Controller
@RequestMapping("/pc/after/customer")
public class CustomerMangerController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private MobileCheck mobileCheck;

	@Autowired
	private RpcUpdateCustormerService rpcUpdateCustormerService;

	@Autowired
	private AttachmentService attachmentService;

	/**
	 * 到用户修改密码验证身份
	 * 
	 * @return
	 */
	@RequestMapping("to-editpasswdcheck-page")
	public String toCustomerPassWdCheck(HttpSession session) {
		Long id = CustomerUtils.getCustomerId(session);

		if (id == null) {
			return "redirect:/pc/view/customer/to-login";
		}
		session.setAttribute("lefttype", 2);

		return "after/usercenter/editpasswordcheck";
	}

	/**
	 * 到用户修改密码页面
	 * 
	 * @return
	 */
	@RequestMapping("to-editpasswd-page")
	public String toCustomerPassWd(HttpSession session) {
		Long id = CustomerUtils.getCustomerId(session);

		if (id == null) {
			return "redirect:/pc/view/customer/to-login";
		}
		session.setAttribute("lefttype", 2);
		return "after/usercenter/editpassword";
	}

	/**
	 * 到用户修改密码成功页面
	 * 
	 * @return
	 */
	@RequestMapping("to-editpasswdsucess-page")
	public String toCustomerPassWdSuccess(HttpSession session) {
		Long id = CustomerUtils.getCustomerId(session);

		if (id == null) {
			return "redirect:/pc/view/customer/to-login";
		}
		session.setAttribute("lefttype", 2);
		return "after/usercenter/editpasswordsuccess";
	}

	/**
	 * 到用户修改手机验证身份
	 * 
	 * @return
	 */
	@RequestMapping("to-editphonecheck-page")
	public String toCustomerPhoneCheck(HttpSession session) {

		Long id = CustomerUtils.getCustomerId(session);

		if (id == null) {
			return "redirect:/pc/view/customer/to-login";
		}
		session.setAttribute("lefttype", 3);
		return "after/usercenter/editphonecheck";
	}

	/**
	 * 到用户修手机号码页面
	 * 
	 * @return
	 */
	@RequestMapping("to-editphone-page")
	public String toCustomerPhone(HttpSession session) {
		Long id = CustomerUtils.getCustomerId(session);

		if (id == null) {
			return "redirect:/pc/view/customer/to-login";
		}
		session.setAttribute("lefttype", 3);
		return "after/usercenter/editphone";
	}

	/**
	 * 到用户修改手机成功页面
	 * 
	 * @return
	 */
	@RequestMapping("to-editphonesucess-page")
	public String toCustomerphoneSuccess(HttpSession session) {

		Long id = CustomerUtils.getCustomerId(session);

		if (id == null) {
			return "redirect:/pc/view/customer/to-login";
		}
		session.setAttribute("lefttype", 3);
		return "after/usercenter/editphonesuccess";
	}

	/**
	 * 到用户修改头像昵称
	 * 
	 * @return
	 */
	@RequestMapping("to-editnickname-page")
	public String toCustomerIcoOrnickname(HttpSession session) {
		Long id = CustomerUtils.getCustomerId(session);

		if (id == null) {
			return "redirect:/pc/view/customer/to-login";
		}
		session.setAttribute("lefttype", 1);
		return "after/usercenter/editnickname";
	}

	/**
	 * 到用户修改头像昵称
	 *
	 * @return
	 */
	@RequestMapping("change-nicknameandinfo")
	@ResponseBody
	public Result changeNickname(

			@RequestParam(value = "aids") String aids, @RequestParam(value = "nickname") String nickname,
			HttpSession session) {

	
		Attachment attachment = null;
		if (org.apache.commons.lang3.StringUtils.isNotBlank(aids)) {

			attachment = attachmentService.getOneById(Long.valueOf(aids));
		}

		Long id = CustomerUtils.getCustomerId(session);

		Customer customer = customerService.getCustomerById(id);

		if (org.apache.commons.lang3.StringUtils.isNotBlank(nickname)) {
			customer.setNickName(nickname);

		}
		if (attachment != null) {
			customer.setImage(attachment.getAddress());
		}
		Customer updateCustorm = rpcUpdateCustormerService.updateCustorm(customer);

		if (updateCustorm != null) {

			session.setAttribute("customer", customer);
			return ResultUtils.returnSuccess("修改成功！");
		}

		return ResultUtils.returnSuccess("修改失败！");
	}

	/**
	 * 用户修改密码
	 * 
	 * @param username
	 * @param password
	 * @return password1
	 * @throws IOException
	 */
	@RequestMapping(value = "change-passwd", method = RequestMethod.POST)
	@ResponseBody
	public Result changePassWd(@RequestParam(value = "oldPassWd") String oldPassWd,
			@RequestParam(value = "password1") String password1, @RequestParam(value = "password2") String password2,
			HttpSession session, HttpServletResponse p) throws IOException {

		Long id = CustomerUtils.getCustomerId(session);
		if (id == null) {

			return ResultUtils.returnError("未登录");
		}

		Result result = customerService.userChangePassWd(id, oldPassWd, password1, password2, session);
		return result;
	}

	/**
	 * 用户修改手机号
	 * 
	 * @param username
	 * @param password
	 * @return password1
	 * @throws IOException
	 */
	@RequestMapping(value = "change-phone", method = RequestMethod.POST)
	@ResponseBody
	public Result changePhone(@RequestParam(value = "phone", required = true) String phone,
			@RequestParam(value = "code", required = true) String code, HttpSession session, HttpServletResponse p) {

		Long id = CustomerUtils.getCustomerId(session);

		if (id == null) {

			return ResultUtils.returnError("未登录");
		}

		Result result = customerService.userChangePhone(id, phone, code, session);

		return result;
	}

	/**
	 * 发送短信验证码
	 * 
	 * @param phone
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "editpwd-sendmsg", method = RequestMethod.POST)
	@ResponseBody
	public Result sendEditMsg(HttpSession session, HttpServletResponse p) throws IOException {

		String customerPhone = CustomerUtils.getCustomerPhone(session);

		Result sendMsg = mobileCheck.sendMsg(customerPhone, "JQ2017031607");

		return sendMsg;
	}

	/**
	 * 修改手机时校验旧手机验证码
	 * 
	 * @param phone
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "editoldphone-sendmsg", method = RequestMethod.POST)
	@ResponseBody
	public Result sendEditoldphone(HttpSession session, HttpServletResponse p) throws IOException {

		String customerPhone = CustomerUtils.getCustomerPhone(session);

		Result sendMsg = mobileCheck.sendMsg(customerPhone, "JQ2017031608");

		return sendMsg;
	}

	/**
	 * 修改手机时校验新手机验证码
	 * 
	 * @param phone
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "editnewphone-sendmsg", method = RequestMethod.POST)
	@ResponseBody
	public Result sendEditnewPhone(@RequestParam(value = "phone", required = true) String phone) {

		Result sendMsg = mobileCheck.sendMsg(phone, "JQ2017031606");

		return sendMsg;
	}

	/**
	 * 校验短信验证码
	 * 
	 * @param phone
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "check-sendmsg", method = RequestMethod.POST)
	@ResponseBody
	public Result checkMsg(HttpSession session, HttpServletResponse p,
			@RequestParam(value = "code", required = true) String code,
			@RequestParam(value = "type", required = true) String type) throws IOException {

		String customerPhone = CustomerUtils.getCustomerPhone(session);

		if (customerPhone == null) {
			p.sendRedirect("/pc/view/customer/to-login");
		}

		if (org.apache.commons.lang3.StringUtils.equals(type, "pwd")) {

			return mobileCheck.checkMsg(customerPhone, code, "JQ2017031607");

		}
		if (org.apache.commons.lang3.StringUtils.equals(type, "phone")) {

			return mobileCheck.checkMsg(customerPhone, code, "JQ2017031608");
		}

		return ResultUtils.returnError("发送失败！");
	}

}
