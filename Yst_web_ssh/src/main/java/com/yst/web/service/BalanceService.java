package com.yst.web.service;

import java.util.List;

import com.yst.web.model.AppResult;
import com.yst.web.model.Balance;

public interface BalanceService {
	public Balance findById(int id);
	public List<Balance> selectAll();
	public void add(Balance balance);
	public void deleteById(int id);
	public void update(Balance balance);
	
	public Balance findByCustomerId(int customerId);
}
