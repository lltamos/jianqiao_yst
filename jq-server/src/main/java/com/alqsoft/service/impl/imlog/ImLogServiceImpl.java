package com.alqsoft.service.impl.imlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.ImLogDao.ImLogDao;
import com.alqsoft.entity.ImLog;
import com.alqsoft.service.imlog.ImLogService;

@Service
public class ImLogServiceImpl implements ImLogService{

	
	@Autowired
	private ImLogDao imLogDao;	
	
	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ImLog get(Long arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImLog saveAndModify(ImLog arg0) {
		return imLogDao.save(arg0);
	}

}
