package com.alqsoft.service.impl.userinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.userInfo.UserBankCardDao;
import com.alqsoft.entity.userinfo.UserBankCard;
import com.alqsoft.service.userinfo.UserBankCardService;

/**
 * 实现类
 * @Description: TODO
 * @author 王耀伟
 * @e-mail wangyaoweitwgdh@163.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017年3月15日 下午4:12:50
 * 
 */
@Service
@Transactional
public class UserBankCardServiceImpl implements UserBankCardService{

	
	@Autowired
	private UserBankCardDao userBankCardDao;
	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserBankCard get(Long id) {
		// TODO Auto-generated method stub
		return userBankCardDao.findOne(id);
	}

	@Override
	public UserBankCard saveAndModify(UserBankCard arg0) {
		return userBankCardDao.save(arg0);
	}

	@Override
	public UserBankCard findUserBankCardByUserId(long userId) {
		// TODO Auto-generated method stub
		return userBankCardDao.findUserBankCardByUserId(userId);
	}

	@Override
	public UserBankCard findUserBankCardByPhone(String phone) {
		// TODO Auto-generated method stub
		return userBankCardDao.findUserBankCardByPhone(phone);
	}

}
