package com.alqsoft.controller.patient;

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

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.patient.Patient;
import com.alqsoft.entity.patient.PatientParams;
import com.alqsoft.model.AppResult;
import com.alqsoft.model.Permission;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.service.patient.PatientService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

/**
 * 患者控制层
 * @author zj
 *
 */
@RequestMapping("patient")
@Controller
public class PatientController {

	@Autowired
	private PatientService patientService;
	@Autowired
	private CustomerService customerService;
	
	/**
	 * 到患者列表页面
	 * @return
	 */
	@RequestMapping("to-patient-list")
	@Permission(SystemRole.ADMIN)
	public String toPatientPage(){
		return "patient/patient-list";
	}
	
	
	/**
	 * 跳转患者添加页面
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("to-patient-add")
	@Permission(SystemRole.ADMIN)
	public String toPatientAdd(Model model,HttpServletRequest request,HttpServletResponse response){
		List<Customer> customers = this.customerService.findAll();
		model.addAttribute("customers", customers);
		return "patient/patient-add";
	}
	
	/**
	 * 患者列表
	 * @param patientname
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping("patient-list")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<Patient>> findPatientByPage(@RequestParam(value="patientname", required=false)String patientname,
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer length){
		Integer page=start/length;
		BootStrapResult<List<Patient>> result = patientService.findPatientByPage(patientname, page, length);
		return result;
	}
	
	/**
	 * 查看档案详情
	 * @param patientid
	 * @param model
	 * @return
	 */
	@RequestMapping("patient-view")
	@Permission(SystemRole.ADMIN)
	public String patientView(@RequestParam(value="id" )Long patientid,Model model){
		Map<String,Object> patientmap = patientService.getArchivesById(patientid);
		model.addAttribute("patientmap",patientmap);
		return "patient/archives-view";
	}
	
	
	/**
	 * 添加患者
	 * @param model
	 * @param patient
	 * @param patientParams
	 * @param request
	 * @param response
	 */
	@RequestMapping("patient-add")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public AppResult patientAjaxAdd(Model model,Patient patient,@RequestParam(required=false) MultipartFile[] imagePatients,PatientParams patientParams,HttpServletRequest request,HttpServletResponse response){
		AppResult appResult = new AppResult();
		appResult=this.patientService.addOrUpdatePatient(patient,imagePatients,patientParams,request,response);
		return appResult;
	}
	
}
