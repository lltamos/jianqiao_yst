package com.alqsoft.service;

import com.alqsoft.entity.user.User;


/**
 * 
 * 
 * @author 张靠勤
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2013-2-28 下午9:28:46
 * 
 */
public interface UserService {
	/**
	 * 查询用户
	 * @param id
	 * @return
	 */
	public User getUser(Long id);
	/**
	 * 修改用户
	 * @param u
	 * @return 
	 */
	public boolean updateUser(User u);
}
