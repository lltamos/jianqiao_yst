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
import com.alqsoft.entity.DicServiceType;
import com.alqsoft.model.Permission;
import com.alqsoft.service.dic.DicServiceTypeService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

@Controller
@RequestMapping("dic/service")
public class DicServiceTypeController {
	@Autowired
	private DicServiceTypeService dicServiceTypeService;
	@RequestMapping("to-service-list")
	@Permission(SystemRole.ADMIN)
	public String toServiceList(){
		return "/dicServiceType/list";
	}
	
	@RequestMapping("service-list")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<DicServiceType>> serviceList(@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			HttpServletRequest request){
		Integer page = start / length;
		return this.dicServiceTypeService.getDicHospitalTypePage(page, length, request);
	}
	
	@RequestMapping("to-service-add")
	@Permission(SystemRole.ADMIN)
	public String toServiceAdd(){
		return "/dicServiceType/add";
	}
	
	@RequestMapping("service-add")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public AppResult serviceAdd(@Valid DicServiceType dicService , BindingResult bindingResult){
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(!bindingResult.hasErrors()){
			DicServiceType saveAndModify = dicServiceTypeService.saveAndModify(dicService);
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
	
	
	
	@RequestMapping("to-service-update")
	@Permission(SystemRole.ADMIN)
	public String toServiceUpdate(Model model,@RequestParam(value = "id", required = false) Long id){
		if(id !=null){
			DicServiceType dicService = dicServiceTypeService.get(id);
			if(dicService!=null){
				model.addAttribute("service", dicService);
			}
		}
		return "/dicServiceType/update";
	}
	
	@RequestMapping("service-delete")
	@Permission(SystemRole.ADMIN)
	public String serviceDelete(@RequestParam(value = "id", required = false) Long id){
		if(id !=null){
			dicServiceTypeService.delete(id);
			return "/dicServiceType/list";
		}
		return "/dicServiceType/list";
	}
}