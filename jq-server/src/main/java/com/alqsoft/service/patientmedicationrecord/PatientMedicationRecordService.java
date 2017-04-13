package com.alqsoft.service.patientmedicationrecord;

import java.util.List;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.patient.PatientMedicationRecord;
import com.alqsoft.utils.BootStrapResult;

public interface PatientMedicationRecordService extends BaseService<PatientMedicationRecord>{

	/**
	 * 用药记录列表
	 * @param doctorname
	 * @param page
	 * @param length
	 * @return
	 */
	public BootStrapResult<List<PatientMedicationRecord>> findPatientMedicationRecordByPage(String doctorname, Integer page,
			Integer length);

}
