package com.yst.web.dao;

import java.util.List;

import org.alqframework.orm.hibernate.BaseDao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import com.yst.web.entity.cashReceiveStation.CashReceiveStation;


public interface CashReceiveStationDao extends BaseDao<CashReceiveStation>{
	
	/***
	 * 查询提现记录表前30个 状态为1的数据
	 * @param customerId
	 * @return
	 */
	@Query("from CashReceiveStation as c where c.status=1 ORDER BY c.updateTime DESC")
	public List<CashReceiveStation> getBankStatus(Pageable pageable);
}