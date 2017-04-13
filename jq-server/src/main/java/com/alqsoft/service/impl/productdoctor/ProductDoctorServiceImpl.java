package com.alqsoft.service.impl.productdoctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.productdoctor.ProductDoctorDao;
import com.alqsoft.entity.doctor.Doctors;
import com.alqsoft.entity.productdoctor.ProductDoctor;
import com.alqsoft.service.productdoctor.ProductDoctorService;


@Service
public class ProductDoctorServiceImpl implements ProductDoctorService {

	@Autowired
	private ProductDoctorDao productDoctorDao;
	
	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ProductDoctor get(Long arg0) {
		// TODO Auto-generated method stub
		return productDoctorDao.findOne(arg0);
	}

	@Override
	public ProductDoctor saveAndModify(ProductDoctor arg0) {
		// TODO Auto-generated method stub
		return productDoctorDao.save(arg0);
	}

	@Override
	public List<Doctors> getDoctorByProductId(Long productId) {
		return this.productDoctorDao.getDoctorByProductId(productId);
	}

}
