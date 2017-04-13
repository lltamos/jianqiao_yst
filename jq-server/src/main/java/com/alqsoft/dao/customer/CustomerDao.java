package com.alqsoft.dao.customer;

import javax.persistence.LockModeType;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.entity.Customer;



/**
 * 会员dao
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017-3-6 下午8:23:19
 * 
 */
public interface CustomerDao extends BaseDao<Customer> {
	@Query("FROM Customer o WHERE o.phone=:phone")
	public Customer getCustomerByPhone(@Param("phone") String phone);
	
	@Lock(value=LockModeType.PESSIMISTIC_WRITE)
	@Query("FROM Customer o WHERE o.id=:id")
	public Customer getCustomerById(@Param("id") Long id);

	@Modifying
	@Query("update Customer o set o.password =:newpasswd where o.id=:id")
	public int updateCustomerPasswByid(@Param(value = "id") Long id,@Param(value = "newpasswd")String newpasswd);

	@Modifying
	@Query("update Customer o set o.phone =:phone where o.id=:id")
	public int updateCustomerPhoneByid(@Param(value = "id") Long id,@Param(value = "phone")String phone);
	
	@Query("select count(*) from Customer where IDNumber=:IDNumber")
	public int getCountByIdcard(@Param("IDNumber")String IDNumber);


}
