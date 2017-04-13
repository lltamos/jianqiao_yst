package com.yst.web.service.impl.consult;

import org.alqframework.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.consult.ConsultDao;
import com.yst.web.entity.consult.Consult;
import com.yst.web.service.consult.ConsultService;
@Service
@Transactional(readOnly=true)
public class ConsultServiceImpl implements ConsultService{

	@Autowired
	private ConsultDao consultDao;
	
	@Override
	public boolean delete(Long arg0) {
		consultDao.delete(arg0);
		return true;
	}

	@Override
	public Consult get(Long arg0) {
		return consultDao.findOne(arg0);
	}

	@Override
	public Consult saveAndModify(Consult arg0) {
		return consultDao.save(arg0);
	}

	@Override
	public Result findConsultList() {
		Iterable<Consult> iterable = consultDao.findAll(new Sort(Direction.DESC, new String[] { "updateTime" }));
		return null;
	}
	
	

}
