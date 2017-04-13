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
import com.alqsoft.entity.DicSpec;
import com.alqsoft.model.Permission;
import com.alqsoft.service.dic.DicSpecService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

@Controller
@RequestMapping("/dic/spec")
public class DicSpecController {
	@Autowired
	private DicSpecService dicSpecService;
	@RequestMapping("to-spec-list")
	@Permission(SystemRole.ADMIN)
	public String toSpecList(){
		return "/dicSpec/list";
	}
	
	@RequestMapping("spec-list")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public BootStrapResult<List<DicSpec>> specList(@RequestParam(value = "start", defaultValue = "1") Integer start,
			@RequestParam(value = "length", defaultValue = "5") Integer length,
			HttpServletRequest request){
		Integer page = start / length;
		return this.dicSpecService.getDicSpecPage(page, length, request);
	}
	
	@RequestMapping("to-spec-add")
	@Permission(SystemRole.ADMIN)
	public String toSpecAdd(){
		return "/dicSpec/add";
	}
	
	@RequestMapping("spec-add")
	@ResponseBody
	@Permission(SystemRole.ADMIN)
	public AppResult specAdd(@Valid DicSpec dicSpec , BindingResult bindingResult){
		AppResult appResult = new AppResult();
		appResult.setResult(AppResult.FAILED);
		appResult.setVersion(1);
		if(!bindingResult.hasErrors()){
			DicSpec saveAndModify = dicSpecService.saveAndModify(dicSpec);
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
	
	
	
	@RequestMapping("to-spec-update")
	@Permission(SystemRole.ADMIN)
	public String toSpecUpdate(Model model,@RequestParam(value = "id", required = false) Long id){
		if(id !=null){
			DicSpec dicSpec = dicSpecService.get(id);
			if(dicSpec!=null){
				model.addAttribute("spec", dicSpec);
			}
		}
		return "/dicSpec/update";
	}
	
	@RequestMapping("spec-delete")
	@Permission(SystemRole.ADMIN)
	public String specDelete(@RequestParam(value = "id", required = false) Long id){
		if(id !=null){
			dicSpecService.delete(id);
			return "/dicSpec/list";
		}
		return "/dicSpec/list";
	}
}
