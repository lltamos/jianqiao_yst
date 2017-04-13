package com.alqsoft.scheduler.share;

import org.alqframework.webmvc.springmvc.Result;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alqsoft.service.productorder.ProductOrderService;

/**
 * 分润作业
 * 
 * @Title: EmailTask.java
 * @Description: TODO
 * @author 俞旺
 * @e-mail 13606024548@139.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2015年4月22日 下午3:12:33 Copyright © 2013 厦门卓讯信息技术有限公司 All rights
 *              reserved.
 * 
 */
@Component
@Lazy(value = false)
public class ShareTask {
	private static Log logger = LogFactory
			.getLog(ShareTask.class);
	@Autowired
	private ProductOrderService productOrderService;

	@Scheduled(cron = "0 0/1 * * * *")
	public void checkTask() {
		Result result=productOrderService.getCheckProductOrderShareMoney();
		logger.info("分润作业,Msg:"+result.getMsg()+",code:"+result.getCode());
	}
}
