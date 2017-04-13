package com.alqsoft.controller.patientorder;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.order.PatientOrder;
import com.alqsoft.service.patientorder.PatientOrderService;
import com.alqsoft.utils.BootStrapResult;

/**
 * 患者订单
 * @author zj
 *
 */
@RequestMapping("patientorder")
@Controller
public class PatientOrderController {

	@Autowired
	private PatientOrderService patientOrderService;
	/**
	 * 跳转患者订单列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("to-patientOrder-list")
	public String getPatientPage(Model model,@RequestParam(value="patientId")Integer patientId,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("patientId", patientId);
		return "patient/patient-order-list";
	}
	
	/**
	 * 获取患者订单列表
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping("patientOrder-list")
	@ResponseBody
	public BootStrapResult<List<PatientOrder>> getAjaxPatientOrderList(Model model,@RequestParam(value="patientId")Integer patientId,
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer length){
		Integer page=start/length;
		BootStrapResult<List<PatientOrder>> result = patientOrderService.findPatientOrderByPage(patientId,page,length);
		return result;
	}
	
	/**
	 * 患者订单详情
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("patientOrder-viwe")
	public String patientOrderView(Model model,@RequestParam(value="id")Long id){
		PatientOrder patientOrder = patientOrderService.get(id);
		model.addAttribute("patientOrder", patientOrder);
		model.addAttribute("patientorderid", id);
		return "patient/patient-order-view";
	}
	
}
