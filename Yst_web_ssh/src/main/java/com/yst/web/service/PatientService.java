package com.yst.web.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.yst.web.entity.patient.Patient;
import com.yst.web.entity.patient.PatientParams;
import com.yst.web.model.AppResult;
import com.yst.web.utils.BootStrapResult;


public interface PatientService {
	
	List<Patient> selectPatientPage(HttpServletRequest request, HttpServletResponse response);

	AppResult addOrUpdatePatient(Patient patient,MultipartFile[] imagePatients, PatientParams patientParams, HttpServletRequest request, HttpServletResponse response);

	Map<String, Object> findByID(Long id);

	Patient getPatient(Long patientId);

	List<Patient> findPatientManagerPage(HttpServletRequest request, HttpServletResponse response);
	
	BootStrapResult<List<Map<String, Object>>> findIllnessRecordPage(Integer patient_id);

	BootStrapResult<List<Map<String, Object>>> findRelativeMedicineRecordPage(Integer patient_id);
}
