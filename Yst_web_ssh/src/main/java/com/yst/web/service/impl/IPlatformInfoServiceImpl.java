package com.yst.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.IPlatformInfoDao;
import com.yst.web.entity.platforminfo.PlatformInfo;
import com.yst.web.service.IPlatformInfoService;



/**
 * 平台信息表service层实现
 */
@Service
@Transactional
public class IPlatformInfoServiceImpl implements IPlatformInfoService {

	@Autowired
	private IPlatformInfoDao iPlatformInfoDao;
	
	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PlatformInfo get(Long arg0) {
		return iPlatformInfoDao.findOne(arg0);
	}

	@Override
	public PlatformInfo saveAndModify(PlatformInfo arg0) {
		return iPlatformInfoDao.save(arg0);
	}



}
