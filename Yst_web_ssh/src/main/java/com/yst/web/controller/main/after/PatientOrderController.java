package com.yst.web.controller.main.after;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yst.web.entity.order.PatientOrder;
import com.yst.web.entity.patient.Patient;
import com.yst.web.model.AppResult;
import com.yst.web.model.Product;
import com.yst.web.service.PatientOrderService;
import com.yst.web.service.PatientService;
import com.yst.web.service.ProductService;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;

/**
 * 
*   
* 项目名称：yst_web  
* 类名称：ProductOrderController  
* 类描述：订单  
* 创建人：whl  
* 创建时间：2016年2月22日 下午5:11:31  
* 修改人：whl  
* 修改时间：2016年2月22日 下午5:11:31  
* 修改备注：  
* @version   
*
 */
@RequestMapping("ydmvc/main/after")
@Controller
public class PatientOrderController {
	
	@Autowired
	private PatientOrderService patientOrderService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private ProductService productService;
	AppResult appResult = new AppResult();
	
	/**
	 * 跳转患者订单列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("patientOrderPage.do")
	public String getPatientPage(Model model,Integer patientId,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("patientId", patientId);
		return "patient/patientOrderList";
	}
	
	/**
	 * 获取患者订单列表
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping("ajaxPatientOrderList.do")
	public void getAjaxPatientOrderList(Model model,Integer patientId,HttpServletRequest request,HttpServletResponse response){
		List<PatientOrder> patientOrders = this.patientOrderService.findByOtherColumn("patientId",patientId);
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(patientOrders);
		JSONUtils.springSendJSON(appResult, response);
	}
	/**
	 * 跳转添加患者订单页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addPatientOrderPage.do")
	public String getAddPatientPage(Model model,Long patientId,HttpServletRequest request,HttpServletResponse response){
		Patient patient = this.patientService.getPatient(patientId);
		model.addAttribute("patient", patient);
		List<Product> products=this.productService.findByOtherColumn("is_real", 1);
		model.addAttribute("products", products);
		return "patient/addPatientOrder";
	}
	/**
	 * 跳转患者订单详情
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("viewPatientOrderPage.do")
	public String getViewPatientPage(Model model,Long id,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> resultMap =this.patientOrderService.findByID(id);
		model.addAttribute("result", resultMap);
		return "patient/viewPatientOrder";
	}
	/**
	 * 添加患者订单
	 * @param model
	 * @param patient
	 * @param patientParams
	 * @param request
	 * @param response
	 */
	@RequestMapping("ajaxAddPatientOrder.do")
	@ResponseBody
	public void getAjaxAddPatientOrder(Model model,PatientOrder patientOrder,HttpServletRequest request,HttpServletResponse response){
		appResult=this.patientOrderService.addPatientOrder(patientOrder,request,response);
		JSONUtils.springSendJSON(appResult, response);
	}
	
	/**
	 * 添加患者订单
	 * @param model
	 * @param patient
	 * @param patientParams
	 * @param request
	 * @param response
	 */
	@RequestMapping("ajaxUpdatePatientOrder.do")
	@ResponseBody
	public void getAjaxUpdatePatientOrder(Model model,@RequestParam(required=false) MultipartFile uploadFile,PatientOrder patientOrder,HttpServletRequest request,HttpServletResponse response){
		appResult=this.patientOrderService.updatePatientOrder(uploadFile,patientOrder,request,response);
		JSONUtils.springSendJSON(appResult, response);
	}
	/**
	 * 跳转PC端模板
	 * @param model
	 * @param tourl
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("model.do")
	public String getModel(Model model,@RequestParam(value = "tourl", required = false) String tourl,HttpServletRequest request,HttpServletResponse response){
		model.addAttribute("tourl", tourl);
		return "patient/modelPage";
	}
}
