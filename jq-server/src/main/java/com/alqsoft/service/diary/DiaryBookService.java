package com.alqsoft.service.diary;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.ProductOrder;
import com.alqsoft.entity.diarybook.DiaryBook;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月24日 下午2:13:17
 * 
 */
public interface DiaryBookService extends BaseService<DiaryBook>{

	Result saveDiaryBook(Integer scorep, ProductOrder productOrder, String bookName, Customer customer, String fabulousval, String steponval);

}
