package com.yst.web.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.yst.web.model.AppResult;
import com.yst.web.model.DicServiceType;
import com.yst.web.model.DoctorServiceOrder;
import com.yst.web.service.DicServiceTypeService;
import com.yst.web.service.DoctorServiceOrderService;
import com.yst.web.utils.BaseAction;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;

@Controller("doctorServiceOrderAction")
@Scope("prototype")
public class DoctorServiceOrderAction extends BaseAction{

	private static final long serialVersionUID = 26073867336977609L;
	@Resource(name = "doctorServiceOrderService")
	private DoctorServiceOrderService doctorServiceOrderService;
	
	@Resource(name="dicServiceTypeService")
	private DicServiceTypeService dicServiceTypeService;
	
	private Map session;
	private HttpServletRequest request;
	private HttpServletResponse response;
	AppResult appResult = new AppResult();
	private DoctorServiceOrder doctorServiceOrder = new DoctorServiceOrder();
	
	
	public void listAjax(){
		appResult = this.doctorServiceOrderService.queryList(doctorServiceOrder.getOrder_id());
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		JSONUtils.sendJSON(appResult);
	}
	
	public void approvalListAjax(){
		appResult = this.doctorServiceOrderService.queryDoctorHomeApprovalList();
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		JSONUtils.sendJSON(appResult);
	}
	
	public String showApproval(){
		doctorServiceOrder = this.doctorServiceOrderService.findDoctorServiceOrderInfo(doctorServiceOrder.getOrder_id());
		return "show_approval";
	}
	
	/**
	 * 跳转审核页面
	 * @return
	 */
	public String toApprovalDoctorHome(){
		doctorServiceOrder = this.doctorServiceOrderService.findDoctorServiceOrderInfo(doctorServiceOrder.getOrder_id());
		return "to_approval";
	}
	
	
	public String deleteAjax(){
		appResult = this.doctorServiceOrderService.deleteDoctorServiceOrder(doctorServiceOrder.getOrder_id());
		JSONUtils.sendJSON(appResult);
		return SUCCESS;
	}
	
	public String showPage(){
		doctorServiceOrder = this.doctorServiceOrderService.findDoctorServiceOrderInfo(doctorServiceOrder.getOrder_id());
		return "show_page";
	}
	
	
	public String updatePage(){
		List<DicServiceType> dicServiceTypesList = this.dicServiceTypeService.selectAll();
		ActionContext.getContext().put("dicServiceTypesList", dicServiceTypesList);
		doctorServiceOrder = this.doctorServiceOrderService.findDoctorServiceOrderInfo(doctorServiceOrder.getOrder_id());
		return "update_page";
	}
	/**
	 * 修改、审核医生服务订单
	 */
	public void updateAjax(){
		appResult = this.doctorServiceOrderService.updateDoctorServiceOrder(doctorServiceOrder);
		JSONUtils.sendJSON(appResult);
	}
	
	
	/**
	 * =====================================================
	 * ========================================以下是接口方法部分===============================
	 * =====================================================
	 */
	/**
	 * 获取医生服务订单记录列表接口
	 */
	public void getDoctorServiceOrders(){
		appResult = this.doctorServiceOrderService.getDoctorServiceOrder(doctorServiceOrder.getDoctor_id());
		JSONUtils.sendJSON(appResult);
	}
	
	
	/**
	 * 医生服务下单接口
	 */
	public void doctorServiceOrders(){
		appResult = this.doctorServiceOrderService.addDoctorServiceOrder(doctorServiceOrder,request);
		JSONUtils.sendJSON(appResult);
	}
	
	/**
	 * 获取医生服务状态信息
	 */
	public void getCustomerServiceOrderInfo(){
		appResult = this.doctorServiceOrderService.findCustomerServiceOrderInfo(doctorServiceOrder.getDoctor_id(),doctorServiceOrder.getCustomer_id());
		JSONUtils.sendJSON(appResult);
	}
	
	/**
	 * 获取我的医疗订单
	 */
	public void getMedicalOrders(){
		appResult = this.doctorServiceOrderService.getMedicalOrders(doctorServiceOrder.getCustomer_id());
		JSONUtils.sendJSON(appResult);
	}

	public AppResult checkServiceTime() {
		appResult = this.doctorServiceOrderService.updateCheckServiceTime();
		return appResult;
	}
	/**
	 * 验证并获取医生咨询时间
	 */
	public void getDoctorConsultTime(){
		appResult = this.doctorServiceOrderService.getDoctorConsultTime(doctorServiceOrder);
		JSONUtils.sendJSON(appResult);
	}
	

	

	@Override
	public void setSession(Map<String, Object> session) {
		this.session= session;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response= response;
	}

	@Override
	public DoctorServiceOrder getModel() {
		return doctorServiceOrder;
	}
}
