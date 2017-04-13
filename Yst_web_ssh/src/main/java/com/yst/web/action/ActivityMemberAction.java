package com.yst.web.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.ActivityMember;
import com.yst.web.model.AppResult;
import com.yst.web.service.ActivityService;
import com.yst.web.utils.BaseAction;

@Controller("activityMemberAction")
@Scope("prototype")
public class ActivityMemberAction extends BaseAction {
	@Resource(name = "activityService")
	private ActivityService activityService;
	private ActivityMember activityMember = new ActivityMember();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();

	public String error() {
		System.out.println("error");
		return "error";
	}

	@Override
	public String execute() {
		return LIST;
	}
	
	@Override
	public ActivityMember getModel() {
		// TODO Auto-generated method stub
		return activityMember;
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

}
