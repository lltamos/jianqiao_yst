package com.yst.web.controller.mobile.view.patientDisease;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.validation.Valid;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yst.web.entity.patient.PatientDisease;
import com.yst.web.service.patient.PatientDiseaseService;

/**
 * 
 * @Description: TODO
 * @author 黄鑫
 * @e-mail zhangzhaocan@yeah.net
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年4月24日 上午10:22:35
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
@RequestMapping("/ydmvc/mobile/view/patientDisease")
@Controller
public class PatientDiseaseController {
	
	@Autowired
	private PatientDiseaseService patientDiseaseService;
	
	/**
	 * 患者求医
	 * @param page
	 * @param rows
	 * @param patientId
	 * @return
	 */
	@RequestMapping("getPatientDiseaseList.do")
	@ResponseBody
	public Result getPatientDiseaseList(@RequestParam(value="page",defaultValue="1")Integer page,
			@RequestParam(value="rows",defaultValue="9")Integer rows){
		return patientDiseaseService.getPatientDiseaseList(page, rows);
	}
	
	/**
	 * 内容详情
	 * @param patientDiseaseId
	 * @return
	 */
	@RequestMapping("getPatientDisease.do")
	@ResponseBody
	public Result getPatientDisease(@RequestParam(value="patientDiseaseId",required=false)Integer patientDiseaseId,
			@RequestParam(value="customerId",required=false)Integer customerId){
		return patientDiseaseService.getPatientDisease(patientDiseaseId,customerId);
	}
	
	/**
	 * 删除
	 * @param patientDiseaseId
	 * @param customerId
	 * @return
	 */
	@RequestMapping("deletePatientDisease.do")
	@ResponseBody
	public Result deletePatientDisease(@RequestParam(value="patientDiseaseId",required=false)Integer patientDiseaseId,
			@RequestParam(value="customerId",required=false)Integer customerId){
		return patientDiseaseService.deletePatientDisease(patientDiseaseId, customerId);
	}
	
	/**
	 * 添加内容()
	 * @param patientDisease
	 * @param imageId
	 * @param patientId
	 * @return
	 */
	@RequestMapping("addPatientDisease.do")
	@ResponseBody
	public Result addPatientDisease(@Valid PatientDisease patientDisease, 
			@RequestParam(value="imageId",required=false)String imageId,
			@RequestParam(value="customerId",required=false)Integer customerId){
		return patientDiseaseService.addPatientDisease(patientDisease, imageId, customerId);
	}
	/**
	 * 添加内容(IOS)
	 * @param patientDisease
	 * @param imageId
	 * @param patientId
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("addPatientDiseaseios.do")
	@ResponseBody
	public Result addPatientDiseaseios(@Valid PatientDisease patientDisease, 
			@RequestParam(value="imageId",required=false)String imageId,
			@RequestParam(value="customerId",required=false)Integer customerId) throws UnsupportedEncodingException{
		patientDisease.setDiseaseDesc(URLDecoder.decode(patientDisease.getDiseaseDesc(),"UTF-8"));
		patientDisease.setNickName(URLDecoder.decode(patientDisease.getNickName(),"UTF-8"));
		patientDisease.setPatientAddress(URLDecoder.decode(patientDisease.getPatientAddress(),"UTF-8"));
		return patientDiseaseService.addPatientDisease(patientDisease, imageId, customerId);
	}
	
}
