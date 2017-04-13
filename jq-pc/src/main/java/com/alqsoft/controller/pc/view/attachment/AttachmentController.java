package com.alqsoft.controller.pc.view.attachment;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.service.attachment.AttachmentService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月20日 上午10:14:49
 * 
 */
@Controller
@RequestMapping("pc/view/attachment")
public class AttachmentController {

	@Autowired
	private AttachmentService attachmentService;
	
	@RequestMapping("newUpload-Attachment.do")
	@ResponseBody
	public Result newUploadAttachment(
			@RequestParam("file") MultipartFile file,
			@RequestParam(required = false , value = "field") Integer field,
			HttpServletRequest request,
			Model model
			){
		
		String[] extendFile = new String[]{".jpg", ".png", ".jpeg", ".bmp", ".gif"}; 
		String module= "jianqiao";
		Result result = attachmentService.mobileUploadAttachment(file,new Object[]{attachmentService,"saveAttachment"},module,extendFile);
		Attachment attachment = (Attachment) result.getContent();
		
		model.addAttribute("aid", attachment.getId());
		result.setMsg(attachment.getId()+"");
		return result;
	}

	
	
	
	
	
	
	
	@RequestMapping("saveMsg")
	@ResponseBody
	public Result newUploadAttachment(HttpServletRequest request,String msg){
		System.out.println(msg);
		return ResultUtils.returnSuccess("11111111111");
	}
	

}
