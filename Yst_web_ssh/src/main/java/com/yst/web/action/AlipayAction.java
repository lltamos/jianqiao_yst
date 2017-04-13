package com.yst.web.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.Alipay;
import com.yst.web.model.AppResult;
import com.yst.web.service.AlipayService;
import com.yst.web.utils.BaseAction;

@Controller("alipayAction")
@Scope("prototype")
public class AlipayAction extends BaseAction {
	@Resource(name = "alipayService")
	private AlipayService alipayService;
	private Alipay alipay = new Alipay();
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
	public Alipay getModel() {
		// TODO Auto-generated method stub
		return alipay;
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

	public void alipayNotify() throws Exception{
		this.alipayService.alipayNotify(alipay,request,response,session);
	}
	public void alipayReturn() throws Exception{
		this.alipayService.alipayReturn(alipay,request,response,session);
	}
}
