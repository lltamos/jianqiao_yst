package com.alqsoft.service.impl.diary;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.DiaryCommentDao;
import com.alqsoft.entity.diarycomment.DiaryComment;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.service.diary.DiaryCommentService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月20日 上午11:09:52
 * 
 */
@Service
@Transactional(readOnly = true)
public class DiaryCommentServiceImpl implements DiaryCommentService{

	@Autowired
	private DiaryCommentDao diaryCommentDao;
	
	@Override
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public DiaryComment get(Long arg0) {
		return null;
	}

	@Override
	public DiaryComment saveAndModify(DiaryComment arg0) {
		return null;
	}

	@Override
	@Transactional
	public Result saveDiaryComment(DiaryComment dc) {
		Result result = new Result();
		diaryCommentDao.save(dc);
		result.setCode(0);
		result.setMsg("保存成功");
		return result;
	}
}
