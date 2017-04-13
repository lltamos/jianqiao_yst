package com.alqsoft.service.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.result.Result;

import com.alqsoft.entity.Customer;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月14日 下午3:53:14
 * 
 */
public interface CustomerService {

	Customer getCustomerById(long cid);
	Customer getCustomerByPhone(String phone,HttpServletRequest request,
			HttpSession session);
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param request 
	 * @param imageCode 
	 * @param session 
	 * @return
	 */
	public Result userLogin(String phone, String password, HttpServletRequest request, String imageCode, HttpSession session);
	
	
	

	public Result userChangePassWd(Long id, String oldPassWd, String newstr1, String newstr2,HttpSession session);

	Result userChangePhone(Long id, String phone, String code,HttpSession session);

	void getVerifyCode(HttpServletRequest request, HttpServletResponse response, HttpSession session);
	/**
	 * 
	* @Title: getCustomer 
	* @Description: 根据手机号码查找用户信息
	* @return Customer    返回类型 
	* @author 腾卉 
	* @throws
	 */
	Customer getCustomer(String phone);

}
