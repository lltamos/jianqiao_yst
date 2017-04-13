package com.yst.web.dao.doctor;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yst.web.entity.doctor.Doctors;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年6月20日 上午11:25:34
 * 
 */
public interface DoctorsDao extends BaseDao<Doctors>{

	@Query("from Doctors as d where product_type_id=:product_id_type and d.verify=1")
	List<Doctors> getDoctorByProductTypeId(@Param("product_id_type")Integer product_id_type);

	@Query("from Doctors as d where d.doctor_id=:doctor_id")
	Doctors getDoctorById(@Param("doctor_id")Integer doctor_id);

	/*@Query("from Doctor as d where d.product_type_id=:product_type_id")
	Page<Doctors> findDoctorByProductTypeId(@Param("product_type_id")Integer product_type_id, PageRequest pageRequest);*/

}
