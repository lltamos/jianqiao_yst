package com.alqsoft.dao.patientcase;

import com.alqsoft.entity.patient.PatientCase;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PatientCaseDao extends BaseDao<PatientCase>{

	@Query("from PatientCase pc where pc.patient.id=:patientid")
	List<PatientCase> findPatientCaseByPatientId(@Param("patientid") Long patientid);

}
