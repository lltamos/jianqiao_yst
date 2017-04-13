package com.alqsoft.controller.dic;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.AppResult;
import com.alqsoft.entity.DicHospitalType;
import com.alqsoft.model.Permission;
import com.alqsoft.service.dic.DicHospitalTypeService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

@Controller
@RequestMapping("/dic/hospital")
public class DicHospitalTypeController {
	@Autowired
	private DicHospitalTypeService dicHospitalService;
	
	
	@RequestMapping("to-hospital-list")
	@Permission(SystemRole.ADMIN)
	public String toHospitalList(){
		return "/dicHospitalType/list";
	}
	
	@RequestMapping("hospital-list")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<DicHospitalType>> hospitalList(@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			HttpServletRequest request){
		Integer page = start / length;
		return this.dicHospitalService.getDicHospitalTypePage(page, length, request);
	}
	
	@RequestMapping("to-hospital-add")
	@Permission(SystemRole.ADMIN)
	public String toHospitalAdd(){
		return "/dicHospitalType/add";
	}
	
	@RequestMapping("hospital-add")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public AppResult hospitalAdd(@Valid DicHospitalType dicHospital , BindingResult bindingResult){
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(!bindingResult.hasErrors()){
			DicHospitalType saveAndModify = dicHospitalService.saveAndModify(dicHospital);
			if(saveAndModify!=null){
				appResult.setResult(AppResult.SUCCESS);
				return appResult;
			}else{
				return appResult;
			}
		}else{
			return appResult;
		}
	}
	
	
	
	@RequestMapping("to-hospital-update")
	@Permission(SystemRole.ADMIN)
	public String toHospitalUpdate(Model model,@RequestParam(value = "id", required = false) Long id){
		if(id !=null){
			DicHospitalType dicHospital = dicHospitalService.get(id);
			if(dicHospital!=null){
				model.addAttribute("hospital", dicHospital);
			}
		}
		return "/dicHospitalType/update";
	}
	
	@RequestMapping("hospital-delete")
	@Permission(SystemRole.ADMIN)
	public String hospitalDelete(@RequestParam(value = "id", required = false) Long id){
		if(id !=null){
			dicHospitalService.delete(id);
			return "/dicHospitalType/list";
		}
		return "/dicHospitalType/list";
	}
}
