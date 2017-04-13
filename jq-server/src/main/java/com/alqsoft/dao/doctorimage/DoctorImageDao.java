package com.alqsoft.dao.doctorimage;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.DoctorImage;
import com.alqsoft.entity.doctor.Doctors;

public interface DoctorImageDao extends BaseDao<DoctorImage>{
	
	@Query("from DoctorImage as d where d.doctorId=:doctorId")
	public List<DoctorImage> findById(@Param("doctorId")Long doctorId);
	
	@Query("from Doctors as d where d.id=:doctorId")
	Doctors getDoctorImageByDoctorId(@Param("doctorId")Long doctorId);
	
}
