package com.alqsoft.dao.balance;

import javax.persistence.LockModeType;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.Balance;

public interface BalanceDao extends BaseDao<Balance>{
	
	@Query("from Balance as b where b.customerId=:id")
	public Balance findByCustomerId(@Param("id")Long id);
	
	@Lock(value=LockModeType.PESSIMISTIC_WRITE)
	@Query("from Balance as b where b.customerId=:id And b.type=:type")
	public Balance findByCustomerIdAndType(@Param("id")Long id,@Param("type")Integer type);
	
	
	@Lock(value=LockModeType.PESSIMISTIC_WRITE)
	@Query("from Balance as b where b.customerId=:id ")
	public Balance getLockByCustomerId(@Param("id")Long id);
	
	@Query("from Balance as b where b.customerId=:id And b.type=:type")
	public Balance getByCustomerIdAndType(@Param("id")Long id,@Param("type")Integer type);
}
