package com.alqsoft.dao.doctorserviceorder;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.DoctorServiceOrder;

public interface DoctorServiceOrderDao extends BaseDao<DoctorServiceOrder>{
	@Query("from DoctorServiceOrder  where order_id=:order_id")
	public DoctorServiceOrder getDoctorServiceOrderByorderNo(@Param("order_id") String order_id);
}
