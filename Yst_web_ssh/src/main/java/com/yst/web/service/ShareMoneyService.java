package com.yst.web.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;
import org.alqframework.result.Result;

import com.yst.web.entity.order.PatientOrder;
import com.yst.web.entity.shareMoney.ShareMoney;
import com.yst.web.model.AppResult;
import com.yst.web.model.ProductOrder;
import com.yst.web.utils.BootStrapResult;

public interface ShareMoneyService extends BaseService<ShareMoney>{
	/***
	 * 分页列表
	 * @param map
	 * @param page
	 * @param rows
	 * @return
	 */
	public BootStrapResult<List<ShareMoney>> getShareMoneyPage(Map<String, Object> map, Integer page, Integer rows);
	/**
	 * 生成分润记录
	 * @param productOrder
	 * @return
	 */
	public Result ShareMoneySave(ProductOrder order);
	/**
	 * 医生后台-疑难杂症订单分润列表
	 * @param searchParams
	 * @param page
	 * @param length
	 * @return
	 */
	public BootStrapResult<List<ShareMoney>> findShareMoneyByPage(Map<String, Object> searchParams, Integer start,
			Integer pageSize,HttpServletRequest request);
}
