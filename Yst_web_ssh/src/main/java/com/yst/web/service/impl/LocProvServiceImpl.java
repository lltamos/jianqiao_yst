package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.CustomerDao;
import com.yst.web.model.LocProv;
import com.yst.web.service.ILocProvService;
@Service(ILocProvService.DINAME)
@Transactional
public class LocProvServiceImpl implements ILocProvService{
	private static Log logger = LogFactory.getLog(LocProvServiceImpl.class);
	@Resource(name = "customerDao")
	private CustomerDao customerDao;
	
	@Override
	public List<LocProv> selectAll() {
		return this.customerDao.query(LocProv.class);
	}
}
