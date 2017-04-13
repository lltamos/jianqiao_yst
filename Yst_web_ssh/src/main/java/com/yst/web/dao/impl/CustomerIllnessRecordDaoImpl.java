package com.yst.web.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yst.web.dao.CustomerIllnessRecordDao;
import com.yst.web.utils.BaseDaoImpl;
@Repository("customerIllnessRecordDao")
public class CustomerIllnessRecordDaoImpl extends BaseDaoImpl implements CustomerIllnessRecordDao{

	@Override
	public void deleteByParame(Integer relative_id) {
		String hql = "delete CustomerIllnessRecord as c where c.relative_id=?";
		Query query = this.getSession().createQuery(hql);
		query.setInteger(0, relative_id);
		query.executeUpdate();
	}

}
