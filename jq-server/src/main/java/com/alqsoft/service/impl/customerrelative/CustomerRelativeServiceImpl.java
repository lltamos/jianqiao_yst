package com.alqsoft.service.impl.customerrelative;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.customerrelative.CustomerRelativeDao;
import com.alqsoft.entity.customerrelative.CustomerRelative;
import com.alqsoft.service.customerrelative.CustomerRelativeService;
@Service
@Transactional
public class CustomerRelativeServiceImpl implements CustomerRelativeService{

	@Autowired
	private CustomerRelativeDao customerRelativeDao;
	@Override
	public boolean delete(Long arg0) {
		customerRelativeDao.delete(arg0);
		return true;
	}

	@Override
	public CustomerRelative get(Long arg0) {
		return customerRelativeDao.findOne(arg0);
	}

	@Override
	public CustomerRelative saveAndModify(CustomerRelative arg0) {
		return customerRelativeDao.save(arg0);
	}

	@Override
	public List<CustomerRelative> findAll() {
		List<CustomerRelative> customerRelativeList = customerRelativeDao.findAllCustomerRelative();
		return customerRelativeList;
	}

}
