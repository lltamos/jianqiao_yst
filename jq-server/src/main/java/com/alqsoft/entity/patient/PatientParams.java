package com.alqsoft.entity.patient;

import java.io.Serializable;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class PatientParams implements Serializable {
	/**
	 * 患者集合参数类
	 */
	private static final long serialVersionUID = 8262359872097263336L;
	private String[] patientCases;
	private String[] patientHistorys;
	private String[] patientMedicationRecords;
	public String[] getPatientCases() {
		return patientCases;
	}
	public void setPatientCases(String[] patientCases) {
		this.patientCases = patientCases;
	}
	public String[] getPatientHistorys() {
		return patientHistorys;
	}
	public void setPatientHistorys(String[] patientHistorys) {
		this.patientHistorys = patientHistorys;
	}
	public String[] getPatientMedicationRecords() {
		return patientMedicationRecords;
	}
	public void setPatientMedicationRecords(String[] patientMedicationRecords) {
		this.patientMedicationRecords = patientMedicationRecords;
	}
}
