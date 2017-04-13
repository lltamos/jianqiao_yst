package com.alqsoft.service.impl.Balance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.alqframework.orm.filter.DynamicSpecifications;
import org.alqframework.orm.filter.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.alqsoft.dao.balance.BalanceTransDao;
import com.alqsoft.entity.Balance;
import com.alqsoft.entity.BalanceTrans;
import com.alqsoft.service.balance.BalanceTransService;
import com.alqsoft.utils.BootStrapResult;
import com.alqsoft.utils.BootStrapResultUtils;

@Service
@Transactional
public class BalanceTransServiceImpl implements BalanceTransService{
	
	@Autowired
	private BalanceTransDao balanceTransDao;

	@Override
	public boolean delete(Long arg0) {
		// TODO Auto-generated method stub
		balanceTransDao.delete(arg0);
		return true;
	}

	@Override
	public BalanceTrans get(Long arg0) {
		// TODO Auto-generated method stub
		BalanceTrans balanceTrans = balanceTransDao.findOne(arg0);
		return balanceTrans;
	}

	@Override
	public BalanceTrans saveAndModify(BalanceTrans arg0) {
		// TODO Auto-generated method stub
		BalanceTrans balanceTrans = balanceTransDao.save(arg0);
		return balanceTrans;
	}


	@Override
	public BootStrapResult<List<BalanceTrans>> findBalanceByPage(Map<String, Object> map, Integer page, Integer length,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, Object> params = new HashMap<String, Object>();
		//params.put("EQ_customerId", customerId);
		Map<String, SearchFilter> filter = SearchFilter.parse(params);
		Specification<BalanceTrans> specification = DynamicSpecifications.bySearchFilter(filter.values(),BalanceTrans.class);
		Page<BalanceTrans> balance = balanceTransDao.findAll(specification,new PageRequest(page,length,new Sort(Direction.DESC, new String[] { "createdTime" })));
		return BootStrapResultUtils.returnPage(BalanceTrans.class, balance);
	}
	
}
