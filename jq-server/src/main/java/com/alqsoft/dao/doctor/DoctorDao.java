package com.alqsoft.dao.doctor;

import javax.persistence.LockModeType;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.doctor.Doctors;

public interface DoctorDao extends BaseDao<Doctors>{
	
	@Query("from Doctors where customerId=:customerId")
	public Doctors getDoctorsByCustomerId(@Param("customerId") Long customerId);

	@Query("from Doctors where id=:id")
	public Doctors getDoctorById(@Param("id")Long id);

	@Lock(value=LockModeType.PESSIMISTIC_WRITE)
	@Query("from Doctors where id=:id")
	public Doctors getlock(@Param("id")Long id);
}
