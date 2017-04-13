package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.UserComment;
import com.yst.web.service.UserCommentService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;

@Controller("commentAction")
@Scope("prototype")
public class UserCommentAction extends BaseAction {
	@Resource(name = "userCommentService")
	private UserCommentService userCommentService;
	private UserComment userComment = new UserComment();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();

	public String error() {
		System.out.println("error");
		return "error";
	}
	public String disable() {
		this.userCommentService.updateInfo(userComment);
		return SUCCESS;
	}
	@Override
	public String execute() {
		return LIST;
	}
	@Override
	public UserComment getModel() {
		// TODO Auto-generated method stub
		return userComment;
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
	//业务接口
	public void getCommentList(){
		appResult=this.userCommentService.getCommentList(userComment);
		JSONUtils.sendJSON(appResult);
	}
	
	public void updateAgree(){
		appResult=this.userCommentService.updateAgree(userComment);
		JSONUtils.sendJSON(appResult);
	}
	
	public void addComment(){
		appResult=this.userCommentService.addComment(userComment);
		JSONUtils.sendJSON(appResult);
	}
	
	//ajax
	public void listAjax(){
		List<UserComment> comments = this.userCommentService.getCommentByParame(userComment);
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(comments);
		JSONUtils.sendJSON(appResult);
	}
}
