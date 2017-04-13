package com.yst.web.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.DoctorComment;
import com.yst.web.service.DoctorCommentService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
@Controller("doctorCommentAction")
@Scope("prototype")
public class DoctorCommentAction extends BaseAction{

	private static final long serialVersionUID = -8724368999932859021L;
	@Resource(name = "doctorCommentService")
	private DoctorCommentService doctorCommentService;
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	private DoctorComment doctorComment = new DoctorComment();
	
	/**
	 * 获取医生评价列表接口
	 */
	/*public void getDoctorCommentList(){
		appResult = this.doctorCommentService.getDoctorCommentList(doctorComment);
		JSONUtils.sendJSON(appResult);
	}*/
	/**
	 * 添加医生评价列表接口
	 */
	/*public void addDoctorComment(){
		appResult = this.doctorCommentService.addDoctorComment(doctorComment);
		JSONUtils.sendJSON(appResult);
	}*/
	
	
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
	public DoctorComment getModel() {
		return doctorComment;
	}

}
