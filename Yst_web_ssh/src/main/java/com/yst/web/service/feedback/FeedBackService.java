package com.yst.web.service.feedback;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.yst.web.entity.feedback.FeedBack;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年11月17日 下午4:50:24
 * 
 */
public interface FeedBackService extends BaseService<FeedBack>{

	Result saveCustomerFeedback(Integer customerId, String content);

}
