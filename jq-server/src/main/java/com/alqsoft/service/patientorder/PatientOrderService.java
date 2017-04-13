package com.alqsoft.service.patientorder;

import java.util.List;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.order.PatientOrder;
import com.alqsoft.utils.BootStrapResult;

public interface PatientOrderService extends BaseService<PatientOrder>{

	/**
	 * 患者订单列表
	 * @param patientId
	 * @param page
	 * @param length
	 * @return
	 */
	BootStrapResult<List<PatientOrder>> findPatientOrderByPage(Integer patientId, Integer page, Integer length);

}
