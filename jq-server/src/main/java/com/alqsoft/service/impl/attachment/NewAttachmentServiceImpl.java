package com.alqsoft.service.impl.attachment;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.attachment.AttachmentDao;
import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.service.attachment.NewAttachmentService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月20日 上午9:30:54
 * 
 */
@Service
@Transactional(readOnly=true)
public class NewAttachmentServiceImpl implements NewAttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;
	
	@Override
	@Transactional
	public Result saveAttachment(Attachment attachment) {
		return null;
	}

	@Override
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public Attachment get(Long arg0) {
		return null;
	}

	@Override
	@Transactional
	public Attachment saveAndModify(Attachment arg0) {
		return null;
	}

	@Override
	@Transactional
	public Attachment saveattachment(Attachment attachment) {
		return attachmentDao.save(attachment);
	}

}
