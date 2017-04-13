package com.alqsoft.service.productdoctor;

import java.util.List;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.productdoctor.ProductDoctor;

public interface ProductDoctorService extends BaseService<ProductDoctor>{
	List<Doctors> getDoctorByProductId(Long productId);
}
