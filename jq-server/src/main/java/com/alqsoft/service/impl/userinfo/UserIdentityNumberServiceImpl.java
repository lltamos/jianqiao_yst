package com.alqsoft.service.impl.userinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.userInfo.UserIdentityNumberDao;
import com.alqsoft.entity.userinfo.UserBankCard;
import com.alqsoft.entity.userinfo.UserIdentityNumber;
import com.alqsoft.service.userinfo.UserIdentityNumberService;

/**
 * 实现类
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月15日 下午4:13:53
 * 
 */
@Service
@Transactional
public class UserIdentityNumberServiceImpl implements UserIdentityNumberService {

	@Autowired
	private UserIdentityNumberDao userIdentityNumberDao;
	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserIdentityNumber get(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserIdentityNumber saveAndModify(UserIdentityNumber arg0) {
		// TODO Auto-generated method stub
		return userIdentityNumberDao.save(arg0);
	}

	public UserBankCard UserIdentityNumberByUserId(String userId) {
		// TODO Auto-generated method stub
		return userIdentityNumberDao.findUserIdentityNumberByUserId(userId);
	}

	@Override
	public UserIdentityNumber findUserIdentityNumberByUserId(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
