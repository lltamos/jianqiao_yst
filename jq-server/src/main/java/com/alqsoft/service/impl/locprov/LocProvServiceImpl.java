package com.alqsoft.service.impl.locprov;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.locprov.LocProvDao;
import com.alqsoft.entity.LocProv;
import com.alqsoft.service.locprov.LocProvService;

/**
 * 省份实体
 * 
 * @author 王海龙
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2017-3-6 下午10:53:50
 * 
 */
@Service
@Transactional(readOnly = true)
public class LocProvServiceImpl implements LocProvService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(LocProvServiceImpl.class);

	@Autowired
	private LocProvDao locProvDao;

	@Override
	@Transactional
	public boolean delete(Long arg0) {
		try {
			locProvDao.delete(arg0);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public LocProv get(Long arg0) {
		return locProvDao.findOne(arg0);
	}

	@Override
	@Transactional
	public LocProv saveAndModify(LocProv arg0) {
		return locProvDao.save(arg0);
	}

	@Override
	public List<LocProv> findAll() {
		return locProvDao.findAllProv();
	}
}
