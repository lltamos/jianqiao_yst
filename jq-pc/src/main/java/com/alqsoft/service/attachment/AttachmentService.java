package com.alqsoft.service.attachment;

import org.alqframework.result.Result;
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.entity.attachment.Attachment;



/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月20日 上午10:16:41
 * 
 */
public interface AttachmentService {

	public Result mobileUploadAttachment(MultipartFile urlfile, Object[] backUrl, String module, String[] extendFile);

	public Result saveAttachment(Attachment attachment);
	
	public Attachment getOneById(Long id);
}
