package com.yst.web.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yst.web.dao.PatientDao;
import com.yst.web.utils.BaseDaoImpl;

@Repository("patientDao")
public class PatientDaoImpl extends BaseDaoImpl implements PatientDao {

	@Override
	public Map<String,String> get(Long id) {
		return (Map<String, String>) this.getSession().createQuery("from Patient p where p.id = :id").setParameter("id", id).uniqueResult();
	}
	
}
