package com.alqsoft.service.impl.loccity;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alqsoft.dao.loccity.LocCityDao;
import com.alqsoft.entity.LocCity;
import com.alqsoft.service.loccity.LocCityService;

/**
 * 市级实体
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
public class LocCityServiceImpl implements LocCityService {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(LocCityServiceImpl.class);

	@Autowired
	private LocCityDao locCityDao;

	@Override
	@Transactional
	public boolean delete(Long arg0) {
		try {
			locCityDao.delete(arg0);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public LocCity get(Long arg0) {
		return locCityDao.findOne(arg0);
	}

	@Override
	@Transactional
	public LocCity saveAndModify(LocCity arg0) {
		return locCityDao.save(arg0);
	}

	@Override
	public List<LocCity> getCityByProvId(Integer provId) {
		return locCityDao.getCityByProvId(provId);
	}
}
