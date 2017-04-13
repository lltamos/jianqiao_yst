package com.yst.web.controller.mobile.view.diaryAttachment;

import java.io.File;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.alqframework.utils.DoubleUtils;
import org.alqframework.utils.UniqueUtils;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yst.web.entity.diaryattachment.DiaryAttachment;
import com.yst.web.service.diary.DiaryAttachmentService;
/**
 * 
 * 
 * @author 张灿
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2013-10-19 上午11:10:03
 * 
 */
@RequestMapping("/ydmvc/mobile/view/diaryAttachment")
@Controller
public class DiaryAttachmentController {
	
	@Autowired
	private  DiaryAttachmentService diaryAttachmentService;
	
	private static Logger logger = LoggerFactory.getLogger(DiaryAttachmentController.class);
	
	
	/**
	 * 文件上传
	 * 
	 * @param urlfile
	 * @return
	 */
	@RequestMapping("import-attachment.do")
	@ResponseBody
	public Result importAttachment(@RequestParam(value="urlfile") MultipartFile urlfile,
			@RequestParam(required=false,value="field")Integer field) {
		String fileName = null;
		DiaryAttachment attachment = null;
		String module = "diary";
		try {
			if (urlfile.isEmpty()) {
				return ResultUtils.returnError("上传文件失败");
			} else {
				String basePath = SpringMVCUtils.getRequest().getRealPath("/upload/" + module);
				String path = null;
				fileName = urlfile.getOriginalFilename();
				boolean isFile= StringUtils.endsWithAny(StringUtils.lowerCase(fileName), new String[] { ".png", ".jpg",".jpeg",".bmp",".gif"});
				String sysFileName = UniqueUtils.getOrder() + "."+ StringUtils.substringAfter(fileName, ".");
				if (isFile) {
					path = basePath + "/" + sysFileName;
				} else {
					return ResultUtils.returnError("文件格式不正确,上传文件失败");
				}
				urlfile.transferTo(new File(path));
				attachment = new DiaryAttachment();
				attachment.setMemory(DoubleUtils.div(urlfile.getSize(), 1024000.0, 2));
				attachment.setName(fileName);
				attachment.setAddress("upload/" + module +"/"+ sysFileName);
				attachment.setField(field);
				attachment=diaryAttachmentService.saveAndModify(attachment);
				return ResultUtils.returnSuccess("上传成功", attachment);
			}
			} catch (Exception e) {
				e.printStackTrace();
				return ResultUtils.returnError("上传失败");
			}
	}
	
	/**
	 * 根据id获取附件接口
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("attachment-by-id")
	@ResponseBody
	public String getAttachmengById(@RequestParam("id") Long id) {
		return diaryAttachmentService.get(id).getName();
	}
}


