package com.alqsoft.service.impl.attachment;

import java.io.File;
import java.lang.reflect.Method;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.alqframework.utils.UniqueUtils;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.dao.attachment.AttachmentDao;
import com.alqsoft.entity.attachment.Attachment;
import com.alqsoft.init.InitParamPc;
import com.alqsoft.rpc.RpcAttachmentService;
import com.alqsoft.service.attachment.AttachmentService;
import com.alqsoft.utils.oss.UpLoadUtils;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月20日 上午10:17:13
 * 
 */
@Service
public class AttachmentServiceImpl implements AttachmentService{

	private static Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);
	
	@Autowired
	private InitParamPc initParam;
	
	@Autowired
	private RpcAttachmentService rpcAttachmentService;
	
	@Autowired
	private AttachmentDao attachmentDao;
	
	@Override
	public Result mobileUploadAttachment(MultipartFile urlfile, Object[] backUrl, String module, String[] extendFile) {
		String fileName = null;
		Attachment attachment=null;
		try {
			if (urlfile.isEmpty()) {
				return ResultUtils.returnError("上传文件失败");
			} else {
				
					String basePath = SpringMVCUtils.getRequest().getRealPath("/upload/" + module);
					String path = null;
					fileName = urlfile.getOriginalFilename();
				
					boolean isFile = StringUtils.endsWithAny(StringUtils.lowerCase(fileName),extendFile );
					String sysFileName = UniqueUtils.getOrder() + "." + StringUtils.substringAfter(fileName, ".");
					if (isFile) {
						path = basePath + "\\" + sysFileName;
					} else {
						return ResultUtils.returnError("文件格式不正确,上传文件失败");
					}
				
					urlfile.transferTo(new File(path));
					
					attachment = new Attachment();
					attachment.setName(fileName);
					attachment.setAddress("upload/" + module +"/"+ sysFileName);
					UpLoadUtils.alyUpload(module, sysFileName, path,initParam);//上传
					
					
					Object obj=backUrl[0];
					Class<? extends Object> clazz=obj.getClass();
					Method method=clazz.getDeclaredMethod(backUrl[1].toString(), attachment.getClass());
					
					Object returnObj=method.invoke(obj,attachment);
					System.out.println(returnObj);
					if(returnObj==null){
						logger.error("上传图片回调方法返回数据为空");
						return ResultUtils.returnError("上传失败");
					}
						
					return (Result) returnObj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();//回滚数据
			
			logger.error("头像图片上传发生异常:"+e.getMessage());
			
			return ResultUtils.returnError("上传失败");
		}
	}
	

	@Override
	public Result saveAttachment(Attachment attachment) {
		attachment = rpcAttachmentService.saveAttachment(attachment);
		System.out.println("attachment="+attachment);
		return ResultUtils.returnSuccess("上传成功", attachment);
	}


	@Override
	public Attachment getOneById(Long id) {
		return attachmentDao.getOneById(id);
	}

}
