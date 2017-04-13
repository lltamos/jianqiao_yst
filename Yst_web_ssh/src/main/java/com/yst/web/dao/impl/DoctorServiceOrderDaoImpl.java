package com.yst.web.dao.impl;

import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.yst.web.dao.doctor.DoctorServiceOrderDao;
import com.yst.web.model.DoctorService;
import com.yst.web.model.DoctorServiceOrder;
import com.yst.web.utils.BaseDaoImpl;
@Repository("doctorServiceOrderDao")
public class DoctorServiceOrderDaoImpl extends BaseDaoImpl implements DoctorServiceOrderDao{
	
	@Override
	public DoctorServiceOrder findDoctorServiceOrderByParam(int customer_id, int doctor_id) {
		String hql = "from DoctorServiceOrder as o where o.customer_id=? and o.doctor_id=?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, customer_id);
		query.setParameter(1, doctor_id);
		return (DoctorServiceOrder) query.uniqueResult();
	}

	@Override
	public void updateDoctorServiceOrder(Integer doctor_id,
			DoctorService doctorService) {
		String hql = "update DoctorServiceOrder as dso where doctor.doctor_id=?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, doctor_id);
		query.executeUpdate();
	}


	@Override
	public Map<String,Object> getDoctorConsultTime(Integer customer_id, Integer doctor_id,Integer service_type_id) {
		StringBuffer sb = new StringBuffer("SELECT ");
		sb.append(" o.online_consult_start_time,o.online_consult_end_time,o.buy_time,o.already_call_duration,o.residue_call_duration,d.can_consult_week,d.can_consult_starttime,d.can_consult_endtime");
		sb.append(" FROM doctor_service_order o,doctor d ");
		sb.append(" WHERE o.doctor_id = d.doctor_id");
		sb.append(" AND o.customer_id="+customer_id);
		sb.append(" AND o.doctor_id="+doctor_id);
		sb.append(" AND o.service_type_id="+service_type_id);
		sb.append(" AND o.pay_time <= o.pay_time + INTERVAL 7 DAY");
		sb.append(" ORDER BY o.pay_time DESC");
		SQLQuery query = this.getSession().createSQLQuery(sb.toString());
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		if(query.list().size()>0){
			Map<String,Object> obj = (Map<String, Object>) query.list().get(0);
			return obj;
		}else{
			return null;
		}
	}

}
