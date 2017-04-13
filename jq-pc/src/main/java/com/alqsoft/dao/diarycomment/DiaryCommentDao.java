package com.alqsoft.dao.diarycomment;

import java.util.List;

import org.alqframework.orm.mybatis.MyBatisRepository;

import com.alqsoft.entity.diarycomment.DiaryComment;

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
public interface DiaryCommentDao{

	public List<DiaryComment> findDiaryCommentAllByDiaryId(Integer did);


}
