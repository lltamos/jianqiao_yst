package com.alqsoft.dao.usertable;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.alqsoft.entity.UserTable;

/**
 * 用户dao
 * 
 * @author 张靠勤
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2013-6-5 下午8:23:19
 * 
 */
public interface UserTableDao extends BaseDao<UserTable> {
	/**
	 * 根据用户名查询用户
	 * @param userName
	 * @return
	 */
	@Query("SELECT u FROM UserTable u WHERE u.loginName=:userName")
	public UserTable getUserByName(@Param("userName") String userName); 
}
