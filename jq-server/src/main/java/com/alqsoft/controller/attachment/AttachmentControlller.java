package com.alqsoft.controller.attachment;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.init.InitParamPc;
import com.alqsoft.model.Permission;
import com.alqsoft.service.attachment.AttachmentService;
import com.alqsoft.utils.SystemRole;
import com.alqsoft.utils.oss.UpLoadUtils;

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
@SuppressWarnings("deprecation")
@RequestMapping("attachment")
@Controller
public class AttachmentControlller {

	@Autowired
	private AttachmentService attachmentService;
	
	@Autowired
	private InitParamPc initParam;

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(AttachmentControlller.class);

	/**
	 * 文件上传
	 * 
	 * @param urlfile
	 * @return
	 */
	@RequestMapping("import-attachment.do")
	@ResponseBody
	public Result importAttachment(
			@RequestParam("urlfile") MultipartFile urlfile,
			@RequestParam(required = false, value = "field") Integer field) {
		String fileName = null;
		Attachment attachment = null;
		String module = "test";
		try {
			if (urlfile.isEmpty()) {
				return ResultUtils.returnError("上传文件失败");
			} else {
				String basePath = SpringMVCUtils.getRequest().getRealPath(
						"/upload/" + module);
				String path = null;
				fileName = urlfile.getOriginalFilename();
				boolean isFile = StringUtils.endsWithAny(
						StringUtils.lowerCase(fileName), new String[] { ".png",
								".jpg", ".jpeg", ".bmp", ".gif" });
				String sysFileName = UniqueUtils.getOrder() + "."
						+ StringUtils.substringAfter(fileName, ".");
				if (isFile) {
					path = basePath + "/" + sysFileName;
				} else {
					return ResultUtils.returnError("文件格式不正确,上传文件失败");
				}
				urlfile.transferTo(new File(path));
				attachment = new Attachment();
				attachment.setMemory(DoubleUtils.div(urlfile.getSize(),
						1024000.0, 2));
				attachment.setName(fileName);
				attachment.setAddress("upload/" + module +"/"+ sysFileName);
				attachment = attachmentService.saveAndModify(attachment);
				UpLoadUtils.alyUpload(module, sysFileName, path,
						InitParamPc.getInitParam());
				return ResultUtils.returnSuccess("上传成功", attachment);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultUtils.returnError("上传失败");
		}
	}
	

	/**
	 * 接受ckeditor图片
	 * 
	 * @param model
	 * @param upload
	 * @param request
	 * @return
	 */
	@RequestMapping("import-attachment-ckeditor.do")
	@Permission(SystemRole.ADMIN)
	public String importAttachmentOfCkeditor(Model model,
			@RequestParam("upload") MultipartFile upload,
			HttpServletRequest request) {
		String fileName = null;
		Attachment attachment = null;
		String sysFileName = "";
		String module = "ckeditor";
		String imageServerUrl = initParam.getProperties().getProperty(
				"img_server");
		// 判断是否为空
		if (upload.isEmpty()) {
			return "上传文件失败";
		} else {
			// 建立唯一地址
			// 获取服务器文件路径
			String basePath = SpringMVCUtils.getRequest()
					.getRealPath("/upload");
			String path = null;
			// 得到文件名字
			fileName = upload.getOriginalFilename();
			sysFileName = UniqueUtils.getOrder() + "."
					+ StringUtils.substringAfterLast(fileName, ".");
			String realPath = basePath + "/" + module ;
			boolean isFile = StringUtils.endsWithAny(
					StringUtils.lowerCase(fileName), new String[] { ".png",
							".jpg", ".jpeg", ".bmp", ".gif" });
			if (isFile) {
				path = realPath + "/"+ sysFileName;
			} 	else {
				return "上传文件格式错误";
			}
			// 将文件保存到指定文件下面
			try {
				File f = new File(realPath);
				if (!f.exists()) {
					f.mkdir();
				}
				upload.transferTo(new File(path));
				attachment = new Attachment();
				attachment.setMemory(DoubleUtils.div(upload.getSize(),
						1024000.0, 2));
				attachment.setName(fileName);
				attachment.setAddress("upload/" + module + "/" + sysFileName);
				attachmentService.saveAndModify(attachment);
				UpLoadUtils.alyUpload(module, sysFileName, path,
						InitParamPc.getInitParam());
			} catch (Exception e) {
				e.printStackTrace();
				return "上传失败";
			}
		}
		// 设置headers参数
		String result = "";
		// 设置返回“图像”选项卡
		String callback = SpringMVCUtils.getRequest().getParameter(
				"CKEditorFuncNum");
		result = "<script type=\"text/javascript\">";
		String parentWindow = "var parentWindow = (window.parent == window)?window.opener : window.parent;";
		result = result + parentWindow;
		result = result + "parentWindow.CKEDITOR.tools.callFunction("
				+ callback + ",'" + imageServerUrl + "/upload/" + module + "/"
				+ sysFileName + "','')";
		result = result + "</script>";
		model.addAttribute("result", result);
		return "ckeditor/ckeditor-fileupload";
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
		return attachmentService.get(id).getName();
	}
	
	/**
	 * 
	* @Title: newUploadAttachment  
	* @Description: 上传阿里云
	* @author   腾卉 
	* @return Result    返回类型
	 */
	@RequestMapping("newUpload-Attachment.do")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
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

}
