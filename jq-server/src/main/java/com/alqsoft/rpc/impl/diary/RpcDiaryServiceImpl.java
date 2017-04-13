package com.alqsoft.rpc.impl.diary;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.diary.DiaryBookDao;
import com.alqsoft.entity.Customer;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.entity.diary.Diary;
import com.alqsoft.entity.diarybook.DiaryBook;
import com.alqsoft.entity.diarycomment.DiaryComment;
import com.alqsoft.entity.diaryfavour.DiaryFavour;
import com.alqsoft.rpc.diary.RpcDiaryService;
import com.alqsoft.service.diary.DiaryBookService;
import com.alqsoft.service.diary.DiaryCommentService;
import com.alqsoft.service.diary.DiaryFavourService;
import com.alqsoft.service.diary.DiaryService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月20日 上午11:00:23
 * 
 */
@Service
public class RpcDiaryServiceImpl implements RpcDiaryService{

	@Autowired
	private DiaryCommentService diaryCommentService;
	
	@Autowired
	private DiaryService diaryService;
	
	@Autowired
	private DiaryBookService diaryBookService;
	
	@Autowired
	private DiaryFavourService diaryFavourService;
	
	@Override
	public Result saveDiaryComment(DiaryComment dc, Long diaryid, Customer customer) {
		System.out.println(dc);
		dc.setDiaryId(diaryid);
		long cid = 0;
		if (customer == null) {
			dc.setCustomerId(cid);
			dc.setCustomerName("匿名");
		} else {
			dc.setCustomerId(customer.getId());
			dc.setCustomerName(customer.getNickName());
			dc.setCustomerHandImg(customer.getImage());
		}
		
		return diaryCommentService.saveDiaryComment(dc);
	}

	@Override
	public Result saveDiaryContent(DiaryBook diaryBook, String content, Customer customer) {
		return diaryService.saveDiaryContent(diaryBook, content, customer);
	}

	@Override
	public Result saveDiaryBook(Integer scorep, ProductOrder productOrder, String bookName, Customer customer, String fabulousval, String steponval) {
		return diaryBookService.saveDiaryBook(scorep, productOrder, bookName, customer, fabulousval, steponval);
	}

	@Override
	public Result saveDiaryFavourCount(Long diaryId, Long customerId) {
		Result result = new Result();
		DiaryFavour diaryFavour = new DiaryFavour();
		diaryFavour.setDiaryId(diaryId);
		diaryFavour.setCustomerId(customerId);
		DiaryFavour saveAndModify = diaryFavourService.saveAndModify(diaryFavour);
		if(saveAndModify != null){
			result.setCode(0);
		}
		return result;
	}

	@Override
	public Result saveDiaryBrowNum(Long id) {
		Result result = new Result();
		Diary diary = diaryService.get(id);
		if(diary == null){
			return null;
		}
		Integer browNum = diary.getBrowNum();
		if(browNum == null){
			browNum = 0;
		}
		Integer browNums = browNum+1;
		diary.setBrowNum(browNums);
		diaryService.saveAndModify(diary);
		return result;
	}

}
