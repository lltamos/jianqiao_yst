package com.alqsoft.service.diary;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.diary.Diary;
import com.alqsoft.entity.diarybook.DiaryBook;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月22日 下午4:02:17
 * 
 */
public interface DiaryService extends BaseService<Diary>{

	public Result saveDiaryContent(DiaryBook diaryBook, String content, Customer customer);

	public Diary getDiaryById(Long diaryId);

}
