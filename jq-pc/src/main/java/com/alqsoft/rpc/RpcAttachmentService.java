package com.alqsoft.rpc;

import org.alqframework.result.Result;

import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.entity.diaryattachment.DiaryAttachment;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月20日 上午10:27:20
 * 
 */
public interface RpcAttachmentService {

	public Attachment saveAttachment(Attachment attachment);
	
	public Result saveDiaryAttachment(DiaryAttachment attachment, Long diaryId);

}
