package com.alqsoft.dao.patienthistory;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.patient.PatientHistory;

public interface PatientHistoryDao extends BaseDao<PatientHistory>{

	@Query("from PatientHistory ph where ph.patient.id=:patientid")
	List<PatientHistory> findPatientHistoryByPatientId(@Param("patientid")Long patientid);

}
