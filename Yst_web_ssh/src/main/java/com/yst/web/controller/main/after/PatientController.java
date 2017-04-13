package com.yst.web.controller.main.after;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yst.web.entity.patient.Patient;
import com.yst.web.entity.patient.PatientParams;
import com.yst.web.model.AppResult;
import com.yst.web.model.Customer;
import com.yst.web.model.RelativeMedicineRecord;
import com.yst.web.service.CustomerService;
import com.yst.web.service.PatientService;
import com.yst.web.utils.BootStrapResult;
import com.yst.web.utils.JSONUtils;
import com.yst.web.utils.PageModelContext;

import jdk.nashorn.internal.runtime.linker.Bootstrap;

/**
 * 
*   
* 项目名称：yst_web  
* 类名称：PatientController  
* 类描述：患者  
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
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	@Resource(name = "customerService")
	private CustomerService customerService;
	AppResult appResult = new AppResult();
	
	/**
	 * 跳转患者列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("patientPage.do")
	public String getPatientPage(Model model,HttpServletRequest request,HttpServletResponse response){
		return "patient/patientList";
	}
	/**
	 * 跳转患者添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addPatientPage.do")
	public String getAddPatientPage(Model model,HttpServletRequest request,HttpServletResponse response){
		List<Customer> customers = this.customerService.selectAll();
		model.addAttribute("customers", customers);
		return "patient/addPatient";
	}
	/**
	 * 跳转患者档案查看
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("viewPatientPage.do")
	public String getViewPatientPage(Model model,Long id,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> resultMap =this.patientService.findByID(id);
		model.addAttribute("result", resultMap);
		return "patient/viewPatient";
	}
	/**
	 * 获取患者列表
	 * @param model
	 * @param request
	 * @param response
	 */
	@RequestMapping("ajaxPatientList.do")
	public void getAjaxPatientList(Model model,HttpServletRequest request,HttpServletResponse response){
		List<Patient> patients = this.patientService.selectPatientPage(request,response);
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(patients);
		JSONUtils.springSendJSON(appResult, response);
	}
	/**
	 * 添加患者
	 * @param model
	 * @param patient
	 * @param patientParams
	 * @param request
	 * @param response
	 */
	@RequestMapping("ajaxAddPatient.do")
	@ResponseBody
	public void getAjaxAddPatient(Model model,Patient patient,@RequestParam(required=false) MultipartFile[] imagePatients,PatientParams patientParams,HttpServletRequest request,HttpServletResponse response){
		appResult=this.patientService.addOrUpdatePatient(patient,imagePatients,patientParams,request,response);
		JSONUtils.springSendJSON(appResult, response);
	}
	/**
	 * 跳转患者管理列表
	 * @return
	 */
	@RequestMapping("toPatientManagerPage.do")
	public String toPatientManagerPage(){
		return "patient/patientsManagerList";
	}
	/**
	 * 获取患者管理列表
	 * @param request
	 */
	@RequestMapping("findPatientManagerPage.do")
	public void findPatientManagerPage(Model model,HttpServletRequest request,HttpServletResponse response){
		List<Patient> patients = this.patientService.findPatientManagerPage(request,response);
		Integer recordsTotal = PageModelContext.getPageModel().getRowCount();
		Integer recordsFiltered = recordsTotal;
		appResult.setRecordsTotal(recordsTotal);
		appResult.setRecordsFiltered(recordsFiltered);
		appResult.setData(patients);
		JSONUtils.springSendJSON(appResult, response);
	}
	/**
	 * 患者详情
	 * @return
	 */
	@RequestMapping("toPatientsDetailPage.do")
	public String toPatientsDetailPage(Model model,@RequestParam(value="patientsId")Long patientsId){
		//Map<String,Object> patientsMap = patientService.findByID(patientsId);
		Patient patient = patientService.getPatient(patientsId);
		model.addAttribute("patient",patient);
		return "patient/patientsManagerDetail";
	}
	/**
	 * 跳转患者病例列表页面
	 * @return
	 */
	@RequestMapping("toIllnessRecordPage.do")
	public String toIllnessRecordPage(Model model,@RequestParam(value="patient_id")Integer patient_id){
		model.addAttribute("patient_id",patient_id);
		return "patient/illnessRecordList";
	}
	/**
	 * 跳转患者用药列表页面
	 * @return
	 */
	@RequestMapping("toRelativeMedicineRecordPage.do")
	public String toRelativeMedicineRecordPage(Model model,@RequestParam(value="patient_id")Integer patient_id){
		model.addAttribute("patient_id",patient_id);
		return "patient/relativeMedicineRecordList";
	}
	/**
	 * 获取患者管理病例列表
	 * @param patient_id
	 * @return
	 */
	@RequestMapping("findIllnessRecordPage.do")
	@ResponseBody
	public BootStrapResult<List<Map<String, Object>>> findIllnessRecordPage(@RequestParam(value="patient_id")Integer patient_id){
		BootStrapResult<List<Map<String, Object>>> result = patientService.findIllnessRecordPage(patient_id);
		return result;
	}
	/**
	 * 获取患者管理用药列表
	 * @param patient_id
	 * @return
	 */
	@RequestMapping("findRelativeMedicineRecordPage.do")
	@ResponseBody
	public BootStrapResult<List<Map<String, Object>>> findRelativeMedicineRecordPage(@RequestParam(value="patient_id")Integer patient_id){
		BootStrapResult<List<Map<String, Object>>> result = patientService.findRelativeMedicineRecordPage(patient_id);
		return result;
	}
	
	
}
