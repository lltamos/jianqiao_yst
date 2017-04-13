package com.alqsoft.service.attachment;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.entity.attachment.Attachment;

public interface AttachmentService extends BaseService<Attachment>{
	
	public List<Attachment> getAttachmentsByIsBanner(Integer page, Integer length,
			HttpServletRequest request);
	public Result mobileUploadAttachment(MultipartFile urlfile, Object[] backUrl, String module, String[] extendFile);

	public Result saveAttachment(Attachment attachment);
	
	public Attachment getOneById(Long id);
	
}
