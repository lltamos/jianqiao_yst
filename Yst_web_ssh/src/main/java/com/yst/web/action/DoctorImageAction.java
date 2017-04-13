package com.yst.web.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorImage;
import com.yst.web.service.DoctorImageService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.BeanUtils;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;
@Controller("doctorImageAction")
@Scope("prototype")
public class DoctorImageAction extends BaseAction{
	private static final long serialVersionUID = -7967444210634853973L;
	@Resource(name="doctorImageService")
	private DoctorImageService doctorImageService;
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private DoctorImage doctorImage = new DoctorImage();
	AppResult appResult = new AppResult();
	
	
	public String listPage(){
		ActionContext.getContext().put("doctor_id", doctorImage.getDoctor_id());
		return "list_page";
	}
	
	/**
	 * 获取医生的图文详情列表
	 */
	public void listAjax(){
		appResult = this.doctorImageService.findDoctorImageList(doctorImage.getDoctor_id());
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String addPage(){
		ActionContext.getContext().put("doctor_id", doctorImage.getDoctor_id());
		return "add_page";
	}
	
	
	public void addAjax(){
		BeanUtils.getBase64Image(doctorImage);//转换image为base64字符串
		appResult = this.doctorImageService.addDoctorImage(doctorImage);
		JSONUtils.sendJSON(appResult);
	}
	
	
	public String deleteAjax(){
		appResult = this.doctorImageService.deleteDoctorImage(doctorImage.getDoctor_image_id());
		JSONUtils.sendJSON(appResult);
		return SUCCESS;
	}
	
	
	public String updatePage(){
		doctorImage = this.doctorImageService.getDoctorImage(doctorImage.getDoctor_image_id());
		return "update_page";
	}
	
	
	public void updateAjax(){
		BeanUtils.getBase64Image(doctorImage);//转换image为base64字符串
		appResult = this.doctorImageService.updateDoctorImage(doctorImage);
		JSONUtils.sendJSON(appResult);
	}
	
	
	//以下为业务接口
	
	/**
	 * 获取医生的图片列表接口
	 */
	public void findDoctorImageList(){
		appResult = this.doctorImageService.findDoctorImageList(doctorImage.getDoctor_id());
		JSONUtils.sendJSON(appResult);
	} 
	
	
	/**
	 * 添加一张医生的图片
	 */
	public void addDoctorImage(){
		appResult = this.doctorImageService.addDoctorImage(doctorImage);
		JSONUtils.sendJSON(appResult);
	}
	
	/**
	 * 修改医生图片
	 */
	public void updateDoctorImage(){
		appResult = this.doctorImageService.updateDoctorImage(doctorImage);
		JSONUtils.sendJSON(appResult);
	}
	
	/**
	 * 删除一张医生的图片
	 */
	public void deleteDoctorImage(){
		appResult = this.doctorImageService.deleteDoctorImage(doctorImage.getDoctor_image_id());
		JSONUtils.sendJSON(appResult);
	}
	
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public DoctorImage getModel() {
		return doctorImage;
	}

}
