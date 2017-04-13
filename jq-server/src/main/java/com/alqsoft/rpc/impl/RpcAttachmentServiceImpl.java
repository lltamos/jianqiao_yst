package com.alqsoft.rpc.impl;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.diary.DiaryDao;
import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.entity.diary.Diary;
import com.alqsoft.entity.diaryattachment.DiaryAttachment;
import com.alqsoft.rpc.RpcAttachmentService;
import com.alqsoft.service.attachment.NewAttachmentService;
import com.alqsoft.service.diary.DiaryAttachmentService;
import com.alqsoft.service.diary.DiaryService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月20日 上午10:43:35
 * 
 */
@Service
@Transactional
public class RpcAttachmentServiceImpl implements RpcAttachmentService{

	@Autowired
	private NewAttachmentService newAttachmentService;
	
	@Autowired
	private DiaryAttachmentService diaryAttachmentService;
	
	@Autowired
	private DiaryService diaryService;
	
	@Override
	public Attachment saveAttachment(Attachment attachment) {
		return newAttachmentService.saveattachment(attachment);
	}

	@Override
	public Result saveDiaryAttachment(DiaryAttachment attachment, Long diaryId) {
		Result result = new Result();
		Diary diary = diaryService.getDiaryById(diaryId);
		attachment.setDiary(diary);
		result = diaryAttachmentService.saveDiaryById(attachment);
		return result;
	}

}
