package com.yst.web.service.impl.diary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yst.web.dao.diaryAttachment.DiaryAttachmentDao;
import com.yst.web.entity.diaryattachment.DiaryAttachment;
import com.yst.web.service.diary.DiaryAttachmentService;

/**
 * 
 * @Description: TODO
 * @author 朱军
 * @e-mail xhzhujun@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年1月6日 下午6:07:22
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
@Service
@Transactional(readOnly=true)
public class DiaryAttachmentServiceImpl implements DiaryAttachmentService{

	private static Logger logger = LoggerFactory.getLogger(DiaryAttachmentServiceImpl.class);
	
	@Autowired
	private DiaryAttachmentDao diaryAttachmentDao;
	
	@Override
	@Transactional(readOnly=false)
	public boolean delete(Long arg0) {
		try {
			diaryAttachmentDao.delete(arg0);
			logger.info("删除附件成功，id=" + arg0);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除附件失败，id=" + arg0);
			return false;
		}
	}

	@Override
	public DiaryAttachment get(Long arg0) {
		return diaryAttachmentDao.findOne(arg0);
	}

	@Override
	@Transactional(readOnly=false)
	public DiaryAttachment saveAndModify(DiaryAttachment arg0) {
		DiaryAttachment diaryAttachment = null;
		try {
			diaryAttachment = diaryAttachmentDao.save(arg0);
			logger.info("附件新增成功,文件名为：" + diaryAttachment.getName());
			return diaryAttachment;
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("附件新增失败，文件名为：" + diaryAttachment.getName());
			return null;
		}
	}

}
