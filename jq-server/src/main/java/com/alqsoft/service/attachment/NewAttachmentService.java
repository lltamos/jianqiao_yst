package com.alqsoft.service.attachment;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.attachment.Attachment;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月20日 上午9:30:38
 * 
 */
public interface NewAttachmentService extends BaseService<Attachment>{

	public Result saveAttachment(Attachment attachment);

	public Attachment saveattachment(Attachment attachment);
}
