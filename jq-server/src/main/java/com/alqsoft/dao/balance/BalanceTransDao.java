package com.alqsoft.dao.balance;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.BalanceTrans;

public interface BalanceTransDao extends BaseDao<BalanceTrans>{
	
	@Query("from BalanceTrans as b where b.customerId=:id")
	public List<BalanceTrans> findByCustomerId(@Param("id")Long id);
	
	/*@Query("from BalanceTrans as b where b.customerId=:id and b.type=:type")
	public List<BalanceTrans> findByCustomerIdAndType(@Param("id")Long id,@Param("type")Integer type);*/
}
