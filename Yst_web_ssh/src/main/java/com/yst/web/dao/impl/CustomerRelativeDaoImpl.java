package com.yst.web.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.yst.web.dao.CustomerRelativeDao;
import com.yst.web.model.CustomerRelative;
import com.yst.web.utils.BaseDaoImpl;

@Repository("customerRelativeDao")
public class CustomerRelativeDaoImpl extends BaseDaoImpl implements CustomerRelativeDao{

	@Override
	public void deleteRelative(int customer_id, int relative_id) {
		String hql = "delete from CustomerRelative as c where c.relative_id=? and c.customer_id=?";
		Query query = this.getSession().createQuery(hql);
		query.setInteger(0, relative_id);
		query.setInteger(1, customer_id);
		query.executeUpdate();
		
	}

	@Override
	public void delete(CustomerRelative customerRelative) {
		this.getSession().delete(customerRelative);
	}

	@Override
	public CustomerRelative getCustomerRelative(int customer_id, int relative_id) {
		String hql = "from CustomerRelative as c where c.relative_id=? and customer.customer_id=?";
		Query query = this.getSession().createQuery(hql);
		query.setParameter(0, relative_id);
		query.setParameter(1, customer_id);
		return (CustomerRelative) query.uniqueResult();
	}
	


}
