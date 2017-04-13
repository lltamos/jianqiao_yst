package com.alqsoft.controller.doctorserviceorder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.alqframework.webmvc.springmvc.Result;
import org.alqframework.webmvc.springmvc.SpringMVCUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.DoctorServiceOrder;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.model.Permission;
import com.alqsoft.service.doctorserviceorder.DoctorServiceOrderService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

import io.netty.handler.codec.http.HttpResponse;

@RequestMapping("doctorserviceorder")
@Controller
public class DoctorServiceOrderController {
	
	@Autowired
	private DoctorServiceOrderService doctorServiceOrderService;
	
	
	@RequestMapping("to-doctorserviceorder-list")
	@Permission(SystemRole.ADMIN)
	public String toDoctorServiceOrderListPage(){
		
		return "orderrecord/list";
	}
	
	
	/**
	 * 医生后台登陆显示自己的在线咨询订单
	 * @return
	 */
	@RequestMapping("to-doctorservice-list")
	@Permission(SystemRole.DOCTOR)
	public String toDoctorServiceOrderList(){
		
		return "balance/list";
	}
	
	@RequestMapping("doctorservice-list")
	@ResponseBody
	@Permission(SystemRole.DOCTOR)
	public BootStrapResult<List<DoctorServiceOrder>> findDoctor(
			@RequestParam(value="orderId", required=false)String orderId,
			@RequestParam(value="customerPhone", required=false)String customerPhone,
			@RequestParam(value="serviceType", required=false)String serviceType,
			@RequestParam(value="payStatus", required=false)Integer payStatus,
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer length,
			HttpServletRequest request,HttpServletResponse response){
		Integer page=start/length;
		Doctors doctors = (Doctors) request.getSession().getAttribute("doctors");
		Long id = doctors.getId();
		Integer doctorId = Integer.valueOf(id+"");
		return doctorServiceOrderService.findDoctor(orderId, customerPhone,doctorId, serviceType, payStatus, page, length);
	}
	
	/**
	 * 医生订单列表
	 * @param doctorname
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping("doctorserviceorder-list")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<DoctorServiceOrder>> findDoctorByPage(
			@RequestParam(value="orderId", required=false)String orderId,
			@RequestParam(value="customerPhone", required=false)String customerPhone,
			@RequestParam(value="doctorPhone", required=false)String doctorPhone,
			@RequestParam(value="payStatus", required=false)Integer payStatus,
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer length){
		Integer page=start/length;
		return doctorServiceOrderService.findDoctorByPage(orderId, customerPhone, doctorPhone, payStatus, page, length);
	}
	
	/**
	 * 医生订单详情
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("doctorserviceorder-view")
	@Permission(SystemRole.ADMIN)
	public String doctorServiceOrderView(@RequestParam(value="id")Long id,Model model){
		DoctorServiceOrder doctorServiceOrder = doctorServiceOrderService.getDoctorServiceOrder(id);
		model.addAttribute("dsorder",doctorServiceOrder);
		return "orderrecord/show";
	}
	
	/**
	 * 删除当前医生订单
	 * @param model
	 * @param doctorServiceOrder
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping("doctorserviceorder-delete")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result deleteDoctorServiceOrder(Model model,DoctorServiceOrder doctorServiceOrder,HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session){
		if(session.getAttribute(SystemRole.ADMIN.getName())==null){
			return SpringMVCUtils.returnError("登录超时，请重新登录！");
		}
		return this.doctorServiceOrderService.deleted(doctorServiceOrder);
	}
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("doctorserviceorder-update")
	@Permission(SystemRole.ADMIN)
	public String updateDoctorServiceOrder(@RequestParam(value="id")Long id,Model model,HttpServletRequest request,HttpServletResponse response){
		DoctorServiceOrder doctorServiceOrder = doctorServiceOrderService.getDoctorServiceOrder(id);
		model.addAttribute("dsorder",doctorServiceOrder);
		return "orderrecord/update";
	}
	/**
	 * 
	* @Title: mmodifyDoctorServiceOrder 
	* @Description: 修改订单信息
	* @return Result    返回类型 
	* @author 腾卉 
	* @throws
	 */
	@RequestMapping("doctorserviceorder-modify")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result mmodifyDoctorServiceOrder(Model model,
			DoctorServiceOrder doctorServiceOrder,
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session){
		if(session.getAttribute(SystemRole.ADMIN.getName())==null){
			return SpringMVCUtils.returnError("登录超时，请重新登录！");
		}
		return this.doctorServiceOrderService.updateDoctorServiceOrder(doctorServiceOrder);
	}
}
