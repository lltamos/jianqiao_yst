package com.alqsoft.rpc.diary;

import org.alqframework.result.Result;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.entity.diarybook.DiaryBook;
import com.alqsoft.entity.diarycomment.DiaryComment;



/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月20日 上午10:59:44
 * 
 */
public interface RpcDiaryService {

	public Result saveDiaryComment(DiaryComment dc, Long diaryid, Customer customer);
	
	public Result saveDiaryContent(DiaryBook diaryBook, String content, Customer customer);
	
	public Result saveDiaryBook(Integer scorep, ProductOrder productOrder, String bookName, Customer customer, String fabulousval, String steponval);

	public Result saveDiaryFavourCount(Long diaryId, Long customerId);

	public Result saveDiaryBrowNum(Long id);
	
}
