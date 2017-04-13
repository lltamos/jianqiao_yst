package com.alqsoft.controller.patientmedicationrecord;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alqsoft.entity.patient.PatientMedicationRecord;
import com.alqsoft.service.patientmedicationrecord.PatientMedicationRecordService;
import com.alqsoft.utils.BootStrapResult;

@RequestMapping("patientmedicationrecord")
@Controller
public class PatientMedicationRecordController {

	
	@Autowired
	private PatientMedicationRecordService patientMedicationRecordService;
	
	
	@RequestMapping("to-patientmedicationrecord-list")
	public String toPatientmedicationrecordPage(){
		return "";
	}
	
	/**
	 * 用药记录列表
	 * @param doctorname
	 * @param start
	 * @param length
	 * @return
	 */
	@RequestMapping("patientmedicationrecord-list")
	@ResponseBody
	public BootStrapResult<List<PatientMedicationRecord>> findDoctorByPage(@RequestParam(value="doctorname", required=false)String doctorname,
			@RequestParam(value="start",defaultValue="1") Integer start,
			@RequestParam(value="length",defaultValue="5") Integer length){
		Integer page=start/length;
		BootStrapResult<List<PatientMedicationRecord>> result = patientMedicationRecordService.findPatientMedicationRecordByPage(doctorname,page,length);
		return result;
	}
}
