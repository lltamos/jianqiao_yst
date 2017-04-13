package com.yst.web.service.impl.prov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.prov.ProvDao;
import com.yst.web.entity.prov.Prov;
import com.yst.web.service.impl.city.CityServiceImp;
import com.yst.web.service.prov.ProvService;

/**
 *
 * @author 黄鑫
 * @e-mail abc12707058@hotmail.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2016年5月18日 下午5:03:23
 * 
 */
@Service
@Transactional(readOnly=true)
public class ProvServiceImp implements ProvService{
	
	private static Logger logger = LoggerFactory.getLogger(CityServiceImp.class);

	@Autowired
	private ProvDao provDao;

	@Override
	@Transactional(readOnly = false)
	public boolean delete(Long arg0) {
		return false;
	}

	@Override
	public Prov get(Long arg0) {
		return null;
	}

	@Override
	@Transactional(readOnly = false)
	public Prov saveAndModify(Prov arg0) {
		return null;
	}

}
