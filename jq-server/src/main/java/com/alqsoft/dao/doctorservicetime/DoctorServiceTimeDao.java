package com.alqsoft.dao.doctorservicetime;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.DoctorServiceTime;

public interface DoctorServiceTimeDao  extends BaseDao<DoctorServiceTime>{
	
	@Query("from DoctorServiceTime where doctor_id=:id")
	public DoctorServiceTime getDoctorServiceTimeByCustomer(@Param("id") Long id);
}
