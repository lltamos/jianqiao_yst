package com.alqsoft.dao.doctorservice;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.DoctorService;

public interface DoctorServiceDao extends BaseDao<DoctorService>{
	@Query("from DoctorService as ds where ds.doctor_id=:doctorId")
	public DoctorService getDoctorServiceByDoctorId(@Param("doctorId")Integer doctorId);
	@Modifying
	@Query("update DoctorService ds set ds.service_online_time_onoff =:onoff where ds.doctor_id=:doctorId")
	public int closeDoctorService(@Param(value = "doctorId") Integer doctorId,@Param(value = "onoff")Integer onoff);

}
