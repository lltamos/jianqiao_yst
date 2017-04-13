package com.alqsoft.controller.relativemedicinerecord;

import java.util.List;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.customerrelative.CustomerRelative;
import com.alqsoft.entity.relativemedicinerecord.RelativeMedicineRecord;
import com.alqsoft.model.Permission;
import com.alqsoft.service.customerrelative.CustomerRelativeService;
import com.alqsoft.service.relativemedicinerecord.RelativeMedicineRecordService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

/**
 * 用药记录控制层
 * @author zj
 *
 */
@RequestMapping("relativemedicinerecord")
@Controller
public class RelativeMedicineRecordController {

	
	@Autowired
	private RelativeMedicineRecordService relativeMedicineRecordService;
	@Autowired
	private CustomerRelativeService customerRelativeService;
	
	/**
	 * 到用药列表
	 * @return
	 */
	@RequestMapping("to-relativemedicinerecord-list")
	@Permission(SystemRole.ADMIN)
	public String toRelativeMedicineRecordPage(){
		
		return "relativemedicinerecord/relativemedicinerecord-list";
	}
	/**
	 * 用药记录列表
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping("relativemedicinerecord-list")
	@Permission(SystemRole.ADMIN)
	@ResponseBody
	public BootStrapResult<List<RelativeMedicineRecord>> findDoctorByPage(
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer length){
		Integer page=start/length;
		BootStrapResult<List<RelativeMedicineRecord>> result = relativeMedicineRecordService.findRelativeMedicineRecordByPage(page,length);
		return result;
	}
	
	/**
	 * 到添加用药接口页面
	 * @param model
	 * @return
	 */
	@RequestMapping("to-relativemedicinerecord-add")
	@Permission(SystemRole.ADMIN)
	public String toRelativeMedicineRecordAdd(Model model){
		List<CustomerRelative> customerRelativedList = customerRelativeService.findAll();
		model.addAttribute("customerRelativedList",customerRelativedList);
		return "relativemedicinerecord/relativemedicinerecord-add";
	}
	
	/**
	 * 到修改用药页面
	 * @param model
	 * @param cirid
	 * @return
	 */
	@RequestMapping("to-relativemedicinerecord-update")
	@Permission(SystemRole.ADMIN)
	public String toCustomerIllnessRecordUpdate(Model model,@RequestParam(value="cirid") Long cirid){
		List<CustomerRelative> customerRelativeList = customerRelativeService.findAll();
		RelativeMedicineRecord relativemedicinerecord = relativeMedicineRecordService.get(cirid);
		model.addAttribute("customerRelativedList",customerRelativeList);
		model.addAttribute("relativemedicinerecord",relativemedicinerecord);
		return "relativemedicinerecord/relativemedicinerecord-update";
	}
	
	
	/**
	 * 添加用药记录
	 * @param relativeMedicineRecord
	 * @return
	 */
	@RequestMapping("relativemedicinerecord-add")
	@Permission(SystemRole.ADMIN)
	@ResponseBody
	public Result relativeMedicineRecordAdd(RelativeMedicineRecord relativemedicinerecord/*,@RequestParam(value="takingDate")String takingDate*/){
		Result result = relativeMedicineRecordService.relativeMedicineRecordAdd(relativemedicinerecord);
		return result;
	}
	
	/**
	 * 修改用药
	 * @param relativeMedicineRecord
	 * @return
	 */
	@RequestMapping("relativemedicinerecord-update")
	@Permission(SystemRole.ADMIN)
	@ResponseBody
	public Result relativeMedicineRecordUpdate(RelativeMedicineRecord relativeMedicineRecord){
		Result result = relativeMedicineRecordService.relativeMedicineRecordUpdate(relativeMedicineRecord);
		return result;
	}
	
	/**
	 * 删除
	 * @param cirid
	 * @return
	 */
	@RequestMapping("relativemedicinerecord-remove")
	@Permission(SystemRole.ADMIN)
	@ResponseBody
	public Result relativeMedicineRecordRemove(@RequestParam(value="cirid")Long cirid){
		Result result = relativeMedicineRecordService.relativeMedicineRecordRemove(cirid);
		return result;
	}
	
	/**
	 * 用药记录详情
	 * @param cirid
	 * @param model
	 * @return
	 */
	@RequestMapping("relativeMedicineRecord-view")
	@Permission(SystemRole.ADMIN)
	public String relativemedicinerecordView(@RequestParam(value="cirid")Long cirid,Model model){
		RelativeMedicineRecord relativemedicinerecord = relativeMedicineRecordService.get(cirid);
		model.addAttribute("relativemedicinerecord",relativemedicinerecord);
		return "relativemedicinerecord/relativemedicinerecord-show";
	}
}
