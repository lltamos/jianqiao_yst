package com.yst.web.dao;

import com.yst.web.model.CustomerRelative;
import com.yst.web.utils.BaseDao;

public interface CustomerRelativeDao extends BaseDao{
	
	public CustomerRelative getCustomerRelative(int customer_id,int relative_id);
	
	public void deleteRelative(int customer_id,int relative_id);
	
	public void delete(CustomerRelative customerRelative);
}
