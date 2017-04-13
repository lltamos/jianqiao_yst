/**
 * Project Name:ykpc
 * File Name:TestScheduler.java
 * Package Name:com.ydg.web.scheduler.test
 * Date:2015年11月12日下午11:01:37
 * Copyright (c) 2015, chenzhou1025@126.com All Rights Reserved.
 *
*/

package com.yst.web.scheduling;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yst.web.model.AppResult;
import com.yst.web.service.CashReceiveStationService;


/**
 * 查看银行返回状态
 * ClassName:DetectionbankReturnStatus <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     2015年11月12日 下午11:01:37 <br/>
 * @author   lenovo
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Component
public class DetectionbankReturnStatus {
	
	@Autowired
	private CashReceiveStationService cashReceiveStationService;
	
	/**
	 * 
	 */
	@Scheduled(cron = "0/1 * * * * ?")
	public void testSche(){
	}
	
	/**
	 * 检查银联返回状态：responseCode：000：查询成功  stat：s成功；2：处理中 交易已接受；3：处理中 财务已确认 4：财务处理中  5已发往银行 6：失败 银行已退单
	 * @throws Exception 
	 */
	/*@Scheduled(cron = "0/10 * * * * ?")
	public void checkStock() throws Exception{
		System.out.println("检查银行返回状态--------------------------------");
		AppResult result=cashReceiveStationService.getBankReturnStatus();
		System.out.println("result:"+result.getError_info());
	}*/
	
	
	
	
}

