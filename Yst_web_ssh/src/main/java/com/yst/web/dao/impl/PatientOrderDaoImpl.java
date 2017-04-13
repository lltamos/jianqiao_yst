package com.yst.web.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.yst.web.dao.PatientOrderDao;
import com.yst.web.utils.BaseDaoImpl;

@Repository("patientOrderDao")
public class PatientOrderDaoImpl extends BaseDaoImpl implements PatientOrderDao {

	@Override
	public Map<String,String> get(Long id) {
		return (Map<String, String>) this.getSession().createQuery("from PatientOrder po where po.id = :id").setParameter("id", id).uniqueResult();
	}
	
}
