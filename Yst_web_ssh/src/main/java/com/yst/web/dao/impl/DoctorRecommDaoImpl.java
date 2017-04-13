package com.yst.web.dao.impl;

import org.springframework.stereotype.Repository;

import com.yst.web.dao.doctor.DoctorRecommDao;
import com.yst.web.model.DoctorRecomm;
import com.yst.web.utils.BaseDaoImpl;

@Repository("doctorRecommDao")
public class DoctorRecommDaoImpl extends BaseDaoImpl implements DoctorRecommDao{
	
	@Override
	public DoctorRecomm findDoctorRecomm() {
		String hql ="from DoctorRecomm as d";
		DoctorRecomm d =(DoctorRecomm) this.getSession().createQuery(hql).uniqueResult();
		return d;
	}
	
}
