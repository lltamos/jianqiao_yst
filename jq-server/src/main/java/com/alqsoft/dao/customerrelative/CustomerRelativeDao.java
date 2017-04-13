package com.alqsoft.dao.customerrelative;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;

import com.alqsoft.entity.customerrelative.CustomerRelative;

public interface CustomerRelativeDao extends BaseDao<CustomerRelative>{

	@Query("from CustomerRelative c ")
	public List<CustomerRelative> findAllCustomerRelative();

}
