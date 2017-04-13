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
import com.alqsoft.entity.DicOffice;
import com.alqsoft.model.Permission;
import com.alqsoft.service.dic.DicOfficeService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

@Controller
@RequestMapping("/dic/office")
public class DicOfficeController {
	@Autowired
	private DicOfficeService dicOfficeService;
	@RequestMapping("to-office-list")
	@Permission(SystemRole.ADMIN)
	public String toOfficeList(){
		return "/dicOffice/list";
	}
	
	@RequestMapping("office-list")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<DicOffice>> officeList(@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			HttpServletRequest request){
		Integer page = start / length;
		return this.dicOfficeService.getDicOfficePage(page, length, request);
	}
	
	@RequestMapping("to-office-add")
	@Permission(SystemRole.ADMIN)
	public String toOfficeAdd(){
		return "/dicOffice/add";
	}
	
	@RequestMapping("office-add")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public AppResult officeAdd(@Valid DicOffice dicOffice , BindingResult bindingResult){
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(!bindingResult.hasErrors()){
			DicOffice saveAndModify = dicOfficeService.saveAndModify(dicOffice);
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
	
	
	
	@RequestMapping("to-office-update")
	@Permission(SystemRole.ADMIN)
	public String toOfficeUpdate(Model model,@RequestParam(value = "id", required = false) Long id){
		if(id !=null){
			DicOffice dicOffice = dicOfficeService.get(id);
			if(dicOffice!=null){
				model.addAttribute("office", dicOffice);
			}
		}
		return "/dicOffice/update";
	}
	
	@RequestMapping("office-delete")
	@Permission(SystemRole.ADMIN)
	public String officeDelete(@RequestParam(value = "id", required = false) Long id){
		if(id !=null){
			dicOfficeService.delete(id);
			return "/dicOffice/list";
		}
		return "/dicOffice/list";
	}
}
