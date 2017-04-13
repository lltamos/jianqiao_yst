package com.alqsoft.service.impl.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.diary.DiaryFavourDao;
import com.alqsoft.entity.diaryfavour.DiaryFavour;
import com.alqsoft.service.diary.DiaryFavourService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年4月11日 下午3:43:48
 * 
 */
@Service
@Transactional(readOnly=true)
public class DiaryFavourServiceImpl implements DiaryFavourService {

	@Autowired
	private DiaryFavourDao diaryFavourDao;
	
	@Override
	@Transactional
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public DiaryFavour get(Long arg0) {
		return null;
	}

	@Override
	@Transactional
	public DiaryFavour saveAndModify(DiaryFavour arg0) {
		return diaryFavourDao.save(arg0);
	}

}
