package com.alqsoft.dao.patientmedicationrecord;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.patient.PatientMedicationRecord;

public interface PatientMedicationRecordDao extends BaseDao<PatientMedicationRecord>{

	@Query("from PatientMedicationRecord pmr where pmr.patient.id=:patientid")
	List<PatientMedicationRecord> findPatientMedicationRecordByPatientId(@Param("patientid")Long patientid);

}
