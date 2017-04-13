package com.yst.web.service.patient;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.yst.web.entity.patient.PatientDisease;

/**
 * 
 * @Description: TODO
 * @author 黄鑫
 * @e-mail zhangzhaocan@yeah.net
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年4月22日 下午6:10:40
 * Copyright © 2013 北京易商通公司 All rights reserved.
 * 
 */
public interface PatientDiseaseService extends BaseService<PatientDisease>{

	/**
	 * 患者求医列表
	 * @param page
	 * @param rows
	 * @param customerId
	 * @return
	 */
	public Result getPatientDiseaseList(Integer page,Integer rows);
	
	/**
	 * 内容详情
	 * @param patientDiseaseId
	 * @return
	 */
	public Result getPatientDisease(Integer patientDiseaseId, Integer customerId);
	
	/**
	 * 删除内容
	 * @param patientDiseaseId
	 * @return
	 */
	public Result deletePatientDisease(Integer patientDiseaseId,Integer customerId);
	
	/**
	 * 添加内容
	 * @param patientDisease
	 * @param customerId
	 * @return
	 */
	public Result addPatientDisease(PatientDisease patientDisease, String imageId,Integer customerId);


}
