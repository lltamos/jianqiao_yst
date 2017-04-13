package com.alqsoft.controller.doctor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.alqframework.result.Result;
import org.alqframework.result.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.DicHospitalType;
import com.alqsoft.entity.DicOffice;
import com.alqsoft.entity.DicSpec;
import com.alqsoft.entity.DicTitle;
import com.alqsoft.entity.Merchant;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.doctor.Doctors.doctorValidation;
import com.alqsoft.model.Permission;
import com.alqsoft.service.customer.CustomerService;
import com.alqsoft.service.dic.DicHospitalTypeService;
import com.alqsoft.service.dic.DicOfficeService;
import com.alqsoft.service.dic.DicSpecService;
import com.alqsoft.service.dic.DicTitleService;
import com.alqsoft.service.doctor.DoctorService;
import com.alqsoft.service.merchant.MerchantService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

/**
 * 医生-控制层
 * @author Administrator
 *
 */
@RequestMapping("doctor")
@Controller
public class DoctorController {

	@Autowired
	private DoctorService doctorService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private DicSpecService dicSpecService;
	@Autowired
	private DicOfficeService dicOfficeService;
	@Autowired
	private DicHospitalTypeService dicHospitalTypeService;
	@Autowired
	private DicTitleService dicTitleService;
	
	@RequestMapping("to-doctor-list")
	@Permission(SystemRole.ADMIN)
	public String toDoctorListPage(){
		
		return "doctor/doctor-list";
	}
	
	
	/**
	 * 医生列表
	 * @param doctorname
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping("doctor-list")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<Doctors>> findDoctorByPage(@RequestParam(value="doctorphone", required=false)String doctorphone,
			@RequestParam(value="status", required=false)Long status,
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer length){
		Integer page=start/length;
		BootStrapResult<List<Doctors>> result = doctorService.findDoctorByPage(doctorphone,status,page,length);
		return result;
	}
	/**
	 * 到医生申请页面
	 * @return
	 */
	@RequestMapping("to-doctory-apply")
	@Permission(SystemRole.ADMIN)
	public String toApply(Model model){
		List<Customer> customerlist = customerService.findAll();
		List<Merchant> merchantlist = merchantService.findAll();
		List<DicSpec> dicspeclist = dicSpecService.findAll();
		List<DicOffice> dicofficelist = dicOfficeService.findAll();
		List<DicTitle> dictitaillist = dicTitleService.findAll();
		List<DicHospitalType> dichospitaltypelist = dicHospitalTypeService.findAll();
		model.addAttribute("customerlist",customerlist);
		model.addAttribute("merchantlist",merchantlist);
		model.addAttribute("dicspeclist",dicspeclist);
		model.addAttribute("dicofficelist",dicofficelist);
		model.addAttribute("dichospitaltypelist",dichospitaltypelist);
		model.addAttribute("dictitaillist",dictitaillist);
		return "doctor/doctor-apply";
	}
	/**
	 * 医生申请
	 * @param doctor
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("doctor-apply")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result doctorApply(@Validated(value=doctorValidation.class)Doctors doctor,BindingResult bindingResult,@RequestParam(required=false) MultipartFile[] imageHeader){
		Result result =new Result();
		if(!bindingResult.hasErrors()){
			result =doctorService.doctorApply(doctor,imageHeader);
			return result;
		}else{
			return ResultUtils.returnError(bindingResult);
		}
	}
	/**
	 * 医生详情
	 * @param doctorid
	 * @param model
	 * @return
	 */
	@RequestMapping("doctor-view")
	@Permission(SystemRole.ADMIN)
	public String doctorView(@RequestParam(value="doctorid")Long doctorid,Model model){
		Doctors doctors = doctorService.findByDoctorId(doctorid);
		model.addAttribute("doctors",doctors);
		return "doctor/doctor-view";
	}
	
