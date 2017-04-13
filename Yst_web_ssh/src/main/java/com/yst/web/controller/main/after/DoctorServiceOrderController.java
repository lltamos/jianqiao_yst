package com.yst.web.controller.main.after;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.webmvc.servlet.ServletUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.entity.shareMoney.ShareMoney;
import com.yst.web.model.AppResult;
import com.yst.web.model.Doctor;
import com.yst.web.model.DoctorServiceOrder;
import com.yst.web.service.DoctorServiceOrderService;
import com.yst.web.utils.BootStrapResult;
import com.yst.web.utils.PageModelContext;
import com.yst.web.utils.ServerParam;
/**
 * 
 * DoctorServiceOrderController.java
 * @Description: 普惠商城- 医生服务订单
 * @author 朱军
 * @copyright 2015-2016
 * @create-time 2016年11月17日 上午9:52:39
 *
 */
@RequestMapping("ydmvc/main/after/doctorserviceorder")
@Controller
public class DoctorServiceOrderController {

	@Autowired
	private DoctorServiceOrderService doctorServiceOrderService;
	/**
	 * 跳转咨询订单分润列表
	 * @return
	 */
	@RequestMapping("toDoctorServiceOrderPage.do")
	public String toDoctorServiceOrderPage(){
		
		return "doctor/doctorserviceorder/doctorserviceorderlist";
	}
	
	/**
	 * 查询咨询订单分润列表
	 * @param model
	 * @param start
	 * @param pageSize
	 * @param search
	 * @param request
	 * @return
	 */
	/*@RequestMapping("findDoctorServiceOrderByPage.do")
	@ResponseBody
	public AppResult findDoctorServiceOrderByPage(Model model,
		HttpServletRequest request) {
		Doctor doctor = (Doctor) request.getSession().getAttribute(ServerParam.DOCTOR_SESSION);
		
		AppResult appResult = doctorServiceOrderService.findConsultOrderPage(doctor.getDoctor_id());
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		return appResult;
	}*/
	
	@RequestMapping("findDoctorServiceOrderByPage.do")
	@ResponseBody
	public BootStrapResult<List<DoctorServiceOrder>> findDoctorServiceOrderByPage(Model model,
			@RequestParam(value="start",defaultValue="1") Integer start,@RequestParam(value="length",defaultValue="5") Integer pageSize,
			@RequestParam(value="search",required=false) String search,HttpServletRequest request) {
		Map<String, Object> searchParams = ServletUtils.getParametersStartingWith(request, "search_");
		BootStrapResult<List<DoctorServiceOrder>> bootStrapResult=doctorServiceOrderService.findDoctorServiceOrderByPage(searchParams, start, pageSize,request);
		return bootStrapResult;
	}
}
