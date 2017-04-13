package com.yst.web.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yst.web.model.AppResult;
import com.yst.web.model.RelativeMedicineImage;
import com.yst.web.service.RelativeMedicineImageService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
@Controller("relativeMedicineImageAction")
@Scope("prototype")
public class RelativeMedicineImageAction extends BaseAction{

	private static final long serialVersionUID = -3579452340864506421L;

	@Resource(name = "relativeMedicineImageService")
	private RelativeMedicineImageService relativeMedicineImageService;
	AppResult appResult = new AppResult();
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	RelativeMedicineImage relativeMedicineImage = new RelativeMedicineImage();
	
	
	public void addRelativeMedicineImage(){
		appResult = this.relativeMedicineImageService.addRelativeMedicineImage(relativeMedicineImage);
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
	public RelativeMedicineImage getModel() {
		return relativeMedicineImage;
	}

}
