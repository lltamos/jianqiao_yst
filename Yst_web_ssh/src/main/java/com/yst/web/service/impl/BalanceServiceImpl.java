package com.yst.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yst.web.dao.BalanceDao;
import com.yst.web.dao.BalanceTransDao;
import com.yst.web.dao.CustomerDao;
import com.yst.web.model.Balance;
import com.yst.web.service.BalanceService;

@Service("balanceService")
@Transactional
public class BalanceServiceImpl implements BalanceService {
	private static Log logger = LogFactory.getLog(BalanceServiceImpl.class);
	@Resource(name = "customerDao")
	private CustomerDao customerDao;
	@Resource(name = "balanceDao")
	private BalanceDao balanceDao;
	@Resource(name = "balanceTransDao")
	private BalanceTransDao balanceTransDao;

	@Override
	public Balance findById(int id) {
		return this.balanceDao.get(Balance.class, id);
	}

	@Override
	public List<Balance> selectAll() {
		return this.balanceDao.query(Balance.class);
	}

	@Override
	public void deleteById(int id) {
		this.balanceDao.delete(Balance.class, id);
	}

	@Override
	public void update(Balance balance) {
		this.balanceDao.update(balance);
	}

	@Override
	public void add(Balance balance) {
		this.balanceDao.save(balance);
	}

	@Override
	public Balance findByCustomerId(int customerId) {
		String hql="from Balance as b where b.customer.customer_id=?";
		List<Balance> blist=balanceDao.query(hql, null, customerId);
		if(blist.size()>0)
		{
			for (Balance balance : blist) {
				return balance;
			}
		}
		
		return null;
		//return balanceDao.findByColumnValue(Balance.class, "customer_id", customerId);
	}

}
