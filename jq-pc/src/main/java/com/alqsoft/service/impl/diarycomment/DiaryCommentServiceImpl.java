package com.alqsoft.service.impl.diarycomment;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.customer.CustomerDao;
import com.alqsoft.dao.diarycomment.DiaryCommentDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.diarycomment.DiaryComment;
import com.alqsoft.service.diarycomment.DiaryCommentService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月14日 下午5:06:36
 * 
 */
@Service
public class DiaryCommentServiceImpl implements DiaryCommentService {

	@Autowired
	private DiaryCommentDao diaryCommentDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Override
	public List<DiaryComment> findDiaryCommentAllByDiaryId(Integer did) {
		List<DiaryComment> commentsList = diaryCommentDao.findDiaryCommentAllByDiaryId(did);
		if(commentsList.size()>0){
			for (DiaryComment diaryComment : commentsList) {
				Long cid = diaryComment.getCustomerId();
				if(cid == 0){
					diaryComment.setCustomerHandImg(null);
				}else{
					Customer dbCustomer = customerDao.getCustomerById(cid);
					diaryComment.setCustomerHandImg(dbCustomer.getImage());
				}
			}
		}
		return commentsList;
	}

}
