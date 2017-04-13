package com.alqsoft.dao.productdoctor;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.productdoctor.ProductDoctor;


public interface ProductDoctorDao extends BaseDao<ProductDoctor>{
	@Query("select d FROM ProductDoctor o,Doctors d WHERE o.productId=:productId and o.doctorId=d.id and d.applyStatus=1 and d.deleted=0")
	List<Doctors> getDoctorByProductId(@Param("productId")Long productId);
	@Modifying
	@Query(value="delete from product_doctor WHERE product_id=:productId",nativeQuery=true)
	void deleteByProductId(@Param("productId")Long productId);
}
