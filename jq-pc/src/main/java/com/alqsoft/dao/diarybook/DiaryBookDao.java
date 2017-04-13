package com.alqsoft.dao.diarybook;

import java.util.List;
import java.util.Map;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.diarybook.DiaryBook;

/**
*
* @author 黄鑫
* @e-mail abc12707058@hotmail.com
* @version v1.0
* @copyright 2010-2015
* @create-time 2017年3月10日 上午11:16:02
* 
*/
@MyBatisRepository
public interface DiaryBookDao{

	DiaryBook findOne(Long diaryBookId);

	List<DiaryBook> getBookServicePcList(Map<String, Object> map);

	int getBookServicePcListCount(Long cid);

}
