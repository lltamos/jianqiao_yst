package com.yst.web.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.yst.web.entity.order.PatientOrder;
import com.yst.web.model.AppResult;


public interface PatientOrderService {
	
	List<PatientOrder> selectPatientPage(HttpServletRequest request, HttpServletResponse response);

	AppResult addPatientOrder(PatientOrder patientOrder, HttpServletRequest request, HttpServletResponse response);

	Map<String, Object> findByID(Long id);
	/**
	 * 非主键查询
	 * @param string
	 * @param patientId
	 * @return
	 */
	public List<PatientOrder> findByOtherColumn(String column, Object value);
	/**
	 * 修改订单状态
	 * @param uploadFile 
	 * @param patientOrder
	 * @param request
	 * @param response
	 * @return
	 */
	AppResult updatePatientOrder(MultipartFile uploadFile, PatientOrder patientOrder,
			HttpServletRequest request, HttpServletResponse response);
	
}
