package com.yst.web.service.patient;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.yst.web.entity.patient.Patient;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年11月19日 下午2:43:06
 * 
 */
public interface PatientService extends BaseService<Patient>{

	/**
	 * 健康档案患者列表
	 * @param customerId
	 * @param rows 
	 * @param page 
	 * @return
	 */
	public Result findPatientArchivesByCid(Integer customerId, Integer page, Integer rows);

	/**
	 * 添加患者档案
	 * @param patient
	 * @param customerId 
	 * @param relation_id 
	 * @return
	 */
	public Result savePatientArchives(Patient patient, Integer customerId, Integer relation_id);
}
