package com.alqsoft.dao.patientimage;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.patient.PatientImage;

public interface PatientImageDao extends BaseDao<PatientImage>{

	@Query("FROM PatientImage p WHERE p.patient.id=:patientid")
	List<PatientImage> findPatientImageByPatientId(@Param("patientid")Long patientid);
	
	@Modifying
	@Query(value="delete from PatientImage p WHERE p.PatientDisease.id=:patientDiseaseId",nativeQuery=true)
	void deleteByPatientDiseaseId(@Param("patientDiseaseId")Long patientDiseaseId);

}
