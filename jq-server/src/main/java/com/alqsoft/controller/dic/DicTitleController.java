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
import com.alqsoft.entity.DicTitle;
import com.alqsoft.model.Permission;
import com.alqsoft.service.dic.DicTitleService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

@Controller
@RequestMapping("dic/title")
public class DicTitleController {
	@Autowired
	private DicTitleService dicTitleService;
	@RequestMapping("to-title-list")
	@Permission(SystemRole.ADMIN)
	public String toRelationList(){
		return "/dicTitle/list";
	}
	
	@RequestMapping("title-list")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<DicTitle>> titleList(@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			HttpServletRequest request){
		Integer page = start / length;
		return this.dicTitleService.getDicHospitalTypePage(page, length, request);
	}
	
	@RequestMapping("to-title-add")
	@Permission(SystemRole.ADMIN)
	public String toTitleAdd(){
		return "/dicTitle/add";
	}
	
	@RequestMapping("title-add")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public AppResult titleAdd(@Valid DicTitle dicTitle , BindingResult bindingResult){
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(!bindingResult.hasErrors()){
			DicTitle saveAndModify = dicTitleService.saveAndModify(dicTitle);
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
	
	
	
	@RequestMapping("to-title-update")
	@Permission(SystemRole.ADMIN)
	public String toTitleUpdate(Model model,@RequestParam(value = "id", required = false) Long id){
		if(id !=null){
			DicTitle dicTitle = dicTitleService.get(id);
			if(dicTitle!=null){
				model.addAttribute("title", dicTitle);
			}
		}
		return "/dicTitle/update";
	}
	
	@RequestMapping("title-delete")
	@Permission(SystemRole.ADMIN)
	public String titleDelete(@RequestParam(value = "id", required = false) Long id){
		if(id !=null){
			dicTitleService.delete(id);
			return "/dicTitle/list";
		}
		return "/dicTitle/list";
	}
}
