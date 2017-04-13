package com.alqsoft.service.balance;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.alqframework.orm.BaseService;

import com.alqsoft.entity.BalanceTrans;
import com.alqsoft.utils.BootStrapResult;

public interface BalanceTransService extends BaseService<BalanceTrans>{

	public BootStrapResult<List<BalanceTrans>> findBalanceByPage(Map<String,Object> map,Integer page, Integer length, HttpServletRequest request);

}
