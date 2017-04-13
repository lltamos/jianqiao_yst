package com.yst.web.controller.mobile.view.patient;

import javax.validation.Valid;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.entity.patient.Patient;
import com.yst.web.service.CustomerRelativeService;
import com.yst.web.service.patient.PatientService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年11月19日 下午2:41:11
 * 
 */
@Controller
@RequestMapping("/ydmvc/mobile/view/patient")
public class PatientControllr {

	@Autowired
	public PatientService patientService;
	
	@Autowired
	public CustomerRelativeService customerRelativeService;
	
	/**
	 * 健康档案患者列表
	 */
	@RequestMapping("findPatientArchivesByCid.do")
	@ResponseBody
	public Result findPatientArchivesByCid(
			@RequestParam(value="customerId",required=false)Integer customerId,
			@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="5")Integer rows
			){
		return this.patientService.findPatientArchivesByCid(customerId,page,rows);
	}
	
	/**
	 * 添加患者
	 * @param customerId
	 * @param relation_id
	 * @param patient
	 * @return
	 */
	@RequestMapping("savePatientArchives.do")
	@ResponseBody
	public Result savePatientArchives(
			@RequestParam(value="customerId",required=false)Integer customerId,
			@RequestParam(value="relation_id",required=false)Integer relation_id,
			@Valid Patient patient
			){
		Result result = new Result();
		result = this.patientService.savePatientArchives(patient,customerId,relation_id);
		if(result.getCode() == 1){
			Patient patient2 = (Patient) result.getContent();
			return this.customerRelativeService.savePatientRelative(patient2,customerId);
		}
		return result;
	}
}
