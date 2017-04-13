package com.alqsoft.service.consultation;

import org.alqframework.result.Result;
import org.springframework.ui.Model;

public interface ConsultationService {

	Result findDoctor(Long id,String doctorName, String phone,Model model);

	Result findAdmin(String string, String string2, Model model);
	
	/**
	 * 校验当前用户购买的在线咨询时间是否到期    用户向医生发起聊天
	 * @param id
	 * @param doctorName
	 * @param phone
	 * @param model
	 * @return
	 */
	Result verifyOrder(Long id,String doctorName, String phone,Model model);
	
	/**
	 * 校验当前用户购买的在线咨询时间是否到期   医生向用户发起聊天
	 * @param id
	 * @param doctorId
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	Result verifyCustomer(Long id, Long doctorId,Model model) throws Exception;

	Result findCustomer(Long id, String customerName, String phone, Model model) throws Exception;

}
