package com.alqsoft.dao.userInfo;

import javax.persistence.LockModeType;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.userinfo.UserBankCard;

/**
 * 
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月15日 下午3:53:52
 * 
 */
public interface UserBankCardDao extends BaseDao<UserBankCard>{
    
	@Query("from UserBankCard u where u.telPhone=:telPhone")
	public UserBankCard findUserBankCardByPhone(@Param("telPhone") String phone);
	
	@Lock(value=LockModeType.PESSIMISTIC_WRITE)
	@Query("from UserBankCard u where u.userId=:userId")
	public UserBankCard findUserBankCardByUserId(@Param("userId") long userId);

	@Query("from UserBankCard u where u.userId=:userId")
	public UserBankCard findOneByCustomerId(@Param("userId") long userId);
		
    
}
