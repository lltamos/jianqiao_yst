package com.yst.web.dao.diary;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yst.web.entity.diary.Diary;

/**
 * 
 * @Description: TODO
 * @author 朱军
 * @e-mail xhzhujun@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月6日 下午5:46:21
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public interface DiaryDao extends BaseDao<Diary>{

	/**
	 * 根据日记本id查找相应的日记数据
	 * @param diaryBookId
	 * @return
	 */
	@Query("SELECT s FROM Diary s WHERE s.diaryBook.id=:diaryBookId")
	public List<Diary> findToOne(@Param("diaryBookId")Long diaryBookId);

	

}
