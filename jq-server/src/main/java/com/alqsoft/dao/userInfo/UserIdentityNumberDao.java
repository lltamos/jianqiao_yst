package com.alqsoft.dao.userInfo;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.userinfo.UserBankCard;
import com.alqsoft.entity.userinfo.UserIdentityNumber;

/**
 * 
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月15日 下午3:55:02
 * 
 */
public interface UserIdentityNumberDao extends BaseDao<UserIdentityNumber>{

	@Query("from UserIdentityNumber u where u.userId=:userId")
	public UserBankCard findUserIdentityNumberByUserId(@Param("userId") String userID);
	

//	@Query("from UserBankCard u where u.userId=:userId")
//	public UserBankCard findUserBankCardByUserId(@Param("userId") String userId);
}
