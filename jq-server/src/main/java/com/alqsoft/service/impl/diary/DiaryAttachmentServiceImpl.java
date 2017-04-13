package com.alqsoft.service.impl.diary;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.diary.DiaryAttachmentDao;
import com.alqsoft.entity.diaryattachment.DiaryAttachment;
import com.alqsoft.service.diary.DiaryAttachmentService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月24日 下午2:54:11
 * 
 */
@Service
@Transactional(readOnly=true)
public class DiaryAttachmentServiceImpl implements DiaryAttachmentService{

	@Autowired
	private DiaryAttachmentDao diaryAttachmentDao;
	
	@Override
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public DiaryAttachment get(Long arg0) {
		return diaryAttachmentDao.findOne(arg0);
	}

	@Override
	@Transactional
	public DiaryAttachment saveAndModify(DiaryAttachment arg0) {
		return diaryAttachmentDao.save(arg0);
	}

	@Override
	@Transactional
	public Result saveDiaryById(DiaryAttachment attachment) {
		Result result = new Result();
		try {
			diaryAttachmentDao.save(attachment);
			result.setCode(0);
			result.setMsg("图片保存成功");
		} catch (Exception e) {
			result.setCode(1);
			result.setMsg("图片保存失败");
		}
		return result;
	}

}
