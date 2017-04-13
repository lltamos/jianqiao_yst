package com.alqsoft.service.patient;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.alqframework.orm.BaseService;
import org.springframework.web.multipart.MultipartFile;

import com.alqsoft.entity.patient.Patient;
import com.alqsoft.entity.patient.PatientParams;
import com.alqsoft.model.AppResult;
import com.alqsoft.utils.BootStrapResult;

public interface PatientService extends BaseService<Patient>{

	/**
	 * 患者分页列表
	 * @param doctorname
	 * @param start
	 * @param length
	 * @return
	 */
	public BootStrapResult<List<Patient>> findPatientByPage(String doctorname, Integer start, Integer length);

	AppResult addOrUpdatePatient(Patient patient,MultipartFile[] imagePatients, PatientParams patientParams, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 按照患者id获取患者档案详情
	 * @param patientid
	 * @return
	 */
	public Map<String, Object> getArchivesById(Long patientid);

}
