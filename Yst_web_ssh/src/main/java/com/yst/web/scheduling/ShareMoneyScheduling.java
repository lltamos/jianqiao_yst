/**
 * Project Name:ykpc
 * File Name:TestScheduler.java
 * Package Name:com.ydg.web.scheduler.test
 * Date:2015年11月12日下午11:01:37
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.yst.web.scheduling;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yst.web.model.AppResult;
import com.yst.web.service.PatientOrderService;
import com.yst.web.service.ProductOrderService;

/***
 * 分润
 * @author Lgn
 *
 */
@Component
public class ShareMoneyScheduling {
	
	@Autowired
	private ProductOrderService productOrderService;
	
	/**
	 * 查询订单状态为已完成
	 * @throws Exception 
	 */
	@Scheduled(cron = "0/10 * * * * ?")
	public void checkStock() throws Exception{
		System.out.println("剑桥开始分润--------------------------------");
		Result result=productOrderService.getProdecOrderFeeStatus();
		System.out.println("result:"+result.getMsg());
	}
	
	
	
	
}

