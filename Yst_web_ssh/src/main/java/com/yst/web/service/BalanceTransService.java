package com.yst.web.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yst.web.model.AppResult;
import com.yst.web.model.BalanceTrans;

public interface BalanceTransService {
	public BalanceTrans findById(int id);
	public List<BalanceTrans> selectAll();
	public void add(BalanceTrans balanceTrans);
	public void deleteById(int id);
	public void update(BalanceTrans balanceTrans);
	public AppResult updateBalance(BalanceTrans balanceTrans,HttpServletResponse response,HttpServletRequest request);
	public AppResult getBalanceList(BalanceTrans balanceTrans);
	public AppResult getBalance(BalanceTrans balanceTrans);
	public void alipayNotify(String out_trade_no,Date gmt_payment,Integer pay_type);
	public AppResult updateBalancePay(BalanceTrans balanceTrans);
}
