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
import com.alqsoft.entity.DicRelation;
import com.alqsoft.model.Permission;
import com.alqsoft.service.dic.DicRelationService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

@Controller
@RequestMapping("/dic/relation")
public class DicRelationController {
	@Autowired
	private DicRelationService dicRelationService;
	@RequestMapping("to-relation-list")
	@Permission(SystemRole.ADMIN)
	public String toRelationList(){
		return "/dicRelation/list";
	}
	
	@RequestMapping("relation-list")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<DicRelation>> relationList(@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			HttpServletRequest request){
		Integer page = start / length;
		return this.dicRelationService.getDicHospitalTypePage(page, length, request);
	}
	
	@RequestMapping("to-relation-add")
	@Permission(SystemRole.ADMIN)
	public String toRelationAdd(){
		return "/dicRelation/add";
	}
	
	@RequestMapping("relation-add")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public AppResult relationAdd(@Valid DicRelation dicRelation , BindingResult bindingResult){
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(!bindingResult.hasErrors()){
			DicRelation saveAndModify = dicRelationService.saveAndModify(dicRelation);
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
	
	
	
	@RequestMapping("to-relation-update")
	@Permission(SystemRole.ADMIN)
	public String toRelationUpdate(Model model,@RequestParam(value = "id", required = false) Long id){
		if(id !=null){
			DicRelation dicRelation = dicRelationService.get(id);
			if(dicRelation!=null){
				model.addAttribute("relation", dicRelation);
			}
		}
		return "/dicRelation/update";
	}
	
	@RequestMapping("relation-delete")
	@Permission(SystemRole.ADMIN)
	public String relationDelete(@RequestParam(value = "id", required = false) Long id){
		if(id !=null){
			dicRelationService.delete(id);
			return "/dicRelation/list";
		}
		return "/dicRelation/list";
	}
}
