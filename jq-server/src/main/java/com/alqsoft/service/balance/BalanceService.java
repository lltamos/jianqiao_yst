package com.alqsoft.service.balance;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.Balance;
import com.alqsoft.utils.BootStrapResult;

public interface BalanceService extends BaseService<Balance>{
	/**
	 * 根据当前登陆用户的id查询
	 * @param customerId
	 * @return
	 */
	public Balance findByCustomerId(Long customerId);
	/**
	 * 根据当前登陆用户的id查询订单记录+加锁查询
	 * @param customerId
	 * @return
	 */
	public Balance findByCustomerId(Long customerId,Integer type);
	
	public List<Balance> findAll();
	
	/**
	 * 分页查询当前的用户提现记录
	 * @param customername
	 * @param start
	 * @param length
	 * @return
	 */
	public BootStrapResult<List<Balance>> findBalanceByPage(Map<String,Object> map,Integer page, Integer length, HttpServletRequest request);
	
	public Balance getBalanceById(Long id);
	
	/**
	 * 提现校验
	 * @param map
	 * @return
	 */
	public Result confirmMoney(Map<String,Object> map,HttpServletRequest request);
	/**
	 * 根据当前登陆用户的id+类型查询
	 * @param customerId
	 * @param type
	 * @return
	 */
	public Object getByCustomerIdAndType(Long customerId, Integer type); 
	
}