	/**
	 * 医生详情
	 * @param doctorid
	 * @param model
	 * @return
	 */
	@RequestMapping("doctor-apply-view")
	@Permission(SystemRole.ADMIN)
	public String doctApplyView(@RequestParam(value="doctorid")Long doctorid,Model model){
		Doctors doctors = doctorService.findByDoctorId(doctorid);
		model.addAttribute("doctors",doctors);
		return "doctor/doctor-apply-view";
	}
	/**
	 * 到修改医生页面
	 * @param model
	 * @param doctorid
	 * @return
	 */
	@RequestMapping("to-doctor-modfiy")
	@Permission(SystemRole.ADMIN)
	public String toDoctorUpdate(Model model,@RequestParam(value="doctorid")Long doctorid){
		Doctors doctor = doctorService.findByDoctorId(doctorid);
		model.addAttribute("doctor",doctor);
		List<Merchant> merchantlist = merchantService.findAll();
		List<DicSpec> dicspeclist = dicSpecService.findAll();
		List<DicOffice> dicofficelist = dicOfficeService.findAll();
		List<DicTitle> dictitaillist = dicTitleService.findAll();
		List<DicHospitalType> dichospitaltypelist = dicHospitalTypeService.findAll();
		model.addAttribute("merchantlist",merchantlist);
		model.addAttribute("dicspeclist",dicspeclist);
		model.addAttribute("dicofficelist",dicofficelist);
		model.addAttribute("dichospitaltypelist",dichospitaltypelist);
		model.addAttribute("dictitaillist",dictitaillist);
		return "doctor/doctor-modify";
		
	}
	/**
	 * 医生修改
	 * @param doctor
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("doctor-modfiy")
	@Permission(SystemRole.ADMIN)
	@ResponseBody
	public Result doctorModify(@Validated(value=doctorValidation.class)Doctors doctor,BindingResult bindingResult){
		Result result =new Result();
		if(!bindingResult.hasErrors()){
			result =doctorService.doctorUpdate(doctor);
			return result;
		}else{
			return ResultUtils.returnError(bindingResult);
		}
	}
	
	/**
	 * 医生审核
	 * @param doctor
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("doctor-approval")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result doctorApproval(@RequestParam(value="doctorid")Long doctorid,@RequestParam(value="verify")Integer verify){
		Result	result =doctorService.doctorApproval(doctorid,verify);
		return result;
	}
	
	/**
	 * 禁用启用
	 * @param doctorid
	 * @param deletstatus
	 * @return
	 */
	@RequestMapping("doctor-disable")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result doctorDisable(@RequestParam(value="doctorid")Long doctorid){
		Result	result =doctorService.doctorDisable(doctorid);
		return result;
	}
	
	/**
	 * 医生搜索下拉列表
	 * @param doctor
	 * @param bindingResult
	 * @return
	 */
	@RequestMapping("doctor-search-select")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public List<Doctors> doctorSelect(Model model,
			HttpServletRequest request,
			HttpSession session){
		return doctorService.findSelect(request);
	}
	
	/**
	 * 医生审核列表
	 * @return
	 */
	@RequestMapping("to-doctor-apply-list")
	@Permission(SystemRole.ADMIN)
	public String toApplyDoctorListPage(){
		
		return "doctor/doctor-apply-list";
	}
	
	/**
	 * 医生审核列表
	 * @param doctorphone
	 * @param status
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping("doctor-apply-list")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<Doctors>> findApplyDoctorByPage(
			@RequestParam(value="doctorphone", required=false)String doctorphone,
			@RequestParam(value="applyStatus", required=false)Integer applyStatus,
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer length){
		Integer page=start/length;
		BootStrapResult<List<Doctors>> result = doctorService.findApplyDoctorByPage(doctorphone,applyStatus,page,length);
		return result;
	}
	
	/**
	 * 审核医生
	 * @param apply
	 * @param id
	 * @return
	 */
	@RequestMapping("examine-doctor")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public Result examinDoctor(
			@RequestParam(value="apply") Integer apply,
			@RequestParam(value="id") Long id,
			@RequestParam(value="reason") String reason
			){
		Result result = new Result();
		result = doctorService.examineDoctor(apply,id,reason);
		return result;
	}
	
	
}
