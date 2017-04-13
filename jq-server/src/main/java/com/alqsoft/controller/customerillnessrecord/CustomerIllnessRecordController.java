package com.alqsoft.controller.customerillnessrecord;

import java.util.List;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.customerillnessrecord.CustomerIllnessRecord;
import com.alqsoft.entity.customerrelative.CustomerRelative;
import com.alqsoft.model.Permission;
import com.alqsoft.service.customerillnessrecord.CustomerIllnessRecordService;
import com.alqsoft.service.customerrelative.CustomerRelativeService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.SystemRole;

@RequestMapping("customerillnessrecord")
@Controller
public class CustomerIllnessRecordController {

	@Autowired
	private CustomerIllnessRecordService customerIllnessRecordService;
	@Autowired
	private CustomerRelativeService customerRelativeService;
	
	/**
	 * 到病例记录列表
	 * @return
	 */
	@RequestMapping("to-customerillnessrecord-list")
	@Permission(SystemRole.ADMIN)
	public String toCustomerIllnessRecordPage(){
		return "customerillnessrecord/customerillnessrecord-list";
	}
	
	/**
	 * 到添加病例接口页面
	 * @param model
	 * @return
	 */
	@RequestMapping("to-customerillnessrecord-add")
	@Permission(SystemRole.ADMIN)
	public String toCustomerIllnessRecordAdd(Model model){
		List<CustomerRelative> customerRelativedList = customerRelativeService.findAll();
		model.addAttribute("customerRelativedList",customerRelativedList);
		return "customerillnessrecord/customerillnessrecord-add";
	}
	
	/**
	 * 到修改病例页面
	 * @param model
	 * @param cirid
	 * @return
	 */
	@RequestMapping("to-customerillnessrecord-update")
	@Permission(SystemRole.ADMIN)
	public String toCustomerIllnessRecordUpdate(Model model,@RequestParam(value="cirid") Long cirid){
		List<CustomerRelative> customerRelativeList = customerRelativeService.findAll();
		CustomerIllnessRecord customerIllnessRecord = customerIllnessRecordService.get(cirid);
		model.addAttribute("customerRelativedList",customerRelativeList);
		model.addAttribute("customerIllnessRecord",customerIllnessRecord);
		return "customerillnessrecord/customerillnessrecord-update";
	}
	
	
	/**
	 * 病例记录列表
	 * @param doctorname
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping("customerIllnessRecord-list")
	@Permission(SystemRole.ADMIN)
	@ResponseBody
	public BootStrapResult<List<CustomerIllnessRecord>> findCustomerIllnessRecordByPage(@RequestParam(value="doctorname", required=false)String doctorname,
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer length){
		Integer page=start/length;
		BootStrapResult<List<CustomerIllnessRecord>> result = customerIllnessRecordService.findCustomerIllnessRecordByPage(doctorname,page,length);
		return result;
	}
	
	/**
	 * 添加病例记录
	 * @param customerillnessrecord
	 * @return
	 */
	@RequestMapping("customerIllnessRecord-add")
	@Permission(SystemRole.ADMIN)
	@ResponseBody
	public Result customerillnessrecordAdd(CustomerIllnessRecord customerillnessrecord){
		Result result = customerIllnessRecordService.customerillnessrecordAdd(customerillnessrecord);
		return result;
	}
	
	/**
	 * 修改病例
	 * @param customerillnessrecord
	 * @return
	 */
	@RequestMapping("customerIllnessRecord-update")
	@Permission(SystemRole.ADMIN)
	@ResponseBody
	public Result customerillnessrecordUpdate(CustomerIllnessRecord customerillnessrecord){
		Result result = customerIllnessRecordService.customerillnessrecordUpdate(customerillnessrecord);
		return result;
	}
	
	/**
	 * 删除
	 * @param cirid
	 * @return
	 */
	@RequestMapping("customerIllnessRecord-remove")
	@Permission(SystemRole.ADMIN)
	@ResponseBody
	public Result customerillnessrecordRemove(@RequestParam(value="cirid")Long cirid){
		Result result = customerIllnessRecordService.customerillnessrecordRemove(cirid);
		return result;
	}
	
	/**
	 * 病例记录详情
	 * @param cirid
	 * @param model
	 * @return
	 */
	@RequestMapping("customerillnessrecord-view")
	@Permission(SystemRole.ADMIN)
	public String customerIllnessRecordView(@RequestParam(value="cirid")Long cirid,Model model){
		CustomerIllnessRecord customerIllnessRecord = customerIllnessRecordService.get(cirid);
		model.addAttribute("customerIllnessRecord",customerIllnessRecord);
		return "customerillnessrecord/customerillnessrecord-show";
	}
	
}
