package com.alqsoft.utils;

import javax.jws.WebParam.Mode;
import javax.servlet.http.HttpSession;

import com.alqsoft.entity.Customer;
import com.google.zxing.Result;

import javassist.compiler.ast.Pair;

/**
 * 
 * @Description: TODO
 * @author 李涛
 * @e-mail llsmpsvn@gmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月18日 上午11:52:07
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public class CustomerUtils {

	public static Long  getCustomerId(HttpSession s){
		
		Customer customer = (Customer) s.getAttribute("customer");

		if (customer == null) {

			return null;
		}

		Long id = customer.getId();
		
		return id;
	}
	
	public static String  getCustomerPhone(HttpSession s){
		
		Customer customer = (Customer) s.getAttribute("customer");

		if (customer == null) {

			return null;
		}

		String phone = customer.getPhone();
		
		return phone;
	}
	
	
}
