package com.yst.web.service.impl.feedback;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.CustomerDao;
import com.yst.web.dao.feedback.FeedBackDao;
import com.yst.web.entity.feedback.FeedBack;
import com.yst.web.model.Customer;
import com.yst.web.service.feedback.FeedBackService;
import com.yst.web.utils.CommUtils;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年11月17日 下午4:51:05
 * 
 */
@Transactional
@Service
public class FeedBackServiceImpl implements FeedBackService{

	@Autowired
	private FeedBackDao feedBackDao;
	
	@Autowired
	private CustomerDao customerDao;
	 
	@Override
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public FeedBack get(Long arg0) {
		return this.feedBackDao.findOne(arg0);
	}

	@Override
	public FeedBack saveAndModify(FeedBack arg0) {
		return this.feedBackDao.save(arg0);
	}

	@Override
	public Result saveCustomerFeedback(Integer customerId, String content) {
		if(customerId == null){
			return ResultUtils.returnError("用户id不能为空");
		}
		Customer customer = this.customerDao.findByColumnValue(Customer.class, "customer_id", customerId);
		if(CommUtils.isNull(customer)){
			return ResultUtils.returnError("用户不存在");
		}
		if(content == null){
			return ResultUtils.returnError("反馈意见不能为空");
		}
		FeedBack feedBack = new FeedBack();
		feedBack.setContent(content);
		Long cId = Long.parseLong(customerId.toString());
		feedBack.setCustomerId(cId);
		feedBackDao.save(feedBack);
		return ResultUtils.returnSuccess("保存成功"); 
	}

}
