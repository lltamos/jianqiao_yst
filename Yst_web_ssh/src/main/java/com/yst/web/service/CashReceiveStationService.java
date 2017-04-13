package com.yst.web.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;




import org.alqframework.orm.BaseService;

import com.yst.web.model.AppResult;
import com.yst.web.utils.BootStrapResult;
import com.yst.web.entity.cashReceiveStation.CashReceiveStation;

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
	public BootStrapResult<List<CashReceiveStation>> getCashReceiveStationPage(Map<String, Object> map, Integer page, Integer rows);

	
	/**
	 * 发起提现  生成一条提现记录
	 * @param crs
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public AppResult luanch(CashReceiveStation crs, HttpServletRequest request, Map session) throws Exception;
	
	/**
	 * 单笔上传
	 * @param crs
	 * @return
	 * @throws Exception
	 */
	public AppResult upload1(CashReceiveStation crs) throws Exception;
	
	/***
	 * 单笔查询
	 * @return
	 * @throws Exception
	 */
	public AppResult getBankReturnStatus() throws Exception;
	/**
	 * 医生提现列表
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 */
	public BootStrapResult<List<CashReceiveStation>> findDoctorCashReceivePage(Map<String, Object> param, Integer page,
			Integer length);
	
	/**
	 * 推广人体现
	 * @param param
	 * @param page
	 * @param length
	 * @return
	 */
	public BootStrapResult<List<CashReceiveStation>> findPushCashReceivePage(Map<String, Object> param, Integer page, Integer length);
}
