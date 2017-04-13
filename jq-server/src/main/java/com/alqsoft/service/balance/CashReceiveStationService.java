package com.alqsoft.service.balance;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.alqsoft.entity.Customer;
import com.alqsoft.entity.cashreceivestation.CashReceiveStation;
import com.alqsoft.utils.BootStrapResult;

public interface CashReceiveStationService extends BaseService<CashReceiveStation>{
	
	/**
	 * 根据id 查询提现记录的详情
	 * @param id
	 * @return
	 */
	public CashReceiveStation selectCashReceiveById(Integer id);
	/***
	 * 分页列表
	 * @param map
	 * @param page
	 * @param rows
	 * @return
	 */
	public BootStrapResult<List<CashReceiveStation>> getCashReceiveStationPage(Map<String, Object> map, Integer page, Integer rows,HttpServletRequest request);

	/**
	 * 推广人体现
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 */
	
	public BootStrapResult<List<CashReceiveStation>> findPushCashReceivePage(Map<String, Object> param, Integer page, Integer length);
	
	/**
	 * 健桥个人中心--提现
	 * @param phone
	 * @param code
	 * @param cashReceiveStation
	 * @param cardId 
	 * @return
	 */
	public Result cashReceiveStationMoney(CashReceiveStation cashReceiveStation, String phone, String code, Long cardId);
	/**
	 * 健桥个人中心-提现  手机号码发送验证码  (需要时打开)
	 * @param moneyPhone
	 * @return
	 */
	//public Result sendMsgForMoneyPhone(Customer customer, String moneyPhone, String codeType);
	/**
	 * 根据订单号获取记录ID
	 * @author Yaowei
	 * @param  
	 * @return Long
	 * @Time 2017年3月23日
	 */
	public Long getIDByMerSeqId(String custOrderId);
	/**
	 * 提现加锁  
	 * @author Yaowei
	 * @param  
	 * @return CashReceiveStation
	 * @Time 2017年3月23日
	 */
	public CashReceiveStation getRowLock(Long id);
	/**
	 * 提现 手机号发送验证码    (需要时打开)
	 * @param tixianPhone
	 * @return
	 */
	//public Result sendMsgForUpdatePhone(String tixianPhone, String codeType);
	/**
	 * 根据订单号加锁获取这笔订单
	 * @author Yaowei
	 * @param  
	 * @return CashReceiveStation
	 * @Time 2017年3月27日
	 */
	public CashReceiveStation getCashReceiveStationByCustOrderId( String custOrderId);
}
