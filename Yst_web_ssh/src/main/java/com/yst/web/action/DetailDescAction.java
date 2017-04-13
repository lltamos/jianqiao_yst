package com.yst.web.action;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.DetailDesc;
import com.yst.web.service.DetailDescService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.ServerParam;

@Controller("detailDescAction")
@Scope("prototype")
public class DetailDescAction extends BaseAction {
	@Resource(name = "detailDescService")
	private DetailDescService detailDescService;
	private DetailDesc detailDesc = new DetailDesc();
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
	public DetailDesc getModel() {
		// TODO Auto-generated method stub
		return detailDesc;
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
	
	public void getInfo(){
		appResult = this.detailDescService.getObjectInfo(detailDesc);
		JSONUtils.sendJSON(appResult);
	}
	
	public void updateInfo(){
		appResult = this.detailDescService.saveOrUpdateInfo(detailDesc);
		JSONUtils.sendJSON(appResult);
	}
	
	public void deleteInfo(){
		appResult = this.detailDescService.deleteInfo(detailDesc);
		JSONUtils.sendJSON(appResult);
	}
	
	public void updateOrder(){
		appResult = this.detailDescService.updateOrder(detailDesc);
		JSONUtils.sendJSON(appResult);
	}
	
	public void getShowPage(){
		appResult = this.detailDescService.addShowPage(detailDesc);
		if(appResult.getResult().equals(AppResult.SUCCESS)){
			String url=appResult.getData().toString();
			try {
				response.sendRedirect(ServerParam.DOMAIN+ServerParam.PROJECT_NAME+url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			JSONUtils.sendJSON(appResult);
		}
	}
	
}
