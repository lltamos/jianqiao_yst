package com.yst.web.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction extends ActionSupport implements ModelDriven,SessionAware,ServletRequestAware,ServletResponseAware {
	private static Log logger = LogFactory.getLog(BaseAction.class); 
	protected static final String SUCCESS = "success";
	protected static final String ERROR = "error";
	protected static final String LOGIN_SUCCESS = "login_success";
	protected static final String ADD_SUCCESS = "add_success";
	protected static final String UPDATE_SUCCESS = "update_success";
	protected static final String DELETE_SUCCESS = "delete_success";
	protected static final String LIST = "list";
	protected static final String ADD_INPUT = "add_input";
	protected static final String UPDATE_INPUT = "update_input";
	//要求子类重写Model
	public abstract Object getModel();
	

	//定义通用Action
	public String execute(){
		// TODO Auto-generated method stub
		return LIST;
	}
	
	public String add(){
		return ADD_SUCCESS;
	}
	public String addInput(){
		return ADD_INPUT;
	}
	public String update(){
		return UPDATE_SUCCESS;
	}
	public String delete(){
		return DELETE_SUCCESS;
	}
	public String updateInput() {
		return UPDATE_INPUT;
	}
}
